/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.hotel.ejb;

import br.ufscar.dc.hotel.core.MyPersistence;
import br.ufscar.dc.hotel.entity.Quarto;
import br.ufscar.dc.hotel.entity.Reserva;
import java.security.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author alex
 */
@Stateless
@LocalBean
public class ReservaSession {

    @EJB
    private MyPersistence myPersistence;

    /**
     * @return the myPersistence
     */
    public MyPersistence getMyPersistence() {
        return myPersistence;
    }

    /**
     * @param myPersistence the myPersistence to set
     */
    public void setMyPersistence(MyPersistence myPersistence) {
        this.myPersistence = myPersistence;
    }

    public List<Quarto> buscarQuartosVagos(Date chegada, Date saida, int nroPessoas, int tipoQuarto) {

        int k = 0;
        int l = 0;
        int size = 0;
        int fk_quarto = 0;
        int refresh = 0;
        //Convertendo para o formato timestamp sql
        java.sql.Timestamp sqlChegada = new java.sql.Timestamp(chegada.getTime());
        java.sql.Timestamp sqlSaida = new java.sql.Timestamp(saida.getTime());

        //System.out.println("--------------------------------");
        //System.out.println("Input date: Chegada " + sqlChegada + " Saida: " + sqlSaida);
        //System.out.println("--------------------------------");

        List<Quarto> list = new ArrayList<Quarto>();
        List<Reserva> reservados = new ArrayList<Reserva>();
        Criteria criteria2 = this.myPersistence.getCriteria(Reserva.class);
        try {
            //reservados = (List<Reserva>) criteria2.list();  
            reservados = (List<Reserva>) criteria2.addOrder(Order.asc("quarto.id")).list(); //ordena por chave estrangeira
        } catch (Exception e) {
            e.printStackTrace();
        }
        /*for (int i = 0; i < reservados.size(); i++) {
        System.out.println("--------------------------------");
        System.out.println("Indice dos reservados nro: " + i);
        System.out.println("Quarto reservado nro" + reservados.get(i).getQuarto().getId());
        System.out.println("As datas são: chegada" + reservados.get(i).getDataChegada() + " Saida: " + reservados.get(i).getDataSaida());
        }
        */
        if (reservados.isEmpty() == false) { //verifica se lista nao esta vazia
            //percorre a lista de reservados e elimina todos cuja as datas NAO tenham sobreposicao (seleciona quartos reservados)
            for (int i = (reservados.size() - 1); i >= 0; i--) {
                if (sqlChegada.before(reservados.get(i).getDataSaida()) && sqlSaida.after(reservados.get(i).getDataChegada())) {
                //System.out.println("+++");
                //System.out.println("Mantido: quarto " + reservados.get(i).getQuarto().getId() + " com i igual a: " + i);
                }else{
                //System.out.println("---");
                //System.out.println("Removido: quarto " + reservados.get(i).getQuarto().getId() + " com i igual a: " + i);
                //System.out.println("As datas são: chegada " + reservados.get(i).getDataChegada() + " Saida: " + reservados.get(i).getDataSaida());
                reservados.remove(i);
                }
            }
        }
        //System.out.println("--------------------------------");
        //System.out.println("SQL date: Chegada "+ reservados.get(0).getDataChegada() + " Saida: " + reservados.get(0).getDataSaida());
        //System.out.println("--------------------------------");
        //test
        //System.out.println("--------------------------------");
        //for(int i = 0; i < reservados.size(); i++){
        //   System.out.println("Chave estrangeira quarto: "+reservados.get(i).getQuarto().getId());
        //   System.out.println("SQL date: Chegada "+ reservados.get(i).getDataChegada() + " Saida: " + reservados.get(i).getDataSaida());
        //}System.out.println("--------------------------------");

        //organiza por chave estrangeira
        Criteria criteria = this.myPersistence.getCriteria(Quarto.class);
        Criterion vacant = Restrictions.eq("ocupado", false); //elimina quartos ocupados
        Criterion type = Restrictions.eq("tipo", tipoQuarto); // elimina quartos que nao correspondem ao tipo
        Criterion nroP = Restrictions.ge("capacidade", nroPessoas); // elimina quartos maiores 
        //LogicalExpression andExp =     
        try {
            //Criteria criteria = this.myPersistence.getCriteria(Quarto.class);
            //list = (List<Quarto>) criteria.add(Restrictions.eq("ocupado", false)).addOrder(Order.asc("nome")).list();
            list = (List<Quarto>) criteria.add(Restrictions.and(vacant, type)).addOrder(Order.asc("id")).list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // eliminar da lista de quartos disponiveis os quartos com reserva na data
        if (reservados.isEmpty() == false) {
        refresh = list.size();
        while (k < refresh && l < reservados.size()){
            fk_quarto = reservados.get(l).getQuarto().getId();
            for(int i = k; i<list.size(); i++){
                if(list.get(i).getId() == fk_quarto){
                    list.remove(i);
                    k = i;
                    refresh --;
                }
            }
            l++;
        }        
        
        }
        return list;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.hotel.ejb;

import br.ufscar.dc.hotel.core.MyPersistence;
import br.ufscar.dc.hotel.entity.Quarto;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import org.hibernate.Criteria;

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

    public List<Quarto> buscarQuartosVagos() {
        List<Quarto> list = new ArrayList<Quarto>();

        try {
            Criteria criteria = this.myPersistence.getCriteria(Quarto.class);

            list = (List<Quarto>) criteria.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}
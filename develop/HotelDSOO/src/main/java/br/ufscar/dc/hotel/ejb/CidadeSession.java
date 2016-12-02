package br.ufscar.dc.hotel.ejb;

import br.ufscar.dc.hotel.core.MyPersistence;
import br.ufscar.dc.hotel.entity.Cidade;
import br.ufscar.dc.hotel.entity.Estado;
import java.util.HashMap;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

@Stateless
@LocalBean
public class CidadeSession {

    @EJB
    private MyPersistence myPersistence;

    public Cidade buscarCodigo(int Codigo) {
        HashMap<String, Object> filtros = new HashMap<String, Object>();
        filtros.put("codigo", Codigo);
        Cidade cidade = (Cidade) this.getMyPersistence().getObject(filtros, Cidade.class);
        return cidade;
    }

    public List<Cidade> getByName(String nome) {
        HashMap<String, Object> filtros = new HashMap<String, Object>();
        filtros.put("nome", nome);
        List<Cidade> cidades = this.getMyPersistence().getObjects(filtros, Cidade.class);
        return cidades;
    }

    public List<Cidade> getByEstado(Estado estado) {
        List<Cidade> list = null;

        try {
            Criteria criteria = this.myPersistence.getCriteria(Cidade.class);

            list = (List<Cidade>) criteria.
                    add(Restrictions.eq("estado.id", estado.getId())).
                    addOrder(Order.asc("nome")).
                    addOrder(Order.asc("estado")).list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public Cidade getBySingleName(String nome) {
        HashMap<String, Object> filtros = new HashMap<String, Object>();
        filtros.put("nome", nome);
        Cidade cidade = (Cidade) this.getMyPersistence().getObject(filtros, Cidade.class);
        return cidade;
    }

    public List<Cidade> all() {
        Criteria criteria = this.myPersistence.getCriteria(Cidade.class);
        List<Cidade> list = null;
        try {
            list = (List<Cidade>) criteria.
                    addOrder(Order.asc("nome")).
                    addOrder(Order.asc("estado")).list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Cidade> autoComplete(String query) {
        Criteria criteria = this.myPersistence.getCriteria(Cidade.class);
        criteria.createAlias("estado", "estado_alias");
        Criterion estado = Restrictions.like("estado_alias.nome", query, MatchMode.ANYWHERE);
        Criterion nome = Restrictions.like("nome", query, MatchMode.ANYWHERE);
        LogicalExpression orExp = Restrictions.or(nome, estado);
        criteria.add(orExp);
        criteria.addOrder(Order.asc("nome"));
        criteria.addOrder(Order.asc("estado"));
        List<Cidade> list = null;
        try {
            list = (List<Cidade>) criteria.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

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

}

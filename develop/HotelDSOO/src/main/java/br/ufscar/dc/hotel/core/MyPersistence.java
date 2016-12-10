   /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.hotel.core;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

@Stateless
@LocalBean
public class MyPersistence {

    @PersistenceContext(unitName = "HotelDsooPU")
    protected EntityManager entityManager;

    public Object getById(Class entity, int id) {
        Object obj;
        obj = this.getEntityManager().find(entity, id);
//        this.getEntityManager().close();
        return obj;
    }

    @SuppressWarnings("unchecked")
    public List findAll(Class entity) {
        List list;
        list = this.getEntityManager().createQuery("FROM " + entity.getName()).getResultList();
//        this.getEntityManager().close();
        return list;
    }

    public boolean save(MyInterfaceEntity entity) {
        boolean save = false;
        if (entity.getId() == null) {
            try {
                this.getEntityManager().persist(entity);
                save = true;
            } catch (Exception ex) {
                ex.printStackTrace();
                save = false;
            } finally {
                return save;
            }
        } else {
            return merge(entity);
        }
    }

    @Transactional
    public boolean save(List<?> objetos) {
        boolean salvou = false;

        for (Object objeto : objetos) {
            this.getEntityManager().merge(objeto);
        }
        salvou = true;

        return salvou;
    }

    public boolean merge(Object object) {
        Object result = this.getEntityManager().merge(object);
        return result != null;
    }

    public boolean remove(Object entity) {
        try {
            this.getEntityManager().remove(getEntityManager().merge(entity));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List getObjects(HashMap<String, Object> filtros, Class classe) {
        Object objeto = null;
        Criteria criteria = getCriteria(classe);
        for (Map.Entry<String, Object> entry : filtros.entrySet()) {
            String campo = entry.getKey();
            Object valor = entry.getValue();
            criteria.add(Restrictions.eq(campo, valor));
        }
        return getObjects(criteria);
    }

    public List buscar(Class classe) {
        Object objeto = null;
        Criteria criteria = getCriteria(classe);
        return getObjects(criteria);
    }

    public Object getObject(HashMap<String, Object> filtros, Class classe) {
        Object objeto = null;
        Criteria criteria = getCriteria(classe);
        for (Map.Entry<String, Object> entry : filtros.entrySet()) {
            String campo = entry.getKey();
            Object valor = entry.getValue();
            criteria.add(Restrictions.eq(campo, valor));
        }
        criteria.setMaxResults(1);//no maximo 1 resultado
        return getObject(criteria);
    }

    public Object getObject(Criteria criteria) {
        Session session = (Session) this.getEntityManager().getDelegate();
        Object object = criteria.uniqueResult();
        //session.close();
        return object;
    }

    public List getObjects(Criteria criteria) {
        Session session = (Session) this.getEntityManager().getDelegate();
        List objects = (List) criteria.list();
//        session.close();
        return objects;
    }

    public Criteria getCriteria(Class classe) {
        Session session = (Session) this.getEntityManager().getDelegate();
        Object objeto = null;
        Transaction tx = null;
        Criteria criteria = null;
        try {
            tx = session.getTransaction();//cria uma transação para o hibernate conectar no banco
            criteria = session.createCriteria(classe);
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            //session.close();
        }
        return criteria;
    }

    public List getListByCriteria(String column, Object value, Class myClass) {
        HashMap<String, Object> parametros = new HashMap();
        parametros.put(column, value);
        return getObjects(parametros, myClass);
    }

    public MyInterfaceEntity getByCriteria(String column, Object value, Class myClass) {
        Session session = (Session) this.getEntityManager().getDelegate();
        try {
            if (myClass != null) {
                MyInterfaceEntity obj = (MyInterfaceEntity) session.createCriteria(myClass).add(Restrictions.eq(column, value)).uniqueResult();
                if (obj == null) {
                    return null;
                } else {
                    return obj;
                }
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public MyInterfaceEntity searchByCriteria(String column, String value, Class myClass) {
        Session session = (Session) this.getEntityManager().getDelegate();
        try {
            if (myClass != null) {
                MyInterfaceEntity obj = (MyInterfaceEntity) session.createCriteria(myClass).add(Restrictions.eq(column, value)).uniqueResult();
                if (obj == null) {
                    return null;
                } else {
                    return obj;
                }
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    /**
     * @return the entityManager
     */
    public EntityManager getEntityManager() {
        return entityManager;
    }

    /**
     * @param entityManager the entityManager to set
     */
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}

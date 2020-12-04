/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hieudn.blos;

import hieudn.entities.Users;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author hp
 */
public class AccountBLO implements Serializable {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("YellowMoonShopPU");

    public void persist(Object object) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

      public boolean checkLogin(String username, String password) throws Exception {
        EntityManager em = emf.createEntityManager();
        String jpql = "Select u FROM Users u WHERE u.userName = :userName AND u.password = :password";
        Query query = em.createQuery(jpql);
        query.setParameter("userName", username);
        query.setParameter("password", password);
        try {
            query.getSingleResult();
            return true;
        } catch (NoResultException e) {
            return false;
        }
    }

    public Users loginPage(String username) {
        Users acc = null;
        EntityManager em = emf.createEntityManager();
        Query queryLogin = em.createNamedQuery("Users.findByUserName");
        queryLogin.setParameter("userName", username);
        try {
            acc = (Users) queryLogin.getSingleResult();
            return acc;
        } catch (NoResultException e) {
            return null;
        }
    }  


}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hieudn.blos;

import hieudn.entities.Cake;
import hieudn.entities.Category;
import hieudn.entities.Order1;
import hieudn.entities.OrderDetails;
import hieudn.entities.Users;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author hp
 */
public class ShoppingBLO {

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

    public List searchByCakeName(String search, int currentPage, int pageMaxSize) {
        EntityManager em = emf.createEntityManager();
        String jpql = "SELECT c FROM Cake c WHERE c.cakeName LIKE :cakeName AND c.status = 'ACTIVE' order by c.cakeCreateDate DESC";
        Query query = em.createQuery(jpql);
        query.setParameter("cakeName", "%" + search + "%");
        query.setFirstResult((currentPage - 1) * pageMaxSize);
        query.setMaxResults(pageMaxSize);
        List result = query.getResultList();
        return result;

    }

    public Cake getCakeById(String cakeId) {
        EntityManager em = emf.createEntityManager();
        String jpql = "Cake.findByCakeId";
        Query query = em.createNamedQuery(jpql);
        query.setParameter("cakeId", cakeId);
        Cake c = (Cake) query.getSingleResult();
        return c;
    }

    public int getAmount(String search, int pageMaxSize) {
        EntityManager em = emf.createEntityManager();
        String sql = "select COUNT(cakeId) from Cake where cakeName like ? AND status = 'ACTIVE'";
        Query query = em.createNativeQuery(sql);
        em.getTransaction().begin();
        query.setParameter("1", "%" + search + "%");
        int count = (int) query.getSingleResult();
        em.getTransaction().commit();
        if (count % pageMaxSize == 0) {
            return count / pageMaxSize;
        }
        return count / pageMaxSize + 1;
    }

    public List searchRangeMoney(int money1, int money2, int currentPage, int pageMaxSize) {
        EntityManager em = emf.createEntityManager();
        String jpql = "Select c From Cake c WHERE c.cakePrice BETWEEN :cakeLowerPrice AND :cakeUpperPrice AND c.status = 'ACTIVE' order by c.cakeCreateDate DESC";
        Query query = em.createQuery(jpql);
        query.setParameter("cakeLowerPrice", money1);
        query.setParameter("cakeUpperPrice", money2);
        query.setFirstResult((currentPage - 1) * pageMaxSize);
        query.setMaxResults(pageMaxSize);
        List result = query.getResultList();
        return result;
    }

    public int getRangeMoneyAmount(int money1, int money2, int pageMaxSize) {
        EntityManager em = emf.createEntityManager();
        String sql = "SELECT COUNT(cakeId) FROM Cake Where cakePrice BETWEEN ? AND ?";
        Query query = em.createNativeQuery(sql);
        em.getTransaction().begin();
        query.setParameter(1, money1);
        query.setParameter(2, money2);
        int count = (int) query.getSingleResult();
        em.getTransaction().commit();
        if (count % pageMaxSize == 0) {
            return count / pageMaxSize;
        }
        return count / pageMaxSize + 1;
    }

    public List getCategory() {
        EntityManager em = emf.createEntityManager();
        String jpql = "Category.findAll";
        Query query = em.createNamedQuery(jpql);
        List result = query.getResultList();
        return result;
    }

    public List getCakeByCategory(Category categoryId, int currentPage, int pageMaxSize) {
        EntityManager em = emf.createEntityManager();
        String jpql = "SELECT c FROM Cake c WHERE c.categoryId = :categoryId AND c.status = 'ACTIVE' order by c.cakeCreateDate DESC";
        Query query = em.createQuery(jpql);
        query.setParameter("categoryId", categoryId);
        query.setFirstResult((currentPage - 1) * pageMaxSize);
        query.setMaxResults(pageMaxSize);
        List result = query.getResultList();
        return result;
    }

    public int getCakeByCatAmount(Category categoryId, int pageMaxSize) {
        EntityManager em = emf.createEntityManager();
        String sql = "SELECT COUNT(cakeId) FROM Cake Where categoryId = '?'";
        Query query = em.createNativeQuery(sql);
        em.getTransaction().begin();
        query.setParameter(1, categoryId);
        int count = (int) query.getSingleResult();
        em.getTransaction().commit();
        if (count % pageMaxSize == 0) {
            return count / pageMaxSize;
        }
        return count / pageMaxSize + 1;
    }

    public boolean createCake(String cakeId, String cakeName, int cakePrice, String cakeDes, int quantity, String cakeImg, Date createCake, Date expireCake, String status, String categoryId) {
        boolean check = false;
        EntityManager em = emf.createEntityManager();
        String sql = "INSERT INTO Cake(cakeId, cakeName, cakePrice, cakeDescription, cakeQuantity, cakeImage, cakeCreateDate, cakeExpiredDate, status, categoryId) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Query query = em.createNativeQuery(sql);
        em.getTransaction().begin();
        query.setParameter(1, cakeId);
        query.setParameter(2, cakeName);
        query.setParameter(3, cakePrice);
        query.setParameter(4, cakeDes);
        query.setParameter(5, quantity);
        query.setParameter(6, cakeImg);
        query.setParameter(7, createCake);
        query.setParameter(8, expireCake);
        query.setParameter(9, status);
        query.setParameter(10, categoryId);
        check = query.executeUpdate() > 0;
        em.getTransaction().commit();
        return check;
    }

    public Cake searchById(String search) {
        EntityManager em = emf.createEntityManager();
        String jpql = "Cake.findByCakeId";
        Query query = em.createNamedQuery(jpql);
        query.setParameter("cakeId", search);
        Cake c = (Cake) query.getSingleResult();
        return c;
    }

    public boolean updateCake(String cakeId, String cakeName, int cakePrice, String cakeDes, int quantity, String cakeImg, Date createCake, Date expireCake, String status, Category categoryId) throws Exception {
        EntityManager em = emf.createEntityManager();
        Cake cake = em.find(Cake.class, cakeId);
        if (cake != null) {
            cake.setCakeName(cakeName);
            cake.setCakePrice(cakePrice);
            cake.setCakeDescription(cakeDes);
            cake.setCakeQuantity(quantity);
            cake.setCakeImage(cakeImg);
            cake.setCakeCreateDate(createCake);
            cake.setCakeExpiredDate(expireCake);
            cake.setStatus(status);
            cake.setCategoryId(categoryId);
            em.getTransaction().begin();
            em.merge(cake);
            em.getTransaction().commit();
            System.out.println("SUCCESSFUL");
            return true;
        }
        return false;
    }

    public boolean insertHistory(String date, Users user, Cake cakeId) {
        boolean check = false;
        EntityManager em = emf.createEntityManager();
        String sql = "INSERT INTO History(userId, cakeId, date) VALUES(?, ?, ?)";
        Query query = em.createNativeQuery(sql);
        em.getTransaction().begin();
        query.setParameter(1, user.getUserId());
        query.setParameter(2, cakeId.getCakeId());
        query.setParameter(3, date);
        check = query.executeUpdate() > 0;
        em.getTransaction().commit();
        return check;
    }

    public boolean insertOrder(String userId, Date orderDate, int orderPrice, String paymentMethod) {
        boolean check = false;
        EntityManager em = emf.createEntityManager();
        String sql = "INSERT INTO Order1(userId, orderDate, orderPrice, paymentMethod) VALUES(?, ?, ?, ?)";
        Query query = em.createNativeQuery(sql);
        em.getTransaction().begin();
        query.setParameter(1, userId);
        query.setParameter(2, orderDate);
        query.setParameter(3, orderPrice);
        query.setParameter(4, paymentMethod);
        check = query.executeUpdate() > 0;
        em.getTransaction().commit();
        return check;
    }

    public Integer addOrder(Order1 order) throws Exception {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(order);
        em.getTransaction().commit();
        return order.getOrderId();
    }

    public boolean insertNewOrder(Date orderDate, int orderPrice, String userName, String address, int phone, String paymentMethod) {
        boolean check = false;
        EntityManager em = emf.createEntityManager();
        String sql = "INSERT INTO Order1(orderDate, orderPrice, userName, address, phone, paymentMethod) VALUES(?, ?, ?, ?, ?, ?)";
        Query query = em.createNativeQuery(sql);
        em.getTransaction().begin();
        query.setParameter(1, orderDate);
        query.setParameter(2, orderPrice);
        query.setParameter(3, userName);
        query.setParameter(4, address);
        query.setParameter(5, phone);
        query.setParameter(6, paymentMethod);
        check = query.executeUpdate() > 0;
        em.getTransaction().commit();
        return check;
    }

    public boolean insertOrderDetails(Order1 orderId, Cake cakeId, int quantity, int detailPrice) {
        EntityManager em = emf.createEntityManager();
        boolean check = false;
        String sql = "INSERT INTO OrderDetails(orderId, cakeId, quantity, detailPrice) VALUES(?, ?, ?, ?)";
        Query query = em.createNativeQuery(sql);
        em.getTransaction().begin();
        query.setParameter(1, orderId.getUserId());
        query.setParameter(2, cakeId.getCakeId());
        query.setParameter(3, quantity);
        query.setParameter(4, detailPrice);
        check = query.executeUpdate() > 0;
        em.getTransaction().commit();

        return check;
    }

    public Order1 getOrderById(String orderId) {
        EntityManager em = emf.createEntityManager();
        String jpql = "Order1.findByOrderId";
        Query query = em.createNamedQuery(jpql);
        query.setParameter("cakeId", orderId);
        Order1 o = (Order1) query.getSingleResult();
        return o;
    }
}

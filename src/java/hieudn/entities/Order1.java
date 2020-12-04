/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hieudn.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author hp
 */
@Entity
@Table(name = "Order1", catalog = "YellowMoonShop", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Order1.findAll", query = "SELECT o FROM Order1 o"),
    @NamedQuery(name = "Order1.findByOrderId", query = "SELECT o FROM Order1 o WHERE o.orderId = :orderId"),
    @NamedQuery(name = "Order1.findByOrderDate", query = "SELECT o FROM Order1 o WHERE o.orderDate = :orderDate"),
    @NamedQuery(name = "Order1.findByOrderPrice", query = "SELECT o FROM Order1 o WHERE o.orderPrice = :orderPrice"),
    @NamedQuery(name = "Order1.findByUserName", query = "SELECT o FROM Order1 o WHERE o.userName = :userName"),
    @NamedQuery(name = "Order1.findByPhone", query = "SELECT o FROM Order1 o WHERE o.phone = :phone"),
    @NamedQuery(name = "Order1.findByPaymentMethod", query = "SELECT o FROM Order1 o WHERE o.paymentMethod = :paymentMethod")})
public class Order1 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "orderId", nullable = false)
    private Integer orderId;
    @Basic(optional = false)
    @Column(name = "orderDate", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate;
    @Basic(optional = false)
    @Column(name = "orderPrice", nullable = false)
    private int orderPrice;
    @Column(name = "userName", length = 10)
    private String userName;
    @Lob
    @Column(name = "address", length = 2147483647)
    private String address;
    @Column(name = "phone", length = 10)
    private String phone;
    @Basic(optional = false)
    @Column(name = "paymentMethod", nullable = false, length = 50)
    private String paymentMethod;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "orderId")
    private Collection<OrderDetails> orderDetailsCollection;
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    @ManyToOne
    private Users userId;

public Order1() {
    }

    public Order1(Integer orderId) {
        this.orderId = orderId;
    }

    public Order1(Integer orderId, Date orderDate, int orderPrice, String paymentMethod) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.orderPrice = orderPrice;
        this.paymentMethod = paymentMethod;
    }

    public Order1(Date orderDate, int orderPrice, String paymentMethod, Users userId) {
        this.orderDate = orderDate;
        this.orderPrice = orderPrice;
        this.paymentMethod = paymentMethod;
        this.userId = userId;
    }
    
    

    public Order1(Date orderDate, int orderPrice, String userName, String address, String phone, String paymentMethod) {
        this.orderDate = orderDate;
        this.orderPrice = orderPrice;
        this.userName = userName;
        this.address = address;
        this.phone = phone;
        this.paymentMethod = paymentMethod;
    }

    public Order1(Integer orderId, Date orderDate, int orderPrice, String userName, String address, String phone, String paymentMethod, Users userId) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.orderPrice = orderPrice;
        this.userName = userName;
        this.address = address;
        this.phone = phone;
        this.paymentMethod = paymentMethod;
        this.userId = userId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public int getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(int orderPrice) {
        this.orderPrice = orderPrice;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    @XmlTransient
    public Collection<OrderDetails> getOrderDetailsCollection() {
        return orderDetailsCollection;
    }

    public void setOrderDetailsCollection(Collection<OrderDetails> orderDetailsCollection) {
        this.orderDetailsCollection = orderDetailsCollection;
    }

    public Users getUserId() {
        return userId;
    }

    public void setUserId(Users userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (orderId != null ? orderId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Order1)) {
            return false;
        }
        Order1 other = (Order1) object;
        if ((this.orderId == null && other.orderId != null) || (this.orderId != null && !this.orderId.equals(other.orderId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hieudn.entities.Order1[ orderId=" + orderId + " ]";
    }
    
}

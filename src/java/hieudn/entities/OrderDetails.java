/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hieudn.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author hp
 */
@Entity
@Table(name = "OrderDetails", catalog = "YellowMoonShop", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OrderDetails.findAll", query = "SELECT o FROM OrderDetails o"),
    @NamedQuery(name = "OrderDetails.findByOrderDetailId", query = "SELECT o FROM OrderDetails o WHERE o.orderDetailId = :orderDetailId"),
    @NamedQuery(name = "OrderDetails.findByQuantity", query = "SELECT o FROM OrderDetails o WHERE o.quantity = :quantity"),
    @NamedQuery(name = "OrderDetails.findByDetailPrice", query = "SELECT o FROM OrderDetails o WHERE o.detailPrice = :detailPrice")})
public class OrderDetails implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "orderDetailId", nullable = false)
    private Integer orderDetailId;
    @Basic(optional = false)
    @Column(name = "quantity", nullable = false)
    private int quantity;
    @Basic(optional = false)
    @Column(name = "detailPrice", nullable = false)
    private int detailPrice;
    @JoinColumn(name = "cakeId", referencedColumnName = "cakeId", nullable = false)
    @ManyToOne(optional = false)
    private Cake cakeId;
    @JoinColumn(name = "orderId", referencedColumnName = "orderId", nullable = false)
    @ManyToOne(optional = false)
    private Order1 orderId;

    public OrderDetails() {
    }

    public OrderDetails(Integer orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public OrderDetails(Integer orderDetailId, int quantity, int detailPrice) {
        this.orderDetailId = orderDetailId;
        this.quantity = quantity;
        this.detailPrice = detailPrice;
    }

    public OrderDetails(int quantity, int detailPrice, Cake cakeId, Order1 orderId) {
        this.quantity = quantity;
        this.detailPrice = detailPrice;
        this.cakeId = cakeId;
        this.orderId = orderId;
    }
    
    

    public Integer getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(Integer orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getDetailPrice() {
        return detailPrice;
    }

    public void setDetailPrice(int detailPrice) {
        this.detailPrice = detailPrice;
    }

    public Cake getCakeId() {
        return cakeId;
    }

    public void setCakeId(Cake cakeId) {
        this.cakeId = cakeId;
    }

    public Order1 getOrderId() {
        return orderId;
    }

    public void setOrderId(Order1 orderId) {
        this.orderId = orderId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (orderDetailId != null ? orderDetailId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrderDetails)) {
            return false;
        }
        OrderDetails other = (OrderDetails) object;
        if ((this.orderDetailId == null && other.orderDetailId != null) || (this.orderDetailId != null && !this.orderDetailId.equals(other.orderDetailId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hieudn.entities.OrderDetails[ orderDetailId=" + orderDetailId + " ]";
    }
    
}

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
@Table(name = "Cake", catalog = "YellowMoonShop", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cake.findAll", query = "SELECT c FROM Cake c"),
    @NamedQuery(name = "Cake.findByCakeId", query = "SELECT c FROM Cake c WHERE c.cakeId = :cakeId"),
    @NamedQuery(name = "Cake.findByCakeName", query = "SELECT c FROM Cake c WHERE c.cakeName = :cakeName"),
    @NamedQuery(name = "Cake.findByCakePrice", query = "SELECT c FROM Cake c WHERE c.cakePrice = :cakePrice"),
    @NamedQuery(name = "Cake.findByCakeQuantity", query = "SELECT c FROM Cake c WHERE c.cakeQuantity = :cakeQuantity"),
    @NamedQuery(name = "Cake.findByCakeCreateDate", query = "SELECT c FROM Cake c WHERE c.cakeCreateDate = :cakeCreateDate"),
    @NamedQuery(name = "Cake.findByCakeExpiredDate", query = "SELECT c FROM Cake c WHERE c.cakeExpiredDate = :cakeExpiredDate"),
    @NamedQuery(name = "Cake.findByStatus", query = "SELECT c FROM Cake c WHERE c.status = :status")})
public class Cake implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cakeId")
    private Collection<History> historyCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "cakeId", nullable = false, length = 10)
    private String cakeId;
    @Basic(optional = false)
    @Column(name = "cakeName", nullable = false, length = 50)
    private String cakeName;
    @Basic(optional = false)
    @Column(name = "cakePrice", nullable = false)
    private int cakePrice;
    @Basic(optional = false)
    @Lob
    @Column(name = "cakeDescription", nullable = false, length = 2147483647)
    private String cakeDescription;
    @Basic(optional = false)
    @Column(name = "cakeQuantity", nullable = false)
    private int cakeQuantity;
    @Lob
    @Column(name = "cakeImage", length = 2147483647)
    private String cakeImage;
    @Basic(optional = false)
    @Column(name = "cakeCreateDate", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date cakeCreateDate;
    @Basic(optional = false)
    @Column(name = "cakeExpiredDate", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date cakeExpiredDate;
    @Basic(optional = false)
    @Column(name = "status", nullable = false, length = 10)
    private String status;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cakeId")
    private Collection<OrderDetails> orderDetailsCollection;
    @JoinColumn(name = "categoryId", referencedColumnName = "idCakeCategory", nullable = false)
    @ManyToOne(optional = false)
    private Category categoryId;

    public Cake() {
    }

    public Cake(String cakeId) {
        this.cakeId = cakeId;
    }

    public Cake(String cakeId, String cakeName, int cakePrice, String cakeDescription, int cakeQuantity, Date cakeCreateDate, Date cakeExpiredDate, String status) {
        this.cakeId = cakeId;
        this.cakeName = cakeName;
        this.cakePrice = cakePrice;
        this.cakeDescription = cakeDescription;
        this.cakeQuantity = cakeQuantity;
        this.cakeCreateDate = cakeCreateDate;
        this.cakeExpiredDate = cakeExpiredDate;
        this.status = status;
    }

    public Cake(String cakeId, String cakeName, int cakePrice, String cakeDescription, int cakeQuantity, String cakeImage, Date cakeCreateDate, Date cakeExpiredDate, String status, Category categoryId) {
        this.cakeId = cakeId;
        this.cakeName = cakeName;
        this.cakePrice = cakePrice;
        this.cakeDescription = cakeDescription;
        this.cakeQuantity = cakeQuantity;
        this.cakeImage = cakeImage;
        this.cakeCreateDate = cakeCreateDate;
        this.cakeExpiredDate = cakeExpiredDate;
        this.status = status;
        this.categoryId = categoryId;
    }
 
    public String getCakeId() {
        return cakeId;
    }

    public void setCakeId(String cakeId) {
        this.cakeId = cakeId;
    }

    public String getCakeName() {
        return cakeName;
    }

    public void setCakeName(String cakeName) {
        this.cakeName = cakeName;
    }

    public int getCakePrice() {
        return cakePrice;
    }

    public void setCakePrice(int cakePrice) {
        this.cakePrice = cakePrice;
    }

    public String getCakeDescription() {
        return cakeDescription;
    }

    public void setCakeDescription(String cakeDescription) {
        this.cakeDescription = cakeDescription;
    }

    public int getCakeQuantity() {
        return cakeQuantity;
    }

    public void setCakeQuantity(int cakeQuantity) {
        this.cakeQuantity = cakeQuantity;
    }

    public String getCakeImage() {
        return cakeImage;
    }

    public void setCakeImage(String cakeImage) {
        this.cakeImage = cakeImage;
    }

    public Date getCakeCreateDate() {
        return cakeCreateDate;
    }

    public void setCakeCreateDate(Date cakeCreateDate) {
        this.cakeCreateDate = cakeCreateDate;
    }

    public Date getCakeExpiredDate() {
        return cakeExpiredDate;
    }

    public void setCakeExpiredDate(Date cakeExpiredDate) {
        this.cakeExpiredDate = cakeExpiredDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @XmlTransient
    public Collection<OrderDetails> getOrderDetailsCollection() {
        return orderDetailsCollection;
    }

    public void setOrderDetailsCollection(Collection<OrderDetails> orderDetailsCollection) {
        this.orderDetailsCollection = orderDetailsCollection;
    }

    public Category getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Category categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cakeId != null ? cakeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cake)) {
            return false;
        }
        Cake other = (Cake) object;
        if ((this.cakeId == null && other.cakeId != null) || (this.cakeId != null && !this.cakeId.equals(other.cakeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hieudn.entities.Cake[ cakeId=" + cakeId + " ]";
    }

    @XmlTransient
    public Collection<History> getHistoryCollection() {
        return historyCollection;
    }

    public void setHistoryCollection(Collection<History> historyCollection) {
        this.historyCollection = historyCollection;
    }
    
}

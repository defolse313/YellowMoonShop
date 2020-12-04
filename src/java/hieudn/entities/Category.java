/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hieudn.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author hp
 */
@Entity
@Table(name = "Category", catalog = "YellowMoonShop", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Category.findAll", query = "SELECT c FROM Category c"),
    @NamedQuery(name = "Category.findByIdCakeCategory", query = "SELECT c FROM Category c WHERE c.idCakeCategory = :idCakeCategory"),
    @NamedQuery(name = "Category.findByCategoryName", query = "SELECT c FROM Category c WHERE c.categoryName = :categoryName")})
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idCakeCategory", nullable = false, length = 10)
    private String idCakeCategory;
    @Basic(optional = false)
    @Column(name = "categoryName", nullable = false, length = 50)
    private String categoryName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "categoryId")
    private Collection<Cake> cakeCollection;

    public Category() {
    }

    public Category(String idCakeCategory) {
        this.idCakeCategory = idCakeCategory;
    }

    public Category(String idCakeCategory, String categoryName) {
        this.idCakeCategory = idCakeCategory;
        this.categoryName = categoryName;
    }

    public String getIdCakeCategory() {
        return idCakeCategory;
    }

    public void setIdCakeCategory(String idCakeCategory) {
        this.idCakeCategory = idCakeCategory;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @XmlTransient
    public Collection<Cake> getCakeCollection() {
        return cakeCollection;
    }

    public void setCakeCollection(Collection<Cake> cakeCollection) {
        this.cakeCollection = cakeCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCakeCategory != null ? idCakeCategory.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Category)) {
            return false;
        }
        Category other = (Category) object;
        if ((this.idCakeCategory == null && other.idCakeCategory != null) || (this.idCakeCategory != null && !this.idCakeCategory.equals(other.idCakeCategory))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hieudn.entities.Category[ idCakeCategory=" + idCakeCategory + " ]";
    }
    
}

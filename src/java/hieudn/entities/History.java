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
@Table(name = "History", catalog = "YellowMoonShop", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "History.findAll", query = "SELECT h FROM History h"),
    @NamedQuery(name = "History.findByHistoryId", query = "SELECT h FROM History h WHERE h.historyId = :historyId"),
    @NamedQuery(name = "History.findByDate", query = "SELECT h FROM History h WHERE h.date = :date")})
public class History implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "historyId", nullable = false)
    private Integer historyId;
    @Basic(optional = false)
    @Column(name = "date", nullable = false, length = 50)
    private String date;
    @JoinColumn(name = "cakeId", referencedColumnName = "cakeId", nullable = false)
    @ManyToOne(optional = false)
    private Cake cakeId;
    @JoinColumn(name = "userId", referencedColumnName = "userId", nullable = false)
    @ManyToOne(optional = false)
    private Users userId;

    public History() {
    }

    public History(String date, Cake cakeId, Users userId) {
        this.date = date;
        this.cakeId = cakeId;
        this.userId = userId;
    }

    public History(Integer historyId) {
        this.historyId = historyId;
    }

    public History(Integer historyId, String date) {
        this.historyId = historyId;
        this.date = date;
    }

    public Integer getHistoryId() {
        return historyId;
    }

    public void setHistoryId(Integer historyId) {
        this.historyId = historyId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Cake getCakeId() {
        return cakeId;
    }

    public void setCakeId(Cake cakeId) {
        this.cakeId = cakeId;
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
        hash += (historyId != null ? historyId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof History)) {
            return false;
        }
        History other = (History) object;
        if ((this.historyId == null && other.historyId != null) || (this.historyId != null && !this.historyId.equals(other.historyId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hieudn.entities.History[ historyId=" + historyId + " ]";
    }
    
}

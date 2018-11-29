package com.hrm.sirhapp.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author Carlos Bernal carlose.bernalh@gmail.com
 * Copyright 2018 Carlos Ernesto Bernal Hernández.
 * SirhApp
 * 
 */
@Entity
@Table(name = "TDHAB45")
@NamedQueries({
    @NamedQuery(name = "Tdhab45.findAll", query = "SELECT t FROM Tdhab45 t")})
public class Tdhab45 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDHAB")
    private Integer idhab;
    @Size(max = 70)
    @Column(name = "DEHAB")
    private String dehab;
    @Size(max = 2)
    @Column(name = "STHAB")
    private String sthab;
    @Column(name = "FEHAB")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fehab;
    @Size(max = 10)
    @Column(name = "USHAB")
    private String ushab;

    /**
     *
     */
    public Tdhab45() {
    }

    /**
     *
     * @param idhab
     */
    public Tdhab45(Integer idhab) {
        this.idhab = idhab;
    }

    /**
     *
     * @return
     */
    public Integer getIdhab() {
        return idhab;
    }

    /**
     *
     * @param idhab
     */
    public void setIdhab(Integer idhab) {
        this.idhab = idhab;
    }

    /**
     *
     * @return
     */
    public String getDehab() {
        return dehab;
    }

    /**
     *
     * @param dehab
     */
    public void setDehab(String dehab) {
        this.dehab = dehab;
    }

    /**
     *
     * @return
     */
    public String getSthab() {
        return sthab;
    }

    /**
     *
     * @param sthab
     */
    public void setSthab(String sthab) {
        this.sthab = sthab;
    }

    /**
     *
     * @return
     */
    public Date getFehab() {
        return fehab;
    }

    /**
     *
     * @param fehab
     */
    public void setFehab(Date fehab) {
        this.fehab = fehab;
    }

    /**
     *
     * @return
     */
    public String getUshab() {
        return ushab;
    }

    /**
     *
     * @param ushab
     */
    public void setUshab(String ushab) {
        this.ushab = ushab;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idhab != null ? idhab.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tdhab45)) {
            return false;
        }
        Tdhab45 other = (Tdhab45) object;
        if ((this.idhab == null && other.idhab != null) || (this.idhab != null && !this.idhab.equals(other.idhab))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return EntityToString.getString(this,this.getClass());
        //return "com.hrm.sirhapp.model.Tdhab45[ idhab=" + idhab + " ]";
    }
    
}

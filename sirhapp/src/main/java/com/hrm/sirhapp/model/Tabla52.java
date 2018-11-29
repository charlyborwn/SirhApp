package com.hrm.sirhapp.model;

import java.io.Serializable;
import java.math.BigInteger;
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
@Table(name = "TABLA52")
@NamedQueries({
    @NamedQuery(name = "Tabla52.findAll", query = "SELECT t FROM Tabla52 t")})
public class Tabla52 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDTAB")
    private Integer idtab;
    @Size(max = 10)
    @Column(name = "NOTAB")
    private String notab;
    @Size(max = 255)
    @Column(name = "DETAB")
    private String detab;
    @Size(max = 255)
    @Column(name = "DTTAB")
    private String dttab;
    @Column(name = "RETAB")
    private Integer retab;
    @Column(name = "FCTAB")
    private BigInteger fctab;
    @Column(name = "ACTAB")
    private Integer actab;
    @Size(max = 2)
    @Column(name = "STTAB")
    private String sttab;
    @Column(name = "FETAB")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fetab;
    @Size(max = 10)
    @Column(name = "USTAB")
    private String ustab;

    /**
     *
     */
    public Tabla52() {
    }

    /**
     *
     * @param idtab
     */
    public Tabla52(Integer idtab) {
        this.idtab = idtab;
    }

    /**
     *
     * @return
     */
    public Integer getIdtab() {
        return idtab;
    }

    /**
     *
     * @param idtab
     */
    public void setIdtab(Integer idtab) {
        this.idtab = idtab;
    }

    /**
     *
     * @return
     */
    public String getNotab() {
        return notab;
    }

    /**
     *
     * @param notab
     */
    public void setNotab(String notab) {
        this.notab = notab;
    }

    /**
     *
     * @return
     */
    public String getDetab() {
        return detab;
    }

    /**
     *
     * @param detab
     */
    public void setDetab(String detab) {
        this.detab = detab;
    }

    /**
     *
     * @return
     */
    public String getDttab() {
        return dttab;
    }

    /**
     *
     * @param dttab
     */
    public void setDttab(String dttab) {
        this.dttab = dttab;
    }

    /**
     *
     * @return
     */
    public Integer getRetab() {
        return retab;
    }

    /**
     *
     * @param retab
     */
    public void setRetab(Integer retab) {
        this.retab = retab;
    }

    /**
     *
     * @return
     */
    public BigInteger getFctab() {
        return fctab;
    }

    /**
     *
     * @param fctab
     */
    public void setFctab(BigInteger fctab) {
        this.fctab = fctab;
    }

    /**
     *
     * @return
     */
    public Integer getActab() {
        return actab;
    }

    /**
     *
     * @param actab
     */
    public void setActab(Integer actab) {
        this.actab = actab;
    }

    /**
     *
     * @return
     */
    public String getSttab() {
        return sttab;
    }

    /**
     *
     * @param sttab
     */
    public void setSttab(String sttab) {
        this.sttab = sttab;
    }

    /**
     *
     * @return
     */
    public Date getFetab() {
        return fetab;
    }

    /**
     *
     * @param fetab
     */
    public void setFetab(Date fetab) {
        this.fetab = fetab;
    }

    /**
     *
     * @return
     */
    public String getUstab() {
        return ustab;
    }

    /**
     *
     * @param ustab
     */
    public void setUstab(String ustab) {
        this.ustab = ustab;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtab != null ? idtab.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tabla52)) {
            return false;
        }
        Tabla52 other = (Tabla52) object;
        if ((this.idtab == null && other.idtab != null) || (this.idtab != null && !this.idtab.equals(other.idtab))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return EntityToString.getString(this,this.getClass());
        //return "com.hrm.sirhapp.model.Tabla52[ idtab=" + idtab + " ]";
    }
    
}

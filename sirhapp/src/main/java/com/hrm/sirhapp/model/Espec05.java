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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Carlos Bernal carlose.bernalh@gmail.com
 * Copyright 2018 Carlos Ernesto Bernal Hernández.
 * SirhApp
 * 
 */
@Entity
@Table(name = "ESPEC05")
@NamedQueries({
    @NamedQuery(name = "Espec05.findAll", query = "SELECT e FROM Espec05 e")})
public class Espec05 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDESP")
    private Integer idesp;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 70)
    @Column(name = "NOESP")
    private String noesp;
    @Size(max = 2)
    @Column(name = "STESP")
    private String stesp;
    @Column(name = "FEESP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date feesp;
    @Size(max = 10)
    @Column(name = "USESP")
    private String usesp;

    /**
     *
     */
    public Espec05() {
    }

    /**
     *
     * @param idesp
     */
    public Espec05(Integer idesp) {
        this.idesp = idesp;
    }

    /**
     *
     * @param idesp
     * @param noesp
     */
    public Espec05(Integer idesp, String noesp) {
        this.idesp = idesp;
        this.noesp = noesp;
    }

    /**
     *
     * @return
     */
    public Integer getIdesp() {
        return idesp;
    }

    /**
     *
     * @param idesp
     */
    public void setIdesp(Integer idesp) {
        this.idesp = idesp;
    }

    /**
     *
     * @return
     */
    public String getNoesp() {
        return noesp;
    }

    /**
     *
     * @param noesp
     */
    public void setNoesp(String noesp) {
        this.noesp = noesp;
    }

    /**
     *
     * @return
     */
    public String getStesp() {
        return stesp;
    }

    /**
     *
     * @param stesp
     */
    public void setStesp(String stesp) {
        this.stesp = stesp;
    }

    /**
     *
     * @return
     */
    public Date getFeesp() {
        return feesp;
    }

    /**
     *
     * @param feesp
     */
    public void setFeesp(Date feesp) {
        this.feesp = feesp;
    }

    /**
     *
     * @return
     */
    public String getUsesp() {
        return usesp;
    }

    /**
     *
     * @param usesp
     */
    public void setUsesp(String usesp) {
        this.usesp = usesp;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idesp != null ? idesp.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Espec05)) {
            return false;
        }
        Espec05 other = (Espec05) object;
        if ((this.idesp == null && other.idesp != null) || (this.idesp != null && !this.idesp.equals(other.idesp))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return EntityToString.getString(this,this.getClass());
        //return "com.hrm.sirhapp.model.Espec05[ idesp=" + idesp + " ]";
    }
    
}

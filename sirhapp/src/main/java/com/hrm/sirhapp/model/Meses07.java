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
@Table(name = "MESES07")
@NamedQueries({
    @NamedQuery(name = "Meses07.findAll", query = "SELECT m FROM Meses07 m")})
public class Meses07 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDMES")
    private Integer idmes;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "CVMES")
    private String cvmes;
    @Column(name = "ORMES")
    private Short ormes;
    @Size(max = 10)
    @Column(name = "NOMES")
    private String nomes;
    @Size(max = 2)
    @Column(name = "STMES")
    private String stmes;
    @Column(name = "FEMES")
    @Temporal(TemporalType.TIMESTAMP)
    private Date femes;
    @Size(max = 10)
    @Column(name = "USMES")
    private String usmes;

    /**
     *
     */
    public Meses07() {
    }

    /**
     *
     * @param idmes
     */
    public Meses07(Integer idmes) {
        this.idmes = idmes;
    }

    /**
     *
     * @param idmes
     * @param cvmes
     */
    public Meses07(Integer idmes, String cvmes) {
        this.idmes = idmes;
        this.cvmes = cvmes;
    }

    /**
     *
     * @return
     */
    public Integer getIdmes() {
        return idmes;
    }

    /**
     *
     * @param idmes
     */
    public void setIdmes(Integer idmes) {
        this.idmes = idmes;
    }

    /**
     *
     * @return
     */
    public String getCvmes() {
        return cvmes;
    }

    /**
     *
     * @param cvmes
     */
    public void setCvmes(String cvmes) {
        this.cvmes = cvmes;
    }

    /**
     *
     * @return
     */
    public Short getOrmes() {
        return ormes;
    }

    /**
     *
     * @param ormes
     */
    public void setOrmes(Short ormes) {
        this.ormes = ormes;
    }

    /**
     *
     * @return
     */
    public String getNomes() {
        return nomes;
    }

    /**
     *
     * @param nomes
     */
    public void setNomes(String nomes) {
        this.nomes = nomes;
    }

    /**
     *
     * @return
     */
    public String getStmes() {
        return stmes;
    }

    /**
     *
     * @param stmes
     */
    public void setStmes(String stmes) {
        this.stmes = stmes;
    }
    
    /**
     *
     * @return
     */
    public Date getFemes() {
        return femes;
    }

    /**
     *
     * @param femes
     */
    public void setFemes(Date femes) {
        this.femes = femes;
    }

    /**
     *
     * @return
     */
    public String getUsmes() {
        return usmes;
    }

    /**
     *
     * @param usmes
     */
    public void setUsmes(String usmes) {
        this.usmes = usmes;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idmes != null ? idmes.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Meses07)) {
            return false;
        }
        Meses07 other = (Meses07) object;
        if ((this.idmes == null && other.idmes != null) || (this.idmes != null && !this.idmes.equals(other.idmes))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return EntityToString.getString(this,this.getClass());
        //return "com.hrm.sirhapp.model.Meses07[ idmes=" + idmes + " ]";
    }

}

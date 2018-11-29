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
@Table(name = "NACIO09")
@NamedQueries({
    @NamedQuery(name = "Nacio09.findAll", query = "SELECT n FROM Nacio09 n")})
public class Nacio09 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDNAC")
    private Integer idnac;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 70)
    @Column(name = "NONAC")
    private String nonac;
    @Size(max = 2)
    @Column(name = "STNAC")
    private String stnac;
    @Column(name = "FENAC")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fenac;
    @Size(max = 10)
    @Column(name = "USNAC")
    private String usnac;

    /**
     *
     */
    public Nacio09() {
    }

    /**
     *
     * @param idnac
     */
    public Nacio09(Integer idnac) {
        this.idnac = idnac;
    }

    /**
     *
     * @param idnac
     * @param nonac
     */
    public Nacio09(Integer idnac, String nonac) {
        this.idnac = idnac;
        this.nonac = nonac;
    }

    /**
     *
     * @return
     */
    public Integer getIdnac() {
        return idnac;
    }

    /**
     *
     * @param idnac
     */
    public void setIdnac(Integer idnac) {
        this.idnac = idnac;
    }

    /**
     *
     * @return
     */
    public String getNonac() {
        return nonac;
    }

    /**
     *
     * @param nonac
     */
    public void setNonac(String nonac) {
        this.nonac = nonac;
    }

    /**
     *
     * @return
     */
    public String getStnac() {
        return stnac;
    }

    /**
     *
     * @param stnac
     */
    public void setStnac(String stnac) {
        this.stnac = stnac;
    }

    /**
     *
     * @return
     */
    public Date getFenac() {
        return fenac;
    }

    /**
     *
     * @param fenac
     */
    public void setFenac(Date fenac) {
        this.fenac = fenac;
    }

    /**
     *
     * @return
     */
    public String getUsnac() {
        return usnac;
    }

    /**
     *
     * @param usnac
     */
    public void setUsnac(String usnac) {
        this.usnac = usnac;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idnac != null ? idnac.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Nacio09)) {
            return false;
        }
        Nacio09 other = (Nacio09) object;
        if ((this.idnac == null && other.idnac != null) || (this.idnac != null && !this.idnac.equals(other.idnac))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return EntityToString.getString(this,this.getClass());
        //return "com.hrm.sirhapp.model.Nacio09[ idnac=" + idnac + " ]";
    }
    
}

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
@Table(name = "SEXOO12")
@NamedQueries({
    @NamedQuery(name = "Sexoo12.findAll", query = "SELECT s FROM Sexoo12 s")})
public class Sexoo12 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDSEX")
    private Integer idsex;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 70)
    @Column(name = "NOSEX")
    private String nosex;
    @Size(max = 2)
    @Column(name = "STSEX")
    private String stsex;
    @Column(name = "FESEX")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fesex;
    @Size(max = 10)
    @Column(name = "USSEX")
    private String ussex;

    /**
     *
     */
    public Sexoo12() {
    }

    /**
     *
     * @param idsex
     */
    public Sexoo12(Integer idsex) {
        this.idsex = idsex;
    }

    /**
     *
     * @param idsex
     * @param nosex
     */
    public Sexoo12(Integer idsex, String nosex) {
        this.idsex = idsex;
        this.nosex = nosex;
    }

    /**
     *
     * @return
     */
    public Integer getIdsex() {
        return idsex;
    }

    /**
     *
     * @param idsex
     */
    public void setIdsex(Integer idsex) {
        this.idsex = idsex;
    }

    /**
     *
     * @return
     */
    public String getNosex() {
        return nosex;
    }

    /**
     *
     * @param nosex
     */
    public void setNosex(String nosex) {
        this.nosex = nosex;
    }

    /**
     *
     * @return
     */
    public String getStsex() {
        return stsex;
    }

    /**
     *
     * @param stsex
     */
    public void setStsex(String stsex) {
        this.stsex = stsex;
    }

    /**
     *
     * @return
     */
    public Date getFesex() {
        return fesex;
    }

    /**
     *
     * @param fesex
     */
    public void setFesex(Date fesex) {
        this.fesex = fesex;
    }

    /**
     *
     * @return
     */
    public String getUssex() {
        return ussex;
    }

    /**
     *
     * @param ussex
     */
    public void setUssex(String ussex) {
        this.ussex = ussex;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idsex != null ? idsex.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sexoo12)) {
            return false;
        }
        Sexoo12 other = (Sexoo12) object;
        if ((this.idsex == null && other.idsex != null) || (this.idsex != null && !this.idsex.equals(other.idsex))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return EntityToString.getString(this,this.getClass());
        //return "com.hrm.sirhapp.model.Sexoo12[ idsex=" + idsex + " ]";
    }
    
}

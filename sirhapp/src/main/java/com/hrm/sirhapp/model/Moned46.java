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
@Table(name = "MONED46")
@NamedQueries({
    @NamedQuery(name = "Moned46.findAll", query = "SELECT m FROM Moned46 m")})
public class Moned46 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDMON")
    private Integer idmon;
    @Size(max = 3)
    @Column(name = "NCMON")
    private String ncmon;
    @Size(max = 20)
    @Column(name = "NLMON")
    private String nlmon;
    @Size(max = 2)
    @Column(name = "STMON")
    private String stmon;
    @Column(name = "FEMON")
    @Temporal(TemporalType.TIMESTAMP)
    private Date femon;
    @Size(max = 10)
    @Column(name = "USMON")
    private String usmon;

    /**
     *
     */
    public Moned46() {
    }

    /**
     *
     * @param idmon
     */
    public Moned46(Integer idmon) {
        this.idmon = idmon;
    }

    /**
     *
     * @return
     */
    public Integer getIdmon() {
        return idmon;
    }

    /**
     *
     * @param idmon
     */
    public void setIdmon(Integer idmon) {
        this.idmon = idmon;
    }

    /**
     *
     * @return
     */
    public String getNcmon() {
        return ncmon;
    }

    /**
     *
     * @param ncmon
     */
    public void setNcmon(String ncmon) {
        this.ncmon = ncmon;
    }

    /**
     *
     * @return
     */
    public String getNlmon() {
        return nlmon;
    }

    /**
     *
     * @param nlmon
     */
    public void setNlmon(String nlmon) {
        this.nlmon = nlmon;
    }

    /**
     *
     * @return
     */
    public String getStmon() {
        return stmon;
    }

    /**
     *
     * @param stmon
     */
    public void setStmon(String stmon) {
        this.stmon = stmon;
    }

    /**
     *
     * @return
     */
    public Date getFemon() {
        return femon;
    }

    /**
     *
     * @param femon
     */
    public void setFemon(Date femon) {
        this.femon = femon;
    }

    /**
     *
     * @return
     */
    public String getUsmon() {
        return usmon;
    }

    /**
     *
     * @param usmon
     */
    public void setUsmon(String usmon) {
        this.usmon = usmon;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idmon != null ? idmon.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Moned46)) {
            return false;
        }
        Moned46 other = (Moned46) object;
        if ((this.idmon == null && other.idmon != null) || (this.idmon != null && !this.idmon.equals(other.idmon))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return EntityToString.getString(this,this.getClass());
        //return "com.hrm.sirhapp.model.Moned46[ idmon=" + idmon + " ]";
    }
    
}

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
@Table(name = "CIVIL06")
@NamedQueries({
    @NamedQuery(name = "Civil06.findAll", query = "SELECT c FROM Civil06 c")})
public class Civil06 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDCIV")
    private Integer idciv;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 70)
    @Column(name = "NOCIV")
    private String nociv;
    @Size(max = 2)
    @Column(name = "STCIV")
    private String stciv;
    @Column(name = "FECIV")
    @Temporal(TemporalType.TIMESTAMP)
    private Date feciv;
    @Size(max = 10)
    @Column(name = "USCIV")
    private String usciv;

    /**
     *
     */
    public Civil06() {
    }

    /**
     *
     * @param idciv
     */
    public Civil06(Integer idciv) {
        this.idciv = idciv;
    }

    /**
     *
     * @param idciv
     * @param nociv
     */
    public Civil06(Integer idciv, String nociv) {
        this.idciv = idciv;
        this.nociv = nociv;
    }

    /**
     *
     * @return
     */
    public Integer getIdciv() {
        return idciv;
    }

    /**
     *
     * @param idciv
     */
    public void setIdciv(Integer idciv) {
        this.idciv = idciv;
    }

    /**
     *
     * @return
     */
    public String getNociv() {
        return nociv;
    }

    /**
     *
     * @param nociv
     */
    public void setNociv(String nociv) {
        this.nociv = nociv;
    }

    /**
     *
     * @return
     */
    public String getStciv() {
        return stciv;
    }

    /**
     *
     * @param stciv
     */
    public void setStciv(String stciv) {
        this.stciv = stciv;
    }

    /**
     *
     * @return
     */
    public Date getFeciv() {
        return feciv;
    }

    /**
     *
     * @param feciv
     */
    public void setFeciv(Date feciv) {
        this.feciv = feciv;
    }

    /**
     *
     * @return
     */
    public String getUsciv() {
        return usciv;
    }

    /**
     *
     * @param usciv
     */
    public void setUsciv(String usciv) {
        this.usciv = usciv;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idciv != null ? idciv.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Civil06)) {
            return false;
        }
        Civil06 other = (Civil06) object;
        if ((this.idciv == null && other.idciv != null) || (this.idciv != null && !this.idciv.equals(other.idciv))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return EntityToString.getString(this,this.getClass());
        //return "com.hrm.sirhapp.model.Civil06[ idciv=" + idciv + " ]";
    }
    
}

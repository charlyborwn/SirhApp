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
@Table(name = "TIINF38")
@NamedQueries({
    @NamedQuery(name = "Tiinf38.findAll", query = "SELECT t FROM Tiinf38 t")})
public class Tiinf38 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDTIN")
    private Integer idtin;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 70)
    @Column(name = "DETIN")
    private String detin;
    @Size(max = 2)
    @Column(name = "STTIN")
    private String sttin;
    @Column(name = "FETIN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fetin;
    @Size(max = 10)
    @Column(name = "USTIN")
    private String ustin;

    /**
     *
     */
    public Tiinf38() {
    }

    /**
     *
     * @param idtin
     */
    public Tiinf38(Integer idtin) {
        this.idtin = idtin;
    }

    /**
     *
     * @param idtin
     * @param detin
     */
    public Tiinf38(Integer idtin, String detin) {
        this.idtin = idtin;
        this.detin = detin;
    }

    /**
     *
     * @return
     */
    public Integer getIdtin() {
        return idtin;
    }

    /**
     *
     * @param idtin
     */
    public void setIdtin(Integer idtin) {
        this.idtin = idtin;
    }

    /**
     *
     * @return
     */
    public String getDetin() {
        return detin;
    }

    /**
     *
     * @param detin
     */
    public void setDetin(String detin) {
        this.detin = detin;
    }

    /**
     *
     * @return
     */
    public String getSttin() {
        return sttin;
    }

    /**
     *
     * @param sttin
     */
    public void setSttin(String sttin) {
        this.sttin = sttin;
    }

    /**
     *
     * @return
     */
    public Date getFetin() {
        return fetin;
    }

    /**
     *
     * @param fetin
     */
    public void setFetin(Date fetin) {
        this.fetin = fetin;
    }

    /**
     *
     * @return
     */
    public String getUstin() {
        return ustin;
    }

    /**
     *
     * @param ustin
     */
    public void setUstin(String ustin) {
        this.ustin = ustin;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtin != null ? idtin.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tiinf38)) {
            return false;
        }
        Tiinf38 other = (Tiinf38) object;
        if ((this.idtin == null && other.idtin != null) || (this.idtin != null && !this.idtin.equals(other.idtin))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return EntityToString.getString(this,this.getClass());
        //return "com.hrm.sirhapp.model.Tiinf38[ idtin=" + idtin + " ]";
    }
    
}

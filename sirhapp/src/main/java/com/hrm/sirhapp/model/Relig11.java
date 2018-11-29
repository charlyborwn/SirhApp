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
@Table(name = "RELIG11")
@NamedQueries({
    @NamedQuery(name = "Relig11.findAll", query = "SELECT r FROM Relig11 r")})
public class Relig11 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDREL")
    private Integer idrel;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 70)
    @Column(name = "NOREL")
    private String norel;
    @Size(max = 2)
    @Column(name = "STREL")
    private String strel;
    @Column(name = "FEREL")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ferel;
    @Size(max = 10)
    @Column(name = "USREL")
    private String usrel;

    /**
     *
     */
    public Relig11() {
    }

    /**
     *
     * @param idrel
     */
    public Relig11(Integer idrel) {
        this.idrel = idrel;
    }

    /**
     *
     * @param idrel
     * @param norel
     */
    public Relig11(Integer idrel, String norel) {
        this.idrel = idrel;
        this.norel = norel;
    }

    /**
     *
     * @return
     */
    public Integer getIdrel() {
        return idrel;
    }

    /**
     *
     * @param idrel
     */
    public void setIdrel(Integer idrel) {
        this.idrel = idrel;
    }

    /**
     *
     * @return
     */
    public String getNorel() {
        return norel;
    }

    /**
     *
     * @param norel
     */
    public void setNorel(String norel) {
        this.norel = norel;
    }

    /**
     *
     * @return
     */
    public String getStrel() {
        return strel;
    }

    /**
     *
     * @param strel
     */
    public void setStrel(String strel) {
        this.strel = strel;
    }

    /**
     *
     * @return
     */
    public Date getFerel() {
        return ferel;
    }

    /**
     *
     * @param ferel
     */
    public void setFerel(Date ferel) {
        this.ferel = ferel;
    }

    /**
     *
     * @return
     */
    public String getUsrel() {
        return usrel;
    }

    /**
     *
     * @param usrel
     */
    public void setUsrel(String usrel) {
        this.usrel = usrel;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idrel != null ? idrel.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Relig11)) {
            return false;
        }
        Relig11 other = (Relig11) object;
        if ((this.idrel == null && other.idrel != null) || (this.idrel != null && !this.idrel.equals(other.idrel))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return EntityToString.getString(this,this.getClass());
        //return "com.hrm.sirhapp.model.Relig11[ idrel=" + idrel + " ]";
    }
    
}

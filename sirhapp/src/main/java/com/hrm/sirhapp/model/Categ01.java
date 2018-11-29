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
@Table(name = "CATEG01")
@NamedQueries({
    @NamedQuery(name = "Categ01.findAll", query = "SELECT c FROM Categ01 c")})
public class Categ01 implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDCAT")
    private Integer idcat;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "CVCAT")
    private String cvcat;
    @Size(max = 10)
    @Column(name = "CECAT")
    private String cecat;
    @Size(max = 70)
    @Column(name = "NECAT")
    private String necat;
    @Size(max = 10)
    @Column(name = "COCAT")
    private String cocat;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "SXCAT")
    private Float sxcat;
    @Size(max = 70)
    @Column(name = "NOCAT")
    private String nocat;
    @Size(max = 2)
    @Column(name = "STCAT")
    private String stcat;
    @Column(name = "FECAT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecat;
    @Size(max = 10)
    @Column(name = "USCAT")
    private String uscat;

    /**
     *
     */
    public Categ01() {
    }

    /**
     *
     * @param idcat
     */
    public Categ01(Integer idcat) {
        this.idcat = idcat;
    }

    /**
     *
     * @param idcat
     * @param cvcat
     */
    public Categ01(Integer idcat, String cvcat) {
        this.idcat = idcat;
        this.cvcat = cvcat;
    }

    /**
     *
     * @return
     */
    public Integer getIdcat() {
        return idcat;
    }

    /**
     *
     * @param idcat
     */
    public void setIdcat(Integer idcat) {
        this.idcat = idcat;
    }

    /**
     *
     * @return
     */
    public String getCvcat() {
        return cvcat;
    }

    /**
     *
     * @param cvcat
     */
    public void setCvcat(String cvcat) {
        this.cvcat = cvcat;
    }

    /**
     *
     * @return
     */
    public String getCecat() {
        return cecat;
    }

    /**
     *
     * @param cecat
     */
    public void setCecat(String cecat) {
        this.cecat = cecat;
    }

    /**
     *
     * @return
     */
    public String getNecat() {
        return necat;
    }

    /**
     *
     * @param necat
     */
    public void setNecat(String necat) {
        this.necat = necat;
    }

    /**
     *
     * @return
     */
    public String getCocat() {
        return cocat;
    }

    /**
     *
     * @param cocat
     */
    public void setCocat(String cocat) {
        this.cocat = cocat;
    }

    /**
     *
     * @return
     */
    public Float getSxcat() {
        return sxcat;
    }

    /**
     *
     * @param sxcat
     */
    public void setSxcat(Float sxcat) {
        this.sxcat = sxcat;
    }

    /**
     *
     * @return
     */
    public String getNocat() {
        return nocat;
    }

    /**
     *
     * @param nocat
     */
    public void setNocat(String nocat) {
        this.nocat = nocat;
    }

    /**
     *
     * @return
     */
    public String getStcat() {
        return stcat;
    }

    /**
     *
     * @param stcat
     */
    public void setStcat(String stcat) {
        this.stcat = stcat;
    }

    /**
     *
     * @return
     */
    public Date getFecat() {
        return fecat;
    }

    /**
     *
     * @param fecat
     */
    public void setFecat(Date fecat) {
        this.fecat = fecat;
    }

    /**
     *
     * @return
     */
    public String getUscat() {
        return uscat;
    }

    /**
     *
     * @param uscat
     */
    public void setUscat(String uscat) {
        this.uscat = uscat;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcat != null ? idcat.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Categ01)) {
            return false;
        }
        Categ01 other = (Categ01) object;
        if ((this.idcat == null && other.idcat != null) || (this.idcat != null && !this.idcat.equals(other.idcat))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return EntityToString.getString(this,this.getClass());
        //return "com.hrm.sirhapp.model.Categ01[ idcat=" + idcat + " ]";
    }
    
}

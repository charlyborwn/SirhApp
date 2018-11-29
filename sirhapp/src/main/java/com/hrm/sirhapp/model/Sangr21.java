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
@Table(name = "SANGR21")
@NamedQueries({
    @NamedQuery(name = "Sangr21.findAll", query = "SELECT s FROM Sangr21 s")})
public class Sangr21 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDSAN")
    private Integer idsan;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 6)
    @Column(name = "CVSAN")
    private String cvsan;
    @Size(max = 2)
    @Column(name = "STSAN")
    private String stsan;
    @Column(name = "FESAN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fesan;
    @Size(max = 10)
    @Column(name = "USSAN")
    private String ussan;

    /**
     *
     */
    public Sangr21() {
    }

    /**
     *
     * @param idsan
     */
    public Sangr21(Integer idsan) {
        this.idsan = idsan;
    }

    /**
     *
     * @param idsan
     * @param cvsan
     */
    public Sangr21(Integer idsan, String cvsan) {
        this.idsan = idsan;
        this.cvsan = cvsan;
    }

    /**
     *
     * @return
     */
    public Integer getIdsan() {
        return idsan;
    }

    /**
     *
     * @param idsan
     */
    public void setIdsan(Integer idsan) {
        this.idsan = idsan;
    }

    /**
     *
     * @return
     */
    public String getCvsan() {
        return cvsan;
    }

    /**
     *
     * @param cvsan
     */
    public void setCvsan(String cvsan) {
        this.cvsan = cvsan;
    }

    /**
     *
     * @return
     */
    public String getStsan() {
        return stsan;
    }

    /**
     *
     * @param stsan
     */
    public void setStsan(String stsan) {
        this.stsan = stsan;
    }

    /**
     *
     * @return
     */
    public Date getFesan() {
        return fesan;
    }

    /**
     *
     * @param fesan
     */
    public void setFesan(Date fesan) {
        this.fesan = fesan;
    }

    /**
     *
     * @return
     */
    public String getUssan() {
        return ussan;
    }

    /**
     *
     * @param ussan
     */
    public void setUssan(String ussan) {
        this.ussan = ussan;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idsan != null ? idsan.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sangr21)) {
            return false;
        }
        Sangr21 other = (Sangr21) object;
        if ((this.idsan == null && other.idsan != null) || (this.idsan != null && !this.idsan.equals(other.idsan))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return EntityToString.getString(this,this.getClass());
        //return "com.hrm.sirhapp.model.Sangr21[ idsan=" + idsan + " ]";
    }
    
}

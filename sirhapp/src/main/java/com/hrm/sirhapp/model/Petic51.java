package com.hrm.sirhapp.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "PETIC51")
public class Petic51 implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDPET")
    private Integer idpet;
    @Column(name = "SRPET")
    private Long srpet;
    @Column(name = "RSPET")
    private Long rspet;
    @Column(name = "ESPET")
    private Integer espet;
    @JoinColumn(name = "REPET", referencedColumnName = "IDREP")
    @ManyToOne(optional = false)
    private Repor50 repet;
    @JoinColumn(name = "CVPET", referencedColumnName = "CVUSU")
    @ManyToOne(optional = false)
    private Usuar24 cvpet;
    @Size(max = 2)
    @Column(name = "STPET")
    private String stpet;
    @Column(name = "FEPET")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fepet;
    @Size(max = 10)
    @Column(name = "USPET")
    private String uspet;

    /**
     *
     */
    public Petic51() {
    }

    /**
     *
     * @param idpet
     */
    public Petic51(Integer idpet) {
        this.idpet = idpet;
    }

    /**
     *
     * @return
     */
    public Integer getIdpet() {
        return idpet;
    }

    /**
     *
     * @param idpet
     */
    public void setIdpet(Integer idpet) {
        this.idpet = idpet;
    }

    /**
     *
     * @return
     */
    public Integer getEspet() {
        return espet;
    }

    /**
     *
     * @param espet
     */
    public void setEspet(Integer espet) {
        this.espet = espet;
    }

    /**
     *
     * @return
     */
    public String getStpet() {
        return stpet;
    }

    /**
     *
     * @param stpet
     */
    public void setStpet(String stpet) {
        this.stpet = stpet;
    }

    /**
     *
     * @return
     */
    public Date getFepet() {
        return fepet;
    }

    /**
     *
     * @param fepet
     */
    public void setFepet(Date fepet) {
        this.fepet = fepet;
    }

    /**
     *
     * @return
     */
    public String getUspet() {
        return uspet;
    }

    /**
     *
     * @param uspet
     */
    public void setUspet(String uspet) {
        this.uspet = uspet;
    }

    /**
     *
     * @return
     */
    public Usuar24 getCvpet() {
        return cvpet;
    }

    /**
     *
     * @param cvpet
     */
    public void setCvpet(Usuar24 cvpet) {
        this.cvpet = cvpet;
    }

    /**
     *
     * @return
     */
    public Repor50 getRepet() {
        return repet;
    }

    /**
     *
     * @param repet
     */
    public void setRepet(Repor50 repet) {
        this.repet = repet;
    }

    /**
     *
     * @return
     */
    public Long getSrpet() {
        return srpet;
    }

    /**
     *
     * @param srpet
     */
    public void setSrpet(Long srpet) {
        this.srpet = srpet;
    }

    /**
     *
     * @return
     */
    public Long getRspet() {
        return rspet;
    }

    /**
     *
     * @param rspet
     */
    public void setRspet(Long rspet) {
        this.rspet = rspet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpet != null ? idpet.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Petic51)) {
            return false;
        }
        Petic51 other = (Petic51) object;
        if ((this.idpet == null && other.idpet != null) || (this.idpet != null && !this.idpet.equals(other.idpet))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return EntityToString.getString(this,this.getClass());
        //return "com.hrm.sirhapp.model.Petic51[ idpet=" + idpet + " ]";
    }

}

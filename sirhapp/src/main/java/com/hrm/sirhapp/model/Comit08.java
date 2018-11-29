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
@Table(name = "COMIT08")
@NamedQueries({
    @NamedQuery(name = "Comit08.findAll", query = "SELECT c FROM Comit08 c")})
public class Comit08 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDCOM")
    private Integer idcom;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "CECOM")
    private String cecom;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 70)
    @Column(name = "NOCOM")
    private String nocom;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "CDCOM")
    private String cdcom;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 70)
    @Column(name = "NDCOM")
    private String ndcom;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "CVCOM")
    private String cvcom;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 70)
    @Column(name = "NCCOM")
    private String nccom;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ORCOM")
    private short orcom;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NTCOM")
    private int ntcom;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 70)
    @Column(name = "NPCOM")
    private String npcom;
    @Size(max = 15)
    @Column(name = "T1COM")
    private String t1com;
    @Size(max = 15)
    @Column(name = "T2COM")
    private String t2com;
    @Size(max = 15)
    @Column(name = "TCCOM")
    private String tccom;
    @Size(max = 70)
    @Column(name = "MACOM")
    private String macom;
    @Size(max = 2)
    @Column(name = "STCOM")
    private String stcom;
    @Column(name = "FECOM")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecom;
    @Size(max = 10)
    @Column(name = "USCOM")
    private String uscom;

    /**
     *
     */
    public Comit08() {
    }

    /**
     *
     * @param idcom
     */
    public Comit08(Integer idcom) {
        this.idcom = idcom;
    }

    /**
     *
     * @param idcom
     * @param cecom
     * @param nocom
     * @param cdcom
     * @param ndcom
     * @param cvcom
     * @param nccom
     * @param orcom
     * @param ntcom
     * @param npcom
     */
    public Comit08(Integer idcom, String cecom, String nocom, String cdcom, String ndcom, String cvcom, String nccom, short orcom, int ntcom, String npcom) {
        this.idcom = idcom;
        this.cecom = cecom;
        this.nocom = nocom;
        this.cdcom = cdcom;
        this.ndcom = ndcom;
        this.cvcom = cvcom;
        this.nccom = nccom;
        this.orcom = orcom;
        this.ntcom = ntcom;
        this.npcom = npcom;
    }

    /**
     *
     * @return
     */
    public Integer getIdcom() {
        return idcom;
    }

    /**
     *
     * @param idcom
     */
    public void setIdcom(Integer idcom) {
        this.idcom = idcom;
    }

    /**
     *
     * @return
     */
    public String getCecom() {
        return cecom;
    }

    /**
     *
     * @param cecom
     */
    public void setCecom(String cecom) {
        this.cecom = cecom;
    }

    /**
     *
     * @return
     */
    public String getNocom() {
        return nocom;
    }

    /**
     *
     * @param nocom
     */
    public void setNocom(String nocom) {
        this.nocom = nocom;
    }

    /**
     *
     * @return
     */
    public String getCdcom() {
        return cdcom;
    }

    /**
     *
     * @param cdcom
     */
    public void setCdcom(String cdcom) {
        this.cdcom = cdcom;
    }

    /**
     *
     * @return
     */
    public String getNdcom() {
        return ndcom;
    }

    /**
     *
     * @param ndcom
     */
    public void setNdcom(String ndcom) {
        this.ndcom = ndcom;
    }

    /**
     *
     * @return
     */
    public String getCvcom() {
        return cvcom;
    }

    /**
     *
     * @param cvcom
     */
    public void setCvcom(String cvcom) {
        this.cvcom = cvcom;
    }

    /**
     *
     * @return
     */
    public String getNccom() {
        return nccom;
    }

    /**
     *
     * @param nccom
     */
    public void setNccom(String nccom) {
        this.nccom = nccom;
    }

    /**
     *
     * @return
     */
    public short getOrcom() {
        return orcom;
    }

    /**
     *
     * @param orcom
     */
    public void setOrcom(short orcom) {
        this.orcom = orcom;
    }

    /**
     *
     * @return
     */
    public int getNtcom() {
        return ntcom;
    }

    /**
     *
     * @param ntcom
     */
    public void setNtcom(int ntcom) {
        this.ntcom = ntcom;
    }

    /**
     *
     * @return
     */
    public String getNpcom() {
        return npcom;
    }

    /**
     *
     * @param npcom
     */
    public void setNpcom(String npcom) {
        this.npcom = npcom;
    }

    /**
     *
     * @return
     */
    public String getT1com() {
        return t1com;
    }

    /**
     *
     * @param t1com
     */
    public void setT1com(String t1com) {
        this.t1com = t1com;
    }

    /**
     *
     * @return
     */
    public String getT2com() {
        return t2com;
    }

    /**
     *
     * @param t2com
     */
    public void setT2com(String t2com) {
        this.t2com = t2com;
    }

    /**
     *
     * @return
     */
    public String getTccom() {
        return tccom;
    }

    /**
     *
     * @param tccom
     */
    public void setTccom(String tccom) {
        this.tccom = tccom;
    }

    /**
     *
     * @return
     */
    public String getMacom() {
        return macom;
    }

    /**
     *
     * @param macom
     */
    public void setMacom(String macom) {
        this.macom = macom;
    }

    /**
     *
     * @return
     */
    public String getStcom() {
        return stcom;
    }

    /**
     *
     * @param stcom
     */
    public void setStcom(String stcom) {
        this.stcom = stcom;
    }

    /**
     *
     * @return
     */
    public Date getFecom() {
        return fecom;
    }

    /**
     *
     * @param fecom
     */
    public void setFecom(Date fecom) {
        this.fecom = fecom;
    }

    /**
     *
     * @return
     */
    public String getUscom() {
        return uscom;
    }

    /**
     *
     * @param uscom
     */
    public void setUscom(String uscom) {
        this.uscom = uscom;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcom != null ? idcom.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Comit08)) {
            return false;
        }
        Comit08 other = (Comit08) object;
        if ((this.idcom == null && other.idcom != null) || (this.idcom != null && !this.idcom.equals(other.idcom))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return EntityToString.getString(this,this.getClass());
        //return "com.hrm.sirhapp.model.Comit08[ idcom=" + idcom + " ]";
    }
    
}

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
@Table(name = "EVENT43")
@NamedQueries({
    @NamedQuery(name = "Event43.findAll", query = "SELECT e FROM Event43 e")})
public class Event43 implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDEVE")
    private Integer ideve;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NUEVE")
    private int nueve;
    @Size(max = 10)
    @Column(name = "CEEVE")
    private String ceeve;
    @Size(max = 70)
    @Column(name = "NEEVE")
    private String neeve;
    @Column(name = "FCEVE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fceve;
    @Column(name = "HOEVE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date hoeve;
    @Size(max = 13)
    @Column(name = "UNEVE")
    private String uneve;
    @Size(max = 20)
    @Column(name = "MAEVE")
    private String maeve;
    @Size(max = 20)
    @Column(name = "MOEVE")
    private String moeve;
    @Size(max = 20)
    @Column(name = "PLEVE")
    private String pleve;
    @Size(max = 13)
    @Column(name = "RUEVE")
    private String rueve;
    @Size(max = 70)
    @Column(name = "ROEVE")
    private String roeve;
    @Size(max = 70)
    @Column(name = "RDEVE")
    private String rdeve;
    @Size(max = 13)
    @Column(name = "RFEVE")
    private String rfeve;
    @Size(max = 70)
    @Column(name = "NCEVE")
    private String nceve;
    @Size(max = 15)
    @Column(name = "TCEVE")
    private String tceve;
    @Size(max = 250)
    @Column(name = "DEEVE")
    private String deeve;
    @Size(max = 250)
    @Column(name = "REEVE")
    private String reeve;
    @Size(max = 2)
    @Column(name = "STEVE")
    private String steve;
    @Column(name = "FEEVE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date feeve;
    @Size(max = 10)
    @Column(name = "USEVE")
    private String useve;

    /**
     *
     */
    public Event43() {
    }

    /**
     *
     * @param ideve
     */
    public Event43(Integer ideve) {
        this.ideve = ideve;
    }

    /**
     *
     * @param ideve
     * @param nueve
     */
    public Event43(Integer ideve, Integer nueve) {
        this.ideve = ideve;
        this.nueve = nueve;
    }

    /**
     *
     * @return
     */
    public String getCeeve() {
        return ceeve;
    }

    /**
     *
     * @param ceeve
     */
    public void setCeeve(String ceeve) {
        this.ceeve = ceeve;
    }

    /**
     *
     * @return
     */
    public String getNeeve() {
        return neeve;
    }

    /**
     *
     * @param neeve
     */
    public void setNeeve(String neeve) {
        this.neeve = neeve;
    }

    /**
     *
     * @return
     */
    public Date getFceve() {
        return fceve;
    }

    /**
     *
     * @param fceve
     */
    public void setFceve(Date fceve) {
        this.fceve = fceve;
    }

    /**
     *
     * @return
     */
    public Date getHoeve() {
        return hoeve;
    }

    /**
     *
     * @param hoeve
     */
    public void setHoeve(Date hoeve) {
        this.hoeve = hoeve;
    }

    /**
     *
     * @return
     */
    public String getUneve() {
        return uneve;
    }

    /**
     *
     * @param uneve
     */
    public void setUneve(String uneve) {
        this.uneve = uneve;
    }

    /**
     *
     * @return
     */
    public String getMaeve() {
        return maeve;
    }

    /**
     *
     * @param maeve
     */
    public void setMaeve(String maeve) {
        this.maeve = maeve;
    }

    /**
     *
     * @return
     */
    public String getMoeve() {
        return moeve;
    }

    /**
     *
     * @param moeve
     */
    public void setMoeve(String moeve) {
        this.moeve = moeve;
    }

    /**
     *
     * @return
     */
    public String getPleve() {
        return pleve;
    }

    /**
     *
     * @param pleve
     */
    public void setPleve(String pleve) {
        this.pleve = pleve;
    }

    /**
     *
     * @return
     */
    public String getRueve() {
        return rueve;
    }

    /**
     *
     * @param rueve
     */
    public void setRueve(String rueve) {
        this.rueve = rueve;
    }

    /**
     *
     * @return
     */
    public String getRoeve() {
        return roeve;
    }

    /**
     *
     * @param roeve
     */
    public void setRoeve(String roeve) {
        this.roeve = roeve;
    }

    /**
     *
     * @return
     */
    public String getRdeve() {
        return rdeve;
    }

    /**
     *
     * @param rdeve
     */
    public void setRdeve(String rdeve) {
        this.rdeve = rdeve;
    }

    /**
     *
     * @return
     */
    public String getRfeve() {
        return rfeve;
    }

    /**
     *
     * @param rfeve
     */
    public void setRfeve(String rfeve) {
        this.rfeve = rfeve;
    }

    /**
     *
     * @return
     */
    public String getNceve() {
        return nceve;
    }

    /**
     *
     * @param nceve
     */
    public void setNceve(String nceve) {
        this.nceve = nceve;
    }

    /**
     *
     * @return
     */
    public String getTceve() {
        return tceve;
    }

    /**
     *
     * @param tceve
     */
    public void setTceve(String tceve) {
        this.tceve = tceve;
    }

    /**
     *
     * @return
     */
    public String getDeeve() {
        return deeve;
    }

    /**
     *
     * @param deeve
     */
    public void setDeeve(String deeve) {
        this.deeve = deeve;
    }

    /**
     *
     * @return
     */
    public String getReeve() {
        return reeve;
    }

    /**
     *
     * @param reeve
     */
    public void setReeve(String reeve) {
        this.reeve = reeve;
    }

    /**
     *
     * @return
     */
    public String getSteve() {
        return steve;
    }

    /**
     *
     * @param steve
     */
    public void setSteve(String steve) {
        this.steve = steve;
    }

    /**
     *
     * @return
     */
    public Date getFeeve() {
        return feeve;
    }

    /**
     *
     * @param feeve
     */
    public void setFeeve(Date feeve) {
        this.feeve = feeve;
    }

    /**
     *
     * @return
     */
    public String getUseve() {
        return useve;
    }

    /**
     *
     * @param useve
     */
    public void setUseve(String useve) {
        this.useve = useve;
    }

    /**
     *
     * @return
     */
    public Integer getIdeve() {
        return ideve;
    }

    /**
     *
     * @param ideve
     */
    public void setIdeve(Integer ideve) {
        this.ideve = ideve;
    }

    /**
     *
     * @return
     */
    public int getNueve() {
        return nueve;
    }

    /**
     *
     * @param nueve
     */
    public void setNueve(int nueve) {
        this.nueve = nueve;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ideve != null ? ideve.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Event43)) {
            return false;
        }
        Event43 other = (Event43) object;
        if ((this.ideve == null && other.ideve != null) || (this.ideve != null && !this.ideve.equals(other.ideve))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return EntityToString.getString(this,this.getClass());
        //return "com.hrm.sirhapp.model.Event43[ ideve=" + ideve + " ]";
    }

}

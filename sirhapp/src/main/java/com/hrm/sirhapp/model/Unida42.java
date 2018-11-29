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
@Table(name = "UNIDA42")
@NamedQueries({
    @NamedQuery(name = "Unida42.findAll", query = "SELECT u FROM Unida42 u")})
public class Unida42 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDUNI")
    private Integer iduni;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 13)
    @Column(name = "NUUNI")
    private String nuuni;
    @Size(max = 10)
    @Column(name = "CEUNI")
    private String ceuni;
    @Size(max = 70)
    @Column(name = "NEUNI")
    private String neuni;
    @Size(max = 20)
    @Column(name = "MAUNI")
    private String mauni;
    @Size(max = 20)
    @Column(name = "MOUNI")
    private String mouni;
    @Size(max = 20)
    @Column(name = "ANUNI")
    private String anuni;
    @Size(max = 20)
    @Column(name = "CAUNI")
    private String cauni;
    @Size(max = 20)
    @Column(name = "PLUNI")
    private String pluni;
    @Size(max = 70)
    @Column(name = "PRUNI")
    private String pruni;
    @Size(max = 70)
    @Column(name = "TCUNI")
    private String tcuni;
    @Size(max = 70)
    @Column(name = "PTUNI")
    private String ptuni;
    @Column(name = "FUUNI")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fuuni;
    @Size(max = 70)
    @Column(name = "NSUNI")
    private String nsuni;
    @Column(name = "VSUNI")
    @Temporal(TemporalType.TIMESTAMP)
    private Date vsuni;
    @Size(max = 70)
    @Column(name = "ASUNI")
    private String asuni;
    @Size(max = 2)
    @Column(name = "STUNI")
    private String stuni;
    @Column(name = "FEUNI")
    @Temporal(TemporalType.TIMESTAMP)
    private Date feuni;
    @Size(max = 10)
    @Column(name = "USUNI")
    private String usuni;

    /**
     *
     */
    public Unida42() {
    }

    /**
     *
     * @param iduni
     */
    public Unida42(Integer iduni) {
        this.iduni = iduni;
    }

    /**
     *
     * @param iduni
     * @param nuuni
     */
    public Unida42(Integer iduni, String nuuni) {
        this.iduni = iduni;
        this.nuuni = nuuni;
    }

    /**
     *
     * @return
     */
    public Integer getIduni() {
        return iduni;
    }

    /**
     *
     * @param iduni
     */
    public void setIduni(Integer iduni) {
        this.iduni = iduni;
    }

    /**
     *
     * @return
     */
    public String getNuuni() {
        return nuuni;
    }

    /**
     *
     * @param nuuni
     */
    public void setNuuni(String nuuni) {
        this.nuuni = nuuni;
    }

    /**
     *
     * @return
     */
    public String getCeuni() {
        return ceuni;
    }

    /**
     *
     * @param ceuni
     */
    public void setCeuni(String ceuni) {
        this.ceuni = ceuni;
    }

    /**
     *
     * @return
     */
    public String getNeuni() {
        return neuni;
    }

    /**
     *
     * @param neuni
     */
    public void setNeuni(String neuni) {
        this.neuni = neuni;
    }

    /**
     *
     * @return
     */
    public String getMauni() {
        return mauni;
    }

    /**
     *
     * @param mauni
     */
    public void setMauni(String mauni) {
        this.mauni = mauni;
    }

    /**
     *
     * @return
     */
    public String getMouni() {
        return mouni;
    }

    /**
     *
     * @param mouni
     */
    public void setMouni(String mouni) {
        this.mouni = mouni;
    }

    /**
     *
     * @return
     */
    public String getAnuni() {
        return anuni;
    }

    /**
     *
     * @param anuni
     */
    public void setAnuni(String anuni) {
        this.anuni = anuni;
    }

    /**
     *
     * @return
     */
    public String getCauni() {
        return cauni;
    }

    /**
     *
     * @param cauni
     */
    public void setCauni(String cauni) {
        this.cauni = cauni;
    }

    /**
     *
     * @return
     */
    public String getPluni() {
        return pluni;
    }

    /**
     *
     * @param pluni
     */
    public void setPluni(String pluni) {
        this.pluni = pluni;
    }

    /**
     *
     * @return
     */
    public String getPruni() {
        return pruni;
    }

    /**
     *
     * @param pruni
     */
    public void setPruni(String pruni) {
        this.pruni = pruni;
    }

    /**
     *
     * @return
     */
    public String getTcuni() {
        return tcuni;
    }

    /**
     *
     * @param tcuni
     */
    public void setTcuni(String tcuni) {
        this.tcuni = tcuni;
    }

    /**
     *
     * @return
     */
    public String getPtuni() {
        return ptuni;
    }

    /**
     *
     * @param ptuni
     */
    public void setPtuni(String ptuni) {
        this.ptuni = ptuni;
    }

    /**
     *
     * @return
     */
    public Date getFuuni() {
        return fuuni;
    }

    /**
     *
     * @param fuuni
     */
    public void setFuuni(Date fuuni) {
        this.fuuni = fuuni;
    }

    /**
     *
     * @return
     */
    public String getNsuni() {
        return nsuni;
    }

    /**
     *
     * @param nsuni
     */
    public void setNsuni(String nsuni) {
        this.nsuni = nsuni;
    }

    /**
     *
     * @return
     */
    public Date getVsuni() {
        return vsuni;
    }

    /**
     *
     * @param vsuni
     */
    public void setVsuni(Date vsuni) {
        this.vsuni = vsuni;
    }

    /**
     *
     * @return
     */
    public String getAsuni() {
        return asuni;
    }

    /**
     *
     * @param asuni
     */
    public void setAsuni(String asuni) {
        this.asuni = asuni;
    }

    /**
     *
     * @return
     */
    public String getStuni() {
        return stuni;
    }

    /**
     *
     * @param stuni
     */
    public void setStuni(String stuni) {
        this.stuni = stuni;
    }

    /**
     *
     * @return
     */
    public Date getFeuni() {
        return feuni;
    }

    /**
     *
     * @param feuni
     */
    public void setFeuni(Date feuni) {
        this.feuni = feuni;
    }

    /**
     *
     * @return
     */
    public String getUsuni() {
        return usuni;
    }

    /**
     *
     * @param usuni
     */
    public void setUsuni(String usuni) {
        this.usuni = usuni;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iduni != null ? iduni.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Unida42)) {
            return false;
        }
        Unida42 other = (Unida42) object;
        if ((this.iduni == null && other.iduni != null) || (this.iduni != null && !this.iduni.equals(other.iduni))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return EntityToString.getString(this,this.getClass());
        //return "com.hrm.sirhapp.model.Unida42[ iduni=" + iduni + " ]";
    }
    
}

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
import javax.persistence.Transient;
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
@Table(name = "BENEF26")
@NamedQueries({
    @NamedQuery(name = "Benef26.findAll", query = "SELECT b FROM Benef26 b")})
public class Benef26 implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDBEN")
    private Integer idben;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NTBEN")
    private int ntben;
    @Size(max = 70)
    @Column(name = "TPBEN")
    private String tpben;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 70)
    @Column(name = "APBEN")
    private String apben;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 70)
    @Column(name = "AMBEN")
    private String amben;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 70)
    @Column(name = "NOBEN")
    private String noben;
    @Column(name = "FNBEN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fnben;
    @Size(max = 70)
    @Column(name = "SEBEN")
    private String seben;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "POBEN")
    private Float poben;
    @Size(max = 250)
    @Column(name = "OBBEN")
    private String obben;
    @Size(max = 2)
    @Column(name = "STBEN")
    private String stben;
    @Column(name = "FEBEN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date feben;
    @Size(max = 10)
    @Column(name = "USBEN")
    private String usben;
    @Size(max = 2)
    @Column(name = "VMBEN")
    private String vmben;
    @Size(max = 70)
    @Column(name = "PABEN")
    private String paben;
    @Size(max = 70)
    @Column(name = "ESBEN")
    private String esben;
    @Size(max = 70)
    @Column(name = "MDBEN")
    private String mdben;
    @Size(max = 70)
    @Column(name = "LOBEN")
    private String loben;
    @Size(max = 70)
    @Column(name = "COBEN")
    private String coben;
    @Size(max = 70)
    @Column(name = "CABEN")
    private String caben;
    @Size(max = 5)
    @Column(name = "CPBEN")
    private String cpben;
    @Size(max = 15)
    @Column(name = "TDBEN")
    private String tdben;
    @Size(max = 15)
    @Column(name = "TCBEN")
    private String tcben;
    @Size(max = 70)
    @Column(name = "CEBEN")
    private String ceben;
    @Size(max = 70)
    @Column(name = "TIBEN")
    private String tiben;
    @Size(max = 6)
    @Column(name = "W1BEN")
    private String w1ben;
    @Size(max = 250)
    @Column(name = "PTBEN")
    private String ptben;
    @Transient
    private String ncben;

    /**
     *
     */
    public Benef26() {
    }

    /**
     *
     * @param idben
     */
    public Benef26(Integer idben) {
        this.idben = idben;
    }

    /**
     *
     * @param idben
     * @param ntben
     * @param apben
     * @param amben
     * @param noben
     */
    public Benef26(Integer idben, int ntben, String apben, String amben, String noben) {
        this.idben = idben;
        this.ntben = ntben;
        this.apben = apben;
        this.amben = amben;
        this.noben = noben;
    }

    /**
     *
     * @return
     */
    public String getNcben() {
        String localNcben = apben + " " + amben + " " + noben;
        return localNcben.toUpperCase();
    }

    /**
     *
     * @param ncben
     */
    public void setNcben(String ncben) {
        String localNcben = apben + " " + amben + " " + noben;
        this.ncben = localNcben.toUpperCase();
    }

    /**
     *
     * @return
     */
    public Integer getIdben() {
        return idben;
    }

    /**
     *
     * @param idben
     */
    public void setIdben(Integer idben) {
        this.idben = idben;
    }

    /**
     *
     * @return
     */
    public int getNtben() {
        return ntben;
    }

    /**
     *
     * @param ntben
     */
    public void setNtben(int ntben) {
        this.ntben = ntben;
    }

    /**
     *
     * @return
     */
    public String getTpben() {
        return tpben;
    }

    /**
     *
     * @param tpben
     */
    public void setTpben(String tpben) {
        this.tpben = tpben;
    }

    /**
     *
     * @return
     */
    public String getApben() {
        return apben;
    }

    /**
     *
     * @param apben
     */
    public void setApben(String apben) {
        this.apben = apben;
    }

    /**
     *
     * @return
     */
    public String getAmben() {
        return amben;
    }

    /**
     *
     * @param amben
     */
    public void setAmben(String amben) {
        this.amben = amben;
    }

    /**
     *
     * @return
     */
    public String getNoben() {
        return noben;
    }

    /**
     *
     * @param noben
     */
    public void setNoben(String noben) {
        this.noben = noben;
    }

    /**
     *
     * @return
     */
    public Date getFnben() {
        return fnben;
    }

    /**
     *
     * @param fnben
     */
    public void setFnben(Date fnben) {
        this.fnben = fnben;
    }

    /**
     *
     * @return
     */
    public String getSeben() {
        return seben;
    }

    /**
     *
     * @param seben
     */
    public void setSeben(String seben) {
        this.seben = seben;
    }

    /**
     *
     * @return
     */
    public Float getPoben() {
        return poben;
    }

    /**
     *
     * @param poben
     */
    public void setPoben(Float poben) {
        this.poben = poben;
    }

    /**
     *
     * @return
     */
    public String getObben() {
        return obben;
    }

    /**
     *
     * @param obben
     */
    public void setObben(String obben) {
        this.obben = obben;
    }

    /**
     *
     * @return
     */
    public String getStben() {
        return stben;
    }

    /**
     *
     * @param stben
     */
    public void setStben(String stben) {
        this.stben = stben;
    }

    /**
     *
     * @return
     */
    public Date getFeben() {
        return feben;
    }

    /**
     *
     * @param feben
     */
    public void setFeben(Date feben) {
        this.feben = feben;
    }

    /**
     *
     * @return
     */
    public String getUsben() {
        return usben;
    }

    /**
     *
     * @param usben
     */
    public void setUsben(String usben) {
        this.usben = usben;
    }

    /**
     *
     * @return
     */
    public String getVmben() {
        return vmben;
    }

    /**
     *
     * @param vmben
     */
    public void setVmben(String vmben) {
        this.vmben = vmben;
    }

    /**
     *
     * @return
     */
    public String getPaben() {
        return paben;
    }

    /**
     *
     * @param paben
     */
    public void setPaben(String paben) {
        this.paben = paben;
    }

    /**
     *
     * @return
     */
    public String getEsben() {
        return esben;
    }

    /**
     *
     * @param esben
     */
    public void setEsben(String esben) {
        this.esben = esben;
    }

    /**
     *
     * @return
     */
    public String getMdben() {
        return mdben;
    }

    /**
     *
     * @param mdben
     */
    public void setMdben(String mdben) {
        this.mdben = mdben;
    }

    /**
     *
     * @return
     */
    public String getLoben() {
        return loben;
    }

    /**
     *
     * @param loben
     */
    public void setLoben(String loben) {
        this.loben = loben;
    }

    /**
     *
     * @return
     */
    public String getCoben() {
        return coben;
    }

    /**
     *
     * @param coben
     */
    public void setCoben(String coben) {
        this.coben = coben;
    }

    /**
     *
     * @return
     */
    public String getCaben() {
        return caben;
    }

    /**
     *
     * @param caben
     */
    public void setCaben(String caben) {
        this.caben = caben;
    }

    /**
     *
     * @return
     */
    public String getCpben() {
        return cpben;
    }

    /**
     *
     * @param cpben
     */
    public void setCpben(String cpben) {
        this.cpben = cpben;
    }

    /**
     *
     * @return
     */
    public String getTdben() {
        return tdben;
    }

    /**
     *
     * @param tdben
     */
    public void setTdben(String tdben) {
        this.tdben = tdben;
    }

    /**
     *
     * @return
     */
    public String getTcben() {
        return tcben;
    }

    /**
     *
     * @param tcben
     */
    public void setTcben(String tcben) {
        this.tcben = tcben;
    }

    /**
     *
     * @return
     */
    public String getCeben() {
        return ceben;
    }

    /**
     *
     * @param ceben
     */
    public void setCeben(String ceben) {
        this.ceben = ceben;
    }

    /**
     *
     * @return
     */
    public String getTiben() {
        return tiben;
    }

    /**
     *
     * @param tiben
     */
    public void setTiben(String tiben) {
        this.tiben = tiben;
    }

    /**
     *
     * @return
     */
    public String getW1ben() {
        return w1ben;
    }

    /**
     *
     * @param w1ben
     */
    public void setW1ben(String w1ben) {
        this.w1ben = w1ben;
    }

    /**
     *
     * @return
     */
    public String getPtben() {
        return ptben;
    }

    /**
     *
     * @param ptben
     */
    public void setPtben(String ptben) {
        this.ptben = ptben;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idben != null ? idben.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Benef26)) {
            return false;
        }
        Benef26 other = (Benef26) object;
        if ((this.idben == null && other.idben != null) || (this.idben != null && !this.idben.equals(other.idben))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return EntityToString.getString(this, this.getClass());
        //return "com.hrm.sirhapp.model.Benef26[ idben=" + idben + " ]";
    }

}

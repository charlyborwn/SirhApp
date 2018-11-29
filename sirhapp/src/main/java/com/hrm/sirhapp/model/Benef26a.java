package com.hrm.sirhapp.model;

import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "BENEF26A")
@NamedQueries({
    @NamedQuery(name = "Benef26a.findAll", query = "SELECT b FROM Benef26a b")})
public class Benef26a extends BaseEntityAudit {

    private static final long serialVersionUID = 1L;

    @Size(max = 2)
    @Column(name = "status")
    private String status;
    @Size(max = 2)
    @Column(name = "VMBEN_A")
    private String vmbenA;
    @Size(max = 70)
    @Column(name = "PABEN_A")
    private String pabenA;
    @Size(max = 70)
    @Column(name = "ESBEN_A")
    private String esbenA;
    @Size(max = 70)
    @Column(name = "MDBEN_A")
    private String mdbenA;
    @Size(max = 70)
    @Column(name = "LOBEN_A")
    private String lobenA;
    @Size(max = 70)
    @Column(name = "COBEN_A")
    private String cobenA;
    @Size(max = 70)
    @Column(name = "CABEN_A")
    private String cabenA;
    @Size(max = 5)
    @Column(name = "CPBEN_A")
    private String cpbenA;
    @Size(max = 15)
    @Column(name = "TDBEN_A")
    private String tdbenA;
    @Size(max = 15)
    @Column(name = "TCBEN_A")
    private String tcbenA;
    @Size(max = 70)
    @Column(name = "CEBEN_A")
    private String cebenA;
    @Size(max = 70)
    @Column(name = "TIBEN_A")
    private String tibenA;
    @Size(max = 6)
    @Column(name = "W1BEN_A")
    private String w1benA;
    @Size(max = 250)
    @Column(name = "PTBEN_A")
    private String ptbenA;
    @Basic(optional = false)
    @Column(name = "IDBEN_A")
    private Integer idbenA;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 13)
    @Column(name = "RFBEN_A")
    private String rfbenA;
    @Size(max = 70)
    @Column(name = "TPBEN_A")
    private String tpbenA;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 70)
    @Column(name = "APBEN_A")
    private String apbenA;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 70)
    @Column(name = "AMBEN_A")
    private String ambenA;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 70)
    @Column(name = "NOBEN_A")
    private String nobenA;
    @Column(name = "FNBEN_A")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fnbenA;
    @Size(max = 70)
    @Column(name = "SEBEN_A")
    private String sebenA;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "POBEN_A")
    private Float pobenA;
    @Size(max = 250)
    @Column(name = "OBBEN_A")
    private String obbenA;
    @Size(max = 2)
    @Column(name = "STBEN_A")
    private String stbenA;
    @Column(name = "FEBEN_A")
    @Temporal(TemporalType.TIMESTAMP)
    private Date febenA;
    @Size(max = 10)
    @Column(name = "USBEN_A")
    private String usbenA;
    @Transient
    private String ncbenA;

    /**
     *
     */
    public Benef26a() {
    }

    /**
     *
     * @return
     */
    public String getNcbenA() {
        String localNcbenA = apbenA + " " + ambenA + " " + nobenA;
        return localNcbenA.toUpperCase();
    }

    /**
     *
     * @param ncbenA
     */
    public void setNcbenA(String ncbenA) {
        String localNcbenA = apbenA + " " + ambenA + " " + nobenA;
        this.ncbenA = localNcbenA.toUpperCase();
    }

    /**
     *
     * @return
     */
    public Integer getIdbenA() {
        return idbenA;
    }

    /**
     *
     * @param idbenA
     */
    public void setIdbenA(Integer idbenA) {
        this.idbenA = idbenA;
    }

    /**
     *
     * @return
     */
    public String getRfbenA() {
        return rfbenA;
    }

    /**
     *
     * @param rfbenA
     */
    public void setRfbenA(String rfbenA) {
        this.rfbenA = rfbenA;
    }

    /**
     *
     * @return
     */
    public String getTpbenA() {
        return tpbenA;
    }

    /**
     *
     * @param tpbenA
     */
    public void setTpbenA(String tpbenA) {
        this.tpbenA = tpbenA;
    }

    /**
     *
     * @return
     */
    public String getApbenA() {
        return apbenA;
    }

    /**
     *
     * @param apbenA
     */
    public void setApbenA(String apbenA) {
        this.apbenA = apbenA;
    }

    /**
     *
     * @return
     */
    public String getAmbenA() {
        return ambenA;
    }

    /**
     *
     * @param ambenA
     */
    public void setAmbenA(String ambenA) {
        this.ambenA = ambenA;
    }

    /**
     *
     * @return
     */
    public String getNobenA() {
        return nobenA;
    }

    /**
     *
     * @param nobenA
     */
    public void setNobenA(String nobenA) {
        this.nobenA = nobenA;
    }

    /**
     *
     * @return
     */
    public Date getFnbenA() {
        return fnbenA;
    }

    /**
     *
     * @param fnbenA
     */
    public void setFnbenA(Date fnbenA) {
        this.fnbenA = fnbenA;
    }

    /**
     *
     * @return
     */
    public String getSebenA() {
        return sebenA;
    }

    /**
     *
     * @param sebenA
     */
    public void setSebenA(String sebenA) {
        this.sebenA = sebenA;
    }

    /**
     *
     * @return
     */
    public Float getPobenA() {
        return pobenA;
    }

    /**
     *
     * @param pobenA
     */
    public void setPobenA(Float pobenA) {
        this.pobenA = pobenA;
    }

    /**
     *
     * @return
     */
    public String getObbenA() {
        return obbenA;
    }

    /**
     *
     * @param obbenA
     */
    public void setObbenA(String obbenA) {
        this.obbenA = obbenA;
    }

    /**
     *
     * @return
     */
    public String getStbenA() {
        return stbenA;
    }

    /**
     *
     * @param stbenA
     */
    public void setStbenA(String stbenA) {
        this.stbenA = stbenA;
    }

    /**
     *
     * @return
     */
    public Date getFebenA() {
        return febenA;
    }

    /**
     *
     * @param febenA
     */
    public void setFebenA(Date febenA) {
        this.febenA = febenA;
    }

    /**
     *
     * @return
     */
    public String getUsbenA() {
        return usbenA;
    }

    /**
     *
     * @param usbenA
     */
    public void setUsbenA(String usbenA) {
        this.usbenA = usbenA;
    }

    /**
     *
     * @return
     */
    public String getVmbenA() {
        return vmbenA;
    }

    /**
     *
     * @param vmbenA
     */
    public void setVmbenA(String vmbenA) {
        this.vmbenA = vmbenA;
    }

    /**
     *
     * @return
     */
    public String getPabenA() {
        return pabenA;
    }

    /**
     *
     * @param pabenA
     */
    public void setPabenA(String pabenA) {
        this.pabenA = pabenA;
    }

    /**
     *
     * @return
     */
    public String getEsbenA() {
        return esbenA;
    }

    /**
     *
     * @param esbenA
     */
    public void setEsbenA(String esbenA) {
        this.esbenA = esbenA;
    }

    /**
     *
     * @return
     */
    public String getMdbenA() {
        return mdbenA;
    }

    /**
     *
     * @param mdbenA
     */
    public void setMdbenA(String mdbenA) {
        this.mdbenA = mdbenA;
    }

    /**
     *
     * @return
     */
    public String getLobenA() {
        return lobenA;
    }

    /**
     *
     * @param lobenA
     */
    public void setLobenA(String lobenA) {
        this.lobenA = lobenA;
    }

    /**
     *
     * @return
     */
    public String getCobenA() {
        return cobenA;
    }

    /**
     *
     * @param cobenA
     */
    public void setCobenA(String cobenA) {
        this.cobenA = cobenA;
    }

    /**
     *
     * @return
     */
    public String getCabenA() {
        return cabenA;
    }

    /**
     *
     * @param cabenA
     */
    public void setCabenA(String cabenA) {
        this.cabenA = cabenA;
    }

    /**
     *
     * @return
     */
    public String getCpbenA() {
        return cpbenA;
    }

    /**
     *
     * @param cpbenA
     */
    public void setCpbenA(String cpbenA) {
        this.cpbenA = cpbenA;
    }

    /**
     *
     * @return
     */
    public String getTdbenA() {
        return tdbenA;
    }

    /**
     *
     * @param tdbenA
     */
    public void setTdbenA(String tdbenA) {
        this.tdbenA = tdbenA;
    }

    /**
     *
     * @return
     */
    public String getTcbenA() {
        return tcbenA;
    }

    /**
     *
     * @param tcbenA
     */
    public void setTcbenA(String tcbenA) {
        this.tcbenA = tcbenA;
    }

    /**
     *
     * @return
     */
    public String getCebenA() {
        return cebenA;
    }

    /**
     *
     * @param cebenA
     */
    public void setCebenA(String cebenA) {
        this.cebenA = cebenA;
    }

    /**
     *
     * @return
     */
    public String getTibenA() {
        return tibenA;
    }

    /**
     *
     * @param tibenA
     */
    public void setTibenA(String tibenA) {
        this.tibenA = tibenA;
    }

    /**
     *
     * @return
     */
    public String getW1benA() {
        return w1benA;
    }

    /**
     *
     * @param w1benA
     */
    public void setW1benA(String w1benA) {
        this.w1benA = w1benA;
    }

    /**
     *
     * @return
     */
    public String getPtbenA() {
        return ptbenA;
    }

    /**
     *
     * @param ptbenA
     */
    public void setPtbenA(String ptbenA) {
        this.ptbenA = ptbenA;
    }

    /**
     *
     * @return
     */
    public String getStatus() {
        return status;
    }

    /**
     *
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return EntityToString.getString(this, this.getClass());
        //return "com.hrm.sirhapp.model.Benef26a[ idtraA=" + idtraA + " ]";   
    }
}

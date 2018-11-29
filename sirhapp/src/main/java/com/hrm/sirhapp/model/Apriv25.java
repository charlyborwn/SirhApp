package com.hrm.sirhapp.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.apache.commons.io.FilenameUtils;

/**
 *
 * @author Carlos Bernal carlose.bernalh@gmail.com
 * Copyright 2018 Carlos Ernesto Bernal Hernández.
 * SirhApp
 * 
 */
@Entity
@Table(name = "APRIV25")
public class Apriv25 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDAPR")
    private Integer idapr;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NTAPR")
    private int ntapr;
    @Size(max = 70)
    @Column(name = "NNAPR")
    private String nnapr;
    @Size(max = 13)
    @Column(name = "RFAPR")
    private String rfapr;
    @Size(max = 250)
    @Column(name = "PFAPR")
    private String pfapr;
    @Size(max = 70)
    @Column(name = "SSAPR")
    private String ssapr;
    @Column(name = "FSAPR")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fsapr;
    @Size(max = 15)
    @Column(name = "NUAPR")
    private String nuapr;
    @Size(max = 20)
    @Column(name = "ESAPR")
    private String esapr;
    @Size(max = 10)
    @Column(name = "CEAPR")
    private String ceapr;
    @Size(max = 70)
    @Column(name = "NEAPR")
    private String neapr;
    @Size(max = 10)
    @Column(name = "NDAPR")
    private String ndapr;
    @Size(max = 70)
    @Column(name = "DEAPR")
    private String deapr;
    @Size(max = 10)
    @Column(name = "CSAPR")
    private String csapr;
    @Size(max = 70)
    @Column(name = "SEAPR")
    private String seapr;
    @Size(max = 10)
    @Column(name = "CCAPR")
    private String ccapr;
    @Size(max = 70)
    @Column(name = "CAAPR")
    private String caapr;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "SXAPR")
    private Float sxapr;
    @Size(max = 6)
    @Column(name = "CTAPR")
    private String ctapr;
    @Size(max = 70)
    @Column(name = "NOAPR")
    private String noapr;
    @Size(max = 6)
    @Column(name = "TCAPR")
    private String tcapr;
    @Size(max = 70)
    @Column(name = "COAPR")
    private String coapr;
    @Column(name = "ICAPR")
    @Temporal(TemporalType.TIMESTAMP)
    private Date icapr;
    @Column(name = "FCAPR")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fcapr;
    @Size(max = 70)
    @Column(name = "TIAPR")
    private String tiapr;
    @Size(max = 6)
    @Column(name = "W1APR")
    private String w1apr;
    @Column(name = "FAAPR")
    @Temporal(TemporalType.TIMESTAMP)
    private Date faapr;
    @Column(name = "HAAPR")
    @Temporal(TemporalType.TIMESTAMP)
    private Date haapr;
    @Column(name = "DIAPR")
    private Short diapr;
    @Column(name = "FTAPR")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ftapr;
    @Column(name = "HTAPR")
    @Temporal(TemporalType.TIMESTAMP)
    private Date htapr;
    @Size(max = 250)
    @Column(name = "P1APR")
    private String p1apr;
    @Size(max = 2)
    @Column(name = "JUAPR")
    private String juapr;
    @Size(max = 70)
    @Column(name = "DJAPR")
    private String djapr;
    @Size(max = 6)
    @Column(name = "W2APR")
    private String w2apr;
    @Size(max = 250)
    @Column(name = "P2APR")
    private String p2apr;
    @Size(max = 2)
    @Column(name = "REAPR")
    private String reapr;
    @Column(name = "FPAPR")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fpapr;
    @Column(name = "FRAPR")
    @Temporal(TemporalType.TIMESTAMP)
    private Date frapr;
    @Column(name = "HRAPR")
    @Temporal(TemporalType.TIMESTAMP)
    private Date hrapr;
    @Size(max = 10)
    @Column(name = "VPAPR")
    private String vpapr;
    @Size(max = 6)
    @Column(name = "W3APR")
    private String w3apr;
    @Size(max = 250)
    @Column(name = "P3APR")
    private String p3apr;
    @Size(max = 250)
    @Column(name = "OBAPR")
    private String obapr;
    @Size(max = 2)
    @Column(name = "STAPR")
    private String stapr;
    @Column(name = "FEAPR")
    @Temporal(TemporalType.TIMESTAMP)
    private Date feapr;
    @Size(max = 10)
    @Column(name = "USAPR")
    private String usapr;

    @Transient
    private Boolean f1apr;
    @Transient
    private Boolean f2apr;
    @Transient
    private Boolean f3apr;

    /**
     *
     */
    public Apriv25() {
    }

    /**
     *
     * @param idapr
     */
    public Apriv25(Integer idapr) {
        this.idapr = idapr;
    }

    /**
     *
     * @param idapr
     * @param ntapr
     */
    public Apriv25(Integer idapr, int ntapr) {
        this.idapr = idapr;
        this.ntapr = ntapr;
    }

    /**
     *
     * @return
     */
    public Integer getIdapr() {
        return idapr;
    }

    /**
     *
     * @param idapr
     */
    public void setIdapr(Integer idapr) {
        this.idapr = idapr;
    }

    /**
     *
     * @return
     */
    public int getNtapr() {
        return ntapr;
    }

    /**
     *
     * @param ntapr
     */
    public void setNtapr(int ntapr) {
        this.ntapr = ntapr;
    }

    /**
     *
     * @return
     */
    public String getNnapr() {
        return nnapr;
    }

    /**
     *
     * @param nnapr
     */
    public void setNnapr(String nnapr) {
        this.nnapr = nnapr;
    }

    /**
     *
     * @return
     */
    public String getRfapr() {
        return rfapr;
    }

    /**
     *
     * @param rfapr
     */
    public void setRfapr(String rfapr) {
        this.rfapr = rfapr;
    }

    /**
     *
     * @return
     */
    public String getPfapr() {
        return pfapr;
    }

    /**
     *
     * @param pfapr
     */
    public void setPfapr(String pfapr) {
        this.pfapr = pfapr;
    }

    /**
     *
     * @return
     */
    public String getSsapr() {
        return ssapr;
    }

    /**
     *
     * @param ssapr
     */
    public void setSsapr(String ssapr) {
        this.ssapr = ssapr;
    }

    /**
     *
     * @return
     */
    public Date getFsapr() {
        return fsapr;
    }

    /**
     *
     * @param fsapr
     */
    public void setFsapr(Date fsapr) {
        this.fsapr = fsapr;
    }

    /**
     *
     * @return
     */
    public String getNuapr() {
        return nuapr;
    }

    /**
     *
     * @param nuapr
     */
    public void setNuapr(String nuapr) {
        this.nuapr = nuapr;
    }

    /**
     *
     * @return
     */
    public String getEsapr() {
        return esapr;
    }

    /**
     *
     * @param esapr
     */
    public void setEsapr(String esapr) {
        this.esapr = esapr;
    }

    /**
     *
     * @return
     */
    public String getCeapr() {
        return ceapr;
    }

    /**
     *
     * @param ceapr
     */
    public void setCeapr(String ceapr) {
        this.ceapr = ceapr;
    }

    /**
     *
     * @return
     */
    public String getNeapr() {
        return neapr;
    }

    /**
     *
     * @param neapr
     */
    public void setNeapr(String neapr) {
        this.neapr = neapr;
    }

    /**
     *
     * @return
     */
    public String getNdapr() {
        return ndapr;
    }

    /**
     *
     * @param ndapr
     */
    public void setNdapr(String ndapr) {
        this.ndapr = ndapr;
    }

    /**
     *
     * @return
     */
    public String getDeapr() {
        return deapr;
    }

    /**
     *
     * @param deapr
     */
    public void setDeapr(String deapr) {
        this.deapr = deapr;
    }

    /**
     *
     * @return
     */
    public String getCsapr() {
        return csapr;
    }

    /**
     *
     * @param csapr
     */
    public void setCsapr(String csapr) {
        this.csapr = csapr;
    }

    /**
     *
     * @return
     */
    public String getSeapr() {
        return seapr;
    }

    /**
     *
     * @param seapr
     */
    public void setSeapr(String seapr) {
        this.seapr = seapr;
    }

    /**
     *
     * @return
     */
    public String getCcapr() {
        return ccapr;
    }

    /**
     *
     * @param ccapr
     */
    public void setCcapr(String ccapr) {
        this.ccapr = ccapr;
    }

    /**
     *
     * @return
     */
    public String getCaapr() {
        return caapr;
    }

    /**
     *
     * @param caapr
     */
    public void setCaapr(String caapr) {
        this.caapr = caapr;
    }

    /**
     *
     * @return
     */
    public Float getSxapr() {
        return sxapr;
    }

    /**
     *
     * @param sxapr
     */
    public void setSxapr(Float sxapr) {
        this.sxapr = sxapr;
    }

    /**
     *
     * @return
     */
    public String getCtapr() {
        return ctapr;
    }

    /**
     *
     * @param ctapr
     */
    public void setCtapr(String ctapr) {
        this.ctapr = ctapr;
    }

    /**
     *
     * @return
     */
    public String getNoapr() {
        return noapr;
    }

    /**
     *
     * @param noapr
     */
    public void setNoapr(String noapr) {
        this.noapr = noapr;
    }

    /**
     *
     * @return
     */
    public String getTcapr() {
        return tcapr;
    }

    /**
     *
     * @param tcapr
     */
    public void setTcapr(String tcapr) {
        this.tcapr = tcapr;
    }

    /**
     *
     * @return
     */
    public String getCoapr() {
        return coapr;
    }

    /**
     *
     * @param coapr
     */
    public void setCoapr(String coapr) {
        this.coapr = coapr;
    }

    /**
     *
     * @return
     */
    public Date getIcapr() {
        return icapr;
    }

    /**
     *
     * @param icapr
     */
    public void setIcapr(Date icapr) {
        this.icapr = icapr;
    }

    /**
     *
     * @return
     */
    public Date getFcapr() {
        return fcapr;
    }

    /**
     *
     * @param fcapr
     */
    public void setFcapr(Date fcapr) {
        this.fcapr = fcapr;
    }

    /**
     *
     * @return
     */
    public String getTiapr() {
        return tiapr;
    }

    /**
     *
     * @param tiapr
     */
    public void setTiapr(String tiapr) {
        this.tiapr = tiapr;
    }

    /**
     *
     * @return
     */
    public String getW1apr() {
        return w1apr;
    }

    /**
     *
     * @param w1apr
     */
    public void setW1apr(String w1apr) {
        this.w1apr = w1apr;
    }

    /**
     *
     * @return
     */
    public Date getFaapr() {
        return faapr;
    }

    /**
     *
     * @param faapr
     */
    public void setFaapr(Date faapr) {
        this.faapr = faapr;
    }

    /**
     *
     * @return
     */
    public Date getHaapr() {
        return haapr;
    }

    /**
     *
     * @param haapr
     */
    public void setHaapr(Date haapr) {
        this.haapr = haapr;
    }

    /**
     *
     * @return
     */
    public Short getDiapr() {
        return diapr;
    }

    /**
     *
     * @param diapr
     */
    public void setDiapr(Short diapr) {
        this.diapr = diapr;
    }

    /**
     *
     * @return
     */
    public Date getFtapr() {
        return ftapr;
    }

    /**
     *
     * @param ftapr
     */
    public void setFtapr(Date ftapr) {
        this.ftapr = ftapr;
    }

    /**
     *
     * @return
     */
    public Date getHtapr() {
        return htapr;
    }

    /**
     *
     * @param htapr
     */
    public void setHtapr(Date htapr) {
        this.htapr = htapr;
    }

    /**
     *
     * @return
     */
    public String getP1apr() {
        return p1apr;
    }

    /**
     *
     * @param p1apr
     */
    public void setP1apr(String p1apr) {
        this.p1apr = p1apr;
    }

    /**
     *
     * @return
     */
    public String getJuapr() {
        return juapr;
    }

    /**
     *
     * @param juapr
     */
    public void setJuapr(String juapr) {
        this.juapr = juapr;
    }

    /**
     *
     * @return
     */
    public String getDjapr() {
        return djapr;
    }

    /**
     *
     * @param djapr
     */
    public void setDjapr(String djapr) {
        this.djapr = djapr;
    }

    /**
     *
     * @return
     */
    public String getW2apr() {
        return w2apr;
    }

    /**
     *
     * @param w2apr
     */
    public void setW2apr(String w2apr) {
        this.w2apr = w2apr;
    }

    /**
     *
     * @return
     */
    public String getP2apr() {
        return p2apr;
    }

    /**
     *
     * @param p2apr
     */
    public void setP2apr(String p2apr) {
        this.p2apr = p2apr;
    }

    /**
     *
     * @return
     */
    public String getReapr() {
        return reapr;
    }

    /**
     *
     * @param reapr
     */
    public void setReapr(String reapr) {
        this.reapr = reapr;
    }

    /**
     *
     * @return
     */
    public Date getFpapr() {
        return fpapr;
    }

    /**
     *
     * @param fpapr
     */
    public void setFpapr(Date fpapr) {
        this.fpapr = fpapr;
    }

    /**
     *
     * @return
     */
    public Date getFrapr() {
        return frapr;
    }

    /**
     *
     * @param frapr
     */
    public void setFrapr(Date frapr) {
        this.frapr = frapr;
    }

    /**
     *
     * @return
     */
    public Date getHrapr() {
        return hrapr;
    }

    /**
     *
     * @param hrapr
     */
    public void setHrapr(Date hrapr) {
        this.hrapr = hrapr;
    }

    /**
     *
     * @return
     */
    public String getVpapr() {
        return vpapr;
    }

    /**
     *
     * @param vpapr
     */
    public void setVpapr(String vpapr) {
        this.vpapr = vpapr;
    }

    /**
     *
     * @return
     */
    public String getW3apr() {
        return w3apr;
    }

    /**
     *
     * @param w3apr
     */
    public void setW3apr(String w3apr) {
        this.w3apr = w3apr;
    }

    /**
     *
     * @return
     */
    public String getP3apr() {
        return p3apr;
    }

    /**
     *
     * @param p3apr
     */
    public void setP3apr(String p3apr) {
        this.p3apr = p3apr;
    }

    /**
     *
     * @return
     */
    public String getObapr() {
        return obapr;
    }

    /**
     *
     * @param obapr
     */
    public void setObapr(String obapr) {
        this.obapr = obapr;
    }

    /**
     *
     * @return
     */
    public String getStapr() {
        return stapr;
    }

    /**
     *
     * @param stapr
     */
    public void setStapr(String stapr) {
        this.stapr = stapr;
    }

    /**
     *
     * @return
     */
    public Date getFeapr() {
        return feapr;
    }

    /**
     *
     * @param feapr
     */
    public void setFeapr(Date feapr) {
        this.feapr = feapr;
    }

    /**
     *
     * @return
     */
    public String getUsapr() {
        return usapr;
    }

    /**
     *
     * @param usapr
     */
    public void setUsapr(String usapr) {
        this.usapr = usapr;
    }

    /**
     *
     * @return
     */
    public Boolean getF1apr() {
        if (this.f1apr == null && p1apr != null && p1apr.length() > 0) {
            return FilenameUtils.getExtension(p1apr).equals("pdf");
        }
        return f1apr;
    }

    /**
     *
     * @param f1apr
     */
    public void setF1apr(Boolean f1apr) {
        this.f1apr = f1apr;
    }

    /**
     *
     * @return
     */
    public Boolean getF2apr() {
        if (this.f2apr == null && p2apr != null && p2apr.length() > 0) {
            return FilenameUtils.getExtension(p2apr).equals("pdf");
        }
        return f2apr;
    }

    /**
     *
     * @param f2apr
     */
    public void setF2apr(Boolean f2apr) {
        this.f2apr = f2apr;
    }

    /**
     *
     * @return
     */
    public Boolean getF3apr() {
        if (this.f3apr == null && p3apr != null && p3apr.length() > 0) {
            return FilenameUtils.getExtension(p3apr).equals("pdf");
        }
        return f3apr;
    }

    /**
     *
     * @param f3apr
     */
    public void setF3apr(Boolean f3apr) {
        this.f3apr = f3apr;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idapr != null ? idapr.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Apriv25)) {
            return false;
        }
        Apriv25 other = (Apriv25) object;
        if ((this.idapr == null && other.idapr != null) || (this.idapr != null && !this.idapr.equals(other.idapr))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return EntityToString.getString(this, this.getClass());
        //return "com.hrm.sirhapp.model.Apriv25[ idapr=" + idapr + " ]";
    }
}

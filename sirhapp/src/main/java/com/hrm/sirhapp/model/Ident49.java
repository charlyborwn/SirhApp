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
@Table(name = "IDENT49")
@NamedQueries({
    @NamedQuery(name = "Ident49.findAll", query = "SELECT i FROM Ident49 i")})
public class Ident49 implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDIDE")
    private Integer idide;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NTIDE")
    private int ntide;
    @Size(max = 70)
    @Column(name = "DIIDE")
    private String diide;
    @Size(max = 13)
    @Column(name = "RFIDE")
    private String rfide;
    @Size(max = 11)
    @Column(name = "RIIDE")
    private String riide;
    @Size(max = 70)
    @Column(name = "UIIDE")
    private String uiide;
    @Size(max = 70)
    @Column(name = "CEIDE")
    private String ceide;
    @Size(max = 2)
    @Column(name = "EEIDE")
    private String eeide;
    @Size(max = 3)
    @Column(name = "MEIDE")
    private String meide;
    @Size(max = 4)
    @Column(name = "LEIDE")
    private String leide;
    @Size(max = 4)
    @Column(name = "SIIDE")
    private String siide;
    @Size(max = 4)
    @Column(name = "EMIDE")
    private String emide;
    @Size(max = 4)
    @Column(name = "VEIDE")
    private String veide;
    @Size(max = 15)
    @Column(name = "NEIDE")
    private String neide;
    @Size(max = 70)
    @Column(name = "RGIDE")
    private String rgide;
    @Size(max = 70)
    @Column(name = "ECIDE")
    private String ecide;
    @Size(max = 6)
    @Column(name = "TSIDE")
    private String tside;
    @Size(max = 2)
    @Column(name = "DSIDE")
    private String dside;
    @Column(name = "FDIDE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fdide;
    @Size(max = 2)
    @Column(name = "DOIDE")
    private String doide;
    @Size(max = 70)
    @Column(name = "ALIDE")
    private String alide;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "KGIDE")
    private Float kgide;
    @Column(name = "FKIDE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fkide;
    @Column(name = "ESIDE")
    private Float eside;
    @Column(name = "FMIDE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fmide;
    @Size(max = 4)
    @Column(name = "CCIDE")
    private String ccide;
    @Size(max = 4)
    @Column(name = "PPIDE")
    private String ppide;
    @Size(max = 4)
    @Column(name = "ZAIDE")
    private String zaide;
    @Size(max = 70)
    @Column(name = "NLIDE")
    private String nlide;
    @Column(name = "VLIDE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date vlide;
    @Size(max = 70)
    @Column(name = "ELIDE")
    private String elide;
    @Size(max = 2)
    @Column(name = "APIDE")
    private String apide;
    @Size(max = 70)
    @Column(name = "MTIDE")
    private String mtide;
    @Size(max = 70)
    @Column(name = "OTIDE")
    private String otide;
    @Column(name = "AOIDE")
    private Short aoide;
    @Size(max = 8)
    @Column(name = "PLIDE")
    private String plide;
    @Column(name = "IIIDE")
    private Short iiide;
    @Column(name = "IFIDE")
    private Short ifide;
    @Size(max = 70)
    @Column(name = "OLIDE")
    private String olide;
    @Column(name = "IOIDE")
    private Short ioide;
    @Size(max = 70)
    @Column(name = "A1IDE")
    private String a1ide;
    @Size(max = 70)
    @Column(name = "A2IDE")
    private String a2ide;
    @Size(max = 2)
    @Column(name = "STIDE")
    private String stide;
    @Column(name = "FEIDE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date feide;
    @Size(max = 10)
    @Column(name = "USIDE")
    private String uside;

    /**
     *
     */
    public Ident49() {
    }

    /**
     *
     * @param idide
     */
    public Ident49(Integer idide) {
        this.idide = idide;
    }

    /**
     *
     * @param idide
     * @param ntide
     */
    public Ident49(Integer idide, int ntide) {
        this.idide = idide;
        this.ntide = ntide;
    }

    /**
     *
     * @return
     */
    public String getRfide() {
        return rfide;
    }

    /**
     *
     * @param rfide
     */
    public void setRfide(String rfide) {
        this.rfide = rfide;
    }

    /**
     *
     * @return
     */
    public String getRiide() {
        return riide;
    }

    /**
     *
     * @param riide
     */
    public void setRiide(String riide) {
        this.riide = riide;
    }

    /**
     *
     * @return
     */
    public String getUiide() {
        return uiide;
    }

    /**
     *
     * @param uiide
     */
    public void setUiide(String uiide) {
        this.uiide = uiide;
    }

    /**
     *
     * @return
     */
    public String getCeide() {
        return ceide;
    }

    /**
     *
     * @param ceide
     */
    public void setCeide(String ceide) {
        this.ceide = ceide;
    }

    /**
     *
     * @return
     */
    public String getEeide() {
        return eeide;
    }

    /**
     *
     * @param eeide
     */
    public void setEeide(String eeide) {
        this.eeide = eeide;
    }

    /**
     *
     * @return
     */
    public String getMeide() {
        return meide;
    }

    /**
     *
     * @param meide
     */
    public void setMeide(String meide) {
        this.meide = meide;
    }

    /**
     *
     * @return
     */
    public String getLeide() {
        return leide;
    }

    /**
     *
     * @param leide
     */
    public void setLeide(String leide) {
        this.leide = leide;
    }

    /**
     *
     * @return
     */
    public String getSiide() {
        return siide;
    }

    /**
     *
     * @param siide
     */
    public void setSiide(String siide) {
        this.siide = siide;
    }

    /**
     *
     * @return
     */
    public String getEmide() {
        return emide;
    }

    /**
     *
     * @param emide
     */
    public void setEmide(String emide) {
        this.emide = emide;
    }

    /**
     *
     * @return
     */
    public String getVeide() {
        return veide;
    }

    /**
     *
     * @param veide
     */
    public void setVeide(String veide) {
        this.veide = veide;
    }

    /**
     *
     * @return
     */
    public String getNeide() {
        return neide;
    }

    /**
     *
     * @param neide
     */
    public void setNeide(String neide) {
        this.neide = neide;
    }

    /**
     *
     * @return
     */
    public String getRgide() {
        return rgide;
    }

    /**
     *
     * @param rgide
     */
    public void setRgide(String rgide) {
        this.rgide = rgide;
    }

    /**
     *
     * @return
     */
    public String getEcide() {
        return ecide;
    }

    /**
     *
     * @param ecide
     */
    public void setEcide(String ecide) {
        this.ecide = ecide;
    }

    /**
     *
     * @return
     */
    public String getTside() {
        return tside;
    }

    /**
     *
     * @param tside
     */
    public void setTside(String tside) {
        this.tside = tside;
    }

    /**
     *
     * @return
     */
    public String getDside() {
        return dside;
    }

    /**
     *
     * @param dside
     */
    public void setDside(String dside) {
        this.dside = dside;
    }

    /**
     *
     * @return
     */
    public Date getFdide() {
        return fdide;
    }

    /**
     *
     * @param fdide
     */
    public void setFdide(Date fdide) {
        this.fdide = fdide;
    }

    /**
     *
     * @return
     */
    public String getDoide() {
        return doide;
    }

    /**
     *
     * @param doide
     */
    public void setDoide(String doide) {
        this.doide = doide;
    }

    /**
     *
     * @return
     */
    public String getAlide() {
        return alide;
    }

    /**
     *
     * @param alide
     */
    public void setAlide(String alide) {
        this.alide = alide;
    }

    /**
     *
     * @return
     */
    public Float getKgide() {
        return kgide;
    }

    /**
     *
     * @param kgide
     */
    public void setKgide(Float kgide) {
        this.kgide = kgide;
    }

    /**
     *
     * @return
     */
    public Date getFkide() {
        return fkide;
    }

    /**
     *
     * @param fkide
     */
    public void setFkide(Date fkide) {
        this.fkide = fkide;
    }

    /**
     *
     * @return
     */
    public Float getEside() {
        return eside;
    }

    /**
     *
     * @param eside
     */
    public void setEside(Float eside) {
        this.eside = eside;
    }

    /**
     *
     * @return
     */
    public Date getFmide() {
        return fmide;
    }

    /**
     *
     * @param fmide
     */
    public void setFmide(Date fmide) {
        this.fmide = fmide;
    }

    /**
     *
     * @return
     */
    public String getCcide() {
        return ccide;
    }

    /**
     *
     * @param ccide
     */
    public void setCcide(String ccide) {
        this.ccide = ccide;
    }

    /**
     *
     * @return
     */
    public String getPpide() {
        return ppide;
    }

    /**
     *
     * @param ppide
     */
    public void setPpide(String ppide) {
        this.ppide = ppide;
    }

    /**
     *
     * @return
     */
    public String getZaide() {
        return zaide;
    }

    /**
     *
     * @param zaide
     */
    public void setZaide(String zaide) {
        this.zaide = zaide;
    }

    /**
     *
     * @return
     */
    public String getNlide() {
        return nlide;
    }

    /**
     *
     * @param nlide
     */
    public void setNlide(String nlide) {
        this.nlide = nlide;
    }

    /**
     *
     * @return
     */
    public Date getVlide() {
        return vlide;
    }

    /**
     *
     * @param vlide
     */
    public void setVlide(Date vlide) {
        this.vlide = vlide;
    }

    /**
     *
     * @return
     */
    public String getElide() {
        return elide;
    }

    /**
     *
     * @param elide
     */
    public void setElide(String elide) {
        this.elide = elide;
    }

    /**
     *
     * @return
     */
    public String getApide() {
        return apide;
    }

    /**
     *
     * @param apide
     */
    public void setApide(String apide) {
        this.apide = apide;
    }

    /**
     *
     * @return
     */
    public String getMtide() {
        return mtide;
    }

    /**
     *
     * @param mtide
     */
    public void setMtide(String mtide) {
        this.mtide = mtide;
    }

    /**
     *
     * @return
     */
    public String getOtide() {
        return otide;
    }

    /**
     *
     * @param otide
     */
    public void setOtide(String otide) {
        this.otide = otide;
    }

    /**
     *
     * @return
     */
    public Short getAoide() {
        return aoide;
    }

    /**
     *
     * @param aoide
     */
    public void setAoide(Short aoide) {
        this.aoide = aoide;
    }

    /**
     *
     * @return
     */
    public String getPlide() {
        return plide;
    }

    /**
     *
     * @param plide
     */
    public void setPlide(String plide) {
        this.plide = plide;
    }

    /**
     *
     * @return
     */
    public Short getIiide() {
        return iiide;
    }

    /**
     *
     * @param iiide
     */
    public void setIiide(Short iiide) {
        this.iiide = iiide;
    }

    /**
     *
     * @return
     */
    public Short getIfide() {
        return ifide;
    }

    /**
     *
     * @param ifide
     */
    public void setIfide(Short ifide) {
        this.ifide = ifide;
    }

    /**
     *
     * @return
     */
    public String getOlide() {
        return olide;
    }

    /**
     *
     * @param olide
     */
    public void setOlide(String olide) {
        this.olide = olide;
    }

    /**
     *
     * @return
     */
    public Short getIoide() {
        return ioide;
    }

    /**
     *
     * @param ioide
     */
    public void setIoide(Short ioide) {
        this.ioide = ioide;
    }

    /**
     *
     * @return
     */
    public String getA1ide() {
        return a1ide;
    }

    /**
     *
     * @param a1ide
     */
    public void setA1ide(String a1ide) {
        this.a1ide = a1ide;
    }

    /**
     *
     * @return
     */
    public String getA2ide() {
        return a2ide;
    }

    /**
     *
     * @param a2ide
     */
    public void setA2ide(String a2ide) {
        this.a2ide = a2ide;
    }

    /**
     *
     * @return
     */
    public String getStide() {
        return stide;
    }

    /**
     *
     * @param stide
     */
    public void setStide(String stide) {
        this.stide = stide;
    }

    /**
     *
     * @return
     */
    public Date getFeide() {
        return feide;
    }

    /**
     *
     * @param feide
     */
    public void setFeide(Date feide) {
        this.feide = feide;
    }

    /**
     *
     * @return
     */
    public String getUside() {
        return uside;
    }

    /**
     *
     * @param uside
     */
    public void setUside(String uside) {
        this.uside = uside;
    }

    /**
     *
     * @return
     */
    public String getDiide() {
        return diide;
    }

    /**
     *
     * @param diide
     */
    public void setDiide(String diide) {
        this.diide = diide;
    }

    /**
     *
     * @return
     */
    public Integer getIdide() {
        return idide;
    }

    /**
     *
     * @param idide
     */
    public void setIdide(Integer idide) {
        this.idide = idide;
    }

    /**
     *
     * @return
     */
    public int getNtide() {
        return ntide;
    }

    /**
     *
     * @param ntide
     */
    public void setNtide(int ntide) {
        this.ntide = ntide;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idide != null ? idide.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ident49)) {
            return false;
        }
        Ident49 other = (Ident49) object;
        if ((this.idide == null && other.idide != null) || (this.idide != null && !this.idide.equals(other.idide))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return EntityToString.getString(this, this.getClass());
        //return "com.hrm.sirhapp.model.Ident49[ idide=" + idide + " ]";
    }

}

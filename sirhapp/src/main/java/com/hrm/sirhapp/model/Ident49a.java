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
@Table(name = "IDENT49A")
@NamedQueries({
    @NamedQuery(name = "Ident49a.findAll", query = "SELECT i FROM Ident49a i")})
public class Ident49a implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDIDE_A")
    private Integer idideA;
    @Size(max = 70)
    @Column(name = "DIIDE_A")
    private String diideA;
    @NotNull
    @Size(min = 1, max = 13)
    @Column(name = "RFIDE_A")
    private String rfideA;
    @Size(max = 13)
    @Column(name = "CUIDE_A")
    private String cuideA;
    @Size(max = 11)
    @Column(name = "RIIDE_A")
    private String riideA;
    @Size(max = 70)
    @Column(name = "UIIDE_A")
    private String uiideA;
    @Size(max = 70)
    @Column(name = "CEIDE_A")
    private String ceideA;
    @Size(max = 2)
    @Column(name = "EEIDE_A")
    private String eeideA;
    @Size(max = 3)
    @Column(name = "MEIDE_A")
    private String meideA;
    @Size(max = 4)
    @Column(name = "LEIDE_A")
    private String leideA;
    @Size(max = 4)
    @Column(name = "SIIDE_A")
    private String siideA;
    @Size(max = 4)
    @Column(name = "EMIDE_A")
    private String emideA;
    @Size(max = 4)
    @Column(name = "VEIDE_A")
    private String veideA;
    @Size(max = 15)
    @Column(name = "NEIDE_A")
    private String neideA;
    @Size(max = 70)
    @Column(name = "RGIDE_A")
    private String rgideA;
    @Size(max = 70)
    @Column(name = "ECIDE_A")
    private String ecideA;
    @Size(max = 6)
    @Column(name = "TSIDE_A")
    private String tsideA;
    @Size(max = 2)
    @Column(name = "DSIDE_A")
    private String dsideA;
    @Column(name = "FDIDE_A")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fdideA;
    @Size(max = 2)
    @Column(name = "DOIDE_A")
    private String doideA;
    @Size(max = 70)
    @Column(name = "ALIDE_A")
    private String alideA;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "KGIDE_A")
    private Float kgideA;
    @Column(name = "FKIDE_A")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fkideA;
    @Column(name = "ESIDE_A")
    private Float esideA;
    @Column(name = "FMIDE_A")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fmideA;
    @Size(max = 4)
    @Column(name = "CCIDE_A")
    private String ccideA;
    @Size(max = 4)
    @Column(name = "PPIDE_A")
    private String ppideA;
    @Size(max = 4)
    @Column(name = "ZAIDE_A")
    private String zaideA;
    @Size(max = 70)
    @Column(name = "NLIDE_A")
    private String nlideA;
    @Column(name = "VLIDE_A")
    @Temporal(TemporalType.TIMESTAMP)
    private Date vlideA;
    @Size(max = 70)
    @Column(name = "ELIDE_A")
    private String elideA;
    @Size(max = 2)
    @Column(name = "APIDE_A")
    private String apideA;
    @Size(max = 70)
    @Column(name = "MTIDE_A")
    private String mtideA;
    @Size(max = 70)
    @Column(name = "OTIDE_A")
    private String otideA;
    @Column(name = "AOIDE_A")
    private Short aoideA;
    @Size(max = 8)
    @Column(name = "PLIDE_A")
    private String plideA;
    @Column(name = "IIIDE_A")
    private Short iiideA;
    @Column(name = "IFIDE_A")
    private Short ifideA;
    @Size(max = 70)
    @Column(name = "OLIDE_A")
    private String olideA;
    @Column(name = "IOIDE_A")
    private Short ioideA;
    @Size(max = 70)
    @Column(name = "A1IDE_A")
    private String a1ideA;
    @Size(max = 70)
    @Column(name = "A2IDE_A")
    private String a2ideA;
    @Size(max = 2)
    @Column(name = "STIDE_A")
    private String stideA;
    @Column(name = "FEIDE_A")
    @Temporal(TemporalType.TIMESTAMP)
    private Date feideA;
    @Size(max = 10)
    @Column(name = "USIDE_A")
    private String usideA;

    /**
     *
     */
    public Ident49a() {
    }

    /**
     *
     * @param idideA
     */
    public Ident49a(Integer idideA) {
        this.idideA = idideA;
    }

    /**
     *
     * @param idideA
     * @param rfideA
     */
    public Ident49a(Integer idideA, String rfideA) {
        this.idideA = idideA;
        this.rfideA = rfideA;
    }

    /**
     *
     * @return
     */
    public String getRfideA() {
        return rfideA;
    }

    /**
     *
     * @param rfideA
     */
    public void setRfideA(String rfideA) {
        this.rfideA = rfideA;
    }

    /**
     *
     * @return
     */
    public String getCuideA() {
        return cuideA;
    }

    /**
     *
     * @param cuideA
     */
    public void setCuideA(String cuideA) {
        this.cuideA = cuideA;
    }

    /**
     *
     * @return
     */
    public String getRiideA() {
        return riideA;
    }

    /**
     *
     * @param riideA
     */
    public void setRiideA(String riideA) {
        this.riideA = riideA;
    }

    /**
     *
     * @return
     */
    public String getUiideA() {
        return uiideA;
    }

    /**
     *
     * @param uiideA
     */
    public void setUiideA(String uiideA) {
        this.uiideA = uiideA;
    }

    /**
     *
     * @return
     */
    public String getCeideA() {
        return ceideA;
    }

    /**
     *
     * @param ceideA
     */
    public void setCeideA(String ceideA) {
        this.ceideA = ceideA;
    }

    /**
     *
     * @return
     */
    public String getEeideA() {
        return eeideA;
    }

    /**
     *
     * @param eeideA
     */
    public void setEeideA(String eeideA) {
        this.eeideA = eeideA;
    }

    /**
     *
     * @return
     */
    public String getMeideA() {
        return meideA;
    }

    /**
     *
     * @param meideA
     */
    public void setMeideA(String meideA) {
        this.meideA = meideA;
    }

    /**
     *
     * @return
     */
    public String getLeideA() {
        return leideA;
    }

    /**
     *
     * @param leideA
     */
    public void setLeideA(String leideA) {
        this.leideA = leideA;
    }

    /**
     *
     * @return
     */
    public String getSiideA() {
        return siideA;
    }

    /**
     *
     * @param siideA
     */
    public void setSiideA(String siideA) {
        this.siideA = siideA;
    }

    /**
     *
     * @return
     */
    public String getEmideA() {
        return emideA;
    }

    /**
     *
     * @param emideA
     */
    public void setEmideA(String emideA) {
        this.emideA = emideA;
    }

    /**
     *
     * @return
     */
    public String getVeideA() {
        return veideA;
    }

    /**
     *
     * @param veideA
     */
    public void setVeideA(String veideA) {
        this.veideA = veideA;
    }

    /**
     *
     * @return
     */
    public String getNeideA() {
        return neideA;
    }

    /**
     *
     * @param neideA
     */
    public void setNeideA(String neideA) {
        this.neideA = neideA;
    }

    /**
     *
     * @return
     */
    public String getRgideA() {
        return rgideA;
    }

    /**
     *
     * @param rgideA
     */
    public void setRgideA(String rgideA) {
        this.rgideA = rgideA;
    }

    /**
     *
     * @return
     */
    public String getEcideA() {
        return ecideA;
    }

    /**
     *
     * @param ecideA
     */
    public void setEcideA(String ecideA) {
        this.ecideA = ecideA;
    }

    /**
     *
     * @return
     */
    public String getTsideA() {
        return tsideA;
    }

    /**
     *
     * @param tsideA
     */
    public void setTsideA(String tsideA) {
        this.tsideA = tsideA;
    }

    /**
     *
     * @return
     */
    public String getDsideA() {
        return dsideA;
    }

    /**
     *
     * @param dsideA
     */
    public void setDsideA(String dsideA) {
        this.dsideA = dsideA;
    }

    /**
     *
     * @return
     */
    public Date getFdideA() {
        return fdideA;
    }

    /**
     *
     * @param fdideA
     */
    public void setFdideA(Date fdideA) {
        this.fdideA = fdideA;
    }

    /**
     *
     * @return
     */
    public String getDoideA() {
        return doideA;
    }

    /**
     *
     * @param doideA
     */
    public void setDoideA(String doideA) {
        this.doideA = doideA;
    }

    /**
     *
     * @return
     */
    public String getAlideA() {
        return alideA;
    }

    /**
     *
     * @param alideA
     */
    public void setAlideA(String alideA) {
        this.alideA = alideA;
    }

    /**
     *
     * @return
     */
    public Float getKgideA() {
        return kgideA;
    }

    /**
     *
     * @param kgideA
     */
    public void setKgideA(Float kgideA) {
        this.kgideA = kgideA;
    }

    /**
     *
     * @return
     */
    public Date getFkideA() {
        return fkideA;
    }

    /**
     *
     * @param fkideA
     */
    public void setFkideA(Date fkideA) {
        this.fkideA = fkideA;
    }

    /**
     *
     * @return
     */
    public Float getEsideA() {
        return esideA;
    }

    /**
     *
     * @param esideA
     */
    public void setEsideA(Float esideA) {
        this.esideA = esideA;
    }

    /**
     *
     * @return
     */
    public Date getFmideA() {
        return fmideA;
    }

    /**
     *
     * @param fmideA
     */
    public void setFmideA(Date fmideA) {
        this.fmideA = fmideA;
    }

    /**
     *
     * @return
     */
    public String getCcideA() {
        return ccideA;
    }

    /**
     *
     * @param ccideA
     */
    public void setCcideA(String ccideA) {
        this.ccideA = ccideA;
    }

    /**
     *
     * @return
     */
    public String getPpideA() {
        return ppideA;
    }

    /**
     *
     * @param ppideA
     */
    public void setPpideA(String ppideA) {
        this.ppideA = ppideA;
    }

    /**
     *
     * @return
     */
    public String getZaideA() {
        return zaideA;
    }

    /**
     *
     * @param zaideA
     */
    public void setZaideA(String zaideA) {
        this.zaideA = zaideA;
    }

    /**
     *
     * @return
     */
    public String getNlideA() {
        return nlideA;
    }

    /**
     *
     * @param nlideA
     */
    public void setNlideA(String nlideA) {
        this.nlideA = nlideA;
    }

    /**
     *
     * @return
     */
    public Date getVlideA() {
        return vlideA;
    }

    /**
     *
     * @param vlideA
     */
    public void setVlideA(Date vlideA) {
        this.vlideA = vlideA;
    }

    /**
     *
     * @return
     */
    public String getElideA() {
        return elideA;
    }

    /**
     *
     * @param elideA
     */
    public void setElideA(String elideA) {
        this.elideA = elideA;
    }

    /**
     *
     * @return
     */
    public String getApideA() {
        return apideA;
    }

    /**
     *
     * @param apideA
     */
    public void setApideA(String apideA) {
        this.apideA = apideA;
    }

    /**
     *
     * @return
     */
    public String getMtideA() {
        return mtideA;
    }

    /**
     *
     * @param mtideA
     */
    public void setMtideA(String mtideA) {
        this.mtideA = mtideA;
    }

    /**
     *
     * @return
     */
    public String getOtideA() {
        return otideA;
    }

    /**
     *
     * @param otideA
     */
    public void setOtideA(String otideA) {
        this.otideA = otideA;
    }

    /**
     *
     * @return
     */
    public Short getAoideA() {
        return aoideA;
    }

    /**
     *
     * @param aoideA
     */
    public void setAoideA(Short aoideA) {
        this.aoideA = aoideA;
    }

    /**
     *
     * @return
     */
    public String getPlideA() {
        return plideA;
    }

    /**
     *
     * @param plideA
     */
    public void setPlideA(String plideA) {
        this.plideA = plideA;
    }

    /**
     *
     * @return
     */
    public Short getIiideA() {
        return iiideA;
    }

    /**
     *
     * @param iiideA
     */
    public void setIiideA(Short iiideA) {
        this.iiideA = iiideA;
    }

    /**
     *
     * @return
     */
    public Short getIfideA() {
        return ifideA;
    }

    /**
     *
     * @param ifideA
     */
    public void setIfideA(Short ifideA) {
        this.ifideA = ifideA;
    }

    /**
     *
     * @return
     */
    public String getOlideA() {
        return olideA;
    }

    /**
     *
     * @param olideA
     */
    public void setOlideA(String olideA) {
        this.olideA = olideA;
    }

    /**
     *
     * @return
     */
    public Short getIoideA() {
        return ioideA;
    }

    /**
     *
     * @param ioideA
     */
    public void setIoideA(Short ioideA) {
        this.ioideA = ioideA;
    }

    /**
     *
     * @return
     */
    public String getA1ideA() {
        return a1ideA;
    }

    /**
     *
     * @param a1ideA
     */
    public void setA1ideA(String a1ideA) {
        this.a1ideA = a1ideA;
    }

    /**
     *
     * @return
     */
    public String getA2ideA() {
        return a2ideA;
    }

    /**
     *
     * @param a2ideA
     */
    public void setA2ideA(String a2ideA) {
        this.a2ideA = a2ideA;
    }

    /**
     *
     * @return
     */
    public String getStideA() {
        return stideA;
    }

    /**
     *
     * @param stideA
     */
    public void setStideA(String stideA) {
        this.stideA = stideA;
    }

    /**
     *
     * @return
     */
    public Date getFeideA() {
        return feideA;
    }

    /**
     *
     * @param feideA
     */
    public void setFeideA(Date feideA) {
        this.feideA = feideA;
    }

    /**
     *
     * @return
     */
    public String getUsideA() {
        return usideA;
    }

    /**
     *
     * @param usideA
     */
    public void setUsideA(String usideA) {
        this.usideA = usideA;
    }

    /**
     *
     * @return
     */
    public String getDiideA() {
        return diideA;
    }

    /**
     *
     * @param diideA
     */
    public void setDiideA(String diideA) {
        this.diideA = diideA;
    }

    /**
     *
     * @return
     */
    public Integer getIdideA() {
        return idideA;
    }

    /**
     *
     * @param idideA
     */
    public void setIdideA(Integer idideA) {
        this.idideA = idideA;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idideA != null ? idideA.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ident49a)) {
            return false;
        }
        Ident49a other = (Ident49a) object;
        if ((this.idideA == null && other.idideA != null) || (this.idideA != null && !this.idideA.equals(other.idideA))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return EntityToString.getString(this, this.getClass());
        //return "com.hrm.sirhapp.model.Ident49a[ idideA=" + idideA + " ]";
    }

}

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
@Table(name = "FAMIL32")
@NamedQueries({
    @NamedQuery(name = "Famil32.findAll", query = "SELECT f FROM Famil32 f")})
public class Famil32 implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDFAM")
    private Integer idfam;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NTFAM")
    private int ntfam;
    @Size(max = 70)
    @Column(name = "TPFAM")
    private String tpfam;
    @Size(max = 6)
    @Column(name = "W1FAM")
    private String w1fam;
    @Column(name = "ORFAM")
    private Short orfam;
    @Size(max = 70)
    @Column(name = "APFAM")
    private String apfam;
    @Size(max = 70)
    @Column(name = "AMFAM")
    private String amfam;
    @Size(max = 70)
    @Column(name = "NOFAM")
    private String nofam;
    @Size(max = 2)
    @Column(name = "VIFAM")
    private String vifam;
    @Size(max = 70)
    @Column(name = "SEFAM")
    private String sefam;
    @Column(name = "FNFAM")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fnfam;
    @Size(max = 70)
    @Column(name = "OFFAM")
    private String offam;
    @Size(max = 2)
    @Column(name = "VMFAM")
    private String vmfam;
    @Size(max = 70)
    @Column(name = "PAFAM")
    private String pafam;
    @Size(max = 70)
    @Column(name = "ESFAM")
    private String esfam;
    @Size(max = 70)
    @Column(name = "MDFAM")
    private String mdfam;
    @Size(max = 70)
    @Column(name = "LOFAM")
    private String lofam;
    @Size(max = 70)
    @Column(name = "COFAM")
    private String cofam;
    @Size(max = 70)
    @Column(name = "CAFAM")
    private String cafam;
    @Size(max = 5)
    @Column(name = "CPFAM")
    private String cpfam;
    @Size(max = 15)
    @Column(name = "TDFAM")
    private String tdfam;
    @Size(max = 15)
    @Column(name = "TCFAM")
    private String tcfam;
    @Size(max = 70)
    @Column(name = "CEFAM")
    private String cefam;
    @Size(max = 70)
    @Column(name = "TIFAM")
    private String tifam;
    @Size(max = 6)
    @Column(name = "W2FAM")
    private String w2fam;
    @Size(max = 70)
    @Column(name = "PTFAM")
    private String ptfam;
    @Size(max = 250)
    @Column(name = "OBFAM")
    private String obfam;
    @Size(max = 2)
    @Column(name = "STFAM")
    private String stfam;
    @Column(name = "FEFAM")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fefam;
    @Size(max = 10)
    @Column(name = "USFAM")
    private String usfam;
    @Transient
    private String ncfam;

    /**
     *
     */
    public Famil32() {
    }

    /**
     *
     * @param idfam
     */
    public Famil32(Integer idfam) {
        this.idfam = idfam;
    }

    /**
     *
     * @param idfam
     * @param ntfam
     */
    public Famil32(Integer idfam, int ntfam) {
        this.idfam = idfam;
        this.ntfam = ntfam;
    }

    /**
     *
     * @return
     */
    public String getNcfam() {
        String localNcfam = apfam + " " + amfam + " " + nofam;
        return localNcfam.toUpperCase();
    }

    /**
     *
     * @param ncfam
     */
    public void setNcfam(String ncfam) {
        String localNcfam = apfam + " " + amfam + " " + nofam;
        this.ncfam = localNcfam.toUpperCase();
    }

    /**
     *
     * @return
     */
    public Integer getIdfam() {
        return idfam;
    }

    /**
     *
     * @param idfam
     */
    public void setIdfam(Integer idfam) {
        this.idfam = idfam;
    }

    /**
     *
     * @return
     */
    public int getNtfam() {
        return ntfam;
    }

    /**
     *
     * @param ntfam
     */
    public void setNtfam(int ntfam) {
        this.ntfam = ntfam;
    }

    /**
     *
     * @return
     */
    public String getTpfam() {
        return tpfam;
    }

    /**
     *
     * @param tpfam
     */
    public void setTpfam(String tpfam) {
        this.tpfam = tpfam;
    }

    /**
     *
     * @return
     */
    public String getW1fam() {
        return w1fam;
    }

    /**
     *
     * @param w1fam
     */
    public void setW1fam(String w1fam) {
        this.w1fam = w1fam;
    }

    /**
     *
     * @return
     */
    public Short getOrfam() {
        return orfam;
    }

    /**
     *
     * @param orfam
     */
    public void setOrfam(Short orfam) {
        this.orfam = orfam;
    }

    /**
     *
     * @return
     */
    public String getApfam() {
        return apfam;
    }

    /**
     *
     * @param apfam
     */
    public void setApfam(String apfam) {
        this.apfam = apfam;
    }

    /**
     *
     * @return
     */
    public String getAmfam() {
        return amfam;
    }

    /**
     *
     * @param amfam
     */
    public void setAmfam(String amfam) {
        this.amfam = amfam;
    }

    /**
     *
     * @return
     */
    public String getNofam() {
        return nofam;
    }

    /**
     *
     * @param nofam
     */
    public void setNofam(String nofam) {
        this.nofam = nofam;
    }

    /**
     *
     * @return
     */
    public String getVifam() {
        return vifam;
    }

    /**
     *
     * @param vifam
     */
    public void setVifam(String vifam) {
        this.vifam = vifam;
    }

    /**
     *
     * @return
     */
    public String getSefam() {
        return sefam;
    }

    /**
     *
     * @param sefam
     */
    public void setSefam(String sefam) {
        this.sefam = sefam;
    }

    /**
     *
     * @return
     */
    public Date getFnfam() {
        return fnfam;
    }

    /**
     *
     * @param fnfam
     */
    public void setFnfam(Date fnfam) {
        this.fnfam = fnfam;
    }

    /**
     *
     * @return
     */
    public String getOffam() {
        return offam;
    }

    /**
     *
     * @param offam
     */
    public void setOffam(String offam) {
        this.offam = offam;
    }

    /**
     *
     * @return
     */
    public String getVmfam() {
        return vmfam;
    }

    /**
     *
     * @param vmfam
     */
    public void setVmfam(String vmfam) {
        this.vmfam = vmfam;
    }

    /**
     *
     * @return
     */
    public String getPafam() {
        return pafam;
    }

    /**
     *
     * @param pafam
     */
    public void setPafam(String pafam) {
        this.pafam = pafam;
    }

    /**
     *
     * @return
     */
    public String getEsfam() {
        return esfam;
    }

    /**
     *
     * @param esfam
     */
    public void setEsfam(String esfam) {
        this.esfam = esfam;
    }

    /**
     *
     * @return
     */
    public String getMdfam() {
        return mdfam;
    }

    /**
     *
     * @param mdfam
     */
    public void setMdfam(String mdfam) {
        this.mdfam = mdfam;
    }

    /**
     *
     * @return
     */
    public String getLofam() {
        return lofam;
    }

    /**
     *
     * @param lofam
     */
    public void setLofam(String lofam) {
        this.lofam = lofam;
    }

    /**
     *
     * @return
     */
    public String getCofam() {
        return cofam;
    }

    /**
     *
     * @param cofam
     */
    public void setCofam(String cofam) {
        this.cofam = cofam;
    }

    /**
     *
     * @return
     */
    public String getCafam() {
        return cafam;
    }

    /**
     *
     * @param cafam
     */
    public void setCafam(String cafam) {
        this.cafam = cafam;
    }

    /**
     *
     * @return
     */
    public String getCpfam() {
        return cpfam;
    }

    /**
     *
     * @param cpfam
     */
    public void setCpfam(String cpfam) {
        this.cpfam = cpfam;
    }

    /**
     *
     * @return
     */
    public String getTdfam() {
        return tdfam;
    }

    /**
     *
     * @param tdfam
     */
    public void setTdfam(String tdfam) {
        this.tdfam = tdfam;
    }

    /**
     *
     * @return
     */
    public String getTcfam() {
        return tcfam;
    }

    /**
     *
     * @param tcfam
     */
    public void setTcfam(String tcfam) {
        this.tcfam = tcfam;
    }

    /**
     *
     * @return
     */
    public String getCefam() {
        return cefam;
    }

    /**
     *
     * @param cefam
     */
    public void setCefam(String cefam) {
        this.cefam = cefam;
    }

    /**
     *
     * @return
     */
    public String getTifam() {
        return tifam;
    }

    /**
     *
     * @param tifam
     */
    public void setTifam(String tifam) {
        this.tifam = tifam;
    }

    /**
     *
     * @return
     */
    public String getW2fam() {
        return w2fam;
    }

    /**
     *
     * @param w2fam
     */
    public void setW2fam(String w2fam) {
        this.w2fam = w2fam;
    }

    /**
     *
     * @return
     */
    public String getPtfam() {
        return ptfam;
    }

    /**
     *
     * @param ptfam
     */
    public void setPtfam(String ptfam) {
        this.ptfam = ptfam;
    }

    /**
     *
     * @return
     */
    public String getObfam() {
        return obfam;
    }

    /**
     *
     * @param obfam
     */
    public void setObfam(String obfam) {
        this.obfam = obfam;
    }

    /**
     *
     * @return
     */
    public String getStfam() {
        return stfam;
    }

    /**
     *
     * @param stfam
     */
    public void setStfam(String stfam) {
        this.stfam = stfam;
    }

    /**
     *
     * @return
     */
    public Date getFefam() {
        return fefam;
    }

    /**
     *
     * @param fefam
     */
    public void setFefam(Date fefam) {
        this.fefam = fefam;
    }

    /**
     *
     * @return
     */
    public String getUsfam() {
        return usfam;
    }

    /**
     *
     * @param usfam
     */
    public void setUsfam(String usfam) {
        this.usfam = usfam;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idfam != null ? idfam.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Famil32)) {
            return false;
        }
        Famil32 other = (Famil32) object;
        if ((this.idfam == null && other.idfam != null) || (this.idfam != null && !this.idfam.equals(other.idfam))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return EntityToString.getString(this, this.getClass());
        //return "com.hrm.sirhapp.model.Famil32[ idfam=" + idfam + " ]";
    }

}

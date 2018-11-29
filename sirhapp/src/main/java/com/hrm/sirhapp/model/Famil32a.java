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
@Table(name = "FAMIL32A")
@NamedQueries({
    @NamedQuery(name = "Famil32a.findAll", query = "SELECT f FROM Famil32a f")})
public class Famil32a implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDFAM_A")
    private Integer idfamA;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 13)
    @Column(name = "RFFAM_A")
    private String rffamA;
    @Size(max = 70)
    @Column(name = "TPFAM_A")
    private String tpfamA;
    @Size(max = 6)
    @Column(name = "W1FAM_A")
    private String w1famA;
    @Column(name = "ORFAM_A")
    private Short orfamA;
    @Size(max = 70)
    @Column(name = "APFAM_A")
    private String apfamA;
    @Size(max = 70)
    @Column(name = "AMFAM_A")
    private String amfamA;
    @Size(max = 70)
    @Column(name = "NOFAM_A")
    private String nofamA;
    @Size(max = 2)
    @Column(name = "VIFAM_A")
    private String vifamA;
    @Size(max = 70)
    @Column(name = "SEFAM_A")
    private String sefamA;
    @Column(name = "FNFAM_A")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fnfamA;
    @Size(max = 70)
    @Column(name = "OFFAM_A")
    private String offamA;
    @Size(max = 2)
    @Column(name = "VMFAM_A")
    private String vmfamA;
    @Size(max = 70)
    @Column(name = "PAFAM_A")
    private String pafamA;
    @Size(max = 70)
    @Column(name = "ESFAM_A")
    private String esfamA;
    @Size(max = 70)
    @Column(name = "MDFAM_A")
    private String mdfamA;
    @Size(max = 70)
    @Column(name = "LOFAM_A")
    private String lofamA;
    @Size(max = 70)
    @Column(name = "COFAM_A")
    private String cofamA;
    @Size(max = 70)
    @Column(name = "CAFAM_A")
    private String cafamA;
    @Size(max = 5)
    @Column(name = "CPFAM_A")
    private String cpfamA;
    @Size(max = 15)
    @Column(name = "TDFAM_A")
    private String tdfamA;
    @Size(max = 15)
    @Column(name = "TCFAM_A")
    private String tcfamA;
    @Size(max = 70)
    @Column(name = "CEFAM_A")
    private String cefamA;
    @Size(max = 70)
    @Column(name = "TIFAM_A")
    private String tifamA;
    @Size(max = 6)
    @Column(name = "W2FAM_A")
    private String w2famA;
    @Size(max = 70)
    @Column(name = "PTFAM_A")
    private String ptfamA;
    @Size(max = 250)
    @Column(name = "OBFAM_A")
    private String obfamA;
    @Size(max = 2)
    @Column(name = "STFAM_A")
    private String stfamA;
    @Column(name = "FEFAM_A")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fefamA;
    @Size(max = 10)
    @Column(name = "USFAM_A")
    private String usfamA;
    @Transient
    private String ncfamA;

    /**
     *
     */
    public Famil32a() {
    }

    /**
     *
     * @param idfamA
     */
    public Famil32a(Integer idfamA) {
        this.idfamA = idfamA;
    }

    /**
     *
     * @param idfamA
     * @param rffamA
     */
    public Famil32a(Integer idfamA, String rffamA) {
        this.idfamA = idfamA;
        this.rffamA = rffamA;
    }

    /**
     *
     * @return
     */
    public String getNcfamA() {
        String localNcfamA = apfamA + " " + amfamA + " " + nofamA;
        return localNcfamA.toUpperCase();
    }

    /**
     *
     * @param ncfamA
     */
    public void setNcfamA(String ncfamA) {
        String localNcfamA = apfamA + " " + amfamA + " " + nofamA;
        this.ncfamA = localNcfamA.toUpperCase();
    }

    /**
     *
     * @return
     */
    public Integer getIdfamA() {
        return idfamA;
    }

    /**
     *
     * @param idfamA
     */
    public void setIdfamA(Integer idfamA) {
        this.idfamA = idfamA;
    }

    /**
     *
     * @return
     */
    public String getRffamA() {
        return rffamA;
    }

    /**
     *
     * @param rffamA
     */
    public void setRffamA(String rffamA) {
        this.rffamA = rffamA;
    }

    /**
     *
     * @return
     */
    public String getTpfamA() {
        return tpfamA;
    }

    /**
     *
     * @param tpfamA
     */
    public void setTpfamA(String tpfamA) {
        this.tpfamA = tpfamA;
    }

    /**
     *
     * @return
     */
    public String getW1famA() {
        return w1famA;
    }

    /**
     *
     * @param w1famA
     */
    public void setW1famA(String w1famA) {
        this.w1famA = w1famA;
    }

    /**
     *
     * @return
     */
    public Short getOrfamA() {
        return orfamA;
    }

    /**
     *
     * @param orfamA
     */
    public void setOrfamA(Short orfamA) {
        this.orfamA = orfamA;
    }

    /**
     *
     * @return
     */
    public String getApfamA() {
        return apfamA;
    }

    /**
     *
     * @param apfamA
     */
    public void setApfamA(String apfamA) {
        this.apfamA = apfamA;
    }

    /**
     *
     * @return
     */
    public String getAmfamA() {
        return amfamA;
    }

    /**
     *
     * @param amfamA
     */
    public void setAmfamA(String amfamA) {
        this.amfamA = amfamA;
    }

    /**
     *
     * @return
     */
    public String getNofamA() {
        return nofamA;
    }

    /**
     *
     * @param nofamA
     */
    public void setNofamA(String nofamA) {
        this.nofamA = nofamA;
    }

    /**
     *
     * @return
     */
    public String getVifamA() {
        return vifamA;
    }

    /**
     *
     * @param vifamA
     */
    public void setVifamA(String vifamA) {
        this.vifamA = vifamA;
    }

    /**
     *
     * @return
     */
    public String getSefamA() {
        return sefamA;
    }

    /**
     *
     * @param sefamA
     */
    public void setSefamA(String sefamA) {
        this.sefamA = sefamA;
    }

    /**
     *
     * @return
     */
    public Date getFnfamA() {
        return fnfamA;
    }

    /**
     *
     * @param fnfamA
     */
    public void setFnfamA(Date fnfamA) {
        this.fnfamA = fnfamA;
    }

    /**
     *
     * @return
     */
    public String getOffamA() {
        return offamA;
    }

    /**
     *
     * @param offamA
     */
    public void setOffamA(String offamA) {
        this.offamA = offamA;
    }

    /**
     *
     * @return
     */
    public String getVmfamA() {
        return vmfamA;
    }

    /**
     *
     * @param vmfamA
     */
    public void setVmfamA(String vmfamA) {
        this.vmfamA = vmfamA;
    }

    /**
     *
     * @return
     */
    public String getPafamA() {
        return pafamA;
    }

    /**
     *
     * @param pafamA
     */
    public void setPafamA(String pafamA) {
        this.pafamA = pafamA;
    }

    /**
     *
     * @return
     */
    public String getEsfamA() {
        return esfamA;
    }

    /**
     *
     * @param esfamA
     */
    public void setEsfamA(String esfamA) {
        this.esfamA = esfamA;
    }

    /**
     *
     * @return
     */
    public String getMdfamA() {
        return mdfamA;
    }

    /**
     *
     * @param mdfamA
     */
    public void setMdfamA(String mdfamA) {
        this.mdfamA = mdfamA;
    }

    /**
     *
     * @return
     */
    public String getLofamA() {
        return lofamA;
    }

    /**
     *
     * @param lofamA
     */
    public void setLofamA(String lofamA) {
        this.lofamA = lofamA;
    }

    /**
     *
     * @return
     */
    public String getCofamA() {
        return cofamA;
    }

    /**
     *
     * @param cofamA
     */
    public void setCofamA(String cofamA) {
        this.cofamA = cofamA;
    }

    /**
     *
     * @return
     */
    public String getCafamA() {
        return cafamA;
    }

    /**
     *
     * @param cafamA
     */
    public void setCafamA(String cafamA) {
        this.cafamA = cafamA;
    }

    /**
     *
     * @return
     */
    public String getCpfamA() {
        return cpfamA;
    }

    /**
     *
     * @param cpfamA
     */
    public void setCpfamA(String cpfamA) {
        this.cpfamA = cpfamA;
    }

    /**
     *
     * @return
     */
    public String getTdfamA() {
        return tdfamA;
    }

    /**
     *
     * @param tdfamA
     */
    public void setTdfamA(String tdfamA) {
        this.tdfamA = tdfamA;
    }

    /**
     *
     * @return
     */
    public String getTcfamA() {
        return tcfamA;
    }

    /**
     *
     * @param tcfamA
     */
    public void setTcfamA(String tcfamA) {
        this.tcfamA = tcfamA;
    }

    /**
     *
     * @return
     */
    public String getCefamA() {
        return cefamA;
    }

    /**
     *
     * @param cefamA
     */
    public void setCefamA(String cefamA) {
        this.cefamA = cefamA;
    }

    /**
     *
     * @return
     */
    public String getTifamA() {
        return tifamA;
    }

    /**
     *
     * @param tifamA
     */
    public void setTifamA(String tifamA) {
        this.tifamA = tifamA;
    }

    /**
     *
     * @return
     */
    public String getW2famA() {
        return w2famA;
    }

    /**
     *
     * @param w2famA
     */
    public void setW2famA(String w2famA) {
        this.w2famA = w2famA;
    }

    /**
     *
     * @return
     */
    public String getPtfamA() {
        if (ptfamA == null) {
            this.ptfamA = "";
        }
        return ptfamA;
    }

    /**
     *
     * @param ptfamA
     */
    public void setPtfamA(String ptfamA) {
        this.ptfamA = ptfamA;
    }

    /**
     *
     * @return
     */
    public String getObfamA() {
        return obfamA;
    }

    /**
     *
     * @param obfamA
     */
    public void setObfamA(String obfamA) {
        this.obfamA = obfamA;
    }

    /**
     *
     * @return
     */
    public String getStfamA() {
        return stfamA;
    }

    /**
     *
     * @param stfamA
     */
    public void setStfamA(String stfamA) {
        this.stfamA = stfamA;
    }

    /**
     *
     * @return
     */
    public Date getFefamA() {
        return fefamA;
    }

    /**
     *
     * @param fefamA
     */
    public void setFefamA(Date fefamA) {
        this.fefamA = fefamA;
    }

    /**
     *
     * @return
     */
    public String getUsfamA() {
        return usfamA;
    }

    /**
     *
     * @param usfamA
     */
    public void setUsfamA(String usfamA) {
        this.usfamA = usfamA;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idfamA != null ? idfamA.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Famil32a)) {
            return false;
        }
        Famil32a other = (Famil32a) object;
        if ((this.idfamA == null && other.idfamA != null) || (this.idfamA != null && !this.idfamA.equals(other.idfamA))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return EntityToString.getString(this, this.getClass());
        //return "com.hrm.sirhapp.model.Famil32a[ idfamA=" + idfamA + " ]";
    }

}

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
@Table(name = "DATOS28A")
@NamedQueries({
    @NamedQuery(name = "Datos28a.findAll", query = "SELECT d FROM Datos28a d")})
public class Datos28a implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDDAT_A")
    private Integer iddatA;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 13)
    @Column(name = "RFDAT_A")
    private String rfdatA;
    @Size(max = 70)
    @Column(name = "ACDAT_A")
    private String acdatA;
    @Size(max = 15)
    @Column(name = "TADAT_A")
    private String tadatA;
    @Size(max = 70)
    @Column(name = "REDAT_A")
    private String redatA;
    @Size(max = 70)
    @Column(name = "PTDAT_A")
    private String ptdatA;
    @Size(max = 15)
    @Column(name = "TRDAT_A")
    private String trdatA;
    @Size(max = 18)
    @Column(name = "CUDAT_A")
    private String cudatA;
    @Size(max = 70)
    @Column(name = "PADAT_A")
    private String padatA;
    @Size(max = 70)
    @Column(name = "ESDAT_A")
    private String esdatA;
    @Size(max = 70)
    @Column(name = "MDDAT_A")
    private String mddatA;
    @Size(max = 70)
    @Column(name = "LODAT_A")
    private String lodatA;
    @Size(max = 70)
    @Column(name = "CODAT_A")
    private String codatA;
    @Size(max = 70)
    @Column(name = "CADAT_A")
    private String cadatA;
    @Size(max = 10)
    @Column(name = "NEDAT_A")
    private String nedatA;
    @Size(max = 10)
    @Column(name = "NIDAT_A")
    private String nidatA;
    @Size(max = 5)
    @Column(name = "CPDAT_A")
    private String cpdatA;
    @Size(max = 70)
    @Column(name = "R1DAT_A")
    private String r1datA;
    @Size(max = 70)
    @Column(name = "R2DAT_A")
    private String r2datA;
    @Size(max = 70)
    @Column(name = "THDAT_A")
    private String thdatA;
    @Size(max = 15)
    @Column(name = "TDDAT_A")
    private String tddatA;
    @Size(max = 15)
    @Column(name = "TCDAT_A")
    private String tcdatA;
    @Size(max = 70)
    @Column(name = "CEDAT_A")
    private String cedatA;
    @Size(max = 70)
    @Column(name = "PNDAT_A")
    private String pndatA;
    @Size(max = 70)
    @Column(name = "ENDAT_A")
    private String endatA;
    @Size(max = 70)
    @Column(name = "MNDAT_A")
    private String mndatA;
    @Size(max = 70)
    @Column(name = "LNDAT_A")
    private String lndatA;
    @Size(max = 70)
    @Column(name = "TPDAT_A")
    private String tpdatA;
    @Size(max = 70)
    @Column(name = "PRDAT_A")
    private String prdatA;
    @Size(max = 70)
    @Column(name = "F1DAT_A")
    private String f1datA;
    @Size(max = 70)
    @Column(name = "F2DAT_A")
    private String f2datA;
    @Size(max = 70)
    @Column(name = "F3DAT_A")
    private String f3datA;
    @Size(max = 2)
    @Column(name = "STDAT_A")
    private String stdatA;
    @Column(name = "FEDAT_A")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fedatA;
    @Size(max = 10)
    @Column(name = "USDAT_A")
    private String usdatA;

    /**
     *
     */
    public Datos28a() {
    }

    /**
     *
     * @param iddatA
     */
    public Datos28a(Integer iddatA) {
        this.iddatA = iddatA;
    }

    /**
     *
     * @param iddatA
     * @param rfdatA
     */
    public Datos28a(Integer iddatA, String rfdatA) {
        this.iddatA = iddatA;
        this.rfdatA = rfdatA;
    }

    /**
     *
     * @return
     */
    public String getRfdatA() {
        return rfdatA;
    }

    /**
     *
     * @param rfdatA
     */
    public void setRfdatA(String rfdatA) {
        this.rfdatA = rfdatA;
    }

    /**
     *
     * @return
     */
    public String getCudatA() {
        return cudatA;
    }

    /**
     *
     * @param cudatA
     */
    public void setCudatA(String cudatA) {
        this.cudatA = cudatA;
    }

    /**
     *
     * @return
     */
    public String getPadatA() {
        return padatA;
    }

    /**
     *
     * @param padatA
     */
    public void setPadatA(String padatA) {
        this.padatA = padatA;
    }

    /**
     *
     * @return
     */
    public String getEsdatA() {
        return esdatA;
    }

    /**
     *
     * @param esdatA
     */
    public void setEsdatA(String esdatA) {
        this.esdatA = esdatA;
    }

    /**
     *
     * @return
     */
    public String getMddatA() {
        return mddatA;
    }

    /**
     *
     * @param mddatA
     */
    public void setMddatA(String mddatA) {
        this.mddatA = mddatA;
    }

    /**
     *
     * @return
     */
    public String getLodatA() {
        return lodatA;
    }

    /**
     *
     * @param lodatA
     */
    public void setLodatA(String lodatA) {
        this.lodatA = lodatA;
    }

    /**
     *
     * @return
     */
    public String getCodatA() {
        return codatA;
    }

    /**
     *
     * @param codatA
     */
    public void setCodatA(String codatA) {
        this.codatA = codatA;
    }

    /**
     *
     * @return
     */
    public String getCadatA() {
        return cadatA;
    }

    /**
     *
     * @param cadatA
     */
    public void setCadatA(String cadatA) {
        this.cadatA = cadatA;
    }

    /**
     *
     * @return
     */
    public String getNedatA() {
        return nedatA;
    }

    /**
     *
     * @param nedatA
     */
    public void setNedatA(String nedatA) {
        this.nedatA = nedatA;
    }

    /**
     *
     * @return
     */
    public String getNidatA() {
        return nidatA;
    }

    /**
     *
     * @param nidatA
     */
    public void setNidatA(String nidatA) {
        this.nidatA = nidatA;
    }

    /**
     *
     * @return
     */
    public String getCpdatA() {
        return cpdatA;
    }

    /**
     *
     * @param cpdatA
     */
    public void setCpdatA(String cpdatA) {
        this.cpdatA = cpdatA;
    }

    /**
     *
     * @return
     */
    public String getR1datA() {
        return r1datA;
    }

    /**
     *
     * @param r1datA
     */
    public void setR1datA(String r1datA) {
        this.r1datA = r1datA;
    }

    /**
     *
     * @return
     */
    public String getR2datA() {
        return r2datA;
    }

    /**
     *
     * @param r2datA
     */
    public void setR2datA(String r2datA) {
        this.r2datA = r2datA;
    }

    /**
     *
     * @return
     */
    public String getThdatA() {
        return thdatA;
    }

    /**
     *
     * @param thdatA
     */
    public void setThdatA(String thdatA) {
        this.thdatA = thdatA;
    }

    /**
     *
     * @return
     */
    public String getTddatA() {
        return tddatA;
    }

    /**
     *
     * @param tddatA
     */
    public void setTddatA(String tddatA) {
        this.tddatA = tddatA;
    }

    /**
     *
     * @return
     */
    public String getTcdatA() {
        return tcdatA;
    }

    /**
     *
     * @param tcdatA
     */
    public void setTcdatA(String tcdatA) {
        this.tcdatA = tcdatA;
    }

    /**
     *
     * @return
     */
    public String getCedatA() {
        return cedatA;
    }

    /**
     *
     * @param cedatA
     */
    public void setCedatA(String cedatA) {
        this.cedatA = cedatA;
    }

    /**
     *
     * @return
     */
    public String getPndatA() {
        return pndatA;
    }

    /**
     *
     * @param pndatA
     */
    public void setPndatA(String pndatA) {
        this.pndatA = pndatA;
    }

    /**
     *
     * @return
     */
    public String getEndatA() {
        return endatA;
    }

    /**
     *
     * @param endatA
     */
    public void setEndatA(String endatA) {
        this.endatA = endatA;
    }

    /**
     *
     * @return
     */
    public String getMndatA() {
        return mndatA;
    }

    /**
     *
     * @param mndatA
     */
    public void setMndatA(String mndatA) {
        this.mndatA = mndatA;
    }

    /**
     *
     * @return
     */
    public String getLndatA() {
        return lndatA;
    }

    /**
     *
     * @param lndatA
     */
    public void setLndatA(String lndatA) {
        this.lndatA = lndatA;
    }

    /**
     *
     * @return
     */
    public String getTpdatA() {
        return tpdatA;
    }

    /**
     *
     * @param tpdatA
     */
    public void setTpdatA(String tpdatA) {
        this.tpdatA = tpdatA;
    }

    /**
     *
     * @return
     */
    public String getPrdatA() {
        return prdatA;
    }

    /**
     *
     * @param prdatA
     */
    public void setPrdatA(String prdatA) {
        this.prdatA = prdatA;
    }

    /**
     *
     * @return
     */
    public String getF1datA() {
        return f1datA;
    }

    /**
     *
     * @param f1datA
     */
    public void setF1datA(String f1datA) {
        this.f1datA = f1datA;
    }

    /**
     *
     * @return
     */
    public String getF2datA() {
        return f2datA;
    }

    /**
     *
     * @param f2datA
     */
    public void setF2datA(String f2datA) {
        this.f2datA = f2datA;
    }

    /**
     *
     * @return
     */
    public String getF3datA() {
        return f3datA;
    }

    /**
     *
     * @param f3datA
     */
    public void setF3datA(String f3datA) {
        this.f3datA = f3datA;
    }

    /**
     *
     * @return
     */
    public String getStdatA() {
        return stdatA;
    }

    /**
     *
     * @param stdatA
     */
    public void setStdatA(String stdatA) {
        this.stdatA = stdatA;
    }

    /**
     *
     * @return
     */
    public Date getFedatA() {
        return fedatA;
    }

    /**
     *
     * @param fedatA
     */
    public void setFedatA(Date fedatA) {
        this.fedatA = fedatA;
    }

    /**
     *
     * @return
     */
    public String getUsdatA() {
        return usdatA;
    }

    /**
     *
     * @param usdatA
     */
    public void setUsdatA(String usdatA) {
        this.usdatA = usdatA;
    }

    /**
     *
     * @return
     */
    public String getAcdatA() {
        return acdatA;
    }

    /**
     *
     * @param acdatA
     */
    public void setAcdatA(String acdatA) {
        this.acdatA = acdatA;
    }

    /**
     *
     * @return
     */
    public String getTadatA() {
        return tadatA;
    }

    /**
     *
     * @param tadatA
     */
    public void setTadatA(String tadatA) {
        this.tadatA = tadatA;
    }

    /**
     *
     * @return
     */
    public String getRedatA() {
        return redatA;
    }

    /**
     *
     * @param redatA
     */
    public void setRedatA(String redatA) {
        this.redatA = redatA;
    }

    /**
     *
     * @return
     */
    public String getPtdatA() {
        return ptdatA;
    }

    /**
     *
     * @param ptdatA
     */
    public void setPtdatA(String ptdatA) {
        this.ptdatA = ptdatA;
    }

    /**
     *
     * @return
     */
    public String getTrdatA() {
        return trdatA;
    }

    /**
     *
     * @param trdatA
     */
    public void setTrdatA(String trdatA) {
        this.trdatA = trdatA;
    }

    /**
     *
     * @return
     */
    public Integer getIddatA() {
        return iddatA;
    }

    /**
     *
     * @param iddatA
     */
    public void setIddatA(Integer iddatA) {
        this.iddatA = iddatA;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iddatA != null ? iddatA.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Datos28a)) {
            return false;
        }
        Datos28a other = (Datos28a) object;
        if ((this.iddatA == null && other.iddatA != null) || (this.iddatA != null && !this.iddatA.equals(other.iddatA))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return EntityToString.getString(this, this.getClass());
        //return "com.hrm.sirhapp.model.Datos28a[ iddatA=" + iddatA + " ]";
    }

}

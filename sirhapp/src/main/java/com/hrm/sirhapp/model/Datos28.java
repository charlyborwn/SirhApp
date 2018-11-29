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
@Table(name = "DATOS28")
@NamedQueries({
    @NamedQuery(name = "Datos28.findAll", query = "SELECT d FROM Datos28 d")})
public class Datos28 implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDDAT")
    private Integer iddat;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NTDAT")
    private int ntdat;
    @Size(max = 70)
    @Column(name = "ACDAT")
    private String acdat;
    @Size(max = 15)
    @Column(name = "TADAT")
    private String tadat;
    @Size(max = 70)
    @Column(name = "REDAT")
    private String redat;
    @Size(max = 70)
    @Column(name = "PTDAT")
    private String ptdat;
    @Size(max = 15)
    @Column(name = "TRDAT")
    private String trdat;
    @Size(max = 13)
    @Column(name = "RFDAT")
    private String rfdat;
    @Size(max = 70)
    @Column(name = "PADAT")
    private String padat;
    @Size(max = 70)
    @Column(name = "ESDAT")
    private String esdat;
    @Size(max = 70)
    @Column(name = "MDDAT")
    private String mddat;
    @Size(max = 70)
    @Column(name = "LODAT")
    private String lodat;
    @Size(max = 70)
    @Column(name = "CODAT")
    private String codat;
    @Size(max = 70)
    @Column(name = "CADAT")
    private String cadat;
    @Size(max = 10)
    @Column(name = "NEDAT")
    private String nedat;
    @Size(max = 10)
    @Column(name = "NIDAT")
    private String nidat;
    @Size(max = 5)
    @Column(name = "CPDAT")
    private String cpdat;
    @Size(max = 70)
    @Column(name = "R1DAT")
    private String r1dat;
    @Size(max = 70)
    @Column(name = "R2DAT")
    private String r2dat;
    @Size(max = 70)
    @Column(name = "THDAT")
    private String thdat;
    @Size(max = 15)
    @Column(name = "TDDAT")
    private String tddat;
    @Size(max = 15)
    @Column(name = "TCDAT")
    private String tcdat;
    @Size(max = 70)
    @Column(name = "CEDAT")
    private String cedat;
    @Size(max = 70)
    @Column(name = "PNDAT")
    private String pndat;
    @Size(max = 70)
    @Column(name = "ENDAT")
    private String endat;
    @Size(max = 70)
    @Column(name = "MNDAT")
    private String mndat;
    @Size(max = 70)
    @Column(name = "LNDAT")
    private String lndat;
    @Size(max = 70)
    @Column(name = "TPDAT")
    private String tpdat;
    @Size(max = 70)
    @Column(name = "PRDAT")
    private String prdat;
    @Size(max = 70)
    @Column(name = "F1DAT")
    private String f1dat;
    @Size(max = 70)
    @Column(name = "F2DAT")
    private String f2dat;
    @Size(max = 70)
    @Column(name = "F3DAT")
    private String f3dat;
    @Size(max = 2)
    @Column(name = "STDAT")
    private String stdat;
    @Column(name = "FEDAT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fedat;
    @Size(max = 10)
    @Column(name = "USDAT")
    private String usdat;

    /**
     *
     */
    public Datos28() {
    }

    /**
     *
     * @param iddat
     */
    public Datos28(Integer iddat) {
        this.iddat = iddat;
    }

    /**
     *
     * @param iddat
     * @param ntdat
     */
    public Datos28(Integer iddat, int ntdat) {
        this.iddat = iddat;
        this.ntdat = ntdat;
    }

    /**
     *
     * @return
     */
    public String getRfdat() {
        return rfdat;
    }

    /**
     *
     * @param rfdat
     */
    public void setRfdat(String rfdat) {
        this.rfdat = rfdat;
    }

    /**
     *
     * @return
     */
    public String getPadat() {
        return padat;
    }

    /**
     *
     * @param padat
     */
    public void setPadat(String padat) {
        this.padat = padat;
    }

    /**
     *
     * @return
     */
    public String getEsdat() {
        return esdat;
    }

    /**
     *
     * @param esdat
     */
    public void setEsdat(String esdat) {
        this.esdat = esdat;
    }

    /**
     *
     * @return
     */
    public String getMddat() {
        return mddat;
    }

    /**
     *
     * @param mddat
     */
    public void setMddat(String mddat) {
        this.mddat = mddat;
    }

    /**
     *
     * @return
     */
    public String getLodat() {
        return lodat;
    }

    /**
     *
     * @param lodat
     */
    public void setLodat(String lodat) {
        this.lodat = lodat;
    }

    /**
     *
     * @return
     */
    public String getCodat() {
        return codat;
    }

    /**
     *
     * @param codat
     */
    public void setCodat(String codat) {
        this.codat = codat;
    }

    /**
     *
     * @return
     */
    public String getCadat() {
        return cadat;
    }

    /**
     *
     * @param cadat
     */
    public void setCadat(String cadat) {
        this.cadat = cadat;
    }

    /**
     *
     * @return
     */
    public String getNedat() {
        return nedat;
    }

    /**
     *
     * @param nedat
     */
    public void setNedat(String nedat) {
        this.nedat = nedat;
    }

    /**
     *
     * @return
     */
    public String getNidat() {
        return nidat;
    }

    /**
     *
     * @param nidat
     */
    public void setNidat(String nidat) {
        this.nidat = nidat;
    }

    /**
     *
     * @return
     */
    public String getCpdat() {
        return cpdat;
    }

    /**
     *
     * @param cpdat
     */
    public void setCpdat(String cpdat) {
        this.cpdat = cpdat;
    }

    /**
     *
     * @return
     */
    public String getR1dat() {
        return r1dat;
    }

    /**
     *
     * @param r1dat
     */
    public void setR1dat(String r1dat) {
        this.r1dat = r1dat;
    }

    /**
     *
     * @return
     */
    public String getR2dat() {
        return r2dat;
    }

    /**
     *
     * @param r2dat
     */
    public void setR2dat(String r2dat) {
        this.r2dat = r2dat;
    }

    /**
     *
     * @return
     */
    public String getThdat() {
        return thdat;
    }

    /**
     *
     * @param thdat
     */
    public void setThdat(String thdat) {
        this.thdat = thdat;
    }

    /**
     *
     * @return
     */
    public String getTddat() {
        return tddat;
    }

    /**
     *
     * @param tddat
     */
    public void setTddat(String tddat) {
        this.tddat = tddat;
    }

    /**
     *
     * @return
     */
    public String getTcdat() {
        return tcdat;
    }

    /**
     *
     * @param tcdat
     */
    public void setTcdat(String tcdat) {
        this.tcdat = tcdat;
    }

    /**
     *
     * @return
     */
    public String getCedat() {
        return cedat;
    }

    /**
     *
     * @param cedat
     */
    public void setCedat(String cedat) {
        this.cedat = cedat;
    }

    /**
     *
     * @return
     */
    public String getPndat() {
        return pndat;
    }

    /**
     *
     * @param pndat
     */
    public void setPndat(String pndat) {
        this.pndat = pndat;
    }

    /**
     *
     * @return
     */
    public String getEndat() {
        return endat;
    }

    /**
     *
     * @param endat
     */
    public void setEndat(String endat) {
        this.endat = endat;
    }

    /**
     *
     * @return
     */
    public String getMndat() {
        return mndat;
    }

    /**
     *
     * @param mndat
     */
    public void setMndat(String mndat) {
        this.mndat = mndat;
    }

    /**
     *
     * @return
     */
    public String getLndat() {
        return lndat;
    }

    /**
     *
     * @param lndat
     */
    public void setLndat(String lndat) {
        this.lndat = lndat;
    }

    /**
     *
     * @return
     */
    public String getTpdat() {
        return tpdat;
    }

    /**
     *
     * @param tpdat
     */
    public void setTpdat(String tpdat) {
        this.tpdat = tpdat;
    }

    /**
     *
     * @return
     */
    public String getPrdat() {
        return prdat;
    }

    /**
     *
     * @param prdat
     */
    public void setPrdat(String prdat) {
        this.prdat = prdat;
    }

    /**
     *
     * @return
     */
    public String getF1dat() {
        return f1dat;
    }

    /**
     *
     * @param f1dat
     */
    public void setF1dat(String f1dat) {
        this.f1dat = f1dat;
    }

    /**
     *
     * @return
     */
    public String getF2dat() {
        return f2dat;
    }

    /**
     *
     * @param f2dat
     */
    public void setF2dat(String f2dat) {
        this.f2dat = f2dat;
    }

    /**
     *
     * @return
     */
    public String getF3dat() {
        return f3dat;
    }

    /**
     *
     * @param f3dat
     */
    public void setF3dat(String f3dat) {
        this.f3dat = f3dat;
    }

    /**
     *
     * @return
     */
    public String getStdat() {
        return stdat;
    }

    /**
     *
     * @param stdat
     */
    public void setStdat(String stdat) {
        this.stdat = stdat;
    }

    /**
     *
     * @return
     */
    public Date getFedat() {
        return fedat;
    }

    /**
     *
     * @param fedat
     */
    public void setFedat(Date fedat) {
        this.fedat = fedat;
    }

    /**
     *
     * @return
     */
    public String getUsdat() {
        return usdat;
    }

    /**
     *
     * @param usdat
     */
    public void setUsdat(String usdat) {
        this.usdat = usdat;
    }

    /**
     *
     * @return
     */
    public String getAcdat() {
        return acdat;
    }

    /**
     *
     * @param acdat
     */
    public void setAcdat(String acdat) {
        this.acdat = acdat;
    }

    /**
     *
     * @return
     */
    public String getTadat() {
        return tadat;
    }

    /**
     *
     * @param tadat
     */
    public void setTadat(String tadat) {
        this.tadat = tadat;
    }

    /**
     *
     * @return
     */
    public String getRedat() {
        return redat;
    }

    /**
     *
     * @param redat
     */
    public void setRedat(String redat) {
        this.redat = redat;
    }

    /**
     *
     * @return
     */
    public String getPtdat() {
        return ptdat;
    }

    /**
     *
     * @param ptdat
     */
    public void setPtdat(String ptdat) {
        this.ptdat = ptdat;
    }

    /**
     *
     * @return
     */
    public String getTrdat() {
        return trdat;
    }

    /**
     *
     * @param trdat
     */
    public void setTrdat(String trdat) {
        this.trdat = trdat;
    }

    /**
     *
     * @return
     */
    public Integer getIddat() {
        return iddat;
    }

    /**
     *
     * @param iddat
     */
    public void setIddat(Integer iddat) {
        this.iddat = iddat;
    }

    /**
     *
     * @return
     */
    public int getNtdat() {
        return ntdat;
    }

    /**
     *
     * @param ntdat
     */
    public void setNtdat(int ntdat) {
        this.ntdat = ntdat;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iddat != null ? iddat.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Datos28)) {
            return false;
        }
        Datos28 other = (Datos28) object;
        if ((this.iddat == null && other.iddat != null) || (this.iddat != null && !this.iddat.equals(other.iddat))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return EntityToString.getString(this, this.getClass());
        //return "com.hrm.sirhapp.model.Datos28[ iddat=" + iddat + " ]";
    }

}

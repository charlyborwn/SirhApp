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
import org.apache.commons.io.FilenameUtils;

/**
 *
 * @author Carlos Bernal carlose.bernalh@gmail.com
 * Copyright 2018 Carlos Ernesto Bernal Hernández.
 * SirhApp
 * 
 */
@Entity
@Table(name = "SANCI35")
@NamedQueries({
    @NamedQuery(name = "Sanci35.findAll", query = "SELECT s FROM Sanci35 s")})
public class Sanci35 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDSAN")
    private Integer idsan;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NTSAN")
    private int ntsan;
    @Size(max = 70)
    @Column(name = "NNSAN")
    private String nnsan;
    @Size(max = 13)
    @Column(name = "RFSAN")
    private String rfsan;
    @Size(max = 250)
    @Column(name = "PFSAN")
    private String pfsan;
    @Size(max = 70)
    @Column(name = "SSSAN")
    private String sssan;
    @Column(name = "FSSAN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fssan;
    @Size(max = 15)
    @Column(name = "NUSAN")
    private String nusan;
    @Size(max = 20)
    @Column(name = "ESSAN")
    private String essan;
    @Size(max = 10)
    @Column(name = "CESAN")
    private String cesan;
    @Size(max = 70)
    @Column(name = "NESAN")
    private String nesan;
    @Size(max = 10)
    @Column(name = "NDSAN")
    private String ndsan;
    @Size(max = 70)
    @Column(name = "DESAN")
    private String desan;
    @Size(max = 10)
    @Column(name = "CSSAN")
    private String cssan;
    @Size(max = 70)
    @Column(name = "SESAN")
    private String sesan;
    @Size(max = 10)
    @Column(name = "CCSAN")
    private String ccsan;
    @Size(max = 70)
    @Column(name = "CASAN")
    private String casan;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "SXSAN")
    private Float sxsan;
    @Size(max = 6)
    @Column(name = "CTSAN")
    private String ctsan;
    @Size(max = 70)
    @Column(name = "NOSAN")
    private String nosan;
    @Size(max = 6)
    @Column(name = "TCSAN")
    private String tcsan;
    @Size(max = 70)
    @Column(name = "COSAN")
    private String cosan;
    @Column(name = "ICSAN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date icsan;
    @Column(name = "FCSAN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fcsan;
    @Size(max = 70)
    @Column(name = "CUSAN")
    private String cusan;
    @Size(max = 6)
    @Column(name = "W1SAN")
    private String w1san;
    @Size(max = 250)
    @Column(name = "DHSAN")
    private String dhsan;
    @Column(name = "FHSAN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fhsan;
    @Column(name = "HHSAN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date hhsan;
    @Size(max = 70)
    @Column(name = "SGSAN")
    private String sgsan;
    @Column(name = "DISAN")
    private Integer disan;
    @Column(name = "FISAN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fisan;
    @Column(name = "FTSAN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ftsan;
    @Size(max = 10)
    @Column(name = "CVSAN")
    private String cvsan;
    @Size(max = 70)
    @Column(name = "NPSAN")
    private String npsan;
    @Size(max = 250)
    @Column(name = "PTSAN")
    private String ptsan;
    @Size(max = 2)
    @Column(name = "STSAN")
    private String stsan;
    @Column(name = "FESAN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fesan;
    @Size(max = 10)
    @Column(name = "USSAN")
    private String ussan;

    @Transient
    private Boolean f1san;

    /**
     *
     */
    public Sanci35() {
    }

    /**
     *
     * @param idsan
     */
    public Sanci35(Integer idsan) {
        this.idsan = idsan;
    }

    /**
     *
     * @param idsan
     * @param ntsan
     */
    public Sanci35(Integer idsan, int ntsan) {
        this.idsan = idsan;
        this.ntsan = ntsan;
    }

    /**
     *
     * @return
     */
    public Integer getIdsan() {
        return idsan;
    }

    /**
     *
     * @param idsan
     */
    public void setIdsan(Integer idsan) {
        this.idsan = idsan;
    }

    /**
     *
     * @return
     */
    public int getNtsan() {
        return ntsan;
    }

    /**
     *
     * @param ntsan
     */
    public void setNtsan(int ntsan) {
        this.ntsan = ntsan;
    }

    /**
     *
     * @return
     */
    public String getNnsan() {
        return nnsan;
    }

    /**
     *
     * @param nnsan
     */
    public void setNnsan(String nnsan) {
        this.nnsan = nnsan;
    }

    /**
     *
     * @return
     */
    public String getRfsan() {
        return rfsan;
    }

    /**
     *
     * @param rfsan
     */
    public void setRfsan(String rfsan) {
        this.rfsan = rfsan;
    }

    /**
     *
     * @return
     */
    public String getPfsan() {
        return pfsan;
    }

    /**
     *
     * @param pfsan
     */
    public void setPfsan(String pfsan) {
        this.pfsan = pfsan;
    }

    /**
     *
     * @return
     */
    public String getSssan() {
        return sssan;
    }

    /**
     *
     * @param sssan
     */
    public void setSssan(String sssan) {
        this.sssan = sssan;
    }

    /**
     *
     * @return
     */
    public Date getFssan() {
        return fssan;
    }

    /**
     *
     * @param fssan
     */
    public void setFssan(Date fssan) {
        this.fssan = fssan;
    }

    /**
     *
     * @return
     */
    public String getNusan() {
        return nusan;
    }

    /**
     *
     * @param nusan
     */
    public void setNusan(String nusan) {
        this.nusan = nusan;
    }

    /**
     *
     * @return
     */
    public String getEssan() {
        return essan;
    }

    /**
     *
     * @param essan
     */
    public void setEssan(String essan) {
        this.essan = essan;
    }

    /**
     *
     * @return
     */
    public String getCesan() {
        return cesan;
    }

    /**
     *
     * @param cesan
     */
    public void setCesan(String cesan) {
        this.cesan = cesan;
    }

    /**
     *
     * @return
     */
    public String getNesan() {
        return nesan;
    }

    /**
     *
     * @param nesan
     */
    public void setNesan(String nesan) {
        this.nesan = nesan;
    }

    /**
     *
     * @return
     */
    public String getNdsan() {
        return ndsan;
    }

    /**
     *
     * @param ndsan
     */
    public void setNdsan(String ndsan) {
        this.ndsan = ndsan;
    }

    /**
     *
     * @return
     */
    public String getDesan() {
        return desan;
    }

    /**
     *
     * @param desan
     */
    public void setDesan(String desan) {
        this.desan = desan;
    }

    /**
     *
     * @return
     */
    public String getCssan() {
        return cssan;
    }

    /**
     *
     * @param cssan
     */
    public void setCssan(String cssan) {
        this.cssan = cssan;
    }

    /**
     *
     * @return
     */
    public String getSesan() {
        return sesan;
    }

    /**
     *
     * @param sesan
     */
    public void setSesan(String sesan) {
        this.sesan = sesan;
    }

    /**
     *
     * @return
     */
    public String getCcsan() {
        return ccsan;
    }

    /**
     *
     * @param ccsan
     */
    public void setCcsan(String ccsan) {
        this.ccsan = ccsan;
    }

    /**
     *
     * @return
     */
    public String getCasan() {
        return casan;
    }

    /**
     *
     * @param casan
     */
    public void setCasan(String casan) {
        this.casan = casan;
    }

    /**
     *
     * @return
     */
    public Float getSxsan() {
        return sxsan;
    }

    /**
     *
     * @param sxsan
     */
    public void setSxsan(Float sxsan) {
        this.sxsan = sxsan;
    }

    /**
     *
     * @return
     */
    public String getCtsan() {
        return ctsan;
    }

    /**
     *
     * @param ctsan
     */
    public void setCtsan(String ctsan) {
        this.ctsan = ctsan;
    }

    /**
     *
     * @return
     */
    public String getNosan() {
        return nosan;
    }

    /**
     *
     * @param nosan
     */
    public void setNosan(String nosan) {
        this.nosan = nosan;
    }

    /**
     *
     * @return
     */
    public String getTcsan() {
        return tcsan;
    }

    /**
     *
     * @param tcsan
     */
    public void setTcsan(String tcsan) {
        this.tcsan = tcsan;
    }

    /**
     *
     * @return
     */
    public String getCosan() {
        return cosan;
    }

    /**
     *
     * @param cosan
     */
    public void setCosan(String cosan) {
        this.cosan = cosan;
    }

    /**
     *
     * @return
     */
    public Date getIcsan() {
        return icsan;
    }

    /**
     *
     * @param icsan
     */
    public void setIcsan(Date icsan) {
        this.icsan = icsan;
    }

    /**
     *
     * @return
     */
    public Date getFcsan() {
        return fcsan;
    }

    /**
     *
     * @param fcsan
     */
    public void setFcsan(Date fcsan) {
        this.fcsan = fcsan;
    }

    /**
     *
     * @return
     */
    public String getCusan() {
        return cusan;
    }

    /**
     *
     * @param cusan
     */
    public void setCusan(String cusan) {
        this.cusan = cusan;
    }

    /**
     *
     * @return
     */
    public String getW1san() {
        return w1san;
    }

    /**
     *
     * @param w1san
     */
    public void setW1san(String w1san) {
        this.w1san = w1san;
    }

    /**
     *
     * @return
     */
    public String getDhsan() {
        return dhsan;
    }

    /**
     *
     * @param dhsan
     */
    public void setDhsan(String dhsan) {
        this.dhsan = dhsan;
    }

    /**
     *
     * @return
     */
    public Date getFhsan() {
        return fhsan;
    }

    /**
     *
     * @param fhsan
     */
    public void setFhsan(Date fhsan) {
        this.fhsan = fhsan;
    }

    /**
     *
     * @return
     */
    public Date getHhsan() {
        return hhsan;
    }

    /**
     *
     * @param hhsan
     */
    public void setHhsan(Date hhsan) {
        this.hhsan = hhsan;
    }

    /**
     *
     * @return
     */
    public String getSgsan() {
        return sgsan;
    }

    /**
     *
     * @param sgsan
     */
    public void setSgsan(String sgsan) {
        this.sgsan = sgsan;
    }

    /**
     *
     * @return
     */
    public Integer getDisan() {
        return disan;
    }

    /**
     *
     * @param disan
     */
    public void setDisan(Integer disan) {
        this.disan = disan;
    }

    /**
     *
     * @return
     */
    public Date getFisan() {
        return fisan;
    }

    /**
     *
     * @param fisan
     */
    public void setFisan(Date fisan) {
        this.fisan = fisan;
    }

    /**
     *
     * @return
     */
    public Date getFtsan() {
        return ftsan;
    }

    /**
     *
     * @param ftsan
     */
    public void setFtsan(Date ftsan) {
        this.ftsan = ftsan;
    }

    /**
     *
     * @return
     */
    public String getCvsan() {
        return cvsan;
    }

    /**
     *
     * @param cvsan
     */
    public void setCvsan(String cvsan) {
        this.cvsan = cvsan;
    }

    /**
     *
     * @return
     */
    public String getNpsan() {
        return npsan;
    }

    /**
     *
     * @param npsan
     */
    public void setNpsan(String npsan) {
        this.npsan = npsan;
    }

    /**
     *
     * @return
     */
    public String getPtsan() {
        return ptsan;
    }

    /**
     *
     * @param ptsan
     */
    public void setPtsan(String ptsan) {
        this.ptsan = ptsan;
    }

    /**
     *
     * @return
     */
    public String getStsan() {
        return stsan;
    }

    /**
     *
     * @param stsan
     */
    public void setStsan(String stsan) {
        this.stsan = stsan;
    }

    /**
     *
     * @return
     */
    public Date getFesan() {
        return fesan;
    }

    /**
     *
     * @param fesan
     */
    public void setFesan(Date fesan) {
        this.fesan = fesan;
    }

    /**
     *
     * @return
     */
    public String getUssan() {
        return ussan;
    }

    /**
     *
     * @param ussan
     */
    public void setUssan(String ussan) {
        this.ussan = ussan;
    }

    /**
     *
     * @return
     */
    public Boolean getF1san() {
        if (this.f1san == null && ptsan != null && ptsan.length() > 0) {
            return FilenameUtils.getExtension(ptsan).equals("pdf");
        }
        return f1san;
    }

    /**
     *
     * @param f1san
     */
    public void setF1san(Boolean f1san) {
        this.f1san = f1san;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idsan != null ? idsan.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sanci35)) {
            return false;
        }
        Sanci35 other = (Sanci35) object;
        if ((this.idsan == null && other.idsan != null) || (this.idsan != null && !this.idsan.equals(other.idsan))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return EntityToString.getString(this, this.getClass());
        //return "com.hrm.sirhapp.model.Sanci35[ idsan=" + idsan + " ]";
    }

}

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
@Table(name = "PRYPR34")
@NamedQueries({
    @NamedQuery(name = "Prypr34.findAll", query = "SELECT p FROM Prypr34 p")})
public class Prypr34 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDPRY")
    private Integer idpry;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NTPRY")
    private int ntpry;
    @Size(max = 70)
    @Column(name = "NNPRY")
    private String nnpry;
    @Size(max = 13)
    @Column(name = "RFPRY")
    private String rfpry;
    @Size(max = 250)
    @Column(name = "PFPRY")
    private String pfpry;
    @Size(max = 70)
    @Column(name = "SSPRY")
    private String sspry;
    @Column(name = "FSPRY")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fspry;
    @Size(max = 15)
    @Column(name = "NUPRY")
    private String nupry;
    @Size(max = 20)
    @Column(name = "ESPRY")
    private String espry;
    @Size(max = 10)
    @Column(name = "CEPRY")
    private String cepry;
    @Size(max = 70)
    @Column(name = "NEPRY")
    private String nepry;
    @Size(max = 10)
    @Column(name = "NDPRY")
    private String ndpry;
    @Size(max = 70)
    @Column(name = "DEPRY")
    private String depry;
    @Size(max = 10)
    @Column(name = "CSPRY")
    private String cspry;
    @Size(max = 70)
    @Column(name = "SEPRY")
    private String sepry;
    @Size(max = 10)
    @Column(name = "CCPRY")
    private String ccpry;
    @Size(max = 70)
    @Column(name = "CAPRY")
    private String capry;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "SXPRY")
    private Float sxpry;
    @Size(max = 6)
    @Column(name = "CTPRY")
    private String ctpry;
    @Size(max = 70)
    @Column(name = "NOPRY")
    private String nopry;
    @Size(max = 6)
    @Column(name = "TCPRY")
    private String tcpry;
    @Size(max = 70)
    @Column(name = "COPRY")
    private String copry;
    @Column(name = "ICPRY")
    @Temporal(TemporalType.TIMESTAMP)
    private Date icpry;
    @Column(name = "FCPRY")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fcpry;
    @Column(name = "FIPRY")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fipry;
    @Size(max = 70)
    @Column(name = "TPPRY")
    private String tppry;
    @Size(max = 6)
    @Column(name = "W1PRY")
    private String w1pry;
    @Size(max = 70)
    @Column(name = "SOPRY")
    private String sopry;
    @Size(max = 250)
    @Column(name = "PSPRY")
    private String pspry;
    @Size(max = 2)
    @Column(name = "SAPRY")
    private String sapry;
    @Column(name = "FPPRY")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fppry;
    @Size(max = 70)
    @Column(name = "CRPRY")
    private String crpry;
    @Size(max = 70)
    @Column(name = "BEPRY")
    private String bepry;
    @Size(max = 70)
    @Column(name = "PAPRY")
    private String papry;
    @Column(name = "IMPRY")
    private Float impry;
    @Size(max = 3)
    @Column(name = "MOPRY")
    private String mopry;
    @Column(name = "TIPRY")
    private Float tipry;
    @Column(name = "FTPRY")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ftpry;
    @Column(name = "DPPRY")
    private Integer dppry;
    @Size(max = 6)
    @Column(name = "W2PRY")
    private String w2pry;
    @Size(max = 250)
    @Column(name = "PTPRY")
    private String ptpry;
    @Size(max = 2)
    @Column(name = "STPRY")
    private String stpry;
    @Column(name = "FEPRY")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fepry;
    @Size(max = 10)
    @Column(name = "USPRY")
    private String uspry;

    @Transient
    private Boolean f1pry;
    @Transient
    private Boolean f2pry;

    /**
     *
     */
    public Prypr34() {
    }

    /**
     *
     * @param idpry
     */
    public Prypr34(Integer idpry) {
        this.idpry = idpry;
    }

    /**
     *
     * @param idpry
     * @param ntpry
     */
    public Prypr34(Integer idpry, int ntpry) {
        this.idpry = idpry;
        this.ntpry = ntpry;
    }

    /**
     *
     * @return
     */
    public Integer getIdpry() {
        return idpry;
    }

    /**
     *
     * @param idpry
     */
    public void setIdpry(Integer idpry) {
        this.idpry = idpry;
    }

    /**
     *
     * @return
     */
    public int getNtpry() {
        return ntpry;
    }

    /**
     *
     * @param ntpry
     */
    public void setNtpry(int ntpry) {
        this.ntpry = ntpry;
    }

    /**
     *
     * @return
     */
    public String getNnpry() {
        return nnpry;
    }

    /**
     *
     * @param nnpry
     */
    public void setNnpry(String nnpry) {
        this.nnpry = nnpry;
    }

    /**
     *
     * @return
     */
    public String getRfpry() {
        return rfpry;
    }

    /**
     *
     * @param rfpry
     */
    public void setRfpry(String rfpry) {
        this.rfpry = rfpry;
    }

    /**
     *
     * @return
     */
    public String getPfpry() {
        return pfpry;
    }

    /**
     *
     * @param pfpry
     */
    public void setPfpry(String pfpry) {
        this.pfpry = pfpry;
    }

    /**
     *
     * @return
     */
    public String getSspry() {
        return sspry;
    }

    /**
     *
     * @param sspry
     */
    public void setSspry(String sspry) {
        this.sspry = sspry;
    }

    /**
     *
     * @return
     */
    public Date getFspry() {
        return fspry;
    }

    /**
     *
     * @param fspry
     */
    public void setFspry(Date fspry) {
        this.fspry = fspry;
    }

    /**
     *
     * @return
     */
    public String getNupry() {
        return nupry;
    }

    /**
     *
     * @param nupry
     */
    public void setNupry(String nupry) {
        this.nupry = nupry;
    }

    /**
     *
     * @return
     */
    public String getEspry() {
        return espry;
    }

    /**
     *
     * @param espry
     */
    public void setEspry(String espry) {
        this.espry = espry;
    }

    /**
     *
     * @return
     */
    public String getCepry() {
        return cepry;
    }

    /**
     *
     * @param cepry
     */
    public void setCepry(String cepry) {
        this.cepry = cepry;
    }

    /**
     *
     * @return
     */
    public String getNepry() {
        return nepry;
    }

    /**
     *
     * @param nepry
     */
    public void setNepry(String nepry) {
        this.nepry = nepry;
    }

    /**
     *
     * @return
     */
    public String getNdpry() {
        return ndpry;
    }

    /**
     *
     * @param ndpry
     */
    public void setNdpry(String ndpry) {
        this.ndpry = ndpry;
    }

    /**
     *
     * @return
     */
    public String getDepry() {
        return depry;
    }

    /**
     *
     * @param depry
     */
    public void setDepry(String depry) {
        this.depry = depry;
    }

    /**
     *
     * @return
     */
    public String getCspry() {
        return cspry;
    }

    /**
     *
     * @param cspry
     */
    public void setCspry(String cspry) {
        this.cspry = cspry;
    }

    /**
     *
     * @return
     */
    public String getSepry() {
        return sepry;
    }

    /**
     *
     * @param sepry
     */
    public void setSepry(String sepry) {
        this.sepry = sepry;
    }

    /**
     *
     * @return
     */
    public String getCcpry() {
        return ccpry;
    }

    /**
     *
     * @param ccpry
     */
    public void setCcpry(String ccpry) {
        this.ccpry = ccpry;
    }

    /**
     *
     * @return
     */
    public String getCapry() {
        return capry;
    }

    /**
     *
     * @param capry
     */
    public void setCapry(String capry) {
        this.capry = capry;
    }

    /**
     *
     * @return
     */
    public Float getSxpry() {
        return sxpry;
    }

    /**
     *
     * @param sxpry
     */
    public void setSxpry(Float sxpry) {
        this.sxpry = sxpry;
    }

    /**
     *
     * @return
     */
    public String getCtpry() {
        return ctpry;
    }

    /**
     *
     * @param ctpry
     */
    public void setCtpry(String ctpry) {
        this.ctpry = ctpry;
    }

    /**
     *
     * @return
     */
    public String getNopry() {
        return nopry;
    }

    /**
     *
     * @param nopry
     */
    public void setNopry(String nopry) {
        this.nopry = nopry;
    }

    /**
     *
     * @return
     */
    public String getTcpry() {
        return tcpry;
    }

    /**
     *
     * @param tcpry
     */
    public void setTcpry(String tcpry) {
        this.tcpry = tcpry;
    }

    /**
     *
     * @return
     */
    public String getCopry() {
        return copry;
    }

    /**
     *
     * @param copry
     */
    public void setCopry(String copry) {
        this.copry = copry;
    }

    /**
     *
     * @return
     */
    public Date getIcpry() {
        return icpry;
    }

    /**
     *
     * @param icpry
     */
    public void setIcpry(Date icpry) {
        this.icpry = icpry;
    }

    /**
     *
     * @return
     */
    public Date getFcpry() {
        return fcpry;
    }

    /**
     *
     * @param fcpry
     */
    public void setFcpry(Date fcpry) {
        this.fcpry = fcpry;
    }

    /**
     *
     * @return
     */
    public Date getFipry() {
        return fipry;
    }

    /**
     *
     * @param fipry
     */
    public void setFipry(Date fipry) {
        this.fipry = fipry;
    }

    /**
     *
     * @return
     */
    public String getTppry() {
        return tppry;
    }

    /**
     *
     * @param tppry
     */
    public void setTppry(String tppry) {
        this.tppry = tppry;
    }

    /**
     *
     * @return
     */
    public String getW1pry() {
        return w1pry;
    }

    /**
     *
     * @param w1pry
     */
    public void setW1pry(String w1pry) {
        this.w1pry = w1pry;
    }

    /**
     *
     * @return
     */
    public String getSopry() {
        return sopry;
    }

    /**
     *
     * @param sopry
     */
    public void setSopry(String sopry) {
        this.sopry = sopry;
    }

    /**
     *
     * @return
     */
    public String getPspry() {
        return pspry;
    }

    /**
     *
     * @param pspry
     */
    public void setPspry(String pspry) {
        this.pspry = pspry;
    }

    /**
     *
     * @return
     */
    public String getSapry() {
        return sapry;
    }

    /**
     *
     * @param sapry
     */
    public void setSapry(String sapry) {
        this.sapry = sapry;
    }

    /**
     *
     * @return
     */
    public Date getFppry() {
        return fppry;
    }

    /**
     *
     * @param fppry
     */
    public void setFppry(Date fppry) {
        this.fppry = fppry;
    }

    /**
     *
     * @return
     */
    public String getCrpry() {
        return crpry;
    }

    /**
     *
     * @param crpry
     */
    public void setCrpry(String crpry) {
        this.crpry = crpry;
    }

    /**
     *
     * @return
     */
    public String getBepry() {
        return bepry;
    }

    /**
     *
     * @param bepry
     */
    public void setBepry(String bepry) {
        this.bepry = bepry;
    }

    /**
     *
     * @return
     */
    public String getPapry() {
        return papry;
    }

    /**
     *
     * @param papry
     */
    public void setPapry(String papry) {
        this.papry = papry;
    }

    /**
     *
     * @return
     */
    public Float getImpry() {
        return impry;
    }

    /**
     *
     * @param impry
     */
    public void setImpry(Float impry) {
        this.impry = impry;
    }

    /**
     *
     * @return
     */
    public String getMopry() {
        return mopry;
    }

    /**
     *
     * @param mopry
     */
    public void setMopry(String mopry) {
        this.mopry = mopry;
    }

    /**
     *
     * @return
     */
    public Float getTipry() {
        return tipry;
    }

    /**
     *
     * @param tipry
     */
    public void setTipry(Float tipry) {
        this.tipry = tipry;
    }

    /**
     *
     * @return
     */
    public Date getFtpry() {
        return ftpry;
    }

    /**
     *
     * @param ftpry
     */
    public void setFtpry(Date ftpry) {
        this.ftpry = ftpry;
    }

    /**
     *
     * @return
     */
    public Integer getDppry() {
        return dppry;
    }

    /**
     *
     * @param dppry
     */
    public void setDppry(Integer dppry) {
        this.dppry = dppry;
    }

    /**
     *
     * @return
     */
    public String getW2pry() {
        return w2pry;
    }

    /**
     *
     * @param w2pry
     */
    public void setW2pry(String w2pry) {
        this.w2pry = w2pry;
    }

    /**
     *
     * @return
     */
    public String getPtpry() {
        return ptpry;
    }

    /**
     *
     * @param ptpry
     */
    public void setPtpry(String ptpry) {
        this.ptpry = ptpry;
    }

    /**
     *
     * @return
     */
    public String getStpry() {
        return stpry;
    }

    /**
     *
     * @param stpry
     */
    public void setStpry(String stpry) {
        this.stpry = stpry;
    }

    /**
     *
     * @return
     */
    public Date getFepry() {
        return fepry;
    }

    /**
     *
     * @param fepry
     */
    public void setFepry(Date fepry) {
        this.fepry = fepry;
    }

    /**
     *
     * @return
     */
    public String getUspry() {
        return uspry;
    }

    /**
     *
     * @param uspry
     */
    public void setUspry(String uspry) {
        this.uspry = uspry;
    }

    /**
     *
     * @return
     */
    public Boolean getF1pry() {
        if (this.f1pry == null && pspry != null && pspry.length() > 0) {
            return FilenameUtils.getExtension(pspry).equals("pdf");
        }
        return f1pry;
    }

    /**
     *
     * @param f1pry
     */
    public void setF1pry(Boolean f1pry) {
        this.f1pry = f1pry;
    }

    /**
     *
     * @return
     */
    public Boolean getF2pry() {
        if (this.f2pry == null && ptpry != null && ptpry.length() > 0) {
            return FilenameUtils.getExtension(ptpry).equals("pdf");
        }
        return f2pry;
    }

    /**
     *
     * @param f2pry
     */
    public void setF2pry(Boolean f2pry) {
        this.f2pry = f2pry;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpry != null ? idpry.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Prypr34)) {
            return false;
        }
        Prypr34 other = (Prypr34) object;
        if ((this.idpry == null && other.idpry != null) || (this.idpry != null && !this.idpry.equals(other.idpry))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return EntityToString.getString(this, this.getClass());
        //return "com.hrm.sirhapp.model.Prypr34[ idpry=" + idpry + " ]";
    }

}

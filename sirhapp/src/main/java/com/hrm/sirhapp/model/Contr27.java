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
@Table(name = "CONTR27")
@NamedQueries({
    @NamedQuery(name = "Contr27.findAll", query = "SELECT c FROM Contr27 c")})
public class Contr27 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDCTO")
    private Integer idcto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NTCTO")
    private int ntcto;
    @Size(max = 70)
    @Column(name = "APCTO")
    private String apcto;
    @Size(max = 70)
    @Column(name = "AMCTO")
    private String amcto;
    @Size(max = 70)
    @Column(name = "NOCTO")
    private String nocto;
    @Size(max = 13)
    @Column(name = "RFCTO")
    private String rfcto;
    @Size(max = 70)
    @Column(name = "SHCTO")
    private String shcto;
    @Column(name = "FHCTO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fhcto;
    @Size(max = 250)
    @Column(name = "PFCTO")
    private String pfcto;
    @Size(max = 70)
    @Column(name = "SSCTO")
    private String sscto;
    @Column(name = "FSCTO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fscto;
    @Size(max = 15)
    @NotNull
    @Column(name = "NUCTO")
    private String nucto;
    @Size(max = 20)
    @Column(name = "SECTO")
    private String secto;
    @Size(max = 10)
    @Column(name = "CVCTO")
    private String cvcto;
    @Size(max = 70)
    @Column(name = "NECTO")
    private String necto;
    @Size(max = 10)
    @Column(name = "NDCTO")
    private String ndcto;
    @Size(max = 70)
    @Column(name = "NBCTO")
    private String nbcto;
    @Size(max = 10)
    @Column(name = "CSCTO")
    private String cscto;
    @Size(max = 70)
    @Column(name = "NSCTO")
    private String nscto;
    @Size(max = 10)
    @Column(name = "CCCTO")
    private String cccto;
    @Size(max = 70)
    @Column(name = "CACTO")
    private String cacto;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "SXCTO")
    private Float sxcto;
    @Size(max = 6)
    @Column(name = "TUCTO")
    private String tucto;
    @Size(max = 70)
    @Column(name = "TTCTO")
    private String ttcto;
    @Size(max = 6)
    @Column(name = "TCCTO")
    private String tccto;
    @Size(max = 70)
    @Column(name = "NCCTO")
    private String nccto;
    @Column(name = "INCTO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date incto;
    @Column(name = "TECTO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tecto;
    @Size(max = 10)
    @Column(name = "SCCTO")
    private String sccto;
    @Size(max = 70)
    @Column(name = "SPCTO")
    private String spcto;
    @Size(max = 10)
    @Column(name = "DCCTO")
    private String dccto;
    @Size(max = 70)
    @Column(name = "DPCTO")
    private String dpcto;
    @Size(max = 70)
    @Column(name = "ESCTO")
    private String escto;
    @Size(max = 6)
    @Column(name = "W1CTO")
    private String w1cto;
    @Size(max = 250)
    @Column(name = "PACTO")
    private String pacto;
    @Size(max = 13)
    @Column(name = "NLCTO")
    private String nlcto;
    @Size(max = 13)
    @Column(name = "CNCTO")
    private String cncto;
    @Size(max = 13)
    @Column(name = "NRCTO")
    private String nrcto;
    @Size(max = 13)
    @Column(name = "RECTO")
    private String recto;
    @Size(max = 250)
    @Column(name = "OBCTO")
    private String obcto;
    @Size(max = 2)
    @Column(name = "STCTO")
    private String stcto;
    @Column(name = "FECTO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecto;
    @Size(max = 10)
    @Column(name = "USCTO")
    private String uscto;

    @Transient
    private String tncto;

    @Transient
    private Boolean ftcto;

    /**
     *
     */
    public Contr27() {
    }

    /**
     *
     * @param ntcto
     * @param apcto
     * @param amcto
     * @param nocto
     * @param rfcto
     * @param shcto
     * @param fhcto
     * @param pfcto
     * @param sscto
     * @param fscto
     * @param w1cto
     * @param incto
     */
    public Contr27(Integer ntcto, String apcto, String amcto, String nocto, String rfcto, String shcto, Date fhcto, String pfcto, String sscto, Date fscto, String w1cto, Date incto) {

        this.ntcto = ntcto;
        this.apcto = apcto;
        this.amcto = amcto;
        this.nocto = nocto;
        this.rfcto = rfcto;
        this.shcto = shcto;
        this.fhcto = fhcto;
        this.pfcto = pfcto;

        this.sscto = sscto;

        this.fscto = fscto;
        this.w1cto = w1cto;

        this.incto = incto;

    }

    /**
     *
     * @param idcto
     */
    public Contr27(Integer idcto) {
        this.idcto = idcto;
    }

    /**
     *
     * @param idcto
     * @param ntcto
     */
    public Contr27(Integer idcto, int ntcto) {
        this.idcto = idcto;
        this.ntcto = ntcto;
    }

    /**
     *
     * @return
     */
    public String getTncto() {
        String localNctra = "";
        if (apcto != null) {
            localNctra = apcto;
        }
        if (amcto != null) {
            localNctra = localNctra + " " + amcto;
        }
        if (nocto != null) {
            localNctra = localNctra + " " + nocto;
        }
        this.tncto = localNctra.toUpperCase();
        return tncto;
    }

    /**
     *
     * @param nccto
     */
    public void setTncto(String nccto) {
        String localTncto = apcto + " " + amcto + " " + nocto;
        this.tncto = localTncto.toUpperCase();
    }

    /**
     *
     * @return
     */
    public Integer getIdcto() {
        return idcto;
    }

    /**
     *
     * @param idcto
     */
    public void setIdcto(Integer idcto) {
        this.idcto = idcto;
    }

    /**
     *
     * @return
     */
    public int getNtcto() {
        return ntcto;
    }

    /**
     *
     * @param ntcto
     */
    public void setNtcto(int ntcto) {
        this.ntcto = ntcto;
    }

    /**
     *
     * @return
     */
    public String getApcto() {
        return apcto;
    }

    /**
     *
     * @param apcto
     */
    public void setApcto(String apcto) {
        this.apcto = apcto;
    }

    /**
     *
     * @return
     */
    public String getAmcto() {
        return amcto;
    }

    /**
     *
     * @param amcto
     */
    public void setAmcto(String amcto) {
        this.amcto = amcto;
    }

    /**
     *
     * @return
     */
    public String getNocto() {
        return nocto;
    }

    /**
     *
     * @param nocto
     */
    public void setNocto(String nocto) {
        this.nocto = nocto;
    }

    /**
     *
     * @return
     */
    public String getRfcto() {
        return rfcto;
    }

    /**
     *
     * @param rfcto
     */
    public void setRfcto(String rfcto) {
        this.rfcto = rfcto;
    }

    /**
     *
     * @return
     */
    public String getShcto() {
        return shcto;
    }

    /**
     *
     * @param shcto
     */
    public void setShcto(String shcto) {
        this.shcto = shcto;
    }

    /**
     *
     * @return
     */
    public Date getFhcto() {
        return fhcto;
    }

    /**
     *
     * @param fhcto
     */
    public void setFhcto(Date fhcto) {
        this.fhcto = fhcto;
    }

    /**
     *
     * @return
     */
    public String getPfcto() {
        return pfcto;
    }

    /**
     *
     * @param pfcto
     */
    public void setPfcto(String pfcto) {
        this.pfcto = pfcto;
    }

    /**
     *
     * @return
     */
    public String getSscto() {
        return sscto;
    }

    /**
     *
     * @param sscto
     */
    public void setSscto(String sscto) {
        this.sscto = sscto;
    }

    /**
     *
     * @return
     */
    public Date getFscto() {
        return fscto;
    }

    /**
     *
     * @param fscto
     */
    public void setFscto(Date fscto) {
        this.fscto = fscto;
    }

    /**
     *
     * @return
     */
    public String getNucto() {
        return nucto;
    }

    /**
     *
     * @param nucto
     */
    public void setNucto(String nucto) {
        this.nucto = nucto;
    }

    /**
     *
     * @return
     */
    public String getSecto() {
        return secto;
    }

    /**
     *
     * @param secto
     */
    public void setSecto(String secto) {
        this.secto = secto;
    }

    /**
     *
     * @return
     */
    public String getCvcto() {
        return cvcto;
    }

    /**
     *
     * @param cvcto
     */
    public void setCvcto(String cvcto) {
        this.cvcto = cvcto;
    }

    /**
     *
     * @return
     */
    public String getNecto() {
        return necto;
    }

    /**
     *
     * @param necto
     */
    public void setNecto(String necto) {
        this.necto = necto;
    }

    /**
     *
     * @return
     */
    public String getNdcto() {
        return ndcto;
    }

    /**
     *
     * @param ndcto
     */
    public void setNdcto(String ndcto) {
        this.ndcto = ndcto;
    }

    /**
     *
     * @return
     */
    public String getNbcto() {
        return nbcto;
    }

    /**
     *
     * @param nbcto
     */
    public void setNbcto(String nbcto) {
        this.nbcto = nbcto;
    }

    /**
     *
     * @return
     */
    public String getCscto() {
        return cscto;
    }

    /**
     *
     * @param cscto
     */
    public void setCscto(String cscto) {
        this.cscto = cscto;
    }

    /**
     *
     * @return
     */
    public String getNscto() {
        return nscto;
    }

    /**
     *
     * @param nscto
     */
    public void setNscto(String nscto) {
        this.nscto = nscto;
    }

    /**
     *
     * @return
     */
    public String getCccto() {
        return cccto;
    }

    /**
     *
     * @param cccto
     */
    public void setCccto(String cccto) {
        this.cccto = cccto;
    }

    /**
     *
     * @return
     */
    public String getCacto() {
        return cacto;
    }

    /**
     *
     * @param cacto
     */
    public void setCacto(String cacto) {
        this.cacto = cacto;
    }

    /**
     *
     * @return
     */
    public Float getSxcto() {
        return sxcto;
    }

    /**
     *
     * @param sxcto
     */
    public void setSxcto(Float sxcto) {
        this.sxcto = sxcto;
    }

    /**
     *
     * @return
     */
    public String getTucto() {
        return tucto;
    }

    /**
     *
     * @param tucto
     */
    public void setTucto(String tucto) {
        this.tucto = tucto;
    }

    /**
     *
     * @return
     */
    public String getTtcto() {
        return ttcto;
    }

    /**
     *
     * @param ttcto
     */
    public void setTtcto(String ttcto) {
        this.ttcto = ttcto;
    }

    /**
     *
     * @return
     */
    public String getTccto() {
        return tccto;
    }

    /**
     *
     * @param tccto
     */
    public void setTccto(String tccto) {
        this.tccto = tccto;
    }

    /**
     *
     * @return
     */
    public String getNccto() {
        return nccto;
    }

    /**
     *
     * @param nccto
     */
    public void setNccto(String nccto) {
        this.nccto = nccto;
    }

    /**
     *
     * @return
     */
    public Date getIncto() {
        return incto;
    }

    /**
     *
     * @param incto
     */
    public void setIncto(Date incto) {
        this.incto = incto;
    }

    /**
     *
     * @return
     */
    public Date getTecto() {
        return tecto;
    }

    /**
     *
     * @param tecto
     */
    public void setTecto(Date tecto) {
        this.tecto = tecto;
    }

    /**
     *
     * @return
     */
    public String getSccto() {
        return sccto;
    }

    /**
     *
     * @param sccto
     */
    public void setSccto(String sccto) {
        this.sccto = sccto;
    }

    /**
     *
     * @return
     */
    public String getSpcto() {
        return spcto;
    }

    /**
     *
     * @param spcto
     */
    public void setSpcto(String spcto) {
        this.spcto = spcto;
    }

    /**
     *
     * @return
     */
    public String getDccto() {
        return dccto;
    }

    /**
     *
     * @param dccto
     */
    public void setDccto(String dccto) {
        this.dccto = dccto;
    }

    /**
     *
     * @return
     */
    public String getDpcto() {
        return dpcto;
    }

    /**
     *
     * @param dpcto
     */
    public void setDpcto(String dpcto) {
        this.dpcto = dpcto;
    }

    /**
     *
     * @return
     */
    public String getEscto() {
        return escto;
    }

    /**
     *
     * @param escto
     */
    public void setEscto(String escto) {
        this.escto = escto;
    }

    /**
     *
     * @return
     */
    public String getW1cto() {
        return w1cto;
    }

    /**
     *
     * @param w1cto
     */
    public void setW1cto(String w1cto) {
        this.w1cto = w1cto;
    }

    /**
     *
     * @return
     */
    public String getPacto() {
        return pacto;
    }

    /**
     *
     * @param pacto
     */
    public void setPacto(String pacto) {
        this.pacto = pacto;
    }

    /**
     *
     * @return
     */
    public String getNlcto() {
        return nlcto;
    }

    /**
     *
     * @param nlcto
     */
    public void setNlcto(String nlcto) {
        this.nlcto = nlcto;
    }

    /**
     *
     * @return
     */
    public String getCncto() {
        return cncto;
    }

    /**
     *
     * @param cncto
     */
    public void setCncto(String cncto) {
        this.cncto = cncto;
    }

    /**
     *
     * @return
     */
    public String getNrcto() {
        return nrcto;
    }

    /**
     *
     * @param nrcto
     */
    public void setNrcto(String nrcto) {
        this.nrcto = nrcto;
    }

    /**
     *
     * @return
     */
    public String getRecto() {
        return recto;
    }

    /**
     *
     * @param recto
     */
    public void setRecto(String recto) {
        this.recto = recto;
    }

    /**
     *
     * @return
     */
    public String getObcto() {
        return obcto;
    }

    /**
     *
     * @param obcto
     */
    public void setObcto(String obcto) {
        this.obcto = obcto;
    }

    /**
     *
     * @return
     */
    public String getStcto() {
        return stcto;
    }

    /**
     *
     * @param stcto
     */
    public void setStcto(String stcto) {
        this.stcto = stcto;
    }

    /**
     *
     * @return
     */
    public Date getFecto() {
        return fecto;
    }

    /**
     *
     * @param fecto
     */
    public void setFecto(Date fecto) {
        this.fecto = fecto;
    }

    /**
     *
     * @return
     */
    public String getUscto() {
        return uscto;
    }

    /**
     *
     * @param uscto
     */
    public void setUscto(String uscto) {
        this.uscto = uscto;
    }

    /**
     *
     * @return
     */
    public Boolean getFtcto() {
        if (this.ftcto == null && pacto != null && pacto.length() > 0) {
            return FilenameUtils.getExtension(pacto).equals("pdf");
        }
        return ftcto;
    }

    /**
     *
     * @param ftcto
     */
    public void setFtcto(Boolean ftcto) {
        this.ftcto = ftcto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcto != null ? idcto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Contr27)) {
            return false;
        }
        Contr27 other = (Contr27) object;
        if ((this.idcto == null && other.idcto != null) || (this.idcto != null && !this.idcto.equals(other.idcto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return EntityToString.getString(this, this.getClass());
        //return "com.hrm.sirhapp.model.Contr27[ idcto=" + idcto + " ]";
    }

}

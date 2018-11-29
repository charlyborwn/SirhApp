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
import javax.validation.constraints.Size;

/**
 *
 * @author Carlos Bernal carlose.bernalh@gmail.com
 * Copyright 2018 Carlos Ernesto Bernal Hernández.
 * SirhApp
 * 
 */
@Entity
@Table(name = "REQUI54")
@NamedQueries({
    @NamedQuery(name = "Requi54.findAll", query = "SELECT r FROM Requi54 r")})
public class Requi54 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDREQ")
    private Integer idreq;
    @Size(max = 13)
    @Column(name = "RFREQ")
    private String rfreq;
    @Size(max = 70)
    @Column(name = "APREQ")
    private String apreq;
    @Size(max = 70)
    @Column(name = "AMREQ")
    private String amreq;
    @Size(max = 70)
    @Column(name = "NOREQ")
    private String noreq;
    @Column(name = "ACREQ")
    private Integer acreq;
    @Column(name = "CUREQ")
    private Integer cureq;
    @Column(name = "CVREQ")
    private Integer cvreq;
    @Column(name = "CDREQ")
    private Integer cdreq;
    @Column(name = "CEREQ")
    private Integer cereq;
    @Column(name = "VOREQ")
    private Integer voreq;
    @Column(name = "CMREQ")
    private Integer cmreq;
    @Column(name = "IMREQ")
    private Integer imreq;
    @Column(name = "SAREQ")
    private Integer sareq;
    @Column(name = "AFREQ")
    private Integer afreq;
    @Column(name = "REREQ")
    private Integer rereq;
    @Column(name = "INREQ")
    private Integer inreq;
    @Column(name = "FOREQ")
    private Integer foreq;
    @Column(name = "CIREQ")
    private Integer cireq;
    @Column(name = "LCREQ")
    private Integer lcreq;
    @Column(name = "PAREQ")
    private Integer pareq;
    @Column(name = "MDREQ")
    private Integer mdreq;
    @Column(name = "HIREQ")
    private Integer hireq;
    @Column(name = "BEREQ")
    private Integer bereq;
    @Column(name = "FRREQ")
    private Integer frreq;
    @Column(name = "FTREQ")
    private Integer ftreq;
    @Column(name = "PEREQ")
    private Integer pereq;
    @Column(name = "ECREQ")
    private Integer ecreq;
    @Column(name = "DPREQ")
    private Integer dpreq;
    @Column(name = "COREQ")
    private Integer coreq;
    @Size(max = 70)
    @Column(name = "PRREQ")
    private String prreq;
    @Column(name = "FVREQ")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fvreq;
    @Size(max = 2)
    @Column(name = "STREQ")
    private String streq;
    @Column(name = "FEREQ")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fereq;
    @Size(max = 10)
    @Column(name = "USREQ")
    private String usreq;

    /**
     *
     */
    public Requi54() {
    }

    /**
     *
     * @param idreq
     */
    public Requi54(Integer idreq) {
        this.idreq = idreq;
    }

    /**
     *
     * @return
     */
    public Integer getIdreq() {
        return idreq;
    }

    /**
     *
     * @param idreq
     */
    public void setIdreq(Integer idreq) {
        this.idreq = idreq;
    }

    /**
     *
     * @return
     */
    public String getRfreq() {
        return rfreq;
    }

    /**
     *
     * @param rfreq
     */
    public void setRfreq(String rfreq) {
        this.rfreq = rfreq;
    }

    /**
     *
     * @return
     */
    public String getApreq() {
        return apreq;
    }

    /**
     *
     * @param apreq
     */
    public void setApreq(String apreq) {
        this.apreq = apreq;
    }

    /**
     *
     * @return
     */
    public String getAmreq() {
        return amreq;
    }

    /**
     *
     * @param amreq
     */
    public void setAmreq(String amreq) {
        this.amreq = amreq;
    }

    /**
     *
     * @return
     */
    public String getNoreq() {
        return noreq;
    }

    /**
     *
     * @param noreq
     */
    public void setNoreq(String noreq) {
        this.noreq = noreq;
    }

    /**
     *
     * @return
     */
    public Integer getAcreq() {
        return acreq;
    }

    /**
     *
     * @param acreq
     */
    public void setAcreq(Integer acreq) {
        this.acreq = acreq;
    }

    /**
     *
     * @return
     */
    public Integer getCureq() {
        return cureq;
    }

    /**
     *
     * @param cureq
     */
    public void setCureq(Integer cureq) {
        this.cureq = cureq;
    }

    /**
     *
     * @return
     */
    public Integer getCvreq() {
        return cvreq;
    }

    /**
     *
     * @param cvreq
     */
    public void setCvreq(Integer cvreq) {
        this.cvreq = cvreq;
    }

    /**
     *
     * @return
     */
    public Integer getCdreq() {
        return cdreq;
    }

    /**
     *
     * @param cdreq
     */
    public void setCdreq(Integer cdreq) {
        this.cdreq = cdreq;
    }

    /**
     *
     * @return
     */
    public Integer getCereq() {
        return cereq;
    }

    /**
     *
     * @param cereq
     */
    public void setCereq(Integer cereq) {
        this.cereq = cereq;
    }

    /**
     *
     * @return
     */
    public Integer getVoreq() {
        return voreq;
    }

    /**
     *
     * @param voreq
     */
    public void setVoreq(Integer voreq) {
        this.voreq = voreq;
    }

    /**
     *
     * @return
     */
    public Integer getCmreq() {
        return cmreq;
    }

    /**
     *
     * @param cmreq
     */
    public void setCmreq(Integer cmreq) {
        this.cmreq = cmreq;
    }

    /**
     *
     * @return
     */
    public Integer getImreq() {
        return imreq;
    }

    /**
     *
     * @param imreq
     */
    public void setImreq(Integer imreq) {
        this.imreq = imreq;
    }

    /**
     *
     * @return
     */
    public Integer getSareq() {
        return sareq;
    }

    /**
     *
     * @param sareq
     */
    public void setSareq(Integer sareq) {
        this.sareq = sareq;
    }

    /**
     *
     * @return
     */
    public Integer getAfreq() {
        return afreq;
    }

    /**
     *
     * @param afreq
     */
    public void setAfreq(Integer afreq) {
        this.afreq = afreq;
    }

    /**
     *
     * @return
     */
    public Integer getRereq() {
        return rereq;
    }

    /**
     *
     * @param rereq
     */
    public void setRereq(Integer rereq) {
        this.rereq = rereq;
    }

    /**
     *
     * @return
     */
    public Integer getInreq() {
        return inreq;
    }

    /**
     *
     * @param inreq
     */
    public void setInreq(Integer inreq) {
        this.inreq = inreq;
    }

    /**
     *
     * @return
     */
    public Integer getForeq() {
        return foreq;
    }

    /**
     *
     * @param foreq
     */
    public void setForeq(Integer foreq) {
        this.foreq = foreq;
    }

    /**
     *
     * @return
     */
    public Integer getCireq() {
        return cireq;
    }

    /**
     *
     * @param cireq
     */
    public void setCireq(Integer cireq) {
        this.cireq = cireq;
    }

    /**
     *
     * @return
     */
    public Integer getLcreq() {
        return lcreq;
    }

    /**
     *
     * @param lcreq
     */
    public void setLcreq(Integer lcreq) {
        this.lcreq = lcreq;
    }

    /**
     *
     * @return
     */
    public Integer getPareq() {
        return pareq;
    }

    /**
     *
     * @param pareq
     */
    public void setPareq(Integer pareq) {
        this.pareq = pareq;
    }

    /**
     *
     * @return
     */
    public Integer getMdreq() {
        return mdreq;
    }

    /**
     *
     * @param mdreq
     */
    public void setMdreq(Integer mdreq) {
        this.mdreq = mdreq;
    }

    /**
     *
     * @return
     */
    public Integer getHireq() {
        return hireq;
    }

    /**
     *
     * @param hireq
     */
    public void setHireq(Integer hireq) {
        this.hireq = hireq;
    }

    /**
     *
     * @return
     */
    public Integer getBereq() {
        return bereq;
    }

    /**
     *
     * @param bereq
     */
    public void setBereq(Integer bereq) {
        this.bereq = bereq;
    }

    /**
     *
     * @return
     */
    public Integer getFrreq() {
        return frreq;
    }

    /**
     *
     * @param frreq
     */
    public void setFrreq(Integer frreq) {
        this.frreq = frreq;
    }

    /**
     *
     * @return
     */
    public Integer getFtreq() {
        return ftreq;
    }

    /**
     *
     * @param ftreq
     */
    public void setFtreq(Integer ftreq) {
        this.ftreq = ftreq;
    }

    /**
     *
     * @return
     */
    public Integer getPereq() {
        return pereq;
    }

    /**
     *
     * @param pereq
     */
    public void setPereq(Integer pereq) {
        this.pereq = pereq;
    }

    /**
     *
     * @return
     */
    public Integer getEcreq() {
        return ecreq;
    }

    /**
     *
     * @param ecreq
     */
    public void setEcreq(Integer ecreq) {
        this.ecreq = ecreq;
    }

    /**
     *
     * @return
     */
    public Integer getDpreq() {
        return dpreq;
    }

    /**
     *
     * @param dpreq
     */
    public void setDpreq(Integer dpreq) {
        this.dpreq = dpreq;
    }

    /**
     *
     * @return
     */
    public Integer getCoreq() {
        return coreq;
    }

    /**
     *
     * @param coreq
     */
    public void setCoreq(Integer coreq) {
        this.coreq = coreq;
    }

    /**
     *
     * @return
     */
    public String getPrreq() {
        return prreq;
    }

    /**
     *
     * @param prreq
     */
    public void setPrreq(String prreq) {
        this.prreq = prreq;
    }

    /**
     *
     * @return
     */
    public Date getFvreq() {
        return fvreq;
    }

    /**
     *
     * @param fvreq
     */
    public void setFvreq(Date fvreq) {
        this.fvreq = fvreq;
    }

    /**
     *
     * @return
     */
    public String getStreq() {
        return streq;
    }

    /**
     *
     * @param streq
     */
    public void setStreq(String streq) {
        this.streq = streq;
    }

    /**
     *
     * @return
     */
    public Date getFereq() {
        return fereq;
    }

    /**
     *
     * @param fereq
     */
    public void setFereq(Date fereq) {
        this.fereq = fereq;
    }

    /**
     *
     * @return
     */
    public String getUsreq() {
        return usreq;
    }

    /**
     *
     * @param usreq
     */
    public void setUsreq(String usreq) {
        this.usreq = usreq;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idreq != null ? idreq.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Requi54)) {
            return false;
        }
        Requi54 other = (Requi54) object;
        if ((this.idreq == null && other.idreq != null) || (this.idreq != null && !this.idreq.equals(other.idreq))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return EntityToString.getString(this,this.getClass());
        //return "com.hrm.sirhapp.model.Requi54[ idreq=" + idreq + " ]";
    }
    
}

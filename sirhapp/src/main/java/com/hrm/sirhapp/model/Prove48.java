package com.hrm.sirhapp.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "PROVE48")
public class Prove48 implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDPRO")
    private Integer idpro;
    @Size(max = 13)
    @Column(name = "RFPRO")
    private String rfpro;
    @Size(max = 30)
    @Column(name = "NCPRO")
    private String ncpro;
    @Size(max = 70)
    @Column(name = "NLPRO")
    private String nlpro;
    @Size(max = 70)
    @Column(name = "EPPRO")
    private String eppro;
    @Size(max = 70)
    @Column(name = "PAPRO")
    private String papro;
    @Size(max = 70)
    @Column(name = "ESPRO")
    private String espro;
    @Size(max = 70)
    @Column(name = "MDPRO")
    private String mdpro;
    @Size(max = 70)
    @Column(name = "LOPRO")
    private String lopro;
    @Size(max = 70)
    @Column(name = "COPRO")
    private String copro;
    @Size(max = 70)
    @Column(name = "CAPRO")
    private String capro;
    @Size(max = 5)
    @Column(name = "CPPRO")
    private String cppro;
    @Size(max = 15)
    @Column(name = "TDPRO")
    private String tdpro;
    @Size(max = 70)
    @Column(name = "PCPRO")
    private String pcpro;
    @Size(max = 70)
    @Column(name = "CRPRO")
    private String crpro;
    @Size(max = 15)
    @Column(name = "TCPRO")
    private String tcpro;
    @Size(max = 70)
    @Column(name = "MAPRO")
    private String mapro;
    @Size(max = 2)
    @Column(name = "STPRO")
    private String stpro;
    @Column(name = "FEPRO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fepro;
    @Size(max = 10)
    @Column(name = "USPRO")
    private String uspro;

    /**
     *
     */
    public Prove48() {
    }

    /**
     *
     * @param idpro
     */
    public Prove48(Integer idpro) {
        this.idpro = idpro;
    }

    /**
     *
     * @return
     */
    public Integer getIdpro() {
        return idpro;
    }

    /**
     *
     * @param idpro
     */
    public void setIdpro(Integer idpro) {
        this.idpro = idpro;
    }

    /**
     *
     * @return
     */
    public String getRfpro() {
        return rfpro;
    }

    /**
     *
     * @param rfpro
     */
    public void setRfpro(String rfpro) {
        this.rfpro = rfpro;
    }

    /**
     *
     * @return
     */
    public String getNcpro() {
        return ncpro;
    }

    /**
     *
     * @param ncpro
     */
    public void setNcpro(String ncpro) {
        this.ncpro = ncpro;
    }

    /**
     *
     * @return
     */
    public String getNlpro() {
        return nlpro;
    }

    /**
     *
     * @param nlpro
     */
    public void setNlpro(String nlpro) {
        this.nlpro = nlpro;
    }

    /**
     *
     * @return
     */
    public String getEppro() {
        return eppro;
    }

    /**
     *
     * @param eppro
     */
    public void setEppro(String eppro) {
        this.eppro = eppro;
    }

    /**
     *
     * @return
     */
    public String getPapro() {
        return papro;
    }

    /**
     *
     * @param papro
     */
    public void setPapro(String papro) {
        this.papro = papro;
    }

    /**
     *
     * @return
     */
    public String getEspro() {
        return espro;
    }

    /**
     *
     * @param espro
     */
    public void setEspro(String espro) {
        this.espro = espro;
    }

    /**
     *
     * @return
     */
    public String getMdpro() {
        return mdpro;
    }

    /**
     *
     * @param mdpro
     */
    public void setMdpro(String mdpro) {
        this.mdpro = mdpro;
    }

    /**
     *
     * @return
     */
    public String getLopro() {
        return lopro;
    }

    /**
     *
     * @param lopro
     */
    public void setLopro(String lopro) {
        this.lopro = lopro;
    }

    /**
     *
     * @return
     */
    public String getCopro() {
        return copro;
    }

    /**
     *
     * @param copro
     */
    public void setCopro(String copro) {
        this.copro = copro;
    }

    /**
     *
     * @return
     */
    public String getCapro() {
        return capro;
    }

    /**
     *
     * @param capro
     */
    public void setCapro(String capro) {
        this.capro = capro;
    }

    /**
     *
     * @return
     */
    public String getCppro() {
        return cppro;
    }

    /**
     *
     * @param cppro
     */
    public void setCppro(String cppro) {
        this.cppro = cppro;
    }

    /**
     *
     * @return
     */
    public String getTdpro() {
        return tdpro;
    }

    /**
     *
     * @param tdpro
     */
    public void setTdpro(String tdpro) {
        this.tdpro = tdpro;
    }

    /**
     *
     * @return
     */
    public String getPcpro() {
        return pcpro;
    }

    /**
     *
     * @param pcpro
     */
    public void setPcpro(String pcpro) {
        this.pcpro = pcpro;
    }

    /**
     *
     * @return
     */
    public String getCrpro() {
        return crpro;
    }

    /**
     *
     * @param crpro
     */
    public void setCrpro(String crpro) {
        this.crpro = crpro;
    }

    /**
     *
     * @return
     */
    public String getTcpro() {
        return tcpro;
    }

    /**
     *
     * @param tcpro
     */
    public void setTcpro(String tcpro) {
        this.tcpro = tcpro;
    }

    /**
     *
     * @return
     */
    public String getMapro() {
        return mapro;
    }

    /**
     *
     * @param mapro
     */
    public void setMapro(String mapro) {
        this.mapro = mapro;
    }

    /**
     *
     * @return
     */
    public String getStpro() {
        return stpro;
    }

    /**
     *
     * @param stpro
     */
    public void setStpro(String stpro) {
        this.stpro = stpro;
    }

    /**
     *
     * @return
     */
    public Date getFepro() {
        return fepro;
    }

    /**
     *
     * @param fepro
     */
    public void setFepro(Date fepro) {
        this.fepro = fepro;
    }

    /**
     *
     * @return
     */
    public String getUspro() {
        return uspro;
    }

    /**
     *
     * @param uspro
     */
    public void setUspro(String uspro) {
        this.uspro = uspro;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpro != null ? idpro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Prove48)) {
            return false;
        }
        Prove48 other = (Prove48) object;
        if ((this.idpro == null && other.idpro != null) || (this.idpro != null && !this.idpro.equals(other.idpro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return EntityToString.getString(this, this.getClass());
        //return "com.hrm.sirhapp.model.Prove48[ idpro=" + idpro + " ]";
    }

}

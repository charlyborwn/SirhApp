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
@Table(name = "CHOFE40")
@NamedQueries({
    @NamedQuery(name = "Chofe40.findAll", query = "SELECT c FROM Chofe40 c")})
public class Chofe40 implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDCHO")
    private Integer idcho;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 13)
    @Column(name = "RFCHO")
    private String rfcho;
    @Size(max = 18)
    @Column(name = "CUCHO")
    private String cucho;
    @Size(max = 70)
    @Column(name = "APCHO")
    private String apcho;
    @Size(max = 70)
    @Column(name = "AMCHO")
    private String amcho;
    @Size(max = 70)
    @Column(name = "NOCHO")
    private String nocho;
    @Size(max = 70)
    @Column(name = "PCCHO")
    private String pccho;
    @Size(max = 70)
    @Column(name = "SECHO")
    private String secho;
    @Column(name = "FNCHO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fncho;
    @Size(max = 20)
    @Column(name = "RICHO")
    private String richo;
    @Size(max = 70)
    @Column(name = "UICHO")
    private String uicho;
    @Size(max = 70)
    @Column(name = "GMCHO")
    private String gmcho;
    @Size(max = 70)
    @Column(name = "ECCHO")
    private String eccho;
    @Size(max = 70)
    @Column(name = "NLCHO")
    private String nlcho;
    @Column(name = "VLCHO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date vlcho;
    @Size(max = 70)
    @Column(name = "ELCHO")
    private String elcho;
    @Size(max = 70)
    @Column(name = "PFCHO")
    private String pfcho;
    @Size(max = 2)
    @Column(name = "EXCHO")
    private String excho;
    @Column(name = "FXCHO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fxcho;
    @Size(max = 70)
    @Column(name = "PACHO")
    private String pacho;
    @Size(max = 70)
    @Column(name = "ESCHO")
    private String escho;
    @Size(max = 70)
    @Column(name = "MDCHO")
    private String mdcho;
    @Size(max = 70)
    @Column(name = "LOCHO")
    private String locho;
    @Size(max = 70)
    @Column(name = "COCHO")
    private String cocho;
    @Size(max = 70)
    @Column(name = "CACHO")
    private String cacho;
    @Size(max = 10)
    @Column(name = "NECHO")
    private String necho;
    @Size(max = 10)
    @Column(name = "NICHO")
    private String nicho;
    @Size(max = 5)
    @Column(name = "CPCHO")
    private String cpcho;
    @Size(max = 15)
    @Column(name = "TDCHO")
    private String tdcho;
    @Size(max = 15)
    @Column(name = "TCCHO")
    private String tccho;
    @Size(max = 70)
    @Column(name = "CECHO")
    private String cecho;
    @Size(max = 2)
    @Column(name = "STCHO")
    private String stcho;
    @Column(name = "FECHO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecho;
    @Size(max = 10)
    @Column(name = "USCHO")
    private String uscho;

    /**
     *
     */
    public Chofe40() {
    }

    /**
     *
     * @param idcho
     */
    public Chofe40(Integer idcho) {
        this.idcho = idcho;
    }

    /**
     *
     * @param idcho
     * @param rfcho
     */
    public Chofe40(Integer idcho, String rfcho) {
        this.idcho = idcho;
        this.rfcho = rfcho;
    }

    /**
     *
     * @return
     */
    public Integer getIdcho() {
        return idcho;
    }

    /**
     *
     * @param idcho
     */
    public void setIdcho(Integer idcho) {
        this.idcho = idcho;
    }

    /**
     *
     * @return
     */
    public String getRfcho() {
        return rfcho;
    }

    /**
     *
     * @param rfcho
     */
    public void setRfcho(String rfcho) {
        this.rfcho = rfcho;
    }

    /**
     *
     * @return
     */
    public String getCucho() {
        return cucho;
    }

    /**
     *
     * @param cucho
     */
    public void setCucho(String cucho) {
        this.cucho = cucho;
    }

    /**
     *
     * @return
     */
    public String getApcho() {
        return apcho;
    }

    /**
     *
     * @param apcho
     */
    public void setApcho(String apcho) {
        this.apcho = apcho;
    }

    /**
     *
     * @return
     */
    public String getAmcho() {
        return amcho;
    }

    /**
     *
     * @param amcho
     */
    public void setAmcho(String amcho) {
        this.amcho = amcho;
    }

    /**
     *
     * @return
     */
    public String getNocho() {
        return nocho;
    }

    /**
     *
     * @param nocho
     */
    public void setNocho(String nocho) {
        this.nocho = nocho;
    }

    /**
     *
     * @return
     */
    public String getPccho() {
        return pccho;
    }

    /**
     *
     * @param pccho
     */
    public void setPccho(String pccho) {
        this.pccho = pccho;
    }

    /**
     *
     * @return
     */
    public String getSecho() {
        return secho;
    }

    /**
     *
     * @param secho
     */
    public void setSecho(String secho) {
        this.secho = secho;
    }

    /**
     *
     * @return
     */
    public Date getFncho() {
        return fncho;
    }

    /**
     *
     * @param fncho
     */
    public void setFncho(Date fncho) {
        this.fncho = fncho;
    }

    /**
     *
     * @return
     */
    public String getRicho() {
        return richo;
    }

    /**
     *
     * @param richo
     */
    public void setRicho(String richo) {
        this.richo = richo;
    }

    /**
     *
     * @return
     */
    public String getUicho() {
        return uicho;
    }

    /**
     *
     * @param uicho
     */
    public void setUicho(String uicho) {
        this.uicho = uicho;
    }

    /**
     *
     * @return
     */
    public String getGmcho() {
        return gmcho;
    }

    /**
     *
     * @param gmcho
     */
    public void setGmcho(String gmcho) {
        this.gmcho = gmcho;
    }

    /**
     *
     * @return
     */
    public String getEccho() {
        return eccho;
    }

    /**
     *
     * @param eccho
     */
    public void setEccho(String eccho) {
        this.eccho = eccho;
    }

    /**
     *
     * @return
     */
    public String getNlcho() {
        return nlcho;
    }

    /**
     *
     * @param nlcho
     */
    public void setNlcho(String nlcho) {
        this.nlcho = nlcho;
    }

    /**
     *
     * @return
     */
    public Date getVlcho() {
        return vlcho;
    }

    /**
     *
     * @param vlcho
     */
    public void setVlcho(Date vlcho) {
        this.vlcho = vlcho;
    }

    /**
     *
     * @return
     */
    public String getElcho() {
        return elcho;
    }

    /**
     *
     * @param elcho
     */
    public void setElcho(String elcho) {
        this.elcho = elcho;
    }

    /**
     *
     * @return
     */
    public String getPfcho() {
        return pfcho;
    }

    /**
     *
     * @param pfcho
     */
    public void setPfcho(String pfcho) {
        this.pfcho = pfcho;
    }

    /**
     *
     * @return
     */
    public String getExcho() {
        return excho;
    }

    /**
     *
     * @param excho
     */
    public void setExcho(String excho) {
        this.excho = excho;
    }

    /**
     *
     * @return
     */
    public Date getFxcho() {
        return fxcho;
    }

    /**
     *
     * @param fxcho
     */
    public void setFxcho(Date fxcho) {
        this.fxcho = fxcho;
    }

    /**
     *
     * @return
     */
    public String getPacho() {
        return pacho;
    }

    /**
     *
     * @param pacho
     */
    public void setPacho(String pacho) {
        this.pacho = pacho;
    }

    /**
     *
     * @return
     */
    public String getEscho() {
        return escho;
    }

    /**
     *
     * @param escho
     */
    public void setEscho(String escho) {
        this.escho = escho;
    }

    /**
     *
     * @return
     */
    public String getMdcho() {
        return mdcho;
    }

    /**
     *
     * @param mdcho
     */
    public void setMdcho(String mdcho) {
        this.mdcho = mdcho;
    }

    /**
     *
     * @return
     */
    public String getLocho() {
        return locho;
    }

    /**
     *
     * @param locho
     */
    public void setLocho(String locho) {
        this.locho = locho;
    }

    /**
     *
     * @return
     */
    public String getCocho() {
        return cocho;
    }

    /**
     *
     * @param cocho
     */
    public void setCocho(String cocho) {
        this.cocho = cocho;
    }

    /**
     *
     * @return
     */
    public String getCacho() {
        return cacho;
    }

    /**
     *
     * @param cacho
     */
    public void setCacho(String cacho) {
        this.cacho = cacho;
    }

    /**
     *
     * @return
     */
    public String getNecho() {
        return necho;
    }

    /**
     *
     * @param necho
     */
    public void setNecho(String necho) {
        this.necho = necho;
    }

    /**
     *
     * @return
     */
    public String getNicho() {
        return nicho;
    }

    /**
     *
     * @param nicho
     */
    public void setNicho(String nicho) {
        this.nicho = nicho;
    }

    /**
     *
     * @return
     */
    public String getCpcho() {
        return cpcho;
    }

    /**
     *
     * @param cpcho
     */
    public void setCpcho(String cpcho) {
        this.cpcho = cpcho;
    }

    /**
     *
     * @return
     */
    public String getTdcho() {
        return tdcho;
    }

    /**
     *
     * @param tdcho
     */
    public void setTdcho(String tdcho) {
        this.tdcho = tdcho;
    }

    /**
     *
     * @return
     */
    public String getTccho() {
        return tccho;
    }

    /**
     *
     * @param tccho
     */
    public void setTccho(String tccho) {
        this.tccho = tccho;
    }

    /**
     *
     * @return
     */
    public String getCecho() {
        return cecho;
    }

    /**
     *
     * @param cecho
     */
    public void setCecho(String cecho) {
        this.cecho = cecho;
    }

    /**
     *
     * @return
     */
    public String getStcho() {
        return stcho;
    }

    /**
     *
     * @param stcho
     */
    public void setStcho(String stcho) {
        this.stcho = stcho;
    }

    /**
     *
     * @return
     */
    public Date getFecho() {
        return fecho;
    }

    /**
     *
     * @param fecho
     */
    public void setFecho(Date fecho) {
        this.fecho = fecho;
    }

    /**
     *
     * @return
     */
    public String getUscho() {
        return uscho;
    }

    /**
     *
     * @param uscho
     */
    public void setUscho(String uscho) {
        this.uscho = uscho;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcho != null ? idcho.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Chofe40)) {
            return false;
        }
        Chofe40 other = (Chofe40) object;
        if ((this.idcho == null && other.idcho != null) || (this.idcho != null && !this.idcho.equals(other.idcho))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return EntityToString.getString(this, this.getClass());
        //return "com.hrm.sirhapp.model.Chofe40[ idcho=" + idcho + " ]";
    }

}

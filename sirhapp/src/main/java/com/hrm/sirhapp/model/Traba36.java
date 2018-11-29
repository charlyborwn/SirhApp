package com.hrm.sirhapp.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
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
@Table(name = "TRABA36")
@NamedQueries({
    @NamedQuery(name = "Traba36.findAll", query = "SELECT t FROM Traba36 t")})
public class Traba36 implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDTRA")
    private Integer idtra;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NUTRA")
    private Integer nutra;
    @Size(max = 13)
    @Column(name = "RFTRA")
    private String rftra;
    @Size(max = 11)
    @Column(name = "RITRA")
    private String ritra;
    @Column(name = "FXTRA")
    private Integer fxtra;
    @Size(max = 10)
    @Column(name = "OETRA")
    private String oetra;
    @Size(max = 10)
    @Column(name = "ODTRA")
    private String odtra;
    @Size(max = 10)
    @Column(name = "OCTRA")
    private String octra;
    @Column(name = "PRTRA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date prtra;
    @Size(max = 20)
    @Column(name = "CITRA")
    private String citra;
    @Size(max = 18)
    @Column(name = "CUTRA")
    private String cutra;
    @Column(name = "CCTRA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date cctra;
    @Size(max = 20)
    @Column(name = "COTRA")
    private String cotra;
    @Size(max = 70)
    @Column(name = "SSTRA")
    private String sstra;
    @Column(name = "FSTRA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fstra;
    @Size(max = 70)
    @Column(name = "APTRA")
    private String aptra;
    @Size(max = 70)
    @Column(name = "AMTRA")
    private String amtra;
    @Size(max = 70)
    @Column(name = "NOTRA")
    private String notra;
    @Size(max = 254)
    @Column(name = "PATRA")
    private String patra;
    @Column(name = "FNTRA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fntra;
    @Size(max = 70)
    @Column(name = "SETRA")
    private String setra;
    @Size(max = 70)
    @Column(name = "NATRA")
    private String natra;
    @Size(max = 70)
    @Column(name = "ESTRA")
    private String estra;
    @Size(max = 20)
    @Column(name = "SITRA")
    private String sitra;
    @Size(max = 10)
    @Column(name = "EITRA")
    private String eitra;
    @Size(max = 10)
    @Column(name = "DITRA")
    private String ditra;
    @Column(name = "ENTRA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date entra;
    @Size(max = 20)
    @Column(name = "RETRA")
    private String retra;
    @Column(name = "EXTRA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date extra;
    @Size(max = 20)
    @Column(name = "RXTRA")
    private String rxtra;
    @Column(name = "FITRA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fitra;
    @Size(max = 20)
    @Column(name = "SATRA")
    private String satra;
    @Size(max = 10)
    @Column(name = "EATRA")
    private String eatra;
    @Size(max = 10)
    @Column(name = "DATRA")
    private String datra;
    @Column(name = "FPTRA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fptra;
    @Column(name = "FUTRA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date futra;
    @Column(name = "FRTRA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date frtra;
    @Size(max = 2)
    @Column(name = "STTRA")
    private String sttra;
    @Column(name = "FETRA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fetra;
    @Size(max = 10)
    @Column(name = "USTRA")
    private String ustra;
    @Transient
    private String nctra;
    @Transient
    private String edtra;
    @Transient
    private String fntra2;

    /**
     *
     */
    public Traba36() {
    }

    /**
     *
     * @param idtra
     */
    public Traba36(Integer idtra) {
        this.idtra = idtra;
    }

    /**
     *
     * @param idtra
     * @param nutra
     */
    public Traba36(Integer idtra, Integer nutra) {
        this.idtra = idtra;
        this.nutra = nutra;
    }

    /**
     *
     * @return
     */
    public String getNctra() {
        String localNctra = "";
        if (aptra != null) {
            localNctra = aptra;
        }
        if (amtra != null) {
            localNctra = localNctra + " " + amtra;
        }
        if (notra != null) {
            localNctra = localNctra + " " + notra;
        }
        this.nctra = localNctra.toUpperCase();

        return nctra;
    }

    /**
     *
     * @param nctra
     */
    public void setNctra(String nctra) {
        this.nctra = nctra;
    }

    /**
     *
     * @return
     */
    public String getEdtra() {
        if (fntra != null) {

            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String date = new SimpleDateFormat("dd/MM/yyyy").format(fntra);

            LocalDate fechaNac = LocalDate.parse(date, fmt);

            LocalDate ahora = LocalDate.now();

            Period periodo = Period.between(fechaNac, ahora);

            this.edtra = Integer.toString(periodo.getYears());

        }
        return edtra;
    }

    /**
     *
     * @param edtra
     */
    public void setEdtra(String edtra) {
        this.edtra = edtra;
    }

    /**
     *
     * @return
     */
    public String getFntra2() {
        return fntra2;
    }

    /**
     *
     * @param fntra2
     */
    public void setFntra2(String fntra2) {
        this.fntra2 = fntra2;
    }

    /**
     *
     * @return
     */
    public String getRftra() {
        return rftra;
    }

    /**
     *
     * @param rftra
     */
    public void setRftra(String rftra) {
        this.rftra = rftra;
    }

    /**
     *
     * @return
     */
    public String getCutra() {
        return cutra;
    }

    /**
     *
     * @param cutra
     */
    public void setCutra(String cutra) {
        this.cutra = cutra;
    }

    /**
     *
     * @return
     */
    public Date getCctra() {
        return cctra;
    }

    /**
     *
     * @param cctra
     */
    public void setCctra(Date cctra) {
        this.cctra = cctra;
    }

    /**
     *
     * @return
     */
    public Integer getIdtra() {
        return idtra;
    }

    /**
     *
     * @param idtra
     */
    public void setIdtra(Integer idtra) {
        this.idtra = idtra;
    }

    /**
     *
     * @return
     */
    public String getCotra() {
        return cotra;
    }

    /**
     *
     * @param cotra
     */
    public void setCotra(String cotra) {
        this.cotra = cotra;
    }

    /**
     *
     * @return
     */
    public String getSstra() {
        return sstra;
    }

    /**
     *
     * @param sstra
     */
    public void setSstra(String sstra) {
        this.sstra = sstra;
    }

    /**
     *
     * @return
     */
    public Date getFstra() {
        return fstra;
    }

    /**
     *
     * @param fstra
     */
    public void setFstra(Date fstra) {
        this.fstra = fstra;
    }

    /**
     *
     * @return
     */
    public String getAptra() {
        return aptra;
    }

    /**
     *
     * @param aptra
     */
    public void setAptra(String aptra) {
        this.aptra = aptra;
    }

    /**
     *
     * @return
     */
    public String getAmtra() {
        return amtra;
    }

    /**
     *
     * @param amtra
     */
    public void setAmtra(String amtra) {
        this.amtra = amtra;
    }

    /**
     *
     * @return
     */
    public String getNotra() {
        return notra;
    }

    /**
     *
     * @param notra
     */
    public void setNotra(String notra) {
        this.notra = notra;
    }

    /**
     *
     * @return
     */
    public String getPatra() {
        if (patra == null) {
            this.patra = "";
        }
        return patra;
    }

    /**
     *
     * @param patra
     */
    public void setPatra(String patra) {
        this.patra = patra;
    }

    /**
     *
     * @return
     */
    public Date getFntra() {
        return fntra;
    }

    /**
     *
     * @param fntra
     */
    public void setFntra(Date fntra) {
        this.fntra = fntra;
    }

    /**
     *
     * @return
     */
    public String getSetra() {
        return setra;
    }

    /**
     *
     * @param setra
     */
    public void setSetra(String setra) {
        this.setra = setra;
    }

    /**
     *
     * @return
     */
    public String getNatra() {
        return natra;
    }

    /**
     *
     * @param natra
     */
    public void setNatra(String natra) {
        this.natra = natra;
    }

    /**
     *
     * @return
     */
    public String getEstra() {
        return estra;
    }

    /**
     *
     * @param estra
     */
    public void setEstra(String estra) {
        this.estra = estra;
    }

    /**
     *
     * @return
     */
    public String getSitra() {
        return sitra;
    }

    /**
     *
     * @param sitra
     */
    public void setSitra(String sitra) {
        this.sitra = sitra;
    }

    /**
     *
     * @return
     */
    public String getEitra() {
        return eitra;
    }

    /**
     *
     * @param eitra
     */
    public void setEitra(String eitra) {
        this.eitra = eitra;
    }

    /**
     *
     * @return
     */
    public String getDitra() {
        return ditra;
    }

    /**
     *
     * @param ditra
     */
    public void setDitra(String ditra) {
        this.ditra = ditra;
    }

    /**
     *
     * @return
     */
    public Date getEntra() {
        return entra;
    }

    /**
     *
     * @param entra
     */
    public void setEntra(Date entra) {
        this.entra = entra;
    }

    /**
     *
     * @return
     */
    public String getRetra() {
        return retra;
    }

    /**
     *
     * @param retra
     */
    public void setRetra(String retra) {
        this.retra = retra;
    }

    /**
     *
     * @return
     */
    public Date getExtra() {
        return extra;
    }

    /**
     *
     * @param extra
     */
    public void setExtra(Date extra) {
        this.extra = extra;
    }

    /**
     *
     * @return
     */
    public String getRxtra() {
        return rxtra;
    }

    /**
     *
     * @param rxtra
     */
    public void setRxtra(String rxtra) {
        this.rxtra = rxtra;
    }

    /**
     *
     * @return
     */
    public Date getFitra() {
        return fitra;
    }

    /**
     *
     * @param fitra
     */
    public void setFitra(Date fitra) {
        this.fitra = fitra;
    }

    /**
     *
     * @return
     */
    public String getSatra() {
        return satra;
    }

    /**
     *
     * @param satra
     */
    public void setSatra(String satra) {
        this.satra = satra;
    }

    /**
     *
     * @return
     */
    public String getEatra() {
        return eatra;
    }

    /**
     *
     * @param eatra
     */
    public void setEatra(String eatra) {
        this.eatra = eatra;
    }

    /**
     *
     * @return
     */
    public String getDatra() {
        return datra;
    }

    /**
     *
     * @param datra
     */
    public void setDatra(String datra) {
        this.datra = datra;
    }

    /**
     *
     * @return
     */
    public Date getFptra() {
        return fptra;
    }

    /**
     *
     * @param fptra
     */
    public void setFptra(Date fptra) {
        this.fptra = fptra;
    }

    /**
     *
     * @return
     */
    public Date getFutra() {
        return futra;
    }

    /**
     *
     * @param futra
     */
    public void setFutra(Date futra) {
        this.futra = futra;
    }

    /**
     *
     * @return
     */
    public Date getFrtra() {
        return frtra;
    }

    /**
     *
     * @param frtra
     */
    public void setFrtra(Date frtra) {
        this.frtra = frtra;
    }

    /**
     *
     * @return
     */
    public String getSttra() {
        return sttra;
    }

    /**
     *
     * @param sttra
     */
    public void setSttra(String sttra) {
        this.sttra = sttra;
    }

    /**
     *
     * @return
     */
    public Date getFetra() {
        return fetra;
    }

    /**
     *
     * @param fetra
     */
    public void setFetra(Date fetra) {
        this.fetra = fetra;
    }

    /**
     *
     * @return
     */
    public String getUstra() {
        return ustra;
    }

    /**
     *
     * @param ustra
     */
    public void setUstra(String ustra) {
        this.ustra = ustra;
    }

    /**
     *
     * @return
     */
    public String getRitra() {
        return ritra;
    }

    /**
     *
     * @param ritra
     */
    public void setRitra(String ritra) {
        this.ritra = ritra;
    }

    /**
     *
     * @return
     */
    public Integer getFxtra() {
        return fxtra;
    }

    /**
     *
     * @param fxtra
     */
    public void setFxtra(Integer fxtra) {
        this.fxtra = fxtra;
    }

    /**
     *
     * @return
     */
    public String getOetra() {
        return oetra;
    }

    /**
     *
     * @param oetra
     */
    public void setOetra(String oetra) {
        this.oetra = oetra;
    }

    /**
     *
     * @return
     */
    public String getOdtra() {
        return odtra;
    }

    /**
     *
     * @param odtra
     */
    public void setOdtra(String odtra) {
        this.odtra = odtra;
    }

    /**
     *
     * @return
     */
    public String getOctra() {
        return octra;
    }

    /**
     *
     * @param octra
     */
    public void setOctra(String octra) {
        this.octra = octra;
    }

    /**
     *
     * @return
     */
    public Date getPrtra() {
        return prtra;
    }

    /**
     *
     * @param prtra
     */
    public void setPrtra(Date prtra) {
        this.prtra = prtra;
    }

    /**
     *
     * @return
     */
    public Integer getNutra() {
        return nutra;
    }

    /**
     *
     * @param nutra
     */
    public void setNutra(Integer nutra) {
        this.nutra = nutra;
    }

    /**
     *
     * @return
     */
    public String getCitra() {
        return citra;
    }

    /**
     *
     * @param citra
     */
    public void setCitra(String citra) {
        this.citra = citra;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtra != null ? idtra.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Traba36)) {
            return false;
        }
        Traba36 other = (Traba36) object;
        if ((this.idtra == null && other.idtra != null) || (this.idtra != null && !this.idtra.equals(other.idtra))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return EntityToString.getString(this, this.getClass());
        //return "com.hrm.sirhapp.model.Traba36[ idtra=" + idtra + " ]";
    }

}

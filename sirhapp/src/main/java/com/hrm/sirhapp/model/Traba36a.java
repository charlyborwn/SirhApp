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
import org.apache.commons.io.FilenameUtils;

/**
 *
 * @author Carlos Bernal carlose.bernalh@gmail.com
 * Copyright 2018 Carlos Ernesto Bernal Hernández.
 * SirhApp
 * 
 */
@Entity
@Table(name = "TRABA36A")
@NamedQueries({
    @NamedQuery(name = "Traba36a.findAll", query = "SELECT t FROM Traba36a t")})
public class Traba36a implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDTRA_A")
    private Integer idtraA;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 13)
    @Column(name = "RFTRA_A")
    private String rftraA;
    @Size(max = 11)
    @Column(name = "RITRA_A")
    private String ritraA;
    @Column(name = "FXTRA_A")
    private Integer fxtraA;
    @Size(max = 10)
    @Column(name = "OETRA_A")
    private String oetraA;
    @Size(max = 10)
    @Column(name = "ODTRA_A")
    private String odtraA;
    @Size(max = 10)
    @Column(name = "OCTRA_A")
    private String octraA;
    @Column(name = "PRTRA_A")
    @Temporal(TemporalType.TIMESTAMP)
    private Date prtraA;

    @Size(min = 1, max = 18)
    @Column(name = "CUTRA_A")
    private String cutraA;

    @Column(name = "CCTRA_A")
    @Temporal(TemporalType.TIMESTAMP)
    private Date cctraA;

    @Size(min = 1, max = 70)
    @Column(name = "APTRA_A")
    private String aptraA;

    @Size(min = 1, max = 70)
    @Column(name = "AMTRA_A")
    private String amtraA;

    @Size(min = 1, max = 70)
    @Column(name = "NOTRA_A")
    private String notraA;
    @Size(max = 70)
    @Column(name = "PATRA_A")
    private String patraA;

    @Column(name = "FNTRA_A")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fntraA;
    @Size(min = 1, max = 70)
    @Column(name = "SETRA_A")
    private String setraA;
    @Size(min = 1, max = 70)
    @Column(name = "NATRA_A")
    private String natraA;
    @Size(min = 1, max = 70)
    @Column(name = "ESTRA_A")
    private String estraA;
    @Size(min = 1, max = 20)
    @Column(name = "SITRA_A")
    private String sitraA;
    @Size(min = 1, max = 10)
    @Column(name = "EITRA_A")
    private String eitraA;
    @Size(min = 1, max = 10)
    @Column(name = "DITRA_A")
    private String ditraA;
    @Column(name = "ENTRA_A")
    @Temporal(TemporalType.TIMESTAMP)
    private Date entraA;
    @Size(max = 20)
    @Column(name = "RETRA_A")
    private String retraA;
    @Column(name = "EXTRA_A")
    @Temporal(TemporalType.TIMESTAMP)
    private Date extraA;
    @Size(max = 20)
    @Column(name = "RXTRA_A")
    private String rxtraA;
    @Size(max = 2)
    @Column(name = "STTRA_A")
    private String sttraA;
    @Column(name = "FETRA_A")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fetraA;
    @Size(max = 10)
    @Column(name = "USTRA_A")
    private String ustraA;
    @Transient
    private String nctraA;
    @Transient
    private String edtraA;
    @Transient
    private String fntraA2;
    @Transient
    private Boolean fttraA;

    /**
     *
     */
    public Traba36a() {
    }

    /**
     *
     * @param idtraA
     */
    public Traba36a(Integer idtraA) {
        this.idtraA = idtraA;
    }

    /**
     *
     * @param idtraA
     * @param rftraA
     */
    public Traba36a(Integer idtraA, String rftraA) {
        this.idtraA = idtraA;
        this.rftraA = rftraA;
    }

    /**
     *
     * @return
     */
    public String getRitraA() {
        return ritraA;
    }

    /**
     *
     * @param ritraA
     */
    public void setRitraA(String ritraA) {
        this.ritraA = ritraA;
    }

    /**
     *
     * @return
     */
    public String getNctraA() {
        String localNctra = "";
        if (aptraA != null) {
            localNctra = aptraA;
        }
        if (amtraA != null) {
            localNctra = localNctra + " " + amtraA;
        }
        if (notraA != null) {
            localNctra = localNctra + " " + notraA;
        }
        this.nctraA = localNctra.toUpperCase();
        return nctraA;
    }

    /**
     *
     * @param nctraA
     */
    public void setNctraA(String nctraA) {
       
        this.nctraA = nctraA;
    }

    /**
     *
     * @return
     */
    public String getEdtraA() {

        if (fntraA != null) {

            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String date = new SimpleDateFormat("dd/MM/yyyy").format(fntraA);

            LocalDate fechaNac = LocalDate.parse(date, fmt);

            LocalDate ahora = LocalDate.now();

            Period periodo = Period.between(fechaNac, ahora);

            this.edtraA = Integer.toString(periodo.getYears());

        }

        return edtraA;
    }

    /**
     *
     * @param edtraA
     */
    public void setEdtraA(String edtraA) {
        if (fntraA != null) {

            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String date = new SimpleDateFormat("dd/MM/yyyy").format(fntraA);

            LocalDate fechaNac = LocalDate.parse(date, fmt);

            LocalDate ahora = LocalDate.now();

            Period periodo = Period.between(fechaNac, ahora);

            this.edtraA = Integer.toString(periodo.getYears());

        }
        this.edtraA = edtraA;
    }

    /**
     *
     * @return
     */
    public String getFntraA2() {
        if (fntraA != null) {

            String date = new SimpleDateFormat("dd/MM/yyyy").format(fntraA);

            this.fntraA2 = "" + date;

        }
        return fntraA2;
    }

    /**
     *
     * @param fntraA2
     */
    public void setFntraA2(String fntraA2) {
        if (fntraA != null) {

            String date = new SimpleDateFormat("dd/MM/yyyy").format(fntraA);

            this.fntraA2 = "" + date;

        }
        this.fntraA2 = fntraA2;
    }

    /**
     *
     * @return
     */
    public String getRftraA() {
        return rftraA;
    }

    /**
     *
     * @param rftraA
     */
    public void setRftraA(String rftraA) {
        this.rftraA = rftraA;
    }

    /**
     *
     * @return
     */
    public String getCutraA() {
        return cutraA;
    }

    /**
     *
     * @param cutraA
     */
    public void setCutraA(String cutraA) {
        this.cutraA = cutraA;
    }

    /**
     *
     * @return
     */
    public Date getCctraA() {
        return cctraA;
    }

    /**
     *
     * @param cctraA
     */
    public void setCctraA(Date cctraA) {
        this.cctraA = cctraA;
    }

    /**
     *
     * @return
     */
    public String getAptraA() {
        return aptraA;
    }

    /**
     *
     * @param aptraA
     */
    public void setAptraA(String aptraA) {
        this.aptraA = aptraA;
    }

    /**
     *
     * @return
     */
    public String getAmtraA() {
        return amtraA;
    }

    /**
     *
     * @param amtraA
     */
    public void setAmtraA(String amtraA) {
        this.amtraA = amtraA;
    }

    /**
     *
     * @return
     */
    public String getNotraA() {
        return notraA;
    }

    /**
     *
     * @param notraA
     */
    public void setNotraA(String notraA) {
        this.notraA = notraA;
    }

    /**
     *
     * @return
     */
    public String getPatraA() {
        if (patraA == null) {
            this.patraA = "";
        }
        return patraA;
    }

    /**
     *
     * @param patraA
     */
    public void setPatraA(String patraA) {
        this.patraA = patraA;
    }

    /**
     *
     * @return
     */
    public Date getFntraA() {
        return fntraA;
    }

    /**
     *
     * @param fntraA
     */
    public void setFntraA(Date fntraA) {
        this.fntraA = fntraA;
    }

    /**
     *
     * @return
     */
    public String getSetraA() {
        return setraA;
    }

    /**
     *
     * @param setraA
     */
    public void setSetraA(String setraA) {
        this.setraA = setraA;
    }

    /**
     *
     * @return
     */
    public String getNatraA() {
        return natraA;
    }

    /**
     *
     * @param natraA
     */
    public void setNatraA(String natraA) {
        this.natraA = natraA;
    }

    /**
     *
     * @return
     */
    public String getEstraA() {
        return estraA;
    }

    /**
     *
     * @param estraA
     */
    public void setEstraA(String estraA) {
        this.estraA = estraA;
    }

    /**
     *
     * @return
     */
    public String getSitraA() {
        return sitraA;
    }

    /**
     *
     * @param sitraA
     */
    public void setSitraA(String sitraA) {
        this.sitraA = sitraA;
    }

    /**
     *
     * @return
     */
    public String getEitraA() {
        return eitraA;
    }

    /**
     *
     * @param eitraA
     */
    public void setEitraA(String eitraA) {
        this.eitraA = eitraA;
    }

    /**
     *
     * @return
     */
    public String getDitraA() {
        return ditraA;
    }

    /**
     *
     * @param ditraA
     */
    public void setDitraA(String ditraA) {
        this.ditraA = ditraA;
    }

    /**
     *
     * @return
     */
    public Date getEntraA() {
        return entraA;
    }

    /**
     *
     * @param entraA
     */
    public void setEntraA(Date entraA) {
        this.entraA = entraA;
    }

    /**
     *
     * @return
     */
    public String getRetraA() {
        return retraA;
    }

    /**
     *
     * @param retraA
     */
    public void setRetraA(String retraA) {
        this.retraA = retraA;
    }

    /**
     *
     * @return
     */
    public Date getExtraA() {
        return extraA;
    }

    /**
     *
     * @param extraA
     */
    public void setExtraA(Date extraA) {
        this.extraA = extraA;
    }

    /**
     *
     * @return
     */
    public String getRxtraA() {
        return rxtraA;
    }

    /**
     *
     * @param rxtraA
     */
    public void setRxtraA(String rxtraA) {
        this.rxtraA = rxtraA;
    }

    /**
     *
     * @return
     */
    public String getSttraA() {
        return sttraA;
    }

    /**
     *
     * @param sttraA
     */
    public void setSttraA(String sttraA) {
        this.sttraA = sttraA;
    }

    /**
     *
     * @return
     */
    public Date getFetraA() {
        return fetraA;
    }

    /**
     *
     * @param fetraA
     */
    public void setFetraA(Date fetraA) {
        this.fetraA = fetraA;
    }

    /**
     *
     * @return
     */
    public String getUstraA() {
        return ustraA;
    }

    /**
     *
     * @param ustraA
     */
    public void setUstraA(String ustraA) {
        this.ustraA = ustraA;
    }

    /**
     *
     * @return
     */
    public Integer getFxtraA() {
        return fxtraA;
    }

    /**
     *
     * @param fxtraA
     */
    public void setFxtraA(Integer fxtraA) {
        this.fxtraA = fxtraA;
    }

    /**
     *
     * @return
     */
    public String getOetraA() {
        return oetraA;
    }

    /**
     *
     * @param oetraA
     */
    public void setOetraA(String oetraA) {
        this.oetraA = oetraA;
    }

    /**
     *
     * @return
     */
    public String getOdtraA() {
        return odtraA;
    }

    /**
     *
     * @param odtraA
     */
    public void setOdtraA(String odtraA) {
        this.odtraA = odtraA;
    }

    /**
     *
     * @return
     */
    public String getOctraA() {
        return octraA;
    }

    /**
     *
     * @param octraA
     */
    public void setOctraA(String octraA) {
        this.octraA = octraA;
    }

    /**
     *
     * @return
     */
    public Date getPrtraA() {

        return prtraA;
    }

    /**
     *
     * @param prtraA
     */
    public void setPrtraA(Date prtraA) {
        this.prtraA = prtraA;
    }

    /**
     *
     * @return
     */
    public Integer getIdtraA() {
        return idtraA;
    }

    /**
     *
     * @param idtraA
     */
    public void setIdtraA(Integer idtraA) {
        this.idtraA = idtraA;
    }

    /**
     *
     * @return
     */
    public Boolean getFttraA() {
        if (this.fttraA == null && patraA != null && patraA.length() > 0) {
            return FilenameUtils.getExtension(patraA).equals("pdf");

        }
        return fttraA;
    }

    /**
     *
     * @param fttraA
     */
    public void setFttraA(Boolean fttraA) {
        this.fttraA = fttraA;
    }

    @Override
    public int hashCode() {
        return (idtraA != null)
             ? (Traba36a.class.hashCode() + idtraA.hashCode())
             : super.hashCode();
    }

    @Override
    public boolean equals(Object object) {
       return (object instanceof Traba36a) && (idtraA != null) 
             ? idtraA.equals(((Traba36a) object).idtraA) 
             : (object == this);
    }

    @Override
    public String toString() {
        return EntityToString.getString(this, this.getClass());
        //return "com.hrm.sirhapp.model.Traba36a[ idtraA=" + idtraA + " ]";
    }

}

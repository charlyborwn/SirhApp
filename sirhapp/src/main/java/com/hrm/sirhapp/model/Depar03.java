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
@Table(name = "DEPAR03")
@NamedQueries({
    @NamedQuery(name = "Depar03.findAll", query = "SELECT d FROM Depar03 d")})
public class Depar03 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDDEP")
    private Integer iddep;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "NUDEP")
    private String nudep;
    @Size(max = 10)
    @Column(name = "CEDEP")
    private String cedep;
    @Size(max = 70)
    @Column(name = "NEDEP")
    private String nedep;
    @Size(max = 20)
    @Column(name = "SEDEP")
    private String sedep;
    @Column(name = "ORDEP")
    private Integer ordep;
    @Size(max = 10)
    @Column(name = "CODEP")
    private String codep;
    @Size(max = 70)
    @Column(name = "NODEP")
    private String nodep;
    @Size(max = 10)
    @Column(name = "CSDEP")
    private String csdep;
    @Size(max = 10)
    @Column(name = "CCDEP")
    private String ccdep;
    @Size(max = 10)
    @Column(name = "SUDEP")
    private String sudep;
    @Size(max = 70)
    @Column(name = "NSDEP")
    private String nsdep;
    @Size(max = 2)
    @Column(name = "STDEP")
    private String stdep;
    @Column(name = "FEDEP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fedep;
    @Size(max = 10)
    @Column(name = "USDEP")
    private String usdep;

    /**
     *
     */
    public Depar03() {
    }

    /**
     *
     * @param iddep
     */
    public Depar03(Integer iddep) {
        this.iddep = iddep;
    }

    /**
     *
     * @param iddep
     * @param nudep
     */
    public Depar03(Integer iddep, String nudep) {
        this.iddep = iddep;
        this.nudep = nudep;
    }

    /**
     *
     * @return
     */
    public Integer getIddep() {
        return iddep;
    }

    /**
     *
     * @param iddep
     */
    public void setIddep(Integer iddep) {
        this.iddep = iddep;
    }

    /**
     *
     * @return
     */
    public String getNudep() {
        return nudep;
    }

    /**
     *
     * @param nudep
     */
    public void setNudep(String nudep) {
        this.nudep = nudep;
    }

    /**
     *
     * @return
     */
    public String getCedep() {
        return cedep;
    }

    /**
     *
     * @param cedep
     */
    public void setCedep(String cedep) {
        this.cedep = cedep;
    }

    /**
     *
     * @return
     */
    public String getNedep() {
        return nedep;
    }

    /**
     *
     * @param nedep
     */
    public void setNedep(String nedep) {
        this.nedep = nedep;
    }

    /**
     *
     * @return
     */
    public String getSedep() {
        return sedep;
    }

    /**
     *
     * @param sedep
     */
    public void setSedep(String sedep) {
        this.sedep = sedep;
    }

    /**
     *
     * @return
     */
    public Integer getOrdep() {
        return ordep;
    }

    /**
     *
     * @param ordep
     */
    public void setOrdep(Integer ordep) {
        this.ordep = ordep;
    }

    /**
     *
     * @return
     */
    public String getCodep() {
        return codep;
    }

    /**
     *
     * @param codep
     */
    public void setCodep(String codep) {
        this.codep = codep;
    }

    /**
     *
     * @return
     */
    public String getNodep() {
        return nodep;
    }

    /**
     *
     * @param nodep
     */
    public void setNodep(String nodep) {
        this.nodep = nodep;
    }

    /**
     *
     * @return
     */
    public String getCsdep() {
        return csdep;
    }

    /**
     *
     * @param csdep
     */
    public void setCsdep(String csdep) {
        this.csdep = csdep;
    }

    /**
     *
     * @return
     */
    public String getCcdep() {
        return ccdep;
    }

    /**
     *
     * @param ccdep
     */
    public void setCcdep(String ccdep) {
        this.ccdep = ccdep;
    }

    /**
     *
     * @return
     */
    public String getSudep() {
        return sudep;
    }

    /**
     *
     * @param sudep
     */
    public void setSudep(String sudep) {
        this.sudep = sudep;
    }

    /**
     *
     * @return
     */
    public String getNsdep() {
        return nsdep;
    }

    /**
     *
     * @param nsdep
     */
    public void setNsdep(String nsdep) {
        this.nsdep = nsdep;
    }

    /**
     *
     * @return
     */
    public String getStdep() {
        return stdep;
    }

    /**
     *
     * @param stdep
     */
    public void setStdep(String stdep) {
        this.stdep = stdep;
    }

    /**
     *
     * @return
     */
    public Date getFedep() {
        return fedep;
    }

    /**
     *
     * @param fedep
     */
    public void setFedep(Date fedep) {
        this.fedep = fedep;
    }

    /**
     *
     * @return
     */
    public String getUsdep() {
        return usdep;
    }

    /**
     *
     * @param usdep
     */
    public void setUsdep(String usdep) {
        this.usdep = usdep;
    }

    @Override
    public int hashCode() {
        return (iddep != null)
                ? (Depar03.class.hashCode() + iddep.hashCode())
                : super.hashCode();
    }

    @Override
    public boolean equals(Object object) {
        return (object instanceof Depar03) && (iddep != null)
                ? iddep.equals(((Depar03) object).iddep)
                : (object == this);
    }

    @Override
    public String toString() {
        return EntityToString.getString(this, this.getClass());
        //return "com.hrm.sirhapp.model.Depar03[ iddep=" + iddep + " ]";
    }

}

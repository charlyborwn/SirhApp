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
@Table(name = "ESCOL30")
@NamedQueries({
    @NamedQuery(name = "Escol30.findAll", query = "SELECT e FROM Escol30 e")})
public class Escol30 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDESC")
    private Integer idesc;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NTESC")
    private int ntesc;
    @Size(max = 70)
    @Column(name = "TNESC")
    private String tnesc;
    @Size(max = 70)
    @Column(name = "NIESC")
    private String niesc;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "ACESC")
    private Float acesc;
    @Size(max = 2)
    @Column(name = "CAESC")
    private String caesc;
    @Size(max = 70)
    @Column(name = "HCESC")
    private String hcesc;
    @Size(max = 2)
    @Column(name = "CEESC")
    private String ceesc;
    @Size(max = 70)
    @Column(name = "ESESC")
    private String esesc;
    @Size(max = 70)
    @Column(name = "TDESC")
    private String tdesc;
    @Size(max = 6)
    @Column(name = "W1ESC")
    private String w1esc;
    @Size(max = 70)
    @Column(name = "PAESC")
    private String paesc;
    @Size(max = 250)
    @Column(name = "OBESC")
    private String obesc;
    @Size(max = 2)
    @Column(name = "STESC")
    private String stesc;
    @Column(name = "FEESC")
    @Temporal(TemporalType.TIMESTAMP)
    private Date feesc;
    @Size(max = 10)
    @Column(name = "USESC")
    private String usesc;

    /**
     *
     */
    public Escol30() {
    }

    /**
     *
     * @param idesc
     */
    public Escol30(Integer idesc) {
        this.idesc = idesc;
    }

    /**
     *
     * @param idesc
     * @param ntesc
     */
    public Escol30(Integer idesc, int ntesc) {
        this.idesc = idesc;
        this.ntesc = ntesc;
    }

    /**
     *
     * @return
     */
    public Integer getIdesc() {
        return idesc;
    }

    /**
     *
     * @param idesc
     */
    public void setIdesc(Integer idesc) {
        this.idesc = idesc;
    }

    /**
     *
     * @return
     */
    public int getNtesc() {
        return ntesc;
    }

    /**
     *
     * @param ntesc
     */
    public void setNtesc(int ntesc) {
        this.ntesc = ntesc;
    }

    /**
     *
     * @return
     */
    public String getTnesc() {
        return tnesc;
    }

    /**
     *
     * @param tnesc
     */
    public void setTnesc(String tnesc) {
        this.tnesc = tnesc;
    }

    /**
     *
     * @return
     */
    public String getNiesc() {
        return niesc;
    }

    /**
     *
     * @param niesc
     */
    public void setNiesc(String niesc) {
        this.niesc = niesc;
    }

    /**
     *
     * @return
     */
    public Float getAcesc() {
        return acesc;
    }

    /**
     *
     * @param acesc
     */
    public void setAcesc(Float acesc) {
        this.acesc = acesc;
    }

    /**
     *
     * @return
     */
    public String getCaesc() {
        return caesc;
    }

    /**
     *
     * @param caesc
     */
    public void setCaesc(String caesc) {
        this.caesc = caesc;
    }

    /**
     *
     * @return
     */
    public String getHcesc() {
        return hcesc;
    }

    /**
     *
     * @param hcesc
     */
    public void setHcesc(String hcesc) {
        this.hcesc = hcesc;
    }

    /**
     *
     * @return
     */
    public String getCeesc() {
        return ceesc;
    }

    /**
     *
     * @param ceesc
     */
    public void setCeesc(String ceesc) {
        this.ceesc = ceesc;
    }

    /**
     *
     * @return
     */
    public String getEsesc() {
        return esesc;
    }

    /**
     *
     * @param esesc
     */
    public void setEsesc(String esesc) {
        this.esesc = esesc;
    }

    /**
     *
     * @return
     */
    public String getTdesc() {
        return tdesc;
    }

    /**
     *
     * @param tdesc
     */
    public void setTdesc(String tdesc) {
        this.tdesc = tdesc;
    }

    /**
     *
     * @return
     */
    public String getW1esc() {
        return w1esc;
    }

    /**
     *
     * @param w1esc
     */
    public void setW1esc(String w1esc) {
        this.w1esc = w1esc;
    }

    /**
     *
     * @return
     */
    public String getPaesc() {
        return paesc;
    }

    /**
     *
     * @param paesc
     */
    public void setPaesc(String paesc) {
        this.paesc = paesc;
    }

    /**
     *
     * @return
     */
    public String getObesc() {
        return obesc;
    }

    /**
     *
     * @param obesc
     */
    public void setObesc(String obesc) {
        this.obesc = obesc;
    }

    /**
     *
     * @return
     */
    public String getStesc() {
        return stesc;
    }

    /**
     *
     * @param stesc
     */
    public void setStesc(String stesc) {
        this.stesc = stesc;
    }

    /**
     *
     * @return
     */
    public Date getFeesc() {
        return feesc;
    }

    /**
     *
     * @param feesc
     */
    public void setFeesc(Date feesc) {
        this.feesc = feesc;
    }

    /**
     *
     * @return
     */
    public String getUsesc() {
        return usesc;
    }

    /**
     *
     * @param usesc
     */
    public void setUsesc(String usesc) {
        this.usesc = usesc;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idesc != null ? idesc.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Escol30)) {
            return false;
        }
        Escol30 other = (Escol30) object;
        if ((this.idesc == null && other.idesc != null) || (this.idesc != null && !this.idesc.equals(other.idesc))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return EntityToString.getString(this,this.getClass());
        //return "com.hrm.sirhapp.model.Escol30[ idesc=" + idesc + " ]";
    }
    
}

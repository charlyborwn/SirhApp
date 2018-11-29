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
@Table(name = "INVEN37")
@NamedQueries({
    @NamedQuery(name = "Inven37.findAll", query = "SELECT i FROM Inven37 i")})
public class Inven37 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDINV")
    private Integer idinv;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "CEINV")
    private String ceinv;
    @Size(max = 70)
    @Column(name = "NEINV")
    private String neinv;
    @Column(name = "FIINV")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fiinv;
    @Size(max = 10)
    @Column(name = "TIINV")
    private String tiinv;
    @Size(max = 13)
    @Column(name = "RFINV")
    private String rfinv;
    @Size(max = 30)
    @Column(name = "NCINV")
    private String ncinv;
    @Column(name = "FAINV")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fainv;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "PUINV")
    private Float puinv;
    @Size(max = 30)
    @Column(name = "ARINV")
    private String arinv;
    @Size(max = 30)
    @Column(name = "MAINV")
    private String mainv;
    @Size(max = 30)
    @Column(name = "MOINV")
    private String moinv;
    @Size(max = 30)
    @Column(name = "COINV")
    private String coinv;
    @Size(max = 4)
    @Column(name = "TAINV")
    private String tainv;
    @Column(name = "CAINV")
    private Integer cainv;
    @Column(name = "NTINV")
    private Integer ntinv;
    @Size(max = 70)
    @Column(name = "NNINV")
    private String nninv;
    @Size(max = 2)
    @Column(name = "STINV")
    private String stinv;
    @Column(name = "FEINV")
    @Temporal(TemporalType.TIMESTAMP)
    private Date feinv;
    @Size(max = 10)
    @Column(name = "USINV")
    private String usinv;

    /**
     *
     */
    public Inven37() {
    }

    /**
     *
     * @param idinv
     */
    public Inven37(Integer idinv) {
        this.idinv = idinv;
    }

    /**
     *
     * @param idinv
     * @param ceinv
     */
    public Inven37(Integer idinv, String ceinv) {
        this.idinv = idinv;
        this.ceinv = ceinv;
    }

    /**
     *
     * @return
     */
    public Integer getIdinv() {
        return idinv;
    }

    /**
     *
     * @param idinv
     */
    public void setIdinv(Integer idinv) {
        this.idinv = idinv;
    }

    /**
     *
     * @return
     */
    public String getCeinv() {
        return ceinv;
    }

    /**
     *
     * @param ceinv
     */
    public void setCeinv(String ceinv) {
        this.ceinv = ceinv;
    }

    /**
     *
     * @return
     */
    public String getNeinv() {
        return neinv;
    }

    /**
     *
     * @param neinv
     */
    public void setNeinv(String neinv) {
        this.neinv = neinv;
    }

    /**
     *
     * @return
     */
    public Date getFiinv() {
        return fiinv;
    }

    /**
     *
     * @param fiinv
     */
    public void setFiinv(Date fiinv) {
        this.fiinv = fiinv;
    }

    /**
     *
     * @return
     */
    public String getTiinv() {
        return tiinv;
    }

    /**
     *
     * @param tiinv
     */
    public void setTiinv(String tiinv) {
        this.tiinv = tiinv;
    }

    /**
     *
     * @return
     */
    public String getRfinv() {
        return rfinv;
    }

    /**
     *
     * @param rfinv
     */
    public void setRfinv(String rfinv) {
        this.rfinv = rfinv;
    }

    /**
     *
     * @return
     */
    public String getNcinv() {
        return ncinv;
    }

    /**
     *
     * @param ncinv
     */
    public void setNcinv(String ncinv) {
        this.ncinv = ncinv;
    }

    /**
     *
     * @return
     */
    public Date getFainv() {
        return fainv;
    }

    /**
     *
     * @param fainv
     */
    public void setFainv(Date fainv) {
        this.fainv = fainv;
    }

    /**
     *
     * @return
     */
    public Float getPuinv() {
        return puinv;
    }

    /**
     *
     * @param puinv
     */
    public void setPuinv(Float puinv) {
        this.puinv = puinv;
    }

    /**
     *
     * @return
     */
    public String getArinv() {
        return arinv;
    }

    /**
     *
     * @param arinv
     */
    public void setArinv(String arinv) {
        this.arinv = arinv;
    }

    /**
     *
     * @return
     */
    public String getMainv() {
        return mainv;
    }

    /**
     *
     * @param mainv
     */
    public void setMainv(String mainv) {
        this.mainv = mainv;
    }

    /**
     *
     * @return
     */
    public String getMoinv() {
        return moinv;
    }

    /**
     *
     * @param moinv
     */
    public void setMoinv(String moinv) {
        this.moinv = moinv;
    }

    /**
     *
     * @return
     */
    public String getCoinv() {
        return coinv;
    }

    /**
     *
     * @param coinv
     */
    public void setCoinv(String coinv) {
        this.coinv = coinv;
    }

    /**
     *
     * @return
     */
    public String getTainv() {
        return tainv;
    }

    /**
     *
     * @param tainv
     */
    public void setTainv(String tainv) {
        this.tainv = tainv;
    }

    /**
     *
     * @return
     */
    public Integer getCainv() {
        return cainv;
    }

    /**
     *
     * @param cainv
     */
    public void setCainv(Integer cainv) {
        this.cainv = cainv;
    }

    /**
     *
     * @return
     */
    public Integer getNtinv() {
        return ntinv;
    }

    /**
     *
     * @param ntinv
     */
    public void setNtinv(Integer ntinv) {
        this.ntinv = ntinv;
    }

    /**
     *
     * @return
     */
    public String getNninv() {
        return nninv;
    }

    /**
     *
     * @param nninv
     */
    public void setNninv(String nninv) {
        this.nninv = nninv;
    }

    /**
     *
     * @return
     */
    public String getStinv() {
        return stinv;
    }

    /**
     *
     * @param stinv
     */
    public void setStinv(String stinv) {
        this.stinv = stinv;
    }

    /**
     *
     * @return
     */
    public Date getFeinv() {
        return feinv;
    }

    /**
     *
     * @param feinv
     */
    public void setFeinv(Date feinv) {
        this.feinv = feinv;
    }

    /**
     *
     * @return
     */
    public String getUsinv() {
        return usinv;
    }

    /**
     *
     * @param usinv
     */
    public void setUsinv(String usinv) {
        this.usinv = usinv;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idinv != null ? idinv.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Inven37)) {
            return false;
        }
        Inven37 other = (Inven37) object;
        if ((this.idinv == null && other.idinv != null) || (this.idinv != null && !this.idinv.equals(other.idinv))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return EntityToString.getString(this,this.getClass( ));
        //return "com.hrm.sirhapp.model.Inven37[ idinv=" + idinv + " ]";
    }
    
}

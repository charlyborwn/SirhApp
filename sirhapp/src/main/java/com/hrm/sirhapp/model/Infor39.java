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
@Table(name = "INFOR39")
@NamedQueries({
    @NamedQuery(name = "Infor39.findAll", query = "SELECT i FROM Infor39 i")})
public class Infor39 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDINF")
    private Integer idinf;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 70)
    @Column(name = "TIINF")
    private String tiinf;
    @Column(name = "FIINF")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fiinf;
    @Size(max = 10)
    @Column(name = "CEINF")
    private String ceinf;
    @Size(max = 70)
    @Column(name = "NEINF")
    private String neinf;
    @Size(max = 70)
    @Column(name = "RCINF")
    private String rcinf;
    @Size(max = 70)
    @Column(name = "PAINF")
    private String painf;
    @Size(max = 2)
    @Column(name = "STINF")
    private String stinf;
    @Column(name = "FEINF")
    @Temporal(TemporalType.TIMESTAMP)
    private Date feinf;
    @Size(max = 10)
    @Column(name = "USINF")
    private String usinf;

    /**
     *
     */
    public Infor39() {
    }

    /**
     *
     * @param idinf
     */
    public Infor39(Integer idinf) {
        this.idinf = idinf;
    }

    /**
     *
     * @param idinf
     * @param tiinf
     */
    public Infor39(Integer idinf, String tiinf) {
        this.idinf = idinf;
        this.tiinf = tiinf;
    }

    /**
     *
     * @return
     */
    public Integer getIdinf() {
        return idinf;
    }

    /**
     *
     * @param idinf
     */
    public void setIdinf(Integer idinf) {
        this.idinf = idinf;
    }

    /**
     *
     * @return
     */
    public String getTiinf() {
        return tiinf;
    }

    /**
     *
     * @param tiinf
     */
    public void setTiinf(String tiinf) {
        this.tiinf = tiinf;
    }

    /**
     *
     * @return
     */
    public Date getFiinf() {
        return fiinf;
    }

    /**
     *
     * @param fiinf
     */
    public void setFiinf(Date fiinf) {
        this.fiinf = fiinf;
    }

    /**
     *
     * @return
     */
    public String getCeinf() {
        return ceinf;
    }

    /**
     *
     * @param ceinf
     */
    public void setCeinf(String ceinf) {
        this.ceinf = ceinf;
    }

    /**
     *
     * @return
     */
    public String getNeinf() {
        return neinf;
    }

    /**
     *
     * @param neinf
     */
    public void setNeinf(String neinf) {
        this.neinf = neinf;
    }

    /**
     *
     * @return
     */
    public String getRcinf() {
        return rcinf;
    }

    /**
     *
     * @param rcinf
     */
    public void setRcinf(String rcinf) {
        this.rcinf = rcinf;
    }

    /**
     *
     * @return
     */
    public String getPainf() {
        return painf;
    }

    /**
     *
     * @param painf
     */
    public void setPainf(String painf) {
        this.painf = painf;
    }

    /**
     *
     * @return
     */
    public String getStinf() {
        return stinf;
    }

    /**
     *
     * @param stinf
     */
    public void setStinf(String stinf) {
        this.stinf = stinf;
    }

    /**
     *
     * @return
     */
    public Date getFeinf() {
        return feinf;
    }

    /**
     *
     * @param feinf
     */
    public void setFeinf(Date feinf) {
        this.feinf = feinf;
    }

    /**
     *
     * @return
     */
    public String getUsinf() {
        return usinf;
    }

    /**
     *
     * @param usinf
     */
    public void setUsinf(String usinf) {
        this.usinf = usinf;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idinf != null ? idinf.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Infor39)) {
            return false;
        }
        Infor39 other = (Infor39) object;
        if ((this.idinf == null && other.idinf != null) || (this.idinf != null && !this.idinf.equals(other.idinf))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return EntityToString.getString(this,this.getClass());
        //return "com.hrm.sirhapp.model.Infor39[ idinf=" + idinf + " ]";
    }
    
}

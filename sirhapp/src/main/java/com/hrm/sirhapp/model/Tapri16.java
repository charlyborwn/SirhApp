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
@Table(name = "TAPRI16")
@NamedQueries({
    @NamedQuery(name = "Tapri16.findAll", query = "SELECT t FROM Tapri16 t")})
public class Tapri16 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDTAP")
    private Integer idtap;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 70)
    @Column(name = "GRTAP")
    private String grtap;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 70)
    @Column(name = "DETAP")
    private String detap;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 6)
    @Column(name = "HMTAP")
    private String hmtap;
    @Size(max = 2)
    @Column(name = "STTAP")
    private String sttap;
    @Column(name = "FETAP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fetap;
    @Size(max = 10)
    @Column(name = "USTAP")
    private String ustap;

    /**
     *
     */
    public Tapri16() {
    }

    /**
     *
     * @param idtap
     */
    public Tapri16(Integer idtap) {
        this.idtap = idtap;
    }

    /**
     *
     * @param idtap
     * @param grtap
     * @param detap
     * @param hmtap
     */
    public Tapri16(Integer idtap, String grtap, String detap, String hmtap) {
        this.idtap = idtap;
        this.grtap = grtap;
        this.detap = detap;
        this.hmtap = hmtap;
    }

    /**
     *
     * @return
     */
    public Integer getIdtap() {
        return idtap;
    }

    /**
     *
     * @param idtap
     */
    public void setIdtap(Integer idtap) {
        this.idtap = idtap;
    }

    /**
     *
     * @return
     */
    public String getGrtap() {
        return grtap;
    }

    /**
     *
     * @param grtap
     */
    public void setGrtap(String grtap) {
        this.grtap = grtap;
    }

    /**
     *
     * @return
     */
    public String getDetap() {
        return detap;
    }

    /**
     *
     * @param detap
     */
    public void setDetap(String detap) {
        this.detap = detap;
    }

    /**
     *
     * @return
     */
    public String getHmtap() {
        return hmtap;
    }

    /**
     *
     * @param hmtap
     */
    public void setHmtap(String hmtap) {
        this.hmtap = hmtap;
    }

    /**
     *
     * @return
     */
    public String getSttap() {
        return sttap;
    }

    /**
     *
     * @param sttap
     */
    public void setSttap(String sttap) {
        this.sttap = sttap;
    }

    /**
     *
     * @return
     */
    public Date getFetap() {
        return fetap;
    }

    /**
     *
     * @param fetap
     */
    public void setFetap(Date fetap) {
        this.fetap = fetap;
    }

    /**
     *
     * @return
     */
    public String getUstap() {
        return ustap;
    }

    /**
     *
     * @param ustap
     */
    public void setUstap(String ustap) {
        this.ustap = ustap;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtap != null ? idtap.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tapri16)) {
            return false;
        }
        Tapri16 other = (Tapri16) object;
        if ((this.idtap == null && other.idtap != null) || (this.idtap != null && !this.idtap.equals(other.idtap))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return EntityToString.getString(this,this.getClass());
        //return "com.hrm.sirhapp.model.Tapri16[ idtap=" + idtap + " ]";
    }
    
}

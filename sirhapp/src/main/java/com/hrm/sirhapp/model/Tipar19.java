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
@Table(name = "TIPAR19")
@NamedQueries({
    @NamedQuery(name = "Tipar19.findAll", query = "SELECT t FROM Tipar19 t")})
public class Tipar19 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDTIP")
    private Integer idtip;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 70)
    @Column(name = "NOTIP")
    private String notip;
    @Size(max = 6)
    @Column(name = "HMTIP")
    private String hmtip;
    @Size(max = 2)
    @Column(name = "STTIP")
    private String sttip;
    @Column(name = "FETIP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fetip;
    @Size(max = 10)
    @Column(name = "USTIP")
    private String ustip;

    /**
     *
     */
    public Tipar19() {
    }

    /**
     *
     * @param idtip
     */
    public Tipar19(Integer idtip) {
        this.idtip = idtip;
    }

    /**
     *
     * @param idtip
     * @param notip
     */
    public Tipar19(Integer idtip, String notip) {
        this.idtip = idtip;
        this.notip = notip;
    }

    /**
     *
     * @return
     */
    public Integer getIdtip() {
        return idtip;
    }

    /**
     *
     * @param idtip
     */
    public void setIdtip(Integer idtip) {
        this.idtip = idtip;
    }

    /**
     *
     * @return
     */
    public String getNotip() {
        return notip;
    }

    /**
     *
     * @param notip
     */
    public void setNotip(String notip) {
        this.notip = notip;
    }

    /**
     *
     * @return
     */
    public String getHmtip() {
        return hmtip;
    }

    /**
     *
     * @param hmtip
     */
    public void setHmtip(String hmtip) {
        this.hmtip = hmtip;
    }

    /**
     *
     * @return
     */
    public String getSttip() {
        return sttip;
    }

    /**
     *
     * @param sttip
     */
    public void setSttip(String sttip) {
        this.sttip = sttip;
    }

    /**
     *
     * @return
     */
    public Date getFetip() {
        return fetip;
    }

    /**
     *
     * @param fetip
     */
    public void setFetip(Date fetip) {
        this.fetip = fetip;
    }

    /**
     *
     * @return
     */
    public String getUstip() {
        return ustip;
    }

    /**
     *
     * @param ustip
     */
    public void setUstip(String ustip) {
        this.ustip = ustip;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtip != null ? idtip.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tipar19)) {
            return false;
        }
        Tipar19 other = (Tipar19) object;
        if ((this.idtip == null && other.idtip != null) || (this.idtip != null && !this.idtip.equals(other.idtip))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return EntityToString.getString(this,this.getClass());
        //return "com.hrm.sirhapp.model.Tipar19[ idtip=" + idtip + " ]";
    }
    
}

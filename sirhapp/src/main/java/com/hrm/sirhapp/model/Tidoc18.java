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
@Table(name = "TIDOC18")
@NamedQueries({
    @NamedQuery(name = "Tidoc18.findAll", query = "SELECT t FROM Tidoc18 t")})
public class Tidoc18 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDTID")
    private Integer idtid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 70)
    @Column(name = "NOTID")
    private String notid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 6)
    @Column(name = "HMTID")
    private String hmtid;
    @Size(max = 2)
    @Column(name = "STTID")
    private String sttid;
    @Column(name = "FETID")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fetid;
    @Size(max = 10)
    @Column(name = "USTID")
    private String ustid;

    /**
     *
     */
    public Tidoc18() {
    }

    /**
     *
     * @param idtid
     */
    public Tidoc18(Integer idtid) {
        this.idtid = idtid;
    }

    /**
     *
     * @param idtid
     * @param notid
     * @param hmtid
     */
    public Tidoc18(Integer idtid, String notid, String hmtid) {
        this.idtid = idtid;
        this.notid = notid;
        this.hmtid = hmtid;
    }

    /**
     *
     * @return
     */
    public Integer getIdtid() {
        return idtid;
    }

    /**
     *
     * @param idtid
     */
    public void setIdtid(Integer idtid) {
        this.idtid = idtid;
    }

    /**
     *
     * @return
     */
    public String getNotid() {
        return notid;
    }

    /**
     *
     * @param notid
     */
    public void setNotid(String notid) {
        this.notid = notid;
    }

    /**
     *
     * @return
     */
    public String getHmtid() {
        return hmtid;
    }

    /**
     *
     * @param hmtid
     */
    public void setHmtid(String hmtid) {
        this.hmtid = hmtid;
    }

    /**
     *
     * @return
     */
    public String getSttid() {
        return sttid;
    }

    /**
     *
     * @param sttid
     */
    public void setSttid(String sttid) {
        this.sttid = sttid;
    }

    /**
     *
     * @return
     */
    public Date getFetid() {
        return fetid;
    }

    /**
     *
     * @param fetid
     */
    public void setFetid(Date fetid) {
        this.fetid = fetid;
    }

    /**
     *
     * @return
     */
    public String getUstid() {
        return ustid;
    }

    /**
     *
     * @param ustid
     */
    public void setUstid(String ustid) {
        this.ustid = ustid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtid != null ? idtid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tidoc18)) {
            return false;
        }
        Tidoc18 other = (Tidoc18) object;
        if ((this.idtid == null && other.idtid != null) || (this.idtid != null && !this.idtid.equals(other.idtid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return EntityToString.getString(this,this.getClass());
        //return "com.hrm.sirhapp.model.Tidoc18[ idtid=" + idtid + " ]";
    }
    
}

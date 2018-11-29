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
import javax.validation.constraints.Size;

/**
 *
 * @author Carlos Bernal carlose.bernalh@gmail.com
 * Copyright 2018 Carlos Ernesto Bernal Hernández.
 * SirhApp
 * 
 */
@Entity
@Table(name = "CATIN44")
@NamedQueries({
    @NamedQuery(name = "Catin44.findAll", query = "SELECT c FROM Catin44 c")})
public class Catin44 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDCIN")
    private Integer idcin;
    @Size(max = 10)
    @Column(name = "CECIN")
    private String cecin;
    @Size(max = 70)
    @Column(name = "NECIN")
    private String necin;
    @Size(max = 30)
    @Column(name = "ARCIN")
    private String arcin;
    @Size(max = 30)
    @Column(name = "MACIN")
    private String macin;
    @Size(max = 30)
    @Column(name = "MOCIN")
    private String mocin;
    @Size(max = 30)
    @Column(name = "COCIN")
    private String cocin;
    @Size(max = 4)
    @Column(name = "TACIN")
    private String tacin;
    @Size(max = 2)
    @Column(name = "STCIN")
    private String stcin;
    @Column(name = "FECIN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecin;
    @Size(max = 10)
    @Column(name = "USCIN")
    private String uscin;

    /**
     *
     */
    public Catin44() {
    }

    /**
     *
     * @param idcin
     */
    public Catin44(Integer idcin) {
        this.idcin = idcin;
    }

    /**
     *
     * @return
     */
    public Integer getIdcin() {
        return idcin;
    }

    /**
     *
     * @param idcin
     */
    public void setIdcin(Integer idcin) {
        this.idcin = idcin;
    }

    /**
     *
     * @return
     */
    public String getCecin() {
        return cecin;
    }

    /**
     *
     * @param cecin
     */
    public void setCecin(String cecin) {
        this.cecin = cecin;
    }

    /**
     *
     * @return
     */
    public String getNecin() {
        return necin;
    }

    /**
     *
     * @param necin
     */
    public void setNecin(String necin) {
        this.necin = necin;
    }

    /**
     *
     * @return
     */
    public String getArcin() {
        return arcin;
    }

    /**
     *
     * @param arcin
     */
    public void setArcin(String arcin) {
        this.arcin = arcin;
    }

    /**
     *
     * @return
     */
    public String getMacin() {
        return macin;
    }

    /**
     *
     * @param macin
     */
    public void setMacin(String macin) {
        this.macin = macin;
    }

    /**
     *
     * @return
     */
    public String getMocin() {
        return mocin;
    }

    /**
     *
     * @param mocin
     */
    public void setMocin(String mocin) {
        this.mocin = mocin;
    }

    /**
     *
     * @return
     */
    public String getCocin() {
        return cocin;
    }

    /**
     *
     * @param cocin
     */
    public void setCocin(String cocin) {
        this.cocin = cocin;
    }

    /**
     *
     * @return
     */
    public String getTacin() {
        return tacin;
    }

    /**
     *
     * @param tacin
     */
    public void setTacin(String tacin) {
        this.tacin = tacin;
    }

    /**
     *
     * @return
     */
    public String getStcin() {
        return stcin;
    }

    /**
     *
     * @param stcin
     */
    public void setStcin(String stcin) {
        this.stcin = stcin;
    }

    /**
     *
     * @return
     */
    public Date getFecin() {
        return fecin;
    }

    /**
     *
     * @param fecin
     */
    public void setFecin(Date fecin) {
        this.fecin = fecin;
    }

    /**
     *
     * @return
     */
    public String getUscin() {
        return uscin;
    }

    /**
     *
     * @param uscin
     */
    public void setUscin(String uscin) {
        this.uscin = uscin;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcin != null ? idcin.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Catin44)) {
            return false;
        }
        Catin44 other = (Catin44) object;
        if ((this.idcin == null && other.idcin != null) || (this.idcin != null && !this.idcin.equals(other.idcin))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return EntityToString.getString(this,this.getClass());
        //return "com.hrm.sirhapp.model.Catin44[ idcin=" + idcin + " ]";
    }
    
}

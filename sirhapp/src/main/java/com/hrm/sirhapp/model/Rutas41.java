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
@Table(name = "RUTAS41")
@NamedQueries({
    @NamedQuery(name = "Rutas41.findAll", query = "SELECT r FROM Rutas41 r")})
public class Rutas41 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDRUT")
    private Integer idrut;
    @Size(max = 13)
    @Column(name = "NURUT")
    private String nurut;
    @Size(max = 10)
    @Column(name = "CERUT")
    private String cerut;
    @Size(max = 70)
    @Column(name = "NERUT")
    private String nerut;
    @Size(max = 70)
    @Column(name = "LORUT")
    private String lorut;
    @Size(max = 70)
    @Column(name = "LDRUT")
    private String ldrut;
    @Size(max = 70)
    @Column(name = "PARUT")
    private String parut;
    @Size(max = 250)
    @Column(name = "OBRUT")
    private String obrut;
    @Size(max = 2)
    @Column(name = "STRUT")
    private String strut;
    @Column(name = "FERUT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ferut;
    @Size(max = 10)
    @Column(name = "USRUT")
    private String usrut;

    /**
     *
     */
    public Rutas41() {
    }

    /**
     *
     * @param idrut
     */
    public Rutas41(Integer idrut) {
        this.idrut = idrut;
    }

    /**
     *
     * @return
     */
    public Integer getIdrut() {
        return idrut;
    }

    /**
     *
     * @param idrut
     */
    public void setIdrut(Integer idrut) {
        this.idrut = idrut;
    }

    /**
     *
     * @return
     */
    public String getNurut() {
        return nurut;
    }

    /**
     *
     * @param nurut
     */
    public void setNurut(String nurut) {
        this.nurut = nurut;
    }

    /**
     *
     * @return
     */
    public String getCerut() {
        return cerut;
    }

    /**
     *
     * @param cerut
     */
    public void setCerut(String cerut) {
        this.cerut = cerut;
    }

    /**
     *
     * @return
     */
    public String getNerut() {
        return nerut;
    }

    /**
     *
     * @param nerut
     */
    public void setNerut(String nerut) {
        this.nerut = nerut;
    }

    /**
     *
     * @return
     */
    public String getLorut() {
        return lorut;
    }

    /**
     *
     * @param lorut
     */
    public void setLorut(String lorut) {
        this.lorut = lorut;
    }

    /**
     *
     * @return
     */
    public String getLdrut() {
        return ldrut;
    }

    /**
     *
     * @param ldrut
     */
    public void setLdrut(String ldrut) {
        this.ldrut = ldrut;
    }

    /**
     *
     * @return
     */
    public String getParut() {
        return parut;
    }

    /**
     *
     * @param parut
     */
    public void setParut(String parut) {
        this.parut = parut;
    }

    /**
     *
     * @return
     */
    public String getObrut() {
        return obrut;
    }

    /**
     *
     * @param obrut
     */
    public void setObrut(String obrut) {
        this.obrut = obrut;
    }

    /**
     *
     * @return
     */
    public String getStrut() {
        return strut;
    }

    /**
     *
     * @param strut
     */
    public void setStrut(String strut) {
        this.strut = strut;
    }

    /**
     *
     * @return
     */
    public Date getFerut() {
        return ferut;
    }

    /**
     *
     * @param ferut
     */
    public void setFerut(Date ferut) {
        this.ferut = ferut;
    }

    /**
     *
     * @return
     */
    public String getUsrut() {
        return usrut;
    }

    /**
     *
     * @param usrut
     */
    public void setUsrut(String usrut) {
        this.usrut = usrut;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idrut != null ? idrut.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rutas41)) {
            return false;
        }
        Rutas41 other = (Rutas41) object;
        if ((this.idrut == null && other.idrut != null) || (this.idrut != null && !this.idrut.equals(other.idrut))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return EntityToString.getString(this,this.getClass());
        //return "com.hrm.sirhapp.model.Rutas41[ idrut=" + idrut + " ]";
    }
    
}

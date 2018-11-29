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
@Table(name = "SEDES53")
@NamedQueries({
    @NamedQuery(name = "Sedes53.findAll", query = "SELECT s FROM Sedes53 s")})
public class Sedes53 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDSED")
    private Integer idsed;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "CVSED")
    private String cvsed;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "DESED")
    private String desed;
    @Column(name = "ORSED")
    private Integer orsed;
    @Size(max = 2)
    @Column(name = "STSED")
    private String stsed;
    @Column(name = "FESED")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fesed;
    @Size(max = 10)
    @Column(name = "USSED")
    private String ussed;

    /**
     *
     */
    public Sedes53() {
    }

    /**
     *
     * @param idsed
     */
    public Sedes53(Integer idsed) {
        this.idsed = idsed;
    }

    /**
     *
     * @param idsed
     * @param cvsed
     * @param desed
     */
    public Sedes53(Integer idsed, String cvsed, String desed) {
        this.idsed = idsed;
        this.cvsed = cvsed;
        this.desed = desed;
    }

    /**
     *
     * @return
     */
    public Integer getIdsed() {
        return idsed;
    }

    /**
     *
     * @param idsed
     */
    public void setIdsed(Integer idsed) {
        this.idsed = idsed;
    }

    /**
     *
     * @return
     */
    public String getCvsed() {
        return cvsed;
    }

    /**
     *
     * @param cvsed
     */
    public void setCvsed(String cvsed) {
        this.cvsed = cvsed;
    }

    /**
     *
     * @return
     */
    public String getDesed() {
        return desed;
    }

    /**
     *
     * @param desed
     */
    public void setDesed(String desed) {
        this.desed = desed;
    }

    /**
     *
     * @return
     */
    public Integer getOrsed() {
        return orsed;
    }

    /**
     *
     * @param orsed
     */
    public void setOrsed(Integer orsed) {
        this.orsed = orsed;
    }

    /**
     *
     * @return
     */
    public String getStsed() {
        return stsed;
    }

    /**
     *
     * @param stsed
     */
    public void setStsed(String stsed) {
        this.stsed = stsed;
    }

    /**
     *
     * @return
     */
    public Date getFesed() {
        return fesed;
    }

    /**
     *
     * @param fesed
     */
    public void setFesed(Date fesed) {
        this.fesed = fesed;
    }

    /**
     *
     * @return
     */
    public String getUssed() {
        return ussed;
    }

    /**
     *
     * @param ussed
     */
    public void setUssed(String ussed) {
        this.ussed = ussed;
    }

    @Override
    public int hashCode() {
        return (idsed != null)
                ? (Sedes53.class.hashCode() + idsed.hashCode())
                : super.hashCode();
    }

    @Override
    public boolean equals(Object object) {
        return (object instanceof Sedes53) && (idsed != null)
                ? idsed.equals(((Sedes53) object).idsed)
                : (object == this);
    }

    @Override
    public String toString() {
        return EntityToString.getString(this, this.getClass());
        //return "com.hrm.sirhapp.model.Sedes53[ idsed=" + idsed + " ]";
    }

}

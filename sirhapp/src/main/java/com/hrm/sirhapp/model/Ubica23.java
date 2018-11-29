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
@Table(name = "UBICA23")
@NamedQueries({
    @NamedQuery(name = "Ubica23.findAll", query = "SELECT u FROM Ubica23 u")})
public class Ubica23 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDUBI")
    private Integer idubi;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 70)
    @Column(name = "PAUBI")
    private String paubi;
    @Size(max = 70)
    @Column(name = "ESUBI")
    private String esubi;
    @Size(max = 70)
    @Column(name = "MDUBI")
    private String mdubi;
    @Size(max = 70)
    @Column(name = "LOUBI")
    private String loubi;
    @Size(max = 70)
    @Column(name = "COUBI")
    private String coubi;
    @Size(max = 5)
    @Column(name = "CPUBI")
    private String cpubi;
    @Size(max = 2)
    @Column(name = "STUBI")
    private String stubi;
    @Column(name = "FEUBI")
    @Temporal(TemporalType.TIMESTAMP)
    private Date feubi;
    @Size(max = 10)
    @Column(name = "USUBI")
    private String usubi;

    /**
     *
     */
    public Ubica23() {
    }

    /**
     *
     * @param idubi
     */
    public Ubica23(Integer idubi) {
        this.idubi = idubi;
    }

    /**
     *
     * @return
     */
    public Integer getIdubi() {
        return idubi;
    }

    /**
     *
     * @param idubi
     */
    public void setIdubi(Integer idubi) {
        this.idubi = idubi;
    }

    /**
     *
     * @return
     */
    public String getPaubi() {
        return paubi;
    }

    /**
     *
     * @param paubi
     */
    public void setPaubi(String paubi) {
        this.paubi = paubi;
    }

    /**
     *
     * @return
     */
    public String getEsubi() {
        return esubi;
    }

    /**
     *
     * @param esubi
     */
    public void setEsubi(String esubi) {
        this.esubi = esubi;
    }

    /**
     *
     * @return
     */
    public String getMdubi() {
        return mdubi;
    }

    /**
     *
     * @param mdubi
     */
    public void setMdubi(String mdubi) {
        this.mdubi = mdubi;
    }

    /**
     *
     * @return
     */
    public String getLoubi() {
        return loubi;
    }

    /**
     *
     * @param loubi
     */
    public void setLoubi(String loubi) {
        this.loubi = loubi;
    }

    /**
     *
     * @return
     */
    public String getCoubi() {
        return coubi;
    }

    /**
     *
     * @param coubi
     */
    public void setCoubi(String coubi) {
        this.coubi = coubi;
    }

    /**
     *
     * @return
     */
    public String getCpubi() {
        return cpubi;
    }

    /**
     *
     * @param cpubi
     */
    public void setCpubi(String cpubi) {
        this.cpubi = cpubi;
    }

    /**
     *
     * @return
     */
    public String getStubi() {
        return stubi;
    }

    /**
     *
     * @param stubi
     */
    public void setStubi(String stubi) {
        this.stubi = stubi;
    }

    /**
     *
     * @return
     */
    public Date getFeubi() {
        return feubi;
    }

    /**
     *
     * @param feubi
     */
    public void setFeubi(Date feubi) {
        this.feubi = feubi;
    }

    /**
     *
     * @return
     */
    public String getUsubi() {
        return usubi;
    }

    /**
     *
     * @param usubi
     */
    public void setUsubi(String usubi) {
        this.usubi = usubi;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idubi != null ? idubi.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ubica23)) {
            return false;
        }
        Ubica23 other = (Ubica23) object;
        if ((this.idubi == null && other.idubi != null) || (this.idubi != null && !this.idubi.equals(other.idubi))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return EntityToString.getString(this, this.getClass());
        //return "com.hrm.sirhapp.model.Ubica23[ idubi=" + idubi + " ]";
    }

}

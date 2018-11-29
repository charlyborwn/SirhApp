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
@Table(name = "TURNO22")
@NamedQueries({
    @NamedQuery(name = "Turno22.findAll", query = "SELECT t FROM Turno22 t")})
public class Turno22 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDTUR")
    private Integer idtur;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 6)
    @Column(name = "CVTUR")
    private String cvtur;
    @Size(max = 70)
    @Column(name = "NOTUR")
    private String notur;
    @Size(max = 2)
    @Column(name = "STTUR")
    private String sttur;
    @Column(name = "FETUR")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fetur;
    @Size(max = 10)
    @Column(name = "USTUR")
    private String ustur;

    /**
     *
     */
    public Turno22() {
    }

    /**
     *
     * @param idtur
     */
    public Turno22(Integer idtur) {
        this.idtur = idtur;
    }

    /**
     *
     * @param idtur
     * @param cvtur
     */
    public Turno22(Integer idtur, String cvtur) {
        this.idtur = idtur;
        this.cvtur = cvtur;
    }

    /**
     *
     * @return
     */
    public Integer getIdtur() {
        return idtur;
    }

    /**
     *
     * @param idtur
     */
    public void setIdtur(Integer idtur) {
        this.idtur = idtur;
    }

    /**
     *
     * @return
     */
    public String getCvtur() {
        return cvtur;
    }

    /**
     *
     * @param cvtur
     */
    public void setCvtur(String cvtur) {
        this.cvtur = cvtur;
    }

    /**
     *
     * @return
     */
    public String getNotur() {
        return notur;
    }

    /**
     *
     * @param notur
     */
    public void setNotur(String notur) {
        this.notur = notur;
    }

    /**
     *
     * @return
     */
    public String getSttur() {
        return sttur;
    }

    /**
     *
     * @param sttur
     */
    public void setSttur(String sttur) {
        this.sttur = sttur;
    }

    /**
     *
     * @return
     */
    public Date getFetur() {
        return fetur;
    }

    /**
     *
     * @param fetur
     */
    public void setFetur(Date fetur) {
        this.fetur = fetur;
    }

    /**
     *
     * @return
     */
    public String getUstur() {
        return ustur;
    }

    /**
     *
     * @param ustur
     */
    public void setUstur(String ustur) {
        this.ustur = ustur;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtur != null ? idtur.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Turno22)) {
            return false;
        }
        Turno22 other = (Turno22) object;
        if ((this.idtur == null && other.idtur != null) || (this.idtur != null && !this.idtur.equals(other.idtur))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return EntityToString.getString(this,this.getClass());
        //return "com.hrm.sirhapp.model.Turno22[ idtur=" + idtur + " ]";
    }
    
}

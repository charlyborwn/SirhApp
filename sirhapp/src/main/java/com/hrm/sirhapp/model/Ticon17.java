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
@Table(name = "TICON17")
@NamedQueries({
    @NamedQuery(name = "Ticon17.findAll", query = "SELECT t FROM Ticon17 t")})
public class Ticon17 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDTIC")
    private Integer idtic;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 6)
    @Column(name = "CVTIC")
    private String cvtic;
    @Size(max = 70)
    @Column(name = "NOTIC")
    private String notic;
    @Size(max = 2)
    @Column(name = "STTIC")
    private String sttic;
    @Column(name = "FETIC")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fetic;
    @Size(max = 10)
    @Column(name = "USTIC")
    private String ustic;

    /**
     *
     */
    public Ticon17() {
    }

    /**
     *
     * @param idtic
     */
    public Ticon17(Integer idtic) {
        this.idtic = idtic;
    }

    /**
     *
     * @param idtic
     * @param cvtic
     */
    public Ticon17(Integer idtic, String cvtic) {
        this.idtic = idtic;
        this.cvtic = cvtic;
    }

    /**
     *
     * @return
     */
    public Integer getIdtic() {
        return idtic;
    }

    /**
     *
     * @param idtic
     */
    public void setIdtic(Integer idtic) {
        this.idtic = idtic;
    }

    /**
     *
     * @return
     */
    public String getCvtic() {
        return cvtic;
    }

    /**
     *
     * @param cvtic
     */
    public void setCvtic(String cvtic) {
        this.cvtic = cvtic;
    }

    /**
     *
     * @return
     */
    public String getNotic() {
        return notic;
    }

    /**
     *
     * @param notic
     */
    public void setNotic(String notic) {
        this.notic = notic;
    }

    /**
     *
     * @return
     */
    public String getSttic() {
        return sttic;
    }

    /**
     *
     * @param sttic
     */
    public void setSttic(String sttic) {
        this.sttic = sttic;
    }

    /**
     *
     * @return
     */
    public Date getFetic() {
        return fetic;
    }

    /**
     *
     * @param fetic
     */
    public void setFetic(Date fetic) {
        this.fetic = fetic;
    }

    /**
     *
     * @return
     */
    public String getUstic() {
        return ustic;
    }

    /**
     *
     * @param ustic
     */
    public void setUstic(String ustic) {
        this.ustic = ustic;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtic != null ? idtic.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ticon17)) {
            return false;
        }
        Ticon17 other = (Ticon17) object;
        if ((this.idtic == null && other.idtic != null) || (this.idtic != null && !this.idtic.equals(other.idtic))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return EntityToString.getString(this,this.getClass());
        //return "com.hrm.sirhapp.model.Ticon17[ idtic=" + idtic + " ]";
    }
    
}

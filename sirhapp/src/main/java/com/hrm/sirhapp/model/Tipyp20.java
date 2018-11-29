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
@Table(name = "TIPYP20")
@NamedQueries({
    @NamedQuery(name = "Tipyp20.findAll", query = "SELECT t FROM Tipyp20 t")})
public class Tipyp20 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDPYP")
    private Integer idpyp;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 70)
    @Column(name = "GRPYP")
    private String grpyp;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 70)
    @Column(name = "NOPYP")
    private String nopyp;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 6)
    @Column(name = "HMPYP")
    private String hmpyp;
    @Size(max = 10)
    @Column(name = "CEPYP")
    private String cepyp;
    @Size(max = 70)
    @Column(name = "NEPYP")
    private String nepyp;
    @Size(max = 2)
    @Column(name = "STPYP")
    private String stpyp;
    @Column(name = "FEPYP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fepyp;
    @Size(max = 10)
    @Column(name = "USPYP")
    private String uspyp;

    /**
     *
     */
    public Tipyp20() {
    }

    /**
     *
     * @param idpyp
     */
    public Tipyp20(Integer idpyp) {
        this.idpyp = idpyp;
    }

    /**
     *
     * @param idpyp
     * @param grpyp
     * @param nopyp
     * @param hmpyp
     */
    public Tipyp20(Integer idpyp, String grpyp, String nopyp, String hmpyp) {
        this.idpyp = idpyp;
        this.grpyp = grpyp;
        this.nopyp = nopyp;
        this.hmpyp = hmpyp;
    }

    /**
     *
     * @return
     */
    public Integer getIdpyp() {
        return idpyp;
    }

    /**
     *
     * @param idpyp
     */
    public void setIdpyp(Integer idpyp) {
        this.idpyp = idpyp;
    }

    /**
     *
     * @return
     */
    public String getGrpyp() {
        return grpyp;
    }

    /**
     *
     * @param grpyp
     */
    public void setGrpyp(String grpyp) {
        this.grpyp = grpyp;
    }

    /**
     *
     * @return
     */
    public String getNopyp() {
        return nopyp;
    }

    /**
     *
     * @param nopyp
     */
    public void setNopyp(String nopyp) {
        this.nopyp = nopyp;
    }

    /**
     *
     * @return
     */
    public String getHmpyp() {
        return hmpyp;
    }

    /**
     *
     * @param hmpyp
     */
    public void setHmpyp(String hmpyp) {
        this.hmpyp = hmpyp;
    }

    /**
     *
     * @return
     */
    public String getCepyp() {
        return cepyp;
    }

    /**
     *
     * @param cepyp
     */
    public void setCepyp(String cepyp) {
        this.cepyp = cepyp;
    }

    /**
     *
     * @return
     */
    public String getNepyp() {
        return nepyp;
    }

    /**
     *
     * @param nepyp
     */
    public void setNepyp(String nepyp) {
        this.nepyp = nepyp;
    }

    /**
     *
     * @return
     */
    public String getStpyp() {
        return stpyp;
    }

    /**
     *
     * @param stpyp
     */
    public void setStpyp(String stpyp) {
        this.stpyp = stpyp;
    }

    /**
     *
     * @return
     */
    public Date getFepyp() {
        return fepyp;
    }

    /**
     *
     * @param fepyp
     */
    public void setFepyp(Date fepyp) {
        this.fepyp = fepyp;
    }

    /**
     *
     * @return
     */
    public String getUspyp() {
        return uspyp;
    }

    /**
     *
     * @param uspyp
     */
    public void setUspyp(String uspyp) {
        this.uspyp = uspyp;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpyp != null ? idpyp.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tipyp20)) {
            return false;
        }
        Tipyp20 other = (Tipyp20) object;
        if ((this.idpyp == null && other.idpyp != null) || (this.idpyp != null && !this.idpyp.equals(other.idpyp))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return EntityToString.getString(this,this.getClass());
        //return "com.hrm.sirhapp.model.Tipyp20[ idpyp=" + idpyp + " ]";
    }
    
}

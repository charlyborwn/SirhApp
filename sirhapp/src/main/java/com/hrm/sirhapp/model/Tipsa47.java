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
@Table(name = "TIPSA47")
@NamedQueries({
    @NamedQuery(name = "Tipsa47.findAll", query = "SELECT t FROM Tipsa47 t")})
public class Tipsa47 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDPSA")
    private Integer idpsa;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 70)
    @Column(name = "GRPSA")
    private String grpsa;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 70)
    @Column(name = "NOPSA")
    private String nopsa;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 6)
    @Column(name = "HMPSA")
    private String hmpsa;
    @Size(max = 10)
    @Column(name = "CEPSA")
    private String cepsa;
    @Size(max = 70)
    @Column(name = "NEPSA")
    private String nepsa;
    @Size(max = 2)
    @Column(name = "STPSA")
    private String stpsa;
    @Column(name = "FEPSA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fepsa;
    @Size(max = 10)
    @Column(name = "USPSA")
    private String uspsa;

    /**
     *
     */
    public Tipsa47() {
    }

    /**
     *
     * @param idpsa
     */
    public Tipsa47(Integer idpsa) {
        this.idpsa = idpsa;
    }

    /**
     *
     * @param idpsa
     * @param grpsa
     * @param nopsa
     * @param hmpsa
     */
    public Tipsa47(Integer idpsa, String grpsa, String nopsa, String hmpsa) {
        this.idpsa = idpsa;
        this.grpsa = grpsa;
        this.nopsa = nopsa;
        this.hmpsa = hmpsa;
    }

    /**
     *
     * @return
     */
    public Integer getIdpsa() {
        return idpsa;
    }

    /**
     *
     * @param idpsa
     */
    public void setIdpsa(Integer idpsa) {
        this.idpsa = idpsa;
    }

    /**
     *
     * @return
     */
    public String getGrpsa() {
        return grpsa;
    }

    /**
     *
     * @param grpsa
     */
    public void setGrpsa(String grpsa) {
        this.grpsa = grpsa;
    }

    /**
     *
     * @return
     */
    public String getNopsa() {
        return nopsa;
    }

    /**
     *
     * @param nopsa
     */
    public void setNopsa(String nopsa) {
        this.nopsa = nopsa;
    }

    /**
     *
     * @return
     */
    public String getHmpsa() {
        return hmpsa;
    }

    /**
     *
     * @param hmpsa
     */
    public void setHmpsa(String hmpsa) {
        this.hmpsa = hmpsa;
    }

    /**
     *
     * @return
     */
    public String getCepsa() {
        return cepsa;
    }

    /**
     *
     * @param cepsa
     */
    public void setCepsa(String cepsa) {
        this.cepsa = cepsa;
    }

    /**
     *
     * @return
     */
    public String getNepsa() {
        return nepsa;
    }

    /**
     *
     * @param nepsa
     */
    public void setNepsa(String nepsa) {
        this.nepsa = nepsa;
    }

    /**
     *
     * @return
     */
    public String getStpsa() {
        return stpsa;
    }

    /**
     *
     * @param stpsa
     */
    public void setStpsa(String stpsa) {
        this.stpsa = stpsa;
    }

    /**
     *
     * @return
     */
    public Date getFepsa() {
        return fepsa;
    }

    /**
     *
     * @param fepsa
     */
    public void setFepsa(Date fepsa) {
        this.fepsa = fepsa;
    }

    /**
     *
     * @return
     */
    public String getUspsa() {
        return uspsa;
    }

    /**
     *
     * @param uspsa
     */
    public void setUspsa(String uspsa) {
        this.uspsa = uspsa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpsa != null ? idpsa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tipsa47)) {
            return false;
        }
        Tipsa47 other = (Tipsa47) object;
        if ((this.idpsa == null && other.idpsa != null) || (this.idpsa != null && !this.idpsa.equals(other.idpsa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return EntityToString.getString(this,this.getClass());
        //return "com.hrm.sirhapp.model.Tipsa47[ idpsa=" + idpsa + " ]";
    }
    
}

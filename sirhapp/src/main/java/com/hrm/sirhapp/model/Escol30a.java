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
@Table(name = "ESCOL30A")
@NamedQueries({
    @NamedQuery(name = "Escol30a.findAll", query = "SELECT e FROM Escol30a e")})
public class Escol30a implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDESC_A")
    private Integer idescA;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 13)
    @Column(name = "RFESC_A")
    private String rfescA;
    @Size(max = 70)
    @Column(name = "TNESC_A")
    private String tnescA;
    @Size(max = 70)
    @Column(name = "NIESC_A")
    private String niescA;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "ACESC_A")
    private Float acescA;
    @Size(max = 2)
    @Column(name = "CAESC_A")
    private String caescA;
    @Size(max = 70)
    @Column(name = "HCESC_A")
    private String hcescA;
    @Size(max = 2)
    @Column(name = "CEESC_A")
    private String ceescA;
    @Size(max = 70)
    @Column(name = "ESESC_A")
    private String esescA;
    @Size(max = 70)
    @Column(name = "TDESC_A")
    private String tdescA;
    @Size(max = 6)
    @Column(name = "W1ESC_A")
    private String w1escA;
    @Size(max = 70)
    @Column(name = "PAESC_A")
    private String paescA;
    @Size(max = 250)
    @Column(name = "OBESC_A")
    private String obescA;
    @Size(max = 2)
    @Column(name = "STESC_A")
    private String stescA;
    @Column(name = "FEESC_A")
    @Temporal(TemporalType.TIMESTAMP)
    private Date feescA;
    @Size(max = 10)
    @Column(name = "USESC_A")
    private String usescA;

    /**
     *
     */
    public Escol30a() {
    }

    /**
     *
     * @param idescA
     */
    public Escol30a(Integer idescA) {
        this.idescA = idescA;
    }

    /**
     *
     * @param idescA
     * @param rfescA
     */
    public Escol30a(Integer idescA, String rfescA) {
        this.idescA = idescA;
        this.rfescA = rfescA;
    }

    /**
     *
     * @return
     */
    public Integer getIdescA() {
        return idescA;
    }

    /**
     *
     * @param idescA
     */
    public void setIdescA(Integer idescA) {
        this.idescA = idescA;
    }

    /**
     *
     * @return
     */
    public String getRfescA() {
        return rfescA;
    }

    /**
     *
     * @param rfescA
     */
    public void setRfescA(String rfescA) {
        this.rfescA = rfescA;
    }

    /**
     *
     * @return
     */
    public String getTnescA() {
        return tnescA;
    }

    /**
     *
     * @param tnescA
     */
    public void setTnescA(String tnescA) {
        this.tnescA = tnescA;
    }

    /**
     *
     * @return
     */
    public String getNiescA() {
        return niescA;
    }

    /**
     *
     * @param niescA
     */
    public void setNiescA(String niescA) {
        this.niescA = niescA;
    }

    /**
     *
     * @return
     */
    public Float getAcescA() {
        return acescA;
    }

    /**
     *
     * @param acescA
     */
    public void setAcescA(Float acescA) {
        this.acescA = acescA;
    }

    /**
     *
     * @return
     */
    public String getCaescA() {
        return caescA;
    }

    /**
     *
     * @param caescA
     */
    public void setCaescA(String caescA) {
        this.caescA = caescA;
    }

    /**
     *
     * @return
     */
    public String getHcescA() {
        return hcescA;
    }

    /**
     *
     * @param hcescA
     */
    public void setHcescA(String hcescA) {
        this.hcescA = hcescA;
    }

    /**
     *
     * @return
     */
    public String getCeescA() {
        return ceescA;
    }

    /**
     *
     * @param ceescA
     */
    public void setCeescA(String ceescA) {
        this.ceescA = ceescA;
    }

    /**
     *
     * @return
     */
    public String getEsescA() {
        return esescA;
    }

    /**
     *
     * @param esescA
     */
    public void setEsescA(String esescA) {
        this.esescA = esescA;
    }

    /**
     *
     * @return
     */
    public String getTdescA() {
        return tdescA;
    }

    /**
     *
     * @param tdescA
     */
    public void setTdescA(String tdescA) {
        this.tdescA = tdescA;
    }

    /**
     *
     * @return
     */
    public String getW1escA() {
        return w1escA;
    }

    /**
     *
     * @param w1escA
     */
    public void setW1escA(String w1escA) {
        this.w1escA = w1escA;
    }

    /**
     *
     * @return
     */
    public String getPaescA() {
        return paescA;
    }

    /**
     *
     * @param paescA
     */
    public void setPaescA(String paescA) {
        this.paescA = paescA;
    }

    /**
     *
     * @return
     */
    public String getObescA() {
        return obescA;
    }

    /**
     *
     * @param obescA
     */
    public void setObescA(String obescA) {
        this.obescA = obescA;
    }

    /**
     *
     * @return
     */
    public String getStescA() {
        return stescA;
    }

    /**
     *
     * @param stescA
     */
    public void setStescA(String stescA) {
        this.stescA = stescA;
    }

    /**
     *
     * @return
     */
    public Date getFeescA() {
        return feescA;
    }

    /**
     *
     * @param feescA
     */
    public void setFeescA(Date feescA) {
        this.feescA = feescA;
    }

    /**
     *
     * @return
     */
    public String getUsescA() {
        return usescA;
    }

    /**
     *
     * @param usescA
     */
    public void setUsescA(String usescA) {
        this.usescA = usescA;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idescA != null ? idescA.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Escol30a)) {
            return false;
        }
        Escol30a other = (Escol30a) object;
        if ((this.idescA == null && other.idescA != null) || (this.idescA != null && !this.idescA.equals(other.idescA))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return EntityToString.getString(this,this.getClass());
        //return "com.hrm.sirhapp.model.Escol30a[ idescA=" + idescA + " ]";
    }
    
}

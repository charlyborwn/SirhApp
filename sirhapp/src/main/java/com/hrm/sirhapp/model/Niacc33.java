package com.hrm.sirhapp.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "NIACC33")
@NamedQueries({
    @NamedQuery(name = "Niacc33.findAll", query = "SELECT n FROM Niacc33 n")})
public class Niacc33 implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDNIA")
    private Integer idnia;
    @JoinColumn(name = "CVNIA", referencedColumnName = "CVUSU")
    @ManyToOne
    private Usuar24 cvnia;
    @Size(max = 20)
    @Column(name = "NANIA")
    private String nania;
    @Size(max = 9)
    @Column(name = "CPNIA")
    private String cpnia;
    @Size(max = 20)
    @Column(name = "NPNIA")
    private String npnia;
    @Size(max = 10)
    @Column(name = "RONIA")
    private String ronia;
    @Size(max = 2)
    @Column(name = "STNIA")
    private String stnia;
    @Column(name = "FENIA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fenia;
    @Size(max = 10)
    @Column(name = "USNIA")
    private String usnia;

    /**
     *
     */
    public Niacc33() {
    }

    /**
     *
     * @param idnia
     */
    public Niacc33(Integer idnia) {
        this.idnia = idnia;
    }

    /**
     *
     * @return
     */
    public Integer getIdnia() {
        return idnia;
    }

    /**
     *
     * @param idnia
     */
    public void setIdnia(Integer idnia) {
        this.idnia = idnia;
    }

    /**
     *
     * @return
     */
    public Usuar24 getCvnia() {
        return cvnia;
    }

    /**
     *
     * @param cvnia
     */
    public void setCvnia(Usuar24 cvnia) {
        this.cvnia = cvnia;
    }

    /**
     *
     * @return
     */
    public String getNania() {
        return nania;
    }

    /**
     *
     * @param nania
     */
    public void setNania(String nania) {
        this.nania = nania.toLowerCase();
    }

    /**
     *
     * @return
     */
    public String getCpnia() {
        return cpnia;
    }

    /**
     *
     * @param cpnia
     */
    public void setCpnia(String cpnia) {
        this.cpnia = cpnia;
    }

    /**
     *
     * @return
     */
    public String getNpnia() {
        return npnia;
    }

    /**
     *
     * @param npnia
     */
    public void setNpnia(String npnia) {
        this.npnia = npnia;
    }

    /**
     *
     * @return
     */
    public String getRonia() {
        return ronia;
    }

    /**
     *
     * @param ronia
     */
    public void setRonia(String ronia) {
        this.ronia = ronia;
    }

    /**
     *
     * @return
     */
    public String getStnia() {
        return stnia;
    }

    /**
     *
     * @param stnia
     */
    public void setStnia(String stnia) {
        this.stnia = stnia;
    }

    /**
     *
     * @return
     */
    public Date getFenia() {
        return fenia;
    }

    /**
     *
     * @param fenia
     */
    public void setFenia(Date fenia) {
        this.fenia = fenia;
    }

    /**
     *
     * @return
     */
    public String getUsnia() {
        return usnia;
    }

    /**
     *
     * @param usnia
     */
    public void setUsnia(String usnia) {
        this.usnia = usnia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idnia != null ? idnia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Niacc33)) {
            return false;
        }
        Niacc33 other = (Niacc33) object;
        if ((this.idnia == null && other.idnia != null) || (this.idnia != null && !this.idnia.equals(other.idnia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return EntityToString.getString(this, this.getClass());
        //return "com.hrm.sirhapp.model.Usuar24[ idusu=" + idusu + " ]";
    }
}

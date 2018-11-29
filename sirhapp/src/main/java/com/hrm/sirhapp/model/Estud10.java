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
@Table(name = "ESTUD10")
@NamedQueries({
    @NamedQuery(name = "Estud10.findAll", query = "SELECT e FROM Estud10 e")})
public class Estud10 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDEST")
    private Integer idest;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 70)
    @Column(name = "NIEST")
    private String niest;
    @Column(name = "OREST")
    private Short orest;
    @Size(max = 2)
    @Column(name = "STEST")
    private String stest;
    @Column(name = "FEEST")
    @Temporal(TemporalType.TIMESTAMP)
    private Date feest;
    @Size(max = 10)
    @Column(name = "USEST")
    private String usest;

    /**
     *
     */
    public Estud10() {
    }

    /**
     *
     * @param idest
     */
    public Estud10(Integer idest) {
        this.idest = idest;
    }

    /**
     *
     * @param idest
     * @param niest
     */
    public Estud10(Integer idest, String niest) {
        this.idest = idest;
        this.niest = niest;
    }

    /**
     *
     * @return
     */
    public Integer getIdest() {
        return idest;
    }

    /**
     *
     * @param idest
     */
    public void setIdest(Integer idest) {
        this.idest = idest;
    }

    /**
     *
     * @return
     */
    public String getNiest() {
        return niest;
    }

    /**
     *
     * @param niest
     */
    public void setNiest(String niest) {
        this.niest = niest;
    }

    /**
     *
     * @return
     */
    public Short getOrest() {
        return orest;
    }

    /**
     *
     * @param orest
     */
    public void setOrest(Short orest) {
        this.orest = orest;
    }

    /**
     *
     * @return
     */
    public String getStest() {
        return stest;
    }

    /**
     *
     * @param stest
     */
    public void setStest(String stest) {
        this.stest = stest;
    }

    /**
     *
     * @return
     */
    public Date getFeest() {
        return feest;
    }

    /**
     *
     * @param feest
     */
    public void setFeest(Date feest) {
        this.feest = feest;
    }

    /**
     *
     * @return
     */
    public String getUsest() {
        return usest;
    }

    /**
     *
     * @param usest
     */
    public void setUsest(String usest) {
        this.usest = usest;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idest != null ? idest.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Estud10)) {
            return false;
        }
        Estud10 other = (Estud10) object;
        if ((this.idest == null && other.idest != null) || (this.idest != null && !this.idest.equals(other.idest))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return EntityToString.getString(this,this.getClass());
        //return "com.hrm.sirhapp.model.Estud10[ idest=" + idest + " ]";
    }
    
}

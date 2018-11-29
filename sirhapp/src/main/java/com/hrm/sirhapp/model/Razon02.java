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
@Table(name = "RAZON02")
@NamedQueries({
    @NamedQuery(name = "Razon02.findAll", query = "SELECT r FROM Razon02 r")})
public class Razon02 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDRAZ")
    private Integer idraz;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "DERAZ")
    private String deraz;
    @Size(max = 2)
    @Column(name = "STRAZ")
    private String straz;
    @Column(name = "FERAZ")
    @Temporal(TemporalType.TIMESTAMP)
    private Date feraz;
    @Size(max = 10)
    @Column(name = "USRAZ")
    private String usraz;

    /**
     *
     */
    public Razon02() {
    }

    /**
     *
     * @param idraz
     */
    public Razon02(Integer idraz) {
        this.idraz = idraz;
    }

    /**
     *
     * @param idraz
     * @param deraz
     */
    public Razon02(Integer idraz, String deraz) {
        this.idraz = idraz;
        this.deraz = deraz;
    }

    /**
     *
     * @return
     */
    public Integer getIdraz() {
        return idraz;
    }

    /**
     *
     * @param idraz
     */
    public void setIdraz(Integer idraz) {
        this.idraz = idraz;
    }

    /**
     *
     * @return
     */
    public String getDeraz() {
        return deraz;
    }

    /**
     *
     * @param deraz
     */
    public void setDeraz(String deraz) {
        this.deraz = deraz;
    }

    /**
     *
     * @return
     */
    public String getStraz() {
        return straz;
    }

    /**
     *
     * @param straz
     */
    public void setStraz(String straz) {
        this.straz = straz;
    }

    /**
     *
     * @return
     */
    public Date getFeraz() {
        return feraz;
    }

    /**
     *
     * @param feraz
     */
    public void setFeraz(Date feraz) {
        this.feraz = feraz;
    }

    /**
     *
     * @return
     */
    public String getUsraz() {
        return usraz;
    }

    /**
     *
     * @param usraz
     */
    public void setUsraz(String usraz) {
        this.usraz = usraz;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idraz != null ? idraz.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Razon02)) {
            return false;
        }
        Razon02 other = (Razon02) object;
        if ((this.idraz == null && other.idraz != null) || (this.idraz != null && !this.idraz.equals(other.idraz))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return EntityToString.getString(this,this.getClass());
        //return "com.hrm.sirhapp.model.Razon02[ idraz=" + idraz + " ]";
    }
    
}

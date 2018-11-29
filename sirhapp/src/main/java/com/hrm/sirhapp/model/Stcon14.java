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
@Table(name = "STCON14")
@NamedQueries({
    @NamedQuery(name = "Stcon14.findAll", query = "SELECT s FROM Stcon14 s")})
public class Stcon14 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDSTC")
    private Integer idstc;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 70)
    @Column(name = "DESTC")
    private String destc;
    @Size(max = 2)
    @Column(name = "STSTC")
    private String ststc;
    @Column(name = "FESTC")
    @Temporal(TemporalType.TIMESTAMP)
    private Date festc;
    @Size(max = 10)
    @Column(name = "USSTC")
    private String usstc;

    /**
     *
     */
    public Stcon14() {
    }

    /**
     *
     * @param idstc
     */
    public Stcon14(Integer idstc) {
        this.idstc = idstc;
    }

    /**
     *
     * @param idstc
     * @param destc
     */
    public Stcon14(Integer idstc, String destc) {
        this.idstc = idstc;
        this.destc = destc;
    }

    /**
     *
     * @return
     */
    public Integer getIdstc() {
        return idstc;
    }

    /**
     *
     * @param idstc
     */
    public void setIdstc(Integer idstc) {
        this.idstc = idstc;
    }

    /**
     *
     * @return
     */
    public String getDestc() {
        return destc;
    }

    /**
     *
     * @param destc
     */
    public void setDestc(String destc) {
        this.destc = destc;
    }

    /**
     *
     * @return
     */
    public String getStstc() {
        return ststc;
    }

    /**
     *
     * @param ststc
     */
    public void setStstc(String ststc) {
        this.ststc = ststc;
    }

    /**
     *
     * @return
     */
    public Date getFestc() {
        return festc;
    }

    /**
     *
     * @param festc
     */
    public void setFestc(Date festc) {
        this.festc = festc;
    }

    /**
     *
     * @return
     */
    public String getUsstc() {
        return usstc;
    }

    /**
     *
     * @param usstc
     */
    public void setUsstc(String usstc) {
        this.usstc = usstc;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idstc != null ? idstc.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Stcon14)) {
            return false;
        }
        Stcon14 other = (Stcon14) object;
        if ((this.idstc == null && other.idstc != null) || (this.idstc != null && !this.idstc.equals(other.idstc))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return EntityToString.getString(this,this.getClass());
        //return "com.hrm.sirhapp.model.Stcon14[ idstc=" + idstc + " ]";
    }
    
}

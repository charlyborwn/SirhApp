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
@Table(name = "STRAB15")
@NamedQueries({
    @NamedQuery(name = "Strab15.findAll", query = "SELECT s FROM Strab15 s")})
public class Strab15 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDSTR")
    private Integer idstr;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 70)
    @Column(name = "DESTR")
    private String destr;
    @Size(max = 2)
    @Column(name = "STSTR")
    private String ststr;
    @Column(name = "FESTR")
    @Temporal(TemporalType.TIMESTAMP)
    private Date festr;
    @Size(max = 10)
    @Column(name = "USSTR")
    private String usstr;

    /**
     *
     */
    public Strab15() {
    }

    /**
     *
     * @param idstr
     */
    public Strab15(Integer idstr) {
        this.idstr = idstr;
    }

    /**
     *
     * @param idstr
     * @param destr
     */
    public Strab15(Integer idstr, String destr) {
        this.idstr = idstr;
        this.destr = destr;
    }

    /**
     *
     * @return
     */
    public Integer getIdstr() {
        return idstr;
    }

    /**
     *
     * @param idstr
     */
    public void setIdstr(Integer idstr) {
        this.idstr = idstr;
    }

    /**
     *
     * @return
     */
    public String getDestr() {
        return destr;
    }

    /**
     *
     * @param destr
     */
    public void setDestr(String destr) {
        this.destr = destr;
    }

    /**
     *
     * @return
     */
    public String getStstr() {
        return ststr;
    }

    /**
     *
     * @param ststr
     */
    public void setStstr(String ststr) {
        this.ststr = ststr;
    }

    /**
     *
     * @return
     */
    public Date getFestr() {
        return festr;
    }

    /**
     *
     * @param festr
     */
    public void setFestr(Date festr) {
        this.festr = festr;
    }

    /**
     *
     * @return
     */
    public String getUsstr() {
        return usstr;
    }

    /**
     *
     * @param usstr
     */
    public void setUsstr(String usstr) {
        this.usstr = usstr;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idstr != null ? idstr.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Strab15)) {
            return false;
        }
        Strab15 other = (Strab15) object;
        if ((this.idstr == null && other.idstr != null) || (this.idstr != null && !this.idstr.equals(other.idstr))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return EntityToString.getString(this,this.getClass());
        //return "com.hrm.sirhapp.model.Strab15[ idstr=" + idstr + " ]";
    }
    
}

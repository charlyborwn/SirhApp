package com.hrm.sirhapp.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
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
@Table(name = "REPOR50")
public class Repor50 implements Serializable {

    @Lob
    @Column(name = "COREP")
    private byte[] corep;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDREP")
    private Integer idrep;
    @Size(max = 255)
    @Column(name = "TIREP")
    private String tirep;
    @Size(max = 255)
    @Column(name = "AUREP")
    private String aurep;
    @NotNull
    @Size(max = 255)
    @Column(name = "CVREP")
    private String cvrep;
    @Size(max = 255)
    @Column(name = "DEREP")
    private String derep;
    @Size(max = 2)
    @Column(name = "STREP")
    private String strep;
    @Column(name = "FEREP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ferep;
    @Size(max = 10)
    @Column(name = "USREP")
    private String usrep;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "repet")
    private List<Petic51> petic51List;

    /**
     *
     */
    public Repor50() {
    }

    /**
     *
     * @param idrep
     * @param tirep
     * @param aurep
     * @param cvrep
     * @param derep
     */
    public Repor50(Integer idrep, String tirep, String aurep, String cvrep, String derep) {
        this.idrep = idrep;
        this.tirep = tirep;
        this.aurep = aurep;
        this.cvrep = cvrep;
        this.derep = derep;
    }

    /**
     *
     * @param idrep
     */
    public Repor50(Integer idrep) {
        this.idrep = idrep;
    }

    /**
     *
     * @return
     */
    public Integer getIdrep() {
        return idrep;
    }

    /**
     *
     * @param idrep
     */
    public void setIdrep(Integer idrep) {
        this.idrep = idrep;
    }

    /**
     *
     * @return
     */
    public String getTirep() {
        return tirep;
    }

    /**
     *
     * @param tirep
     */
    public void setTirep(String tirep) {
        this.tirep = tirep;
    }

    /**
     *
     * @return
     */
    public String getAurep() {
        return aurep;
    }

    /**
     *
     * @param aurep
     */
    public void setAurep(String aurep) {
        this.aurep = aurep;
    }

    /**
     *
     * @return
     */
    public String getCvrep() {
        return cvrep;
    }

    /**
     *
     * @param cvrep
     */
    public void setCvrep(String cvrep) {
        this.cvrep = cvrep;
    }

    /**
     *
     * @return
     */
    public String getDerep() {
        return derep;
    }

    /**
     *
     * @param derep
     */
    public void setDerep(String derep) {
        this.derep = derep;
    }

    /**
     *
     * @return
     */
    public String getStrep() {
        return strep;
    }

    /**
     *
     * @param strep
     */
    public void setStrep(String strep) {
        this.strep = strep;
    }

    /**
     *
     * @return
     */
    public Date getFerep() {
        return ferep;
    }

    /**
     *
     * @param ferep
     */
    public void setFerep(Date ferep) {
        this.ferep = ferep;
    }

    /**
     *
     * @return
     */
    public String getUsrep() {
        return usrep;
    }

    /**
     *
     * @param usrep
     */
    public void setUsrep(String usrep) {
        this.usrep = usrep;
    }

    /**
     *
     * @return
     */
    public List<Petic51> getPetic51List() {
        return petic51List;
    }

    /**
     *
     * @param petic51List
     */
    public void setPetic51List(List<Petic51> petic51List) {
        this.petic51List = petic51List;
    }

    /**
     *
     * @return
     */
    public byte[] getCorep() {
        return corep;
    }

    /**
     *
     * @param corep
     */
    public void setCorep(byte[] corep) {
        this.corep = corep;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idrep != null ? idrep.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Repor50)) {
            return false;
        }
        Repor50 other = (Repor50) object;
        if ((this.idrep == null && other.idrep != null) || (this.idrep != null && !this.idrep.equals(other.idrep))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return EntityToString.getString(this, this.getClass());
        //return "com.hrm.sirhapp.model.Repor50[ idrep=" + idrep + " ]";
    }
}

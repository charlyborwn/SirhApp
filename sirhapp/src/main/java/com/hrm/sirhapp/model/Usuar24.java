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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Carlos Bernal carlose.bernalh@gmail.com
 * Copyright 2018 Carlos Ernesto Bernal Hernández.
 * SirhApp
 * 
 */
@Entity
@Table(name = "USUAR24")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuar24.findAll", query = "SELECT u FROM Usuar24 u")})
public class Usuar24 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDUSU")
    private Integer idusu;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10, message = "{usuar24.useridlength}")
    @Column(name = "CVUSU", unique = true)
    private String cvusu;
    @Size(max = 70)
    @Column(name = "APUSU")
    private String apusu;
    @Size(max = 70)
    @Column(name = "AMUSU")
    private String amusu;
    @Size(max = 70)
    @Column(name = "NOUSU")
    private String nousu;
    @Size(max = 10)
    @Column(name = "PAUSU")
    private String pausu;
    @Size(max = 70)
    @Column(name = "COUSU", unique = true)
    private String cousu;
    @Size(max = 70)
    @Column(name = "NEUSU")
    private String neusu;
    @Size(max = 20)
    @Column(name = "SEUSU")
    private String seusu;
    @Size(max = 10)
    @Column(name = "EMUSU")
    private String emusu;
    @Size(max = 10)
    @Column(name = "DEUSU")
    private String deusu;
    @Size(max = 2)
    @Column(name = "NAUSU")
    private String nausu;
    @Size(max = 2)
    @Column(name = "STUSU")
    private String stusu;
    @Column(name = "FEUSU")
    @Temporal(TemporalType.TIMESTAMP)
    private Date feusu;
    @Size(max = 10)
    @Column(name = "USUSU")
    private String ususu;

    @Transient
    private String ncusu;

    @Transient
    private String password2;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cvnia")
    @OrderBy("stnia DESC")
    private List<Niacc33> niacc33List;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cvpet")
    private List<Petic51> petic51List;

    /**
     *
     */
    public Usuar24() {
    }

    /**
     *
     * @param idusu
     */
    public Usuar24(Integer idusu) {
        this.idusu = idusu;
    }

    /**
     *
     * @param idusu
     * @param cvusu
     */
    public Usuar24(Integer idusu, String cvusu) {
        this.idusu = idusu;
        this.cvusu = cvusu;
    }

    /**
     *
     * @return
     */
    public Integer getIdusu() {
        return idusu;
    }

    /**
     *
     * @param idusu
     */
    public void setIdusu(Integer idusu) {
        this.idusu = idusu;
    }

    /**
     *
     * @return
     */
    public String getCvusu() {
        return cvusu;
    }

    /**
     *
     * @param cvusu
     */
    public void setCvusu(String cvusu) {
        this.cvusu = cvusu;
    }

    /**
     *
     * @return
     */
    public String getApusu() {
        return apusu;
    }

    /**
     *
     * @param apusu
     */
    public void setApusu(String apusu) {
        this.apusu = apusu;
    }

    /**
     *
     * @return
     */
    public String getAmusu() {
        return amusu;
    }

    /**
     *
     * @param amusu
     */
    public void setAmusu(String amusu) {
        this.amusu = amusu;
    }

    /**
     *
     * @return
     */
    public String getNcusu() {
        if (cvusu != null && cvusu.length() > 0) {
            ncusu = apusu + " " + amusu + " " + nousu;
        } else {
            ncusu = "SYSTEM";
        }
        return ncusu.toUpperCase();
    }

    /**
     *
     * @param ncusu
     */
    public void setNcusu(String ncusu) {

        this.ncusu = ncusu;
    }

    /**
     *
     * @return
     */
    public String getNousu() {
        return nousu;
    }

    /**
     *
     * @param nousu
     */
    public void setNousu(String nousu) {
        this.nousu = nousu;
    }

    /**
     *
     * @return
     */
    public String getPausu() {
        return pausu;
    }

    /**
     *
     * @param pausu
     */
    public void setPausu(String pausu) {
        this.pausu = pausu;
    }

    /**
     *
     * @return
     */
    public String getCousu() {
        return cousu;
    }

    /**
     *
     * @param cousu
     */
    public void setCousu(String cousu) {
        this.cousu = cousu;
    }

    /**
     *
     * @return
     */
    public String getNeusu() {
        return neusu;
    }

    /**
     *
     * @param neusu
     */
    public void setNeusu(String neusu) {
        this.neusu = neusu;
    }

    /**
     *
     * @return
     */
    public String getSeusu() {
        return seusu;
    }

    /**
     *
     * @param seusu
     */
    public void setSeusu(String seusu) {
        this.seusu = seusu;
    }

    /**
     *
     * @return
     */
    public String getEmusu() {
        return emusu;
    }

    /**
     *
     * @param emusu
     */
    public void setEmusu(String emusu) {
        this.emusu = emusu;
    }

    /**
     *
     * @return
     */
    public String getDeusu() {
        return deusu;
    }

    /**
     *
     * @param deusu
     */
    public void setDeusu(String deusu) {
        this.deusu = deusu;
    }

    /**
     *
     * @return
     */
    public String getNausu() {
        return nausu;
    }

    /**
     *
     * @param nausu
     */
    public void setNausu(String nausu) {
        this.nausu = nausu;
    }

    /**
     *
     * @return
     */
    public String getStusu() {
        return stusu;
    }

    /**
     *
     * @param stusu
     */
    public void setStusu(String stusu) {
        this.stusu = stusu;
    }

    /**
     *
     * @return
     */
    public Date getFeusu() {
        return feusu;
    }

    /**
     *
     * @param feusu
     */
    public void setFeusu(Date feusu) {
        this.feusu = feusu;
    }

    /**
     *
     * @return
     */
    public String getUsusu() {
        return ususu;
    }

    /**
     *
     * @param ususu
     */
    public void setUsusu(String ususu) {
        this.ususu = ususu;
    }

    /**
     *
     * @return
     */
    public String getPassword2() {
        return password2;
    }

    /**
     *
     * @param password2
     */
    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    /**
     *
     * @return
     */
    public List<Niacc33> getNiacc33List() {
        return niacc33List;
    }

    /**
     *
     * @param niacc33List
     */
    public void setNiacc33List(List<Niacc33> niacc33List) {
        this.niacc33List = niacc33List;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idusu != null ? idusu.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuar24)) {
            return false;
        }
        Usuar24 other = (Usuar24) object;
        if ((this.idusu == null && other.idusu != null) || (this.idusu != null && !this.idusu.equals(other.idusu))) {
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

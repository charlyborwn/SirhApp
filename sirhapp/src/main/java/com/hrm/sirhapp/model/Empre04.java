package com.hrm.sirhapp.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "EMPRE04")
public class Empre04 implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDEMP")
    private Integer idemp;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "CVEMP")
    private String cvemp;
    @Size(max = 70)
    @Column(name = "NOEMP")
    private String noemp;
    @Size(max = 20)
    @Column(name = "SEEMP")
    private String seemp;
    @Column(name = "OREMP")
    private Integer oremp;
    @Size(max = 250)
    @Column(name = "GIEMP")
    private String giemp;
    @Size(max = 70)
    @Column(name = "PAEMP")
    private String paemp;
    @Size(max = 70)
    @Column(name = "ESEMP")
    private String esemp;
    @Size(max = 70)
    @Column(name = "MDEMP")
    private String mdemp;
    @Size(max = 70)
    @Column(name = "LOEMP")
    private String loemp;
    @Size(max = 70)
    @Column(name = "COEMP")
    private String coemp;
    @Size(max = 5)
    @Column(name = "CPEMP")
    private String cpemp;
    @Size(max = 70)
    @Column(name = "NEEMP")
    private String neemp;
    @Size(max = 2)
    @Column(name = "STEMP")
    private String stemp;
    @Column(name = "FEEMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date feemp;
    @Size(max = 10)
    @Column(name = "USEMP")
    private String usemp;

    /**
     *
     */
    public Empre04() {
    }

    /**
     *
     * @param idemp
     */
    public Empre04(Integer idemp) {
        this.idemp = idemp;
    }

    /**
     *
     * @param idemp
     * @param cvemp
     */
    public Empre04(Integer idemp, String cvemp) {
        this.idemp = idemp;
        this.cvemp = cvemp;
    }

    /**
     *
     * @return
     */
    public Integer getIdemp() {
        return idemp;
    }

    /**
     *
     * @param idemp
     */
    public void setIdemp(Integer idemp) {
        this.idemp = idemp;
    }

    /**
     *
     * @return
     */
    public String getCvemp() {
        return cvemp;
    }

    /**
     *
     * @param cvemp
     */
    public void setCvemp(String cvemp) {
        this.cvemp = cvemp;
    }

    /**
     *
     * @return
     */
    public String getNoemp() {
        return noemp;
    }

    /**
     *
     * @param noemp
     */
    public void setNoemp(String noemp) {
        this.noemp = noemp;
    }

    /**
     *
     * @return
     */
    public String getSeemp() {
        return seemp;
    }

    /**
     *
     * @param seemp
     */
    public void setSeemp(String seemp) {
        this.seemp = seemp;
    }

    /**
     *
     * @return
     */
    public Integer getOremp() {
        return oremp;
    }

    /**
     *
     * @param oremp
     */
    public void setOremp(Integer oremp) {
        this.oremp = oremp;
    }

    /**
     *
     * @return
     */
    public String getGiemp() {
        return giemp;
    }

    /**
     *
     * @param giemp
     */
    public void setGiemp(String giemp) {
        this.giemp = giemp;
    }

    /**
     *
     * @return
     */
    public String getPaemp() {
        return paemp;
    }

    /**
     *
     * @param paemp
     */
    public void setPaemp(String paemp) {
        this.paemp = paemp;
    }

    /**
     *
     * @return
     */
    public String getEsemp() {
        return esemp;
    }

    /**
     *
     * @param esemp
     */
    public void setEsemp(String esemp) {
        this.esemp = esemp;
    }

    /**
     *
     * @return
     */
    public String getMdemp() {
        return mdemp;
    }

    /**
     *
     * @param mdemp
     */
    public void setMdemp(String mdemp) {
        this.mdemp = mdemp;
    }

    /**
     *
     * @return
     */
    public String getLoemp() {
        return loemp;
    }

    /**
     *
     * @param loemp
     */
    public void setLoemp(String loemp) {
        this.loemp = loemp;
    }

    /**
     *
     * @return
     */
    public String getCoemp() {
        return coemp;
    }

    /**
     *
     * @param coemp
     */
    public void setCoemp(String coemp) {
        this.coemp = coemp;
    }

    /**
     *
     * @return
     */
    public String getCpemp() {
        return cpemp;
    }

    /**
     *
     * @param cpemp
     */
    public void setCpemp(String cpemp) {
        this.cpemp = cpemp;
    }

    /**
     *
     * @return
     */
    public String getNeemp() {
        return neemp;
    }

    /**
     *
     * @param neemp
     */
    public void setNeemp(String neemp) {
        this.neemp = neemp;
    }

    /**
     *
     * @return
     */
    public String getStemp() {
        return stemp;
    }

    /**
     *
     * @param stemp
     */
    public void setStemp(String stemp) {
        this.stemp = stemp;
    }

    /**
     *
     * @return
     */
    public Date getFeemp() {
        return feemp;
    }

    /**
     *
     * @param feemp
     */
    public void setFeemp(Date feemp) {
        this.feemp = feemp;
    }

    /**
     *
     * @return
     */
    public String getUsemp() {
        return usemp;
    }

    /**
     *
     * @param usemp
     */
    public void setUsemp(String usemp) {
        this.usemp = usemp;
    }
    
    @Override
    public int hashCode() {
        return (idemp != null)
                ? (Empre04.class.hashCode() + idemp.hashCode())
                : super.hashCode();
    }

    @Override
    public boolean equals(Object object) {
        return (object instanceof Empre04) && (idemp != null)
                ? idemp.equals(((Empre04) object).idemp)
                : (object == this);
    }


    @Override
    public String toString() {
        return EntityToString.getString(this, this.getClass());
        //return "com.hrm.sirhapp.model.Empre04[ idemp=" + idemp + " ]";
    }

}

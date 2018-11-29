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
@Table(name = "EXPER31")
@NamedQueries({
    @NamedQuery(name = "Exper31.findAll", query = "SELECT e FROM Exper31 e")})
public class Exper31 implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDEXP")
    private Integer idexp;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NTEXP")
    private int ntexp;
    @Size(max = 70)
    @Column(name = "EMEXP")
    private String emexp;
    @Size(max = 70)
    @Column(name = "CEEXP")
    private String ceexp;
    @Column(name = "INEXP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date inexp;
    @Column(name = "TEEXP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date teexp;
    @Size(max = 70)
    @Column(name = "CSEXP")
    private String csexp;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "SXEXP")
    private Float sxexp;
    @Size(max = 70)
    @Column(name = "SIEXP")
    private String siexp;
    @Size(max = 70)
    @Column(name = "PAEXP")
    private String paexp;
    @Size(max = 70)
    @Column(name = "ESEXP")
    private String esexp;
    @Size(max = 70)
    @Column(name = "MDEXP")
    private String mdexp;
    @Size(max = 70)
    @Column(name = "LOEXP")
    private String loexp;
    @Size(max = 70)
    @Column(name = "COEXP")
    private String coexp;
    @Size(max = 70)
    @Column(name = "CAEXP")
    private String caexp;
    @Size(max = 5)
    @Column(name = "CPEXP")
    private String cpexp;
    @Size(max = 15)
    @Column(name = "TDEXP")
    private String tdexp;
    @Size(max = 70)
    @Column(name = "JIEXP")
    private String jiexp;
    @Size(max = 70)
    @Column(name = "CJEXP")
    private String cjexp;
    @Size(max = 15)
    @Column(name = "TCEXP")
    private String tcexp;
    @Size(max = 70)
    @Column(name = "MAEXP")
    private String maexp;
    @Size(max = 70)
    @Column(name = "TIEXP")
    private String tiexp;
    @Size(max = 6)
    @Column(name = "W1EXP")
    private String w1exp;
    @Size(max = 70)
    @Column(name = "PTEXP")
    private String ptexp;
    @Size(max = 250)
    @Column(name = "OBEXP")
    private String obexp;
    @Size(max = 2)
    @Column(name = "STEXP")
    private String stexp;
    @Column(name = "FEEXP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date feexp;
    @Size(max = 10)
    @Column(name = "USEXP")
    private String usexp;

    /**
     *
     */
    public Exper31() {
    }

    /**
     *
     * @param idexp
     */
    public Exper31(Integer idexp) {
        this.idexp = idexp;
    }

    /**
     *
     * @param idexp
     * @param ntexp
     */
    public Exper31(Integer idexp, int ntexp) {
        this.idexp = idexp;
        this.ntexp = ntexp;
    }

    /**
     *
     * @return
     */
    public Integer getIdexp() {
        return idexp;
    }

    /**
     *
     * @param idexp
     */
    public void setIdexp(Integer idexp) {
        this.idexp = idexp;
    }

    /**
     *
     * @return
     */
    public int getNtexp() {
        return ntexp;
    }

    /**
     *
     * @param ntexp
     */
    public void setNtexp(int ntexp) {
        this.ntexp = ntexp;
    }

    /**
     *
     * @return
     */
    public String getEmexp() {
        return emexp;
    }

    /**
     *
     * @param emexp
     */
    public void setEmexp(String emexp) {
        this.emexp = emexp;
    }

    /**
     *
     * @return
     */
    public String getCeexp() {
        return ceexp;
    }

    /**
     *
     * @param ceexp
     */
    public void setCeexp(String ceexp) {
        this.ceexp = ceexp;
    }

    /**
     *
     * @return
     */
    public Date getInexp() {
        return inexp;
    }

    /**
     *
     * @param inexp
     */
    public void setInexp(Date inexp) {
        this.inexp = inexp;
    }

    /**
     *
     * @return
     */
    public Date getTeexp() {
        return teexp;
    }

    /**
     *
     * @param teexp
     */
    public void setTeexp(Date teexp) {
        this.teexp = teexp;
    }

    /**
     *
     * @return
     */
    public String getCsexp() {
        return csexp;
    }

    /**
     *
     * @param csexp
     */
    public void setCsexp(String csexp) {
        this.csexp = csexp;
    }

    /**
     *
     * @return
     */
    public Float getSxexp() {
        return sxexp;
    }

    /**
     *
     * @param sxexp
     */
    public void setSxexp(Float sxexp) {
        this.sxexp = sxexp;
    }

    /**
     *
     * @return
     */
    public String getSiexp() {
        return siexp;
    }

    /**
     *
     * @param siexp
     */
    public void setSiexp(String siexp) {
        this.siexp = siexp;
    }

    /**
     *
     * @return
     */
    public String getPaexp() {
        return paexp;
    }

    /**
     *
     * @param paexp
     */
    public void setPaexp(String paexp) {
        this.paexp = paexp;
    }

    /**
     *
     * @return
     */
    public String getEsexp() {
        return esexp;
    }

    /**
     *
     * @param esexp
     */
    public void setEsexp(String esexp) {
        this.esexp = esexp;
    }

    /**
     *
     * @return
     */
    public String getMdexp() {
        return mdexp;
    }

    /**
     *
     * @param mdexp
     */
    public void setMdexp(String mdexp) {
        this.mdexp = mdexp;
    }

    /**
     *
     * @return
     */
    public String getLoexp() {
        return loexp;
    }

    /**
     *
     * @param loexp
     */
    public void setLoexp(String loexp) {
        this.loexp = loexp;
    }

    /**
     *
     * @return
     */
    public String getCoexp() {
        return coexp;
    }

    /**
     *
     * @param coexp
     */
    public void setCoexp(String coexp) {
        this.coexp = coexp;
    }

    /**
     *
     * @return
     */
    public String getCaexp() {
        return caexp;
    }

    /**
     *
     * @param caexp
     */
    public void setCaexp(String caexp) {
        this.caexp = caexp;
    }

    /**
     *
     * @return
     */
    public String getCpexp() {
        return cpexp;
    }

    /**
     *
     * @param cpexp
     */
    public void setCpexp(String cpexp) {
        this.cpexp = cpexp;
    }

    /**
     *
     * @return
     */
    public String getTdexp() {
        return tdexp;
    }

    /**
     *
     * @param tdexp
     */
    public void setTdexp(String tdexp) {
        this.tdexp = tdexp;
    }

    /**
     *
     * @return
     */
    public String getJiexp() {
        return jiexp;
    }

    /**
     *
     * @param jiexp
     */
    public void setJiexp(String jiexp) {
        this.jiexp = jiexp;
    }

    /**
     *
     * @return
     */
    public String getCjexp() {
        return cjexp;
    }

    /**
     *
     * @param cjexp
     */
    public void setCjexp(String cjexp) {
        this.cjexp = cjexp;
    }

    /**
     *
     * @return
     */
    public String getTcexp() {
        return tcexp;
    }

    /**
     *
     * @param tcexp
     */
    public void setTcexp(String tcexp) {
        this.tcexp = tcexp;
    }

    /**
     *
     * @return
     */
    public String getMaexp() {
        return maexp;
    }

    /**
     *
     * @param maexp
     */
    public void setMaexp(String maexp) {
        this.maexp = maexp;
    }

    /**
     *
     * @return
     */
    public String getTiexp() {
        return tiexp;
    }

    /**
     *
     * @param tiexp
     */
    public void setTiexp(String tiexp) {
        this.tiexp = tiexp;
    }

    /**
     *
     * @return
     */
    public String getW1exp() {
        return w1exp;
    }

    /**
     *
     * @param w1exp
     */
    public void setW1exp(String w1exp) {
        this.w1exp = w1exp;
    }

    /**
     *
     * @return
     */
    public String getPtexp() {
        return ptexp;
    }

    /**
     *
     * @param ptexp
     */
    public void setPtexp(String ptexp) {
        this.ptexp = ptexp;
    }

    /**
     *
     * @return
     */
    public String getObexp() {
        return obexp;
    }

    /**
     *
     * @param obexp
     */
    public void setObexp(String obexp) {
        this.obexp = obexp;
    }

    /**
     *
     * @return
     */
    public String getStexp() {
        return stexp;
    }

    /**
     *
     * @param stexp
     */
    public void setStexp(String stexp) {
        this.stexp = stexp;
    }

    /**
     *
     * @return
     */
    public Date getFeexp() {
        return feexp;
    }

    /**
     *
     * @param feexp
     */
    public void setFeexp(Date feexp) {
        this.feexp = feexp;
    }

    /**
     *
     * @return
     */
    public String getUsexp() {
        return usexp;
    }

    /**
     *
     * @param usexp
     */
    public void setUsexp(String usexp) {
        this.usexp = usexp;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idexp != null ? idexp.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Exper31)) {
            return false;
        }
        Exper31 other = (Exper31) object;
        if ((this.idexp == null && other.idexp != null) || (this.idexp != null && !this.idexp.equals(other.idexp))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return EntityToString.getString(this, this.getClass());
        //return "com.hrm.sirhapp.model.Exper31[ idexp=" + idexp + " ]";
    }

}

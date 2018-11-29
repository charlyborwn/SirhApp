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
@Table(name = "EXPER31A")
@NamedQueries({
    @NamedQuery(name = "Exper31a.findAll", query = "SELECT e FROM Exper31a e")})
public class Exper31a implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDEXP_A")
    private Integer idexpA;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 13)
    @Column(name = "RFEXP_A")
    private String rfexpA;
    @Size(max = 70)
    @Column(name = "EMEXP_A")
    private String emexpA;
    @Size(max = 70)
    @Column(name = "CEEXP_A")
    private String ceexpA;
    @Column(name = "INEXP_A")
    @Temporal(TemporalType.TIMESTAMP)
    private Date inexpA;
    @Column(name = "TEEXP_A")
    @Temporal(TemporalType.TIMESTAMP)
    private Date teexpA;
    @Size(max = 70)
    @Column(name = "CSEXP_A")
    private String csexpA;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "SXEXP_A")
    private Float sxexpA;
    @Size(max = 70)
    @Column(name = "SIEXP_A")
    private String siexpA;
    @Size(max = 70)
    @Column(name = "PAEXP_A")
    private String paexpA;
    @Size(max = 70)
    @Column(name = "ESEXP_A")
    private String esexpA;
    @Size(max = 70)
    @Column(name = "MDEXP_A")
    private String mdexpA;
    @Size(max = 70)
    @Column(name = "LOEXP_A")
    private String loexpA;
    @Size(max = 70)
    @Column(name = "COEXP_A")
    private String coexpA;
    @Size(max = 70)
    @Column(name = "CAEXP_A")
    private String caexpA;
    @Size(max = 5)
    @Column(name = "CPEXP_A")
    private String cpexpA;
    @Size(max = 15)
    @Column(name = "TDEXP_A")
    private String tdexpA;
    @Size(max = 70)
    @Column(name = "JIEXP_A")
    private String jiexpA;
    @Size(max = 70)
    @Column(name = "CJEXP_A")
    private String cjexpA;
    @Size(max = 15)
    @Column(name = "TCEXP_A")
    private String tcexpA;
    @Size(max = 70)
    @Column(name = "MAEXP_A")
    private String maexpA;
    @Size(max = 70)
    @Column(name = "TIEXP_A")
    private String tiexpA;
    @Size(max = 6)
    @Column(name = "W1EXP_A")
    private String w1expA;
    @Size(max = 70)
    @Column(name = "PTEXP_A")
    private String ptexpA;
    @Size(max = 250)
    @Column(name = "OBEXP_A")
    private String obexpA;
    @Size(max = 2)
    @Column(name = "STEXP_A")
    private String stexpA;
    @Column(name = "FEEXP_A")
    @Temporal(TemporalType.TIMESTAMP)
    private Date feexpA;
    @Size(max = 10)
    @Column(name = "USEXP_A")
    private String usexpA;

    /**
     *
     */
    public Exper31a() {
    }

    /**
     *
     * @param idexpA
     */
    public Exper31a(Integer idexpA) {
        this.idexpA = idexpA;
    }

    /**
     *
     * @param idexpA
     * @param rfexpA
     */
    public Exper31a(Integer idexpA, String rfexpA) {
        this.idexpA = idexpA;
        this.rfexpA = rfexpA;
    }

    /**
     *
     * @return
     */
    public Integer getIdexpA() {
        return idexpA;
    }

    /**
     *
     * @param idexpA
     */
    public void setIdexpA(Integer idexpA) {
        this.idexpA = idexpA;
    }

    /**
     *
     * @return
     */
    public String getRfexpA() {
        return rfexpA;
    }

    /**
     *
     * @param rfexpA
     */
    public void setRfexpA(String rfexpA) {
        this.rfexpA = rfexpA;
    }

    /**
     *
     * @return
     */
    public String getEmexpA() {
        return emexpA;
    }

    /**
     *
     * @param emexpA
     */
    public void setEmexpA(String emexpA) {
        this.emexpA = emexpA;
    }

    /**
     *
     * @return
     */
    public String getCeexpA() {
        return ceexpA;
    }

    /**
     *
     * @param ceexpA
     */
    public void setCeexpA(String ceexpA) {
        this.ceexpA = ceexpA;
    }

    /**
     *
     * @return
     */
    public Date getInexpA() {
        return inexpA;
    }

    /**
     *
     * @param inexpA
     */
    public void setInexpA(Date inexpA) {
        this.inexpA = inexpA;
    }

    /**
     *
     * @return
     */
    public Date getTeexpA() {
        return teexpA;
    }

    /**
     *
     * @param teexpA
     */
    public void setTeexpA(Date teexpA) {
        this.teexpA = teexpA;
    }

    /**
     *
     * @return
     */
    public String getCsexpA() {
        return csexpA;
    }

    /**
     *
     * @param csexpA
     */
    public void setCsexpA(String csexpA) {
        this.csexpA = csexpA;
    }

    /**
     *
     * @return
     */
    public Float getSxexpA() {
        return sxexpA;
    }

    /**
     *
     * @param sxexpA
     */
    public void setSxexpA(Float sxexpA) {
        this.sxexpA = sxexpA;
    }

    /**
     *
     * @return
     */
    public String getSiexpA() {
        return siexpA;
    }

    /**
     *
     * @param siexpA
     */
    public void setSiexpA(String siexpA) {
        this.siexpA = siexpA;
    }

    /**
     *
     * @return
     */
    public String getPaexpA() {
        return paexpA;
    }

    /**
     *
     * @param paexpA
     */
    public void setPaexpA(String paexpA) {
        this.paexpA = paexpA;
    }

    /**
     *
     * @return
     */
    public String getEsexpA() {
        return esexpA;
    }

    /**
     *
     * @param esexpA
     */
    public void setEsexpA(String esexpA) {
        this.esexpA = esexpA;
    }

    /**
     *
     * @return
     */
    public String getMdexpA() {
        return mdexpA;
    }

    /**
     *
     * @param mdexpA
     */
    public void setMdexpA(String mdexpA) {
        this.mdexpA = mdexpA;
    }

    /**
     *
     * @return
     */
    public String getLoexpA() {
        return loexpA;
    }

    /**
     *
     * @param loexpA
     */
    public void setLoexpA(String loexpA) {
        this.loexpA = loexpA;
    }

    /**
     *
     * @return
     */
    public String getCoexpA() {
        return coexpA;
    }

    /**
     *
     * @param coexpA
     */
    public void setCoexpA(String coexpA) {
        this.coexpA = coexpA;
    }

    /**
     *
     * @return
     */
    public String getCaexpA() {
        return caexpA;
    }

    /**
     *
     * @param caexpA
     */
    public void setCaexpA(String caexpA) {
        this.caexpA = caexpA;
    }

    /**
     *
     * @return
     */
    public String getCpexpA() {
        return cpexpA;
    }

    /**
     *
     * @param cpexpA
     */
    public void setCpexpA(String cpexpA) {
        this.cpexpA = cpexpA;
    }

    /**
     *
     * @return
     */
    public String getTdexpA() {
        return tdexpA;
    }

    /**
     *
     * @param tdexpA
     */
    public void setTdexpA(String tdexpA) {
        this.tdexpA = tdexpA;
    }

    /**
     *
     * @return
     */
    public String getJiexpA() {
        return jiexpA;
    }

    /**
     *
     * @param jiexpA
     */
    public void setJiexpA(String jiexpA) {
        this.jiexpA = jiexpA;
    }

    /**
     *
     * @return
     */
    public String getCjexpA() {
        return cjexpA;
    }

    /**
     *
     * @param cjexpA
     */
    public void setCjexpA(String cjexpA) {
        this.cjexpA = cjexpA;
    }

    /**
     *
     * @return
     */
    public String getTcexpA() {
        return tcexpA;
    }

    /**
     *
     * @param tcexpA
     */
    public void setTcexpA(String tcexpA) {
        this.tcexpA = tcexpA;
    }

    /**
     *
     * @return
     */
    public String getMaexpA() {
        return maexpA;
    }

    /**
     *
     * @param maexpA
     */
    public void setMaexpA(String maexpA) {
        this.maexpA = maexpA;
    }

    /**
     *
     * @return
     */
    public String getTiexpA() {
        return tiexpA;
    }

    /**
     *
     * @param tiexpA
     */
    public void setTiexpA(String tiexpA) {
        this.tiexpA = tiexpA;
    }

    /**
     *
     * @return
     */
    public String getW1expA() {
        return w1expA;
    }

    /**
     *
     * @param w1expA
     */
    public void setW1expA(String w1expA) {
        this.w1expA = w1expA;
    }

    /**
     *
     * @return
     */
    public String getPtexpA() {
        if (ptexpA == null) {
            this.ptexpA = "";
        }
        return ptexpA;
    }

    /**
     *
     * @param ptexpA
     */
    public void setPtexpA(String ptexpA) {
        this.ptexpA = ptexpA;
    }

    /**
     *
     * @return
     */
    public String getObexpA() {
        return obexpA;
    }

    /**
     *
     * @param obexpA
     */
    public void setObexpA(String obexpA) {
        this.obexpA = obexpA;
    }

    /**
     *
     * @return
     */
    public String getStexpA() {
        return stexpA;
    }

    /**
     *
     * @param stexpA
     */
    public void setStexpA(String stexpA) {
        this.stexpA = stexpA;
    }

    /**
     *
     * @return
     */
    public Date getFeexpA() {
        return feexpA;
    }

    /**
     *
     * @param feexpA
     */
    public void setFeexpA(Date feexpA) {
        this.feexpA = feexpA;
    }

    /**
     *
     * @return
     */
    public String getUsexpA() {
        return usexpA;
    }

    /**
     *
     * @param usexpA
     */
    public void setUsexpA(String usexpA) {
        this.usexpA = usexpA;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idexpA != null ? idexpA.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Exper31a)) {
            return false;
        }
        Exper31a other = (Exper31a) object;
        if ((this.idexpA == null && other.idexpA != null) || (this.idexpA != null && !this.idexpA.equals(other.idexpA))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return EntityToString.getString(this, this.getClass());
        //return "com.hrm.sirhapp.model.Exper31a[ idexpA=" + idexpA + " ]";
    }

}

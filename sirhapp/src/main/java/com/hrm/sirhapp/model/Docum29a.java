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
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.apache.commons.io.FilenameUtils;

/**
 *
 * @author Carlos Bernal carlose.bernalh@gmail.com
 * Copyright 2018 Carlos Ernesto Bernal Hernández.
 * SirhApp
 * 
 */
@Entity
@Table(name = "DOCUM29A")
@NamedQueries({
    @NamedQuery(name = "Docum29a.findAll", query = "SELECT d FROM Docum29a d")})
public class Docum29a implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDDOC_A")
    private Integer iddocA;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 13)
    @Column(name = "RFDOC_A")
    private String rfdocA;
    @Size(max = 70)
    @Column(name = "TDDOC_A")
    private String tddocA;
    @Size(max = 6)
    @Column(name = "W1DOC_A")
    private String w1docA;
    @Size(max = 70)
    @Column(name = "CADOC_A")
    private String cadocA;
    @Size(max = 70)
    @Column(name = "DEDOC_A")
    private String dedocA;
    @Column(name = "INDOC_A")
    @Temporal(TemporalType.TIMESTAMP)
    private Date indocA;
    @Column(name = "TEDOC_A")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tedocA;
    @Size(max = 70)
    @Column(name = "PADOC_A")
    private String padocA;
    @Size(max = 250)
    @Column(name = "OBDOC_A")
    private String obdocA;
    @Size(max = 2)
    @Column(name = "STDOC_A")
    private String stdocA;
    @Column(name = "FEDOC_A")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fedocA;
    @Size(max = 10)
    @Column(name = "USDOC_A")
    private String usdocA;
    @Transient
    private Boolean ftdocA;

    /**
     *
     */
    public Docum29a() {
    }

    /**
     *
     * @param iddocA
     */
    public Docum29a(Integer iddocA) {
        this.iddocA = iddocA;
    }

    /**
     *
     * @param iddocA
     * @param rfdocA
     */
    public Docum29a(Integer iddocA, String rfdocA) {
        this.iddocA = iddocA;
        this.rfdocA = rfdocA;
    }

    /**
     *
     * @return
     */
    public Integer getIddocA() {
        return iddocA;
    }

    /**
     *
     * @param iddocA
     */
    public void setIddocA(Integer iddocA) {
        this.iddocA = iddocA;
    }

    /**
     *
     * @return
     */
    public String getRfdocA() {
        return rfdocA;
    }

    /**
     *
     * @param rfdocA
     */
    public void setRfdocA(String rfdocA) {
        this.rfdocA = rfdocA;
    }

    /**
     *
     * @return
     */
    public String getTddocA() {
        return tddocA;
    }

    /**
     *
     * @param tddocA
     */
    public void setTddocA(String tddocA) {
        this.tddocA = tddocA;
    }

    /**
     *
     * @return
     */
    public String getW1docA() {
        return w1docA;
    }

    /**
     *
     * @param w1docA
     */
    public void setW1docA(String w1docA) {
        this.w1docA = w1docA;
    }

    /**
     *
     * @return
     */
    public String getCadocA() {
        return cadocA;
    }

    /**
     *
     * @param cadocA
     */
    public void setCadocA(String cadocA) {
        this.cadocA = cadocA;
    }

    /**
     *
     * @return
     */
    public String getDedocA() {
        return dedocA;
    }

    /**
     *
     * @param dedocA
     */
    public void setDedocA(String dedocA) {
        this.dedocA = dedocA;
    }

    /**
     *
     * @return
     */
    public Date getIndocA() {
        return indocA;
    }

    /**
     *
     * @param indocA
     */
    public void setIndocA(Date indocA) {
        this.indocA = indocA;
    }

    /**
     *
     * @return
     */
    public Date getTedocA() {
        return tedocA;
    }

    /**
     *
     * @param tedocA
     */
    public void setTedocA(Date tedocA) {
        this.tedocA = tedocA;
    }

    /**
     *
     * @return
     */
    public String getPadocA() {
        if (padocA == null) {
            this.padocA = "";
        }
        return padocA;
    }

    /**
     *
     * @param padocA
     */
    public void setPadocA(String padocA) {
        this.padocA = padocA;
    }

    /**
     *
     * @return
     */
    public String getObdocA() {
        return obdocA;
    }

    /**
     *
     * @param obdocA
     */
    public void setObdocA(String obdocA) {
        this.obdocA = obdocA;
    }

    /**
     *
     * @return
     */
    public String getStdocA() {
        return stdocA;
    }

    /**
     *
     * @param stdocA
     */
    public void setStdocA(String stdocA) {
        this.stdocA = stdocA;
    }

    /**
     *
     * @return
     */
    public Date getFedocA() {
        return fedocA;
    }

    /**
     *
     * @param fedocA
     */
    public void setFedocA(Date fedocA) {
        this.fedocA = fedocA;
    }

    /**
     *
     * @return
     */
    public String getUsdocA() {
        return usdocA;
    }

    /**
     *
     * @param usdocA
     */
    public void setUsdocA(String usdocA) {
        this.usdocA = usdocA;
    }

    /**
     *
     * @return
     */
    public Boolean getFtdocA() {
        if (this.ftdocA == null && padocA != null && padocA.length() > 0) {
            return FilenameUtils.getExtension(padocA).equals("pdf");
        }
        return ftdocA;
    }

    /**
     *
     * @param ftdocA
     */
    public void setFtdocA(Boolean ftdocA) {
        this.ftdocA = ftdocA;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iddocA != null ? iddocA.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Docum29a)) {
            return false;
        }
        Docum29a other = (Docum29a) object;
        if ((this.iddocA == null && other.iddocA != null) || (this.iddocA != null && !this.iddocA.equals(other.iddocA))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return EntityToString.getString(this, this.getClass());
        //return "com.hrm.sirhapp.model.Docum29a[ iddocA=" + iddocA + " ]";
    }

}

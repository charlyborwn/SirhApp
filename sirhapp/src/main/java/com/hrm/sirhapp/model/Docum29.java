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
@Table(name = "DOCUM29")
@NamedQueries({
    @NamedQuery(name = "Docum29.findAll", query = "SELECT d FROM Docum29 d")})
public class Docum29 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDDOC")
    private Integer iddoc;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NTDOC")
    private int ntdoc;
    @Size(max = 70)
    @Column(name = "TDDOC")
    private String tddoc;
    @Size(max = 6)
    @Column(name = "W1DOC")
    private String w1doc;
    @Size(max = 70)
    @Column(name = "CADOC")
    private String cadoc;
    @Size(max = 70)
    @Column(name = "DEDOC")
    private String dedoc;
    @Column(name = "INDOC")
    @Temporal(TemporalType.TIMESTAMP)
    private Date indoc;
    @Column(name = "TEDOC")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tedoc;
    @Size(max = 70)
    @Column(name = "PADOC")
    private String padoc;
    @Size(max = 250)
    @Column(name = "OBDOC")
    private String obdoc;
    @Size(max = 2)
    @Column(name = "STDOC")
    private String stdoc;
    @Column(name = "FEDOC")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fedoc;
    @Size(max = 10)
    @Column(name = "USDOC")
    private String usdoc;
    @Transient
    private Boolean ftdoc;

    /**
     *
     */
    public Docum29() {
    }

    /**
     *
     * @param iddoc
     */
    public Docum29(Integer iddoc) {
        this.iddoc = iddoc;
    }

    /**
     *
     * @param iddoc
     * @param ntdoc
     */
    public Docum29(Integer iddoc, int ntdoc) {
        this.iddoc = iddoc;
        this.ntdoc = ntdoc;
    }

    /**
     *
     * @return
     */
    public Integer getIddoc() {
        return iddoc;
    }

    /**
     *
     * @param iddoc
     */
    public void setIddoc(Integer iddoc) {
        this.iddoc = iddoc;
    }

    /**
     *
     * @return
     */
    public int getNtdoc() {
        return ntdoc;
    }

    /**
     *
     * @param ntdoc
     */
    public void setNtdoc(int ntdoc) {
        this.ntdoc = ntdoc;
    }

    /**
     *
     * @return
     */
    public String getTddoc() {
        return tddoc;
    }

    /**
     *
     * @param tddoc
     */
    public void setTddoc(String tddoc) {
        this.tddoc = tddoc;
    }

    /**
     *
     * @return
     */
    public String getW1doc() {
        return w1doc;
    }

    /**
     *
     * @param w1doc
     */
    public void setW1doc(String w1doc) {
        this.w1doc = w1doc;
    }

    /**
     *
     * @return
     */
    public String getCadoc() {
        return cadoc;
    }

    /**
     *
     * @param cadoc
     */
    public void setCadoc(String cadoc) {
        this.cadoc = cadoc;
    }

    /**
     *
     * @return
     */
    public String getDedoc() {
        return dedoc;
    }

    /**
     *
     * @param dedoc
     */
    public void setDedoc(String dedoc) {
        this.dedoc = dedoc;
    }

    /**
     *
     * @return
     */
    public Date getIndoc() {
        return indoc;
    }

    /**
     *
     * @param indoc
     */
    public void setIndoc(Date indoc) {
        this.indoc = indoc;
    }

    /**
     *
     * @return
     */
    public Date getTedoc() {
        return tedoc;
    }

    /**
     *
     * @param tedoc
     */
    public void setTedoc(Date tedoc) {
        this.tedoc = tedoc;
    }

    /**
     *
     * @return
     */
    public String getPadoc() {
        return padoc;
    }

    /**
     *
     * @param padoc
     */
    public void setPadoc(String padoc) {
        this.padoc = padoc;
    }

    /**
     *
     * @return
     */
    public String getObdoc() {
        return obdoc;
    }

    /**
     *
     * @param obdoc
     */
    public void setObdoc(String obdoc) {
        this.obdoc = obdoc;
    }

    /**
     *
     * @return
     */
    public String getStdoc() {
        return stdoc;
    }

    /**
     *
     * @param stdoc
     */
    public void setStdoc(String stdoc) {
        this.stdoc = stdoc;
    }

    /**
     *
     * @return
     */
    public Date getFedoc() {
        return fedoc;
    }

    /**
     *
     * @param fedoc
     */
    public void setFedoc(Date fedoc) {
        this.fedoc = fedoc;
    }

    /**
     *
     * @return
     */
    public String getUsdoc() {
        return usdoc;
    }

    /**
     *
     * @param usdoc
     */
    public void setUsdoc(String usdoc) {
        this.usdoc = usdoc;
    }

    /**
     *
     * @return
     */
    public Boolean getFtdoc() {
        if (this.ftdoc == null && padoc != null && padoc.length() > 0) {
            return FilenameUtils.getExtension(padoc).equals("pdf");
        }
        return ftdoc;
    }

    /**
     *
     * @param ftdoc
     */
    public void setFtdoc(Boolean ftdoc) {
        this.ftdoc = ftdoc;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iddoc != null ? iddoc.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Docum29)) {
            return false;
        }
        Docum29 other = (Docum29) object;
        if ((this.iddoc == null && other.iddoc != null) || (this.iddoc != null && !this.iddoc.equals(other.iddoc))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return EntityToString.getString(this, this.getClass());
        //return "com.hrm.sirhapp.model.Docum29[ iddoc=" + iddoc + " ]";
    }

}

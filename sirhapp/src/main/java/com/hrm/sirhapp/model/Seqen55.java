package com.hrm.sirhapp.model;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
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
@Table(name = "SEQEN55")
@XmlRootElement         
@NamedQueries({
    @NamedQuery(name = "Seqen55.findAll", query = "SELECT s FROM Seqen55 s")
    , @NamedQuery(name = "Seqen55.findBySequenceName", query = "SELECT s FROM Seqen55 s WHERE s.sequenceName = :sequenceName")
    , @NamedQuery(name = "Seqen55.findBySequenceIncrement", query = "SELECT s FROM Seqen55 s WHERE s.sequenceIncrement = :sequenceIncrement")
    , @NamedQuery(name = "Seqen55.findBySequenceMinValue", query = "SELECT s FROM Seqen55 s WHERE s.sequenceMinValue = :sequenceMinValue")
    , @NamedQuery(name = "Seqen55.findBySequenceMaxValue", query = "SELECT s FROM Seqen55 s WHERE s.sequenceMaxValue = :sequenceMaxValue")
    , @NamedQuery(name = "Seqen55.findBySequenceCurValue", query = "SELECT s FROM Seqen55 s WHERE s.sequenceCurValue = :sequenceCurValue")
    , @NamedQuery(name = "Seqen55.findBySequenceCycle", query = "SELECT s FROM Seqen55 s WHERE s.sequenceCycle = :sequenceCycle")})
public class Seqen55 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "sequence_name")
    private String sequenceName;
    @Basic(optional = false)
    @NotNull
    @Column(name = "sequence_increment")
    private int sequenceIncrement;
    @Basic(optional = false)
    @NotNull
    @Column(name = "sequence_min_value")
    private int sequenceMinValue;
    @Basic(optional = false)
    @NotNull
    @Column(name = "sequence_max_value")
    private long sequenceMaxValue;
    @Column(name = "sequence_cur_value")
    private BigInteger sequenceCurValue;
    @Basic(optional = false)
    @NotNull
    @Column(name = "sequence_cycle")
    private boolean sequenceCycle;

    /**
     *
     */
    public Seqen55() {
    }

    /**
     *
     * @param sequenceName
     */
    public Seqen55(String sequenceName) {
        this.sequenceName = sequenceName;
    }

    /**
     *
     * @param sequenceName
     * @param sequenceIncrement
     * @param sequenceMinValue
     * @param sequenceMaxValue
     * @param sequenceCycle
     */
    public Seqen55(String sequenceName, int sequenceIncrement, int sequenceMinValue, long sequenceMaxValue, boolean sequenceCycle) {
        this.sequenceName = sequenceName;
        this.sequenceIncrement = sequenceIncrement;
        this.sequenceMinValue = sequenceMinValue;
        this.sequenceMaxValue = sequenceMaxValue;
        this.sequenceCycle = sequenceCycle;
    }

    /**
     *
     * @return
     */
    public String getSequenceName() {
        return sequenceName;
    }

    /**
     *
     * @param sequenceName
     */
    public void setSequenceName(String sequenceName) {
        this.sequenceName = sequenceName;
    }

    /**
     *
     * @return
     */
    public int getSequenceIncrement() {
        return sequenceIncrement;
    }

    /**
     *
     * @param sequenceIncrement
     */
    public void setSequenceIncrement(int sequenceIncrement) {
        this.sequenceIncrement = sequenceIncrement;
    }

    /**
     *
     * @return
     */
    public int getSequenceMinValue() {
        return sequenceMinValue;
    }

    /**
     *
     * @param sequenceMinValue
     */
    public void setSequenceMinValue(int sequenceMinValue) {
        this.sequenceMinValue = sequenceMinValue;
    }

    /**
     *
     * @return
     */
    public long getSequenceMaxValue() {
        return sequenceMaxValue;
    }

    /**
     *
     * @param sequenceMaxValue
     */
    public void setSequenceMaxValue(long sequenceMaxValue) {
        this.sequenceMaxValue = sequenceMaxValue;
    }

    /**
     *
     * @return
     */
    public BigInteger getSequenceCurValue() {
        return sequenceCurValue;
    }

    /**
     *
     * @param sequenceCurValue
     */
    public void setSequenceCurValue(BigInteger sequenceCurValue) {
        this.sequenceCurValue = sequenceCurValue;
    }

    /**
     *
     * @return
     */
    public boolean getSequenceCycle() {
        return sequenceCycle;
    }

    /**
     *
     * @param sequenceCycle
     */
    public void setSequenceCycle(boolean sequenceCycle) {
        this.sequenceCycle = sequenceCycle;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sequenceName != null ? sequenceName.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Seqen55)) {
            return false;
        }
        Seqen55 other = (Seqen55) object;
        if ((this.sequenceName == null && other.sequenceName != null) || (this.sequenceName != null && !this.sequenceName.equals(other.sequenceName))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hrm.sirhapp.model.Seqen55[ sequenceName=" + sequenceName + " ]";
    }

}

package com.hrm.sirhapp.model;

import com.hrm.sirhapp.util.FacesUtil;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author Carlos Bernal carlose.bernalh@gmail.com
 * Copyright 2018 Carlos Ernesto Bernal Hernández.
 * SirhApp
 * 
 */
@MappedSuperclass
public abstract class BaseEntityAudit extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Size(max = 20)
    @Column(name = "created_by", length = 10)
    private String createdBy;

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @Size(max = 20)
    @Column(name = "updated_by", length = 10)
    private String updatedBy;

    /**
     *
     * @return
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     *
     * @param createdAt
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     *
     * @return
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     *
     * @param createdBy
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     *
     * @return
     */
    public Date getUpdatedAt() {
        return updatedAt;
    }

    /**
     *
     * @param updatedAt
     */
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     *
     * @return
     */
    public String getUpdatedBy() {
        return updatedBy;
    }

    /**
     *
     * @param updatedBy
     */
    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    /**
     * Sets createdAt before insert
     */
    @PrePersist
    public void setPrePersist() {
        this.createdAt = new Date();
        this.createdBy = FacesUtil.getUserName();
    }

    /**
     * Sets updatedAt before update
     */
    @PreUpdate
    public void setPreUpdate() {
        this.updatedAt = new Date();
        this.updatedBy = FacesUtil.getUserName();
    }

}

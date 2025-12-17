package com.assignment.indomaret_master_data_management.domain.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.sql.Timestamp;

@MappedSuperclass
@Data
public abstract class BaseEntity {
    
    @CreationTimestamp
    @Column(name = "created_at", updatable = false, nullable = false)
    private Timestamp createdAt;
    
    @Column(name = "created_by", updatable = false, nullable = false)
    private String createdBy;
    
    @UpdateTimestamp
    @Column(name = "updated_at")
    private Timestamp updatedAt;
    
    @Column(name = "updated_by")
    private String updatedBy;
    
    @Column(name = "is_active")
    private Boolean isActive = true;
    
    @Column(name = "deleted_at")
    private Timestamp deletedAt;
    
    @Column(name = "deleted_by")
    private String deletedBy;
}
package com.assignment.indomaret_master_data_management.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "stores", indexes = {
    @Index(name = "idx_store_province", columnList = "province_name, is_active, is_whitelist"),
    @Index(name = "idx_store_whitelist", columnList = "is_whitelist, is_active"),
    @Index(name = "idx_store_branch", columnList = "branch_uuid, is_active")
})
public class StoreEntity extends BaseEntity {
    
    @Id
    @JdbcTypeCode(SqlTypes.UUID)
    @Column(name = "uuid", updatable = false, nullable = false)
    private UUID uuid;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branch_uuid", nullable = false)
    private BranchEntity branch;
    
    @Column(name = "store_name", nullable = false)
    private String storeName;
    
    @Column(name = "is_whitelist")
    private Boolean isWhitelist = false;
    
    // Denormalized field for faster query
    @Column(name = "province_name")
    private String provinceName;
}
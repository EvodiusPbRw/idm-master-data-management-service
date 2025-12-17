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
@Table(name = "branches", indexes = {
    @Index(name = "idx_branch_province", columnList = "province_uuid, is_active")
})
public class BranchEntity extends BaseEntity {
    
    @Id
    @JdbcTypeCode(SqlTypes.UUID)
    @Column(name = "uuid", updatable = false, nullable = false)
    private UUID uuid;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "province_uuid", nullable = false)
    private ProvinceEntity province;
    
    @Column(name = "branch_name", nullable = false, unique = true)
    private String branchName;
}
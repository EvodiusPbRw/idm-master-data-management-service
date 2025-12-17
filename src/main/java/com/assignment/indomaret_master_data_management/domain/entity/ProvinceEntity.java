package com.assignment.indomaret_master_data_management.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "provinces")
@NoArgsConstructor
@AllArgsConstructor
public class ProvinceEntity extends BaseEntity {
    
    @Id
    @JdbcTypeCode(SqlTypes.UUID)
    @Column(name = "uuid", updatable = false, nullable = false)
    private UUID uuid;
    
    @Column(name = "province_name", nullable = false, unique = true)
    private String provinceName;
}
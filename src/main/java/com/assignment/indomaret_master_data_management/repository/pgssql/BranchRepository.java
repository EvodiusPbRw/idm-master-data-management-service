package com.assignment.indomaret_master_data_management.repository.pgssql;

import com.assignment.indomaret_master_data_management.domain.entity.BranchEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BranchRepository extends JpaRepository<BranchEntity, UUID> {
    
    @Query("SELECT b FROM BranchEntity b WHERE b.uuid = :uuid AND b.isActive = true")
    Optional<BranchEntity> findActiveById(@Param("uuid") UUID uuid);
    
    @Query("SELECT b FROM BranchEntity b WHERE b.branchName = :name AND b.isActive = true")
    Optional<BranchEntity> findActiveByName(@Param("name") String name);
    
    @Query("SELECT b FROM BranchEntity b WHERE b.province.uuid = :provinceUuid AND b.isActive = true")
    List<BranchEntity> findActiveByProvinceId(@Param("provinceUuid") UUID provinceUuid);
    
    @Query("SELECT b FROM BranchEntity b WHERE b.isActive = true")
    Page<BranchEntity> findAllActive(Pageable pageable);
    
    @Modifying
    @Transactional
    @Query("UPDATE BranchEntity b SET b.isActive = false, b.deletedAt = CURRENT_TIMESTAMP, b.deletedBy = :deletedBy WHERE b.uuid = :uuid")
    int softDelete(@Param("uuid") UUID uuid, @Param("deletedBy") String deletedBy);
}
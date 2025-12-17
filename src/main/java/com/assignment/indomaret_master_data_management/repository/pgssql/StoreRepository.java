package com.assignment.indomaret_master_data_management.repository.pgssql;

import com.assignment.indomaret_master_data_management.domain.entity.StoreEntity;
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
public interface StoreRepository extends JpaRepository<StoreEntity, UUID> {
    
    @Query("SELECT s FROM StoreEntity s WHERE s.uuid = :uuid AND s.isActive = true")
    Optional<StoreEntity> findActiveById(@Param("uuid") UUID uuid);
    
    @Query("SELECT s FROM StoreEntity s WHERE s.storeName = :name AND s.isActive = true")
    Optional<StoreEntity> findActiveByName(@Param("name") String name);
    
    @Query("SELECT s FROM StoreEntity s WHERE s.branch.uuid = :branchUuid AND s.isActive = true")
    List<StoreEntity> findActiveByBranchId(@Param("branchUuid") UUID branchUuid);
    
    @Query("SELECT s FROM StoreEntity s WHERE s.isWhitelist = true AND s.isActive = true")
    List<StoreEntity> findAllActiveWhitelist();
    
    // Main search query dengan whitelist
    @Query("SELECT s FROM StoreEntity s WHERE " +
           "(s.provinceName ILIKE %:provinceName% OR s.isWhitelist = true) " +
           "AND s.isActive = true")
    Page<StoreEntity> searchByProvinceNameOrWhitelist(
        @Param("provinceName") String provinceName,
        Pageable pageable
    );
    
    @Query("SELECT s FROM StoreEntity s WHERE " +
           "s.provinceName ILIKE %:provinceName% AND s.isActive = true")
    Page<StoreEntity> searchByProvinceName(
        @Param("provinceName") String provinceName,
        Pageable pageable
    );
    
    @Modifying
    @Transactional
    @Query("UPDATE StoreEntity s SET s.isWhitelist = :isWhitelist, s.updatedBy = :updatedBy " +
           "WHERE s.uuid = :uuid AND s.isActive = true")
    int updateWhitelistStatus(
        @Param("uuid") UUID uuid, 
        @Param("isWhitelist") Boolean isWhitelist,
        @Param("updatedBy") String updatedBy
    );
}
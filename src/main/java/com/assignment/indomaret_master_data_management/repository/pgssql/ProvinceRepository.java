package com.assignment.indomaret_master_data_management.repository.pgssql;

import com.assignment.indomaret_master_data_management.domain.entity.ProvinceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProvinceRepository extends JpaRepository<ProvinceEntity, UUID> {
    
    @Query("SELECT p FROM ProvinceEntity p WHERE p.uuid = :uuid AND p.isActive = true")
    Optional<ProvinceEntity> findActiveById(@Param("uuid") UUID uuid);
    
    @Query("SELECT p FROM ProvinceEntity p WHERE p.provinceName = :name AND p.isActive = true")
    Optional<ProvinceEntity> findActiveByName(@Param("name") String name);
    
    @Query("SELECT p FROM ProvinceEntity p WHERE p.isActive = true ORDER BY p.provinceName")
    List<ProvinceEntity> findAllActive();
}
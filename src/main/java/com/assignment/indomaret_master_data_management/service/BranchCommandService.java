package com.assignment.indomaret_master_data_management.service;

import com.assignment.indomaret_master_data_management.domain.entity.BranchEntity;
import com.assignment.indomaret_master_data_management.domain.entity.ProvinceEntity;
import com.assignment.indomaret_master_data_management.domain.entity.StoreEntity;
import com.assignment.indomaret_master_data_management.presentation.rest.dto.request.ReqUpdateBranchDto;
import com.assignment.indomaret_master_data_management.presentation.rest.dto.response.ResBranchDto;
import com.assignment.indomaret_master_data_management.repository.pgssql.BranchRepository;
import com.assignment.indomaret_master_data_management.repository.pgssql.ProvinceRepository;
import com.assignment.indomaret_master_data_management.repository.pgssql.StoreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class BranchCommandService {
    
    private final BranchRepository branchRepository;
    private final StoreRepository storeRepository;
    private final ProvinceRepository provinceRepository;
    
    public ResBranchDto updateBranch(UUID uuid, ReqUpdateBranchDto request) {
        // Find active branch
        BranchEntity branch = branchRepository.findActiveById(uuid)
            .orElseThrow(() -> new RuntimeException("Branch not found or inactive"));
        
        // Update branch name if provided
        if (request.getBranchName() != null && !request.getBranchName().trim().isEmpty()) {
            branch.setBranchName(request.getBranchName());
        }
        
        // Update province if provided
        if (request.getProvinceUuid() != null) {
            ProvinceEntity province = provinceRepository.findActiveById(request.getProvinceUuid())
                .orElseThrow(() -> new RuntimeException("Province not found or inactive"));
            branch.setProvince(province);
            
            // Update province name in all related stores
            updateProvinceNameInStores(branch.getUuid(), province.getProvinceName(), "system");
        }
        
        branch.setUpdatedBy("system");
        branchRepository.save(branch);
        
        return mapToBranchResponse(branch);
    }
    
    public void deleteBranch(UUID uuid) {
        // Soft delete branch
        int updated = branchRepository.softDelete(uuid, "system");
        
        if (updated == 0) {
            throw new RuntimeException("Branch not found or already deleted");
        }
        
        // Soft delete all stores under this branch
        List<StoreEntity> stores = storeRepository.findActiveByBranchId(uuid);
        stores.forEach(store -> {
            store.setIsActive(false);
            store.setDeletedBy("system");
            store.setDeletedAt(new Timestamp(System.currentTimeMillis()));
        });
        storeRepository.saveAll(stores);
        
        log.info("Branch {} soft deleted by {}", uuid, "system");
    }
    
    private void updateProvinceNameInStores(UUID branchUuid, String provinceName, String updatedBy) {
        List<StoreEntity> stores = storeRepository.findActiveByBranchId(branchUuid);
        stores.forEach(store -> {
            store.setProvinceName(provinceName);
            store.setUpdatedBy(updatedBy);
        });
        storeRepository.saveAll(stores);
    }
    
    private ResBranchDto mapToBranchResponse(BranchEntity branch) {
        return new ResBranchDto(
            branch.getUuid(),
            branch.getBranchName(),
            branch.getProvince().getUuid(),
            branch.getProvince().getProvinceName(),
            branch.getIsActive()
        );
    }
}
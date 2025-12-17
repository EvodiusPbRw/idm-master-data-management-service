package com.assignment.indomaret_master_data_management.service;

import com.assignment.indomaret_master_data_management.domain.entity.StoreEntity;
import com.assignment.indomaret_master_data_management.presentation.rest.dto.request.ReqSearchStoreDto;
import com.assignment.indomaret_master_data_management.presentation.rest.dto.response.PagedResponse;
import com.assignment.indomaret_master_data_management.presentation.rest.dto.response.ResStoreDto;
import com.assignment.indomaret_master_data_management.repository.pgssql.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreQueryService {
    
    private final StoreRepository storeRepository;
    
    public PagedResponse<ResStoreDto> searchStoresByProvince(ReqSearchStoreDto request) {
        // Create pageable with sorting
        Pageable pageable = PageRequest.of(
            request.getPage(), 
            request.getSize(),
            Sort.by(Sort.Direction.ASC, "storeName")
        );
        
        Page<StoreEntity> storePage;
        
        if (request.getIncludeWhitelist()) {
            // Search with whitelist included
            storePage = storeRepository.searchByProvinceNameOrWhitelist(
                request.getProvinceName() != null ? request.getProvinceName() : "",
                pageable
            );
        } else {
            // Search without whitelist
            storePage = storeRepository.searchByProvinceName(
                request.getProvinceName() != null ? request.getProvinceName() : "",
                pageable
            );
        }
        
        // Map to response
        List<ResStoreDto> responses = storePage.getContent().stream()
            .map(this::mapToStoreResponse)
            .collect(Collectors.toList());
        
        return new PagedResponse<>(
            responses,
            storePage.getNumber(),
            storePage.getSize(),
            storePage.getTotalElements(),
            storePage.getTotalPages(),
            storePage.isLast()
        );
    }
    
    public List<ResStoreDto> getAllWhitelistStores() {
        List<StoreEntity> whitelistStores = storeRepository.findAllActiveWhitelist();
        
        return whitelistStores.stream()
            .map(this::mapToStoreResponse)
            .collect(Collectors.toList());
    }
    
    private ResStoreDto mapToStoreResponse(StoreEntity store) {
        return new ResStoreDto(
            store.getUuid(),
            store.getStoreName(),
            store.getBranch().getBranchName(),
            store.getProvinceName(),
            store.getIsWhitelist(),
            store.getIsActive(),
            store.getCreatedAt().toLocalDateTime()
        );
    }
}
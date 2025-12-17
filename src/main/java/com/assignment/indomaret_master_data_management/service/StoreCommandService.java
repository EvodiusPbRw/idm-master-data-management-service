package com.assignment.indomaret_master_data_management.service;

import com.assignment.indomaret_master_data_management.presentation.rest.dto.request.ReqWhitelistStoreDto;
import com.assignment.indomaret_master_data_management.repository.pgssql.StoreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class StoreCommandService {
    
    private final StoreRepository storeRepository;
    
    public void updateWhitelistStatus(UUID uuid, ReqWhitelistStoreDto request) {
        int updated = storeRepository.updateWhitelistStatus(
            uuid,
            request.getIsWhitelist(), 
            "system"
        );
        
        if (updated == 0) {
            throw new RuntimeException("Store not found or inactive");
        }
        
        log.info("Store {} whitelist status updated to {} by {}", 
            uuid, request.getIsWhitelist(), "system");
    }
}
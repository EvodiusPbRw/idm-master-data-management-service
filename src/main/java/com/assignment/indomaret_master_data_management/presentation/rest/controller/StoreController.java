package com.assignment.indomaret_master_data_management.presentation.rest.controller;

import com.assignment.indomaret_master_data_management.presentation.rest.dto.request.ReqSearchStoreDto;
import com.assignment.indomaret_master_data_management.presentation.rest.dto.request.ReqWhitelistStoreDto;
import com.assignment.indomaret_master_data_management.presentation.rest.dto.response.BaseResponse;
import com.assignment.indomaret_master_data_management.presentation.rest.dto.response.PagedResponse;
import com.assignment.indomaret_master_data_management.presentation.rest.dto.response.ResStoreDto;
import com.assignment.indomaret_master_data_management.service.StoreCommandService;
import com.assignment.indomaret_master_data_management.service.StoreQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/stores")
@RequiredArgsConstructor
public class StoreController {
    private final StoreCommandService storeCommandService;
    private final StoreQueryService storeQueryService;

    @GetMapping("/search")
    public ResponseEntity<BaseResponse<PagedResponse<ResStoreDto>>> searchStores(
            @RequestParam(required = false) String provinceName,
            @RequestParam(defaultValue = "true") boolean includeWhitelist,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {

        ReqSearchStoreDto request = new ReqSearchStoreDto();
        request.setProvinceName(provinceName);
        request.setIncludeWhitelist(includeWhitelist);
        request.setPage(page);
        request.setSize(size);

        PagedResponse<ResStoreDto> response = storeQueryService.searchStoresByProvince(request);
        return ResponseEntity.ok(new BaseResponse<>(response));
    }

    @GetMapping("/whitelist")
    public ResponseEntity<BaseResponse<List<ResStoreDto>>> getWhitelistStores() {
        List<ResStoreDto> response = storeQueryService.getAllWhitelistStores();
        return ResponseEntity.ok(new BaseResponse<>(response));
    }

    @PatchMapping("/{storeUuid}/whitelist")
    public ResponseEntity<BaseResponse<Void>> updateWhitelist(
            @PathVariable UUID storeUuid,
            @RequestBody ReqWhitelistStoreDto request) {

        storeCommandService.updateWhitelistStatus(storeUuid, request);
        return ResponseEntity.ok(new BaseResponse<>(null));
    }
}

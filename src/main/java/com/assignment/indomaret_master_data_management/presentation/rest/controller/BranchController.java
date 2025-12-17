package com.assignment.indomaret_master_data_management.presentation.rest.controller;

import com.assignment.indomaret_master_data_management.presentation.rest.dto.request.ReqUpdateBranchDto;
import com.assignment.indomaret_master_data_management.presentation.rest.dto.response.BaseResponse;
import com.assignment.indomaret_master_data_management.presentation.rest.dto.response.ResBranchDto;
import com.assignment.indomaret_master_data_management.service.BranchCommandService;
import com.assignment.indomaret_master_data_management.service.BranchQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/branches")
@RequiredArgsConstructor
public class BranchController {
    private final BranchQueryService branchQueryService;
    private final BranchCommandService branchCommandService;

    @PutMapping("/{branchUuid}")
    public ResponseEntity<BaseResponse<ResBranchDto>> updateBranch(
            @PathVariable UUID branchUuid,
            @RequestBody ReqUpdateBranchDto request) {
        ResBranchDto response = branchCommandService.updateBranch(branchUuid, request);
        return ResponseEntity.ok(new BaseResponse<>(response));
    }

    @DeleteMapping("/{branchUuid}")
    public ResponseEntity<Void> deleteBranch(@PathVariable UUID branchUuid) {

        branchCommandService.deleteBranch(branchUuid);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<BaseResponse<List<ResBranchDto>>> getAllBranches() {
        List<ResBranchDto> response = branchQueryService.getAllActiveBranches();
        return ResponseEntity.ok(new BaseResponse<>(response));
    }
}

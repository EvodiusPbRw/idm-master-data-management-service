package com.assignment.indomaret_master_data_management.service;

import com.assignment.indomaret_master_data_management.domain.entity.BranchEntity;
import com.assignment.indomaret_master_data_management.presentation.rest.dto.response.ResBranchDto;
import com.assignment.indomaret_master_data_management.repository.pgssql.BranchRepository;
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
public class BranchQueryService {
    
    private final BranchRepository branchRepository;
    
    public List<ResBranchDto> getAllActiveBranches() {
        Pageable pageable = PageRequest.of(0, 1000, Sort.by("branchName"));
        Page<BranchEntity> branchPage = branchRepository.findAllActive(pageable);
        
        return branchPage.getContent().stream()
            .map(this::mapToBranchResponse)
            .collect(Collectors.toList());
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
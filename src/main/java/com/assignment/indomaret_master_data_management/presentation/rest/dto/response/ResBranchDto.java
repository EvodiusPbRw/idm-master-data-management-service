package com.assignment.indomaret_master_data_management.presentation.rest.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResBranchDto {
    private UUID uuid;
    private String branchName;
    private UUID provinceUuid;
    private String provinceName;
    private Boolean isActive;
}
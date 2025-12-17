package com.assignment.indomaret_master_data_management.presentation.rest.dto.request;

import jakarta.validation.constraints.Size;
import lombok.Data;
import java.util.UUID;

@Data
public class ReqUpdateBranchDto {
    @Size(min = 1, max = 100)
    private String branchName;
    private UUID provinceUuid;
}
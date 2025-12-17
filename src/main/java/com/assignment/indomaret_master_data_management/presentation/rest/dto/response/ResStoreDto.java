package com.assignment.indomaret_master_data_management.presentation.rest.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResStoreDto {
    private UUID uuid;
    private String storeName;
    private String branchName;
    private String provinceName;
    private Boolean isWhitelist;
    private Boolean isActive;
    private LocalDateTime createdAt;
}
package com.assignment.indomaret_master_data_management.presentation.rest.dto.request;

import lombok.Data;
import java.util.UUID;

@Data
public class ReqWhitelistStoreDto {
    private Boolean isWhitelist = true;
}
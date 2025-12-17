package com.assignment.indomaret_master_data_management.presentation.rest.dto.request;

import lombok.Data;

@Data
public class ReqSearchStoreDto {
    private String provinceName;
    private Boolean includeWhitelist = true;
    private Integer page = 0;
    private Integer size = 20;
}
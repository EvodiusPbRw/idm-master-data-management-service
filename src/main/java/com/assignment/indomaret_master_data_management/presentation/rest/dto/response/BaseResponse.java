package com.assignment.indomaret_master_data_management.presentation.rest.dto.response;

import com.assignment.indomaret_master_data_management.helper.constant.ConstantVariable;
import lombok.Data;

import java.util.UUID;

@Data
public class BaseResponse<T> {
    private UUID reqId = UUID.randomUUID();
    private String status = ConstantVariable.SUCCESS_STATUS;
    private String message = ConstantVariable.SUCCESS_MESSAGE;
    private String error = null;
    private T data = null;

    public BaseResponse(T data){
        this.data = data;
    }
}

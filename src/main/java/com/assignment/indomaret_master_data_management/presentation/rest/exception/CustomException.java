package com.assignment.indomaret_master_data_management.presentation.rest.exception;

import lombok.Getter;
import org.springframework.http.HttpStatusCode;


public class CustomException extends RuntimeException {

  private final int status;
  @Getter
  private final Object data;

  public CustomException(String message, int status, Object data) {
    super(message);
    this.status = status;
    this.data = data;
  }

  public CustomException(String message, int status){
    super(message);
    this.status = status;
    this.data = null;
  }

  public HttpStatusCode getStatus() {
    return HttpStatusCode.valueOf(status);
  }
}


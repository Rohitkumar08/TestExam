package com.examBuddy.ExamBuddy.utils;

/**
 * Created 12/02/20 7:28 PM
 *
 * @author Rohit Rawani
 */
public enum StatusCode {
  //Base codes
  OK(200, 0, "Success"),
  SERVER_ERROR(400, 1, "Failed!"),
  SUCCESS(201, 0, "Success"),


  //Server & security error codes
  NO_DATA(300, 2, "No data found!"),
  INTERNAL_SERVER_ERROR(500, 1, "Internal server error");

  private Integer code;
  private Integer errorState;
  private String statusMessage;

  StatusCode(Integer code, Integer errorState, String statusMessage) {
    this.code = code;
    this.statusMessage = statusMessage;
    this.errorState = errorState;
  }

  public StatusCode getByStatusCode(Integer code) {
    StatusCode[] values = values();
    for (StatusCode statusCode : values) {
      if (statusCode.getCode().equals(code)) {
        return statusCode;
      }
    }
    return StatusCode.SERVER_ERROR;
  }

  public Integer getCode() {
    return this.code;
  }

  public String getStatusMessage() {
    return this.statusMessage;
  }

  public Integer getErrorState() {
    return errorState;
  }

}

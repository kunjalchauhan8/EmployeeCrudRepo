package com.nhs.management.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Error {


    private String errorMessage;

    private String errorCode;

    private String request;

    private String requestType;

    private String customMessage;

}

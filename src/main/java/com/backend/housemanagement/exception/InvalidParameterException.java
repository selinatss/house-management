package com.backend.housemanagement.exception;

import com.backend.housemanagement.util.ReturnCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InvalidParameterException extends RuntimeException{
    private final int errorCode;
    private final String errorDescription;

    public InvalidParameterException(int errorCode, String errorDescription){
        super("ERROR_CODE: " + errorCode + " - ERROR_DESCRIPTION: " + errorDescription);
        this.errorCode = errorCode;
        this.errorDescription = errorDescription;
    }

    public InvalidParameterException(ReturnCode returnCode){
        this(returnCode.getCode(), returnCode.name());
    }
}

package com.backend.housemanagement.exception;

import com.backend.housemanagement.util.ReturnCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class ExceptionProcessor {
    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    @ResponseBody
    public ResponseEntity<?> argumentTypeMismatchError(MethodArgumentTypeMismatchException exception){
        ErrorDetail errorDetail = new ErrorDetail(ReturnCode.INVALID_PARAMETER_EXCEPTION.getCode(),
                ReturnCode.INVALID_PARAMETER_EXCEPTION.name() + " - " + exception.getParameter().getParameterName());
        return new ResponseEntity<>(errorDetail, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({HttpMessageNotReadableException.class})
    @ResponseBody
    public ResponseEntity<?> argumentTypeMismatchError(HttpMessageNotReadableException exception){
        ErrorDetail errorDetail = new ErrorDetail(ReturnCode.INVALID_PARAMETER_EXCEPTION.getCode(),
                exception.getCause().getMessage());
        return new ResponseEntity<>(errorDetail, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({InvalidParameterException.class})
    @ResponseBody
    public ResponseEntity<?> validationFailed(InvalidParameterException exception){
        ErrorDetail errorDetail = new ErrorDetail(exception.getErrorCode(), exception.getErrorDescription());
        return new ResponseEntity<>(errorDetail, HttpStatus.BAD_REQUEST);
    }
}

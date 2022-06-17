package com.backend.housemanagement.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ErrorDetail {
    private int errorCode;
    private String errorDefinition;
}

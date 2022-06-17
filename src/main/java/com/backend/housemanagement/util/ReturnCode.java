package com.backend.housemanagement.util;

import java.util.LinkedHashMap;
import java.util.Map;

public enum ReturnCode {
    SUCCESS(0),
    FALIED(-1),
    INTERNAL_ERROR(-999),
    INVALID_PARAMETER_EXCEPTION(400),
    REQUEST_EMPTY(1000),
    ADDRESS_CANNOT_BE_EMPTY(1001),
    INVALID_COUNTRY_NAME(1002),
    INVALID_CITY_NAME(1003),
    INVALID_DISTRICT_NAME(1004),
    INVALID_STREET_NAME(1005),
    INVALID_ROOM_NUMBER(1006),
    INVALID_PRICE_VALUE(1007),
    INVALID_ID_PARAMETER(1008),
    INVALID_HOUSE_NO(1010),
    HAS_BALCONY_CANNOT_BE_NULL(1011),
    HAS_GARDEN_CANNOT_BE_NULL(1012),
    INVALID_RENT_OR_SALE_VALUE(1013);


    private final int code;

    private static final Map<Integer, ReturnCode> LOOKUP_MAP = new LinkedHashMap<>();

    private ReturnCode(int code){
        this.code = code;
    }

    public int getCode(){
        return code;
    }

    public static ReturnCode getFromCode(int code){
        return LOOKUP_MAP.get(code);
    }
}

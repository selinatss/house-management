package com.backend.housemanagement.controller.validator;

import com.backend.housemanagement.exception.InvalidParameterException;
import com.backend.housemanagement.model.request.AddHouseRequest;
import com.backend.housemanagement.util.Constants;
import com.backend.housemanagement.util.ReturnCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.validator.routines.DoubleValidator;
import org.apache.commons.validator.routines.IntegerValidator;
import org.springframework.boot.autoconfigure.cassandra.CassandraProperties;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Service
@Slf4j
public class AddHouseRequestValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(AddHouseRequest.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        AddHouseRequest request = (AddHouseRequest) target;
        if(ObjectUtils.isEmpty(request)) {
            log.error("Request is empty!");
            throw new InvalidParameterException(ReturnCode.REQUEST_EMPTY);
        }
        if(!StringUtils.hasText(request.getCountryName())){
            log.error("Country name is not valid!");
            throw new InvalidParameterException(ReturnCode.INVALID_COUNTRY_NAME);
        }
        if(!StringUtils.hasText(request.getHouseNo())){
            log.error("House no is not valid");
            throw new InvalidParameterException(ReturnCode.INVALID_HOUSE_NO);
        }
        if(!StringUtils.hasText(request.getCityName())){
            log.error("City name is not valid!");
            throw new InvalidParameterException(ReturnCode.INVALID_CITY_NAME);
        }
        if(!StringUtils.hasText(request.getDistrictName())){
            log.error("District name is not valid!");
            throw new InvalidParameterException(ReturnCode.INVALID_DISTRICT_NAME);
        }
        if(!StringUtils.hasText(request.getStreetName())){
            log.error("Street name is not valid!");
            throw new InvalidParameterException(ReturnCode.INVALID_STREET_NAME);
        }
        if(request.getHasBalcony() == null){
            log.error("HasBalcony field is null");
            throw new InvalidParameterException(ReturnCode.HAS_BALCONY_CANNOT_BE_NULL);
        }
        if(request.getHasGarden() == null){
            log.error("HasGarden field is null");
            throw new InvalidParameterException(ReturnCode.HAS_GARDEN_CANNOT_BE_NULL);
        }
        if(!IntegerValidator.getInstance().isValid(String.valueOf(request.getRoomNumber()))){
            log.error("Room number is not valid");
            throw new InvalidParameterException(ReturnCode.INVALID_ROOM_NUMBER);
        }
        if(request.getRentOrSale() == null || !(request.getRentOrSale().equalsIgnoreCase(Constants.RENT) || request.getRentOrSale().equalsIgnoreCase(Constants.SALE))){
            log.error("Rent or sale information is not valid!");
            throw new InvalidParameterException(ReturnCode.INVALID_RENT_OR_SALE_VALUE);
        }
        if(!DoubleValidator.getInstance().isValid(String.valueOf(request.getPrice()))){
            log.error("Price value is not valid!");
            throw new InvalidParameterException(ReturnCode.INVALID_PRICE_VALUE);
        }
    }
}

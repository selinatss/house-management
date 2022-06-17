package com.backend.housemanagement.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddHouseRequest {
    private String streetName;
    private String districtName;
    private String houseNo;
    private String cityName;
    private String countryName;
    private Integer roomNumber;
    private Boolean hasGarden;
    private Boolean hasBalcony;
    private String rentOrSale;
    private Double price;
}

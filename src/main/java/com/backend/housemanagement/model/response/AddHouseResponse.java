package com.backend.housemanagement.model.response;

import com.backend.housemanagement.model.entity.HouseEntity;
import lombok.Data;

@Data
public class AddHouseResponse {
    private ResponseHeader responseHeader;
    private HouseEntity houseEntity;
}

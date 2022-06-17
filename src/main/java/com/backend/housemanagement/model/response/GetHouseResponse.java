package com.backend.housemanagement.model.response;

import com.backend.housemanagement.model.entity.HouseEntity;
import lombok.Data;
import java.util.List;

@Data
public class GetHouseResponse {
    private ResponseHeader responseHeader;
    private List<HouseEntity> houseList;
}

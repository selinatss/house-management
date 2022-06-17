package com.backend.housemanagement.model.response;

import lombok.Data;

@Data
public class DeleteHouseResponse {
    private ResponseHeader responseHeader;
    private Long houseId;
}

package com.backend.housemanagement.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseHeader {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd G HH:mm:ss z");

    private String responseDateTime = sdf.format(new Date());
    private String responseDescription = "SUCCESS";
}

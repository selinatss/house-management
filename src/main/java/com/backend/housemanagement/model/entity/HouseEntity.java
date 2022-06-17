package com.backend.housemanagement.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="HOUSE_DETAILS")
public class HouseEntity {
    @Id
    @SequenceGenerator(
            name="house_details_id_sequence",
            sequenceName = "house_details_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "house_details_id_sequence"
    )
    private Long id;

    private Date createDate;
    private String streetName;
    private String districtName;
    private String houseNo;
    private String cityName;
    private String countryName;
    private int roomNumber;
    private Boolean hasGarden;
    private Boolean hasBalcony;
    private String rentOrSale;
    private double price;
}

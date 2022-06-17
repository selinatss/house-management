package com.backend.housemanagement.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.backend.housemanagement.dao.HouseRepository;
import com.backend.housemanagement.model.entity.HouseEntity;
import com.backend.housemanagement.model.request.AddHouseRequest;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {HouseService.class})
@ExtendWith(SpringExtension.class)
class HouseServiceTest {
    @MockBean
    private HouseRepository houseRepository;

    @Autowired
    private HouseService houseService;

    @Test
    void testSaveHouse() {
        HouseEntity houseEntity = new HouseEntity(123L, null, "Istiklal", "Beyoglu",
                "11A", "Istanbul",  "Turkey",4, true, true, "Rent",
                1000.0);
        when(this.houseRepository.save((HouseEntity) any())).thenReturn(houseEntity);
        assertSame(houseEntity, this.houseService
                .saveHouse(new AddHouseRequest("Istiklal", "Beyoglu",
                        "11A", "Istanbul",  "Turkey", 10, true, true,
                        "Rent Or Sale", 10.0))
                .getHouseEntity());
        verify(this.houseRepository).save((HouseEntity) any());
    }

    @Test
    void testGetHouse() {
        HouseEntity houseEntity = new HouseEntity(123L, null, "Istiklal", "Beyoglu",
                "11A", "Istanbul",  "Turkey",4, true, true, "Rent",
                1000.0);
        Optional<HouseEntity> ofResult = Optional.of(houseEntity);
        when(this.houseRepository.findAll()).thenReturn(new ArrayList<>());
        when(this.houseRepository.findById((Long) any())).thenReturn(ofResult);
        assertTrue(this.houseService.getHouse(null).getHouseList().isEmpty());
        verify(this.houseRepository).findAll();
    }

    @Test
    void testDeleteHouse() {
        doNothing().when(this.houseRepository).deleteById((Long) any());
        assertEquals(123L, this.houseService.deleteHouse(123L).getHouseId().longValue());
        verify(this.houseRepository).deleteById((Long) any());
    }
}


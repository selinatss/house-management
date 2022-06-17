package com.backend.housemanagement.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.backend.housemanagement.controller.validator.AddHouseRequestValidator;
import com.backend.housemanagement.dao.HouseRepository;
import com.backend.housemanagement.model.entity.HouseEntity;
import com.backend.housemanagement.model.request.AddHouseRequest;
import com.backend.housemanagement.model.response.AddHouseResponse;
import com.backend.housemanagement.model.response.DeleteHouseResponse;
import com.backend.housemanagement.model.response.GetHouseResponse;
import com.backend.housemanagement.model.response.ResponseHeader;
import com.backend.housemanagement.service.HouseService;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {HouseController.class})
@ExtendWith(SpringExtension.class)
class HouseControllerTest {
    @MockBean
    private AddHouseRequestValidator addHouseRequestValidator;

    @Autowired
    private HouseController houseController;

    @MockBean
    private HouseService houseService;

    @Test
    void testAddHouse() {
        HouseEntity houseEntity = new HouseEntity(123L, null, "Istiklal", "Beyoglu",
                "11A", "Istanbul", "Turkey", 4, true, true, "Rent",
                1000.0);
        HouseRepository houseRepository = mock(HouseRepository.class);
        when(houseRepository.save((HouseEntity) any())).thenReturn(houseEntity);
        HouseController houseController = new HouseController(new HouseService(houseRepository));
        ResponseEntity<AddHouseResponse> actualAddHouseResult = houseController.addHouse(new AddHouseRequest("Istiklal", "Beyoglu",
                "11A", "Istanbul", "Turkey", 4, true, true, "Rent",
                1000.0));
        assertTrue(actualAddHouseResult.getHeaders().isEmpty());
        assertTrue(actualAddHouseResult.hasBody());
        assertEquals(HttpStatus.OK, actualAddHouseResult.getStatusCode());
        AddHouseResponse body = actualAddHouseResult.getBody();
        assertSame(houseEntity, body.getHouseEntity());
        assertEquals("SUCCESS", body.getResponseHeader().getResponseDescription());
        verify(houseRepository).save((HouseEntity) any());
    }

    @Test
    void testDeleteHouse() throws Exception {
        DeleteHouseResponse deleteHouseResponse = new DeleteHouseResponse();
        deleteHouseResponse.setHouseId(123L);
        deleteHouseResponse.setResponseHeader(new ResponseHeader());
        when(this.houseService.deleteHouse((Long) any())).thenReturn(deleteHouseResponse);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/rest/house/{id}", 123L);
        MockMvcBuilders.standaloneSetup(this.houseController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testGetHouse() throws Exception {
        GetHouseResponse getHouseResponse = new GetHouseResponse();
        getHouseResponse.setHouseList(new ArrayList<>());
        getHouseResponse.setResponseHeader(new ResponseHeader());
        when(this.houseService.getHouse((Long) any())).thenReturn(getHouseResponse);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/rest/house");
        MockMvcBuilders.standaloneSetup(this.houseController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}


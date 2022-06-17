package com.backend.housemanagement.controller;

import com.backend.housemanagement.controller.validator.AddHouseRequestValidator;
import com.backend.housemanagement.model.request.AddHouseRequest;
import com.backend.housemanagement.model.response.AddHouseResponse;
import com.backend.housemanagement.model.response.DeleteHouseResponse;
import com.backend.housemanagement.model.response.GetHouseResponse;
import com.backend.housemanagement.model.response.ResponseHeader;
import com.backend.housemanagement.service.HouseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping(value = "/rest/house")
public class HouseController {
    private HouseService houseService;

    @Autowired
    private AddHouseRequestValidator addHouseRequestValidator;

    @InitBinder("addHouseRequest")
    void initAddHouseRequestBinder(WebDataBinder binder){
        binder.setValidator(this.addHouseRequestValidator);
    }

    public HouseController(HouseService houseService) {
        this.houseService = houseService;
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<AddHouseResponse> addHouse(@Valid @RequestBody AddHouseRequest addHouseRequest){
        AddHouseResponse response = new AddHouseResponse();
        String logMessage = "<addHouse>  ";
        try{
            log.info(logMessage + "NEW_REQUEST_RECEIVED request: " + addHouseRequest);
            response = houseService.saveHouse(addHouseRequest);
            response.setResponseHeader(new ResponseHeader());
            log.info(logMessage + "RESPONSE: " + response);
        }catch (Exception e){
            log.error(logMessage + "ERROR" + e.getMessage());
            response.getResponseHeader().setResponseDescription(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return  new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = {"", "/{id}"})
    @ResponseBody
    public ResponseEntity<GetHouseResponse> getHouse(@PathVariable(required = false) Long id){
        GetHouseResponse response = new GetHouseResponse();
        String logMessage = "<getHouse>  ";
        try{
            log.info(logMessage + " NEW_REQUEST_RECEIVED " + (id != null ? "id: " + id : null));
            response = houseService.getHouse(id);
            response.setResponseHeader(new ResponseHeader());
            log.info(logMessage + " RESPONSE: " + response);
        }catch (Exception e){
            log.error(logMessage + " ERROR " + e.getMessage());
            response.getResponseHeader().setResponseDescription(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseBody
    public ResponseEntity<DeleteHouseResponse> deleteHouse(@PathVariable Long id){
        DeleteHouseResponse response = new DeleteHouseResponse();
        String logMessage = "<deleteHouse>  ";
        try{
            log.info(logMessage + " NEW_REQUEST_RECEIVED  id: " + id);
            response = houseService.deleteHouse(id);
            response.setResponseHeader(new ResponseHeader());
            log.info(logMessage + " RESPONSE: " + response);
        }catch (Exception e){
            log.error(logMessage + " ERROR " + e.getMessage());
            response.getResponseHeader().setResponseDescription(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

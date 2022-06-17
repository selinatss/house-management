package com.backend.housemanagement.service;

import com.backend.housemanagement.dao.HouseRepository;
import com.backend.housemanagement.model.entity.HouseEntity;
import com.backend.housemanagement.model.request.AddHouseRequest;
import com.backend.housemanagement.model.response.AddHouseResponse;
import com.backend.housemanagement.model.response.DeleteHouseResponse;
import com.backend.housemanagement.model.response.GetHouseResponse;
import com.backend.housemanagement.util.Constants;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class HouseService {
    @Autowired
    private HouseRepository houseRepository;

    public AddHouseResponse saveHouse(AddHouseRequest addHouseRequest){
        String logMessage = "HouseService - saveHouse";
        AddHouseResponse addHouseResponse = new AddHouseResponse();
        try{
            log.info(logMessage + Constants.STARTED);
            HouseEntity houseEntity = requestToEntity(addHouseRequest);
            houseEntity.setCreateDate(new Date());
            addHouseResponse.setHouseEntity(houseRepository.save(houseEntity));
            log.info(logMessage + Constants.ENDED);
        }catch (Exception e){
            log.error(logMessage + Constants.ERROR + e.getMessage());
        }
        return addHouseResponse;
    }

    public GetHouseResponse getHouse(Long id){
        String logMessage = "HouseService - getHouse";
        GetHouseResponse getHouseResponse = new GetHouseResponse();
        List<HouseEntity> houseList = null;
        try{
            log.info(logMessage + Constants.STARTED);
            if(id != null){
                houseList = new ArrayList<>();
                Optional<HouseEntity> houseEntityOptional = houseRepository.findById(id);
                if(!houseEntityOptional.isEmpty()){
                    houseList.add(houseRepository.findById(id).get());
                }
            }else{
                houseList = houseRepository.findAll();
            }
            getHouseResponse.setHouseList(houseList);
            log.info(logMessage + Constants.ENDED);
        }catch (Exception e){
            log.error(logMessage + Constants.ERROR + e.getMessage());
        }
        return getHouseResponse;
    }

    public DeleteHouseResponse deleteHouse(Long id){
        String logMessage = "HouseService - deleteHouse";
        DeleteHouseResponse deleteHouseResponse = new DeleteHouseResponse();
        try{
            log.info(logMessage + Constants.STARTED);
            houseRepository.deleteById(id);
            deleteHouseResponse.setHouseId(id);
            log.info(logMessage + Constants.ENDED);
        }catch (Exception e){
            log.error(logMessage + Constants.ERROR  + e.getMessage());
        }
        return deleteHouseResponse;
    }

    private HouseEntity requestToEntity(AddHouseRequest request){
        ModelMapper mapper = new ModelMapper();
        return mapper.map(request, HouseEntity.class);
    }

}

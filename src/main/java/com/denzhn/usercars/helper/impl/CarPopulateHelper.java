package com.denzhn.usercars.helper.impl;

import com.denzhn.usercars.dto.CarsDto;
import com.denzhn.usercars.helper.PopulateHelper;
import com.denzhn.usercars.models.Cars;
import org.springframework.stereotype.Component;

@Component
public class CarPopulateHelper implements PopulateHelper<CarsDto, Cars> {
    @Override
    public Cars populateToEntity(CarsDto dto) {
        final Cars cars = new Cars();
        cars.setMake(dto.getMake());
        cars.setModel(dto.getModel());
        cars.setNumberPlate(dto.getNumberPlate());
        return cars;
    }

    @Override
    public CarsDto populateToDto(Cars entity) {
        return CarsDto.builder()
                .id(entity.getId())
                .model(entity.getModel())
                .make(entity.getMake())
                .numberPlate(entity.getNumberPlate())
                .userId(entity.getUser().getId()).build();
    }
}

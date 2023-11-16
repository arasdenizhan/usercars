package com.denzhn.usercars.service;

import com.denzhn.usercars.dto.CarsDto;

import java.util.List;

public interface CarsService {
    List<CarsDto> list();
    CarsDto get(Long id);
}

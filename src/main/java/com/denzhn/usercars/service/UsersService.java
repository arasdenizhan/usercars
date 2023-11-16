package com.denzhn.usercars.service;

import com.denzhn.usercars.dto.CarsDto;
import com.denzhn.usercars.dto.UsersDto;

import java.util.List;

public interface UsersService {
    UsersDto create(UsersDto usersDto);
    UsersDto get(Long id);
    List<UsersDto> list();
    List<CarsDto> listCars(Long id);
}

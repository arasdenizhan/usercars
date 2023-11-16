package com.denzhn.usercars.service.impl;

import com.denzhn.usercars.dto.CarsDto;
import com.denzhn.usercars.dto.UsersDto;
import com.denzhn.usercars.exception.BusinessLayerException;
import com.denzhn.usercars.exception.PopulatingException;
import com.denzhn.usercars.exception.EntityNotFoundException;
import com.denzhn.usercars.helper.impl.CarPopulateHelper;
import com.denzhn.usercars.helper.impl.UsersPopulateHelper;
import com.denzhn.usercars.models.Users;
import com.denzhn.usercars.repository.UsersRepository;
import com.denzhn.usercars.service.UsersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {
    private final UsersPopulateHelper usersPopulateHelper;
    private final CarPopulateHelper carsPopulateHelper;
    private final UsersRepository usersRepository;

    @Override
    public UsersDto create(UsersDto usersDto) {
        try {
            Users user = usersPopulateHelper.populateToEntity(usersDto);
            var cars = usersDto.getCars().stream().map(carsDto -> {
                var carsEntity = carsPopulateHelper.populateToEntity(carsDto);
                carsEntity.setUser(user);
                return carsEntity;
            }).toList();
            user.setCars(cars);

            var savedEntity = usersRepository.save(user);
            log.info("A new user is saved with id={}", savedEntity.getId());

            UsersDto resultDto = usersPopulateHelper.populateToDto(savedEntity);
            resultDto.setCars(savedEntity.getCars().stream().map(carsPopulateHelper::populateToDto).toList());
            return resultDto;
        } catch (Exception e) {
            log.error("Error happened while creating a user, error={}", e.getMessage());
            throw new BusinessLayerException(e.getMessage(), e);
        }
    }

    @Override
    public UsersDto get(Long id) {
        try {
            return usersRepository.findById(id)
                    .map(users -> {
                        var resultDto = usersPopulateHelper.populateToDto(users);
                        resultDto.setCars(users.getCars().stream().map(carsPopulateHelper::populateToDto).toList());
                        return resultDto;
                    }).orElseThrow(() -> new EntityNotFoundException("Requested user not found!"));
        } catch (EntityNotFoundException e) {
            log.error("Requested User Entity not found id={}", id);
            throw new EntityNotFoundException(e.getMessage());
        }
        catch (Exception e) {
            log.error("Error happened while getting user with id={}, error={}", id, e.getMessage());
            throw new BusinessLayerException(e.getMessage(), e);
        }
    }

    @Override
    public List<UsersDto> list() {
        try {
            return usersRepository.findAll().stream()
                    .map(users -> {
                        var resultDto = usersPopulateHelper.populateToDto(users);
                        resultDto.setCars(users.getCars().stream().map(carsPopulateHelper::populateToDto).toList());
                        return resultDto;
                    }).toList();
        } catch (Exception e) {
            log.error("Error happened while listing users, error={}", e.getMessage());
            throw new BusinessLayerException(e.getMessage(), e);
        }
    }

    @Override
    public List<CarsDto> listCars(Long id) {
        try {
            return usersRepository.findById(id)
                    .stream()
                    .map(users -> users.getCars().stream().map(carsPopulateHelper::populateToDto).toList())
                    .findAny().orElseThrow(() -> new PopulatingException("Error happened while populating cars!"));
        } catch (Exception e) {
            log.error("Error happened while listing users, error={}", e.getMessage());
            throw new BusinessLayerException(e.getMessage(), e);
        }
    }
}

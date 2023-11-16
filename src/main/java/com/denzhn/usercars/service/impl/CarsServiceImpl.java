package com.denzhn.usercars.service.impl;

import com.denzhn.usercars.dto.CarsDto;
import com.denzhn.usercars.exception.BusinessLayerException;
import com.denzhn.usercars.exception.EntityNotFoundException;
import com.denzhn.usercars.helper.impl.CarPopulateHelper;
import com.denzhn.usercars.repository.CarsRepository;
import com.denzhn.usercars.service.CarsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CarsServiceImpl implements CarsService {
    private final CarPopulateHelper carPopulateHelper;
    private final CarsRepository carsRepository;


    @Override
    public List<CarsDto> list() {
        try {
            return carsRepository.findAll().stream().map(carPopulateHelper::populateToDto).toList();
        } catch (Exception e) {
            log.error("Error happened while listing users, error={}", e.getMessage());
            throw new BusinessLayerException(e.getMessage(), e);
        }
    }

    @Override
    public CarsDto get(Long id) {
        try {
            return carsRepository.findById(id).map(carPopulateHelper::populateToDto)
                    .orElseThrow(() -> new EntityNotFoundException("Requested car not found!"));
        } catch (EntityNotFoundException e) {
            log.error("Requested Car Entity not found id={}", id);
            throw new EntityNotFoundException(e.getMessage());
        }
        catch (Exception e) {
            log.error("Error happened while getting car with id={}, error={}", id, e.getMessage());
            throw new BusinessLayerException(e.getMessage(), e);
        }
    }
}

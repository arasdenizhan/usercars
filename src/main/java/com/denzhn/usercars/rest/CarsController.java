package com.denzhn.usercars.rest;

import com.denzhn.usercars.dto.CarsDto;
import com.denzhn.usercars.service.CarsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000"})

@RequestMapping("usercars/v1/cars")
public class CarsController {
    private final CarsService carsService;

    @GetMapping()
    public ResponseEntity<List<CarsDto>> list(){
        return ResponseEntity.ok(carsService.list());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarsDto> get(@PathVariable Long id){
        return ResponseEntity.ok(carsService.get(id));
    }

}

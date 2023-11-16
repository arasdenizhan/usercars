package com.denzhn.usercars.rest;

import com.denzhn.usercars.dto.CarsDto;
import com.denzhn.usercars.dto.UsersDto;
import com.denzhn.usercars.service.UsersService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("usercars/v1/users")
public class UsersController {
    private final UsersService usersService;

    @PostMapping
    public ResponseEntity<UsersDto> create(@RequestBody @Valid UsersDto dto){
        return ResponseEntity.ok(usersService.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<UsersDto>> list(){
        return ResponseEntity.ok(usersService.list());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsersDto> get(@PathVariable Long id){
        return ResponseEntity.ok(usersService.get(id));
    }

    @GetMapping("/{id}/cars")
    public ResponseEntity<List<CarsDto>> listCars(@PathVariable Long id){
        return ResponseEntity.ok(usersService.listCars(id));
    }
}

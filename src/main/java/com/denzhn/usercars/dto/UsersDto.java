package com.denzhn.usercars.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class UsersDto {
    private Long id;
    @NotBlank
    private String name;
    private List<@Valid CarsDto> cars;
}

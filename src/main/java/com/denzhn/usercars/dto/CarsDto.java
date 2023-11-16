package com.denzhn.usercars.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CarsDto {
    private Long id;
    @NotBlank
    private String make;
    @NotBlank
    private String model;
    @NotBlank
    @JsonAlias("numberplate")
    private String numberPlate;
    private Long userId;
}

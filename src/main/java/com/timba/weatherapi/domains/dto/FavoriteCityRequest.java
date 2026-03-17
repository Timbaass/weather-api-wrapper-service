package com.timba.weatherapi.domains.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class FavoriteCityRequest {
    @NotBlank(message = "City name can not be empty.")
    private String cityName;
}

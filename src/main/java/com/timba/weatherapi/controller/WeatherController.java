package com.timba.weatherapi.controller;

import com.timba.weatherapi.domains.dto.WeatherResponse;
import com.timba.weatherapi.service.IWeatherService;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/weather")
@RequiredArgsConstructor
public class WeatherController {

    private final IWeatherService weatherService;

    @GetMapping("/{city}")
    public ResponseEntity<WeatherResponse> getWeather(@PathVariable @NotBlank(message = "City name can not be empty.") String city) {
        WeatherResponse response = weatherService.getWeatherData(city);
        return ResponseEntity.ok(response);
    }
}

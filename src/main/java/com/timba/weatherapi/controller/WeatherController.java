package com.timba.weatherapi.controller;

import com.timba.weatherapi.domains.WeatherResponse;
import com.timba.weatherapi.service.IWeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/weather")
@RequiredArgsConstructor
public class WeatherController {

    private final IWeatherService weatherService;

    @GetMapping("/{city}")
    public ResponseEntity<WeatherResponse> getWeather(@PathVariable String city) {
        WeatherResponse response = weatherService.getWeatherData(city);
        return ResponseEntity.ok(response);
    }
}

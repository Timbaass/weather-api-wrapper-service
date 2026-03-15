package com.timba.weatherapi.controller;

import com.timba.weatherapi.domains.dto.FavoriteCityRequest;
import com.timba.weatherapi.domains.dto.FavoriteCityResponse;
import com.timba.weatherapi.domains.dto.WeatherResponse;
import com.timba.weatherapi.service.IWeatherService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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

    @PostMapping("/favorites")
    public ResponseEntity<FavoriteCityResponse> addFavoriteCity(@Valid @RequestBody FavoriteCityRequest request){
        return null;
    }

    @GetMapping("/favorites")
    public ResponseEntity<List<FavoriteCityResponse>> getFavoriteCities(){
        return null;
    }

    @DeleteMapping("/favorites/{id}")
    public ResponseEntity<Void> deleteFavoriteCity(@PathVariable UUID id){
        return null;
    }
}

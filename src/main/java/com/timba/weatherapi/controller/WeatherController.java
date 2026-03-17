package com.timba.weatherapi.controller;

import com.timba.weatherapi.domains.dto.FavoriteCityRequest;
import com.timba.weatherapi.domains.dto.FavoriteCityResponse;
import com.timba.weatherapi.domains.dto.WeatherResponse;
import com.timba.weatherapi.service.IWeatherService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
        FavoriteCityResponse response = weatherService.addFavoriteCity(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/favorites")
    public ResponseEntity<List<FavoriteCityResponse>> getFavoriteCities(){
        List<FavoriteCityResponse> response = weatherService.getFavoriteCities();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/favorites/{id}")
    public ResponseEntity<Void> deleteFavoriteCity(@PathVariable UUID id){
        weatherService.deleteFavoriteCity(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

package com.timba.weatherapi.service;

import com.timba.weatherapi.domains.dto.FavoriteCityRequest;
import com.timba.weatherapi.domains.dto.FavoriteCityResponse;
import com.timba.weatherapi.domains.dto.WeatherResponse;

import java.util.List;
import java.util.UUID;

public interface IWeatherService {
    WeatherResponse getWeatherData(String city);
    List<FavoriteCityResponse> getFavoriteCities();
    FavoriteCityResponse addFavoriteCity(FavoriteCityRequest request);
    void deleteFavoriteCity(UUID cityId);
}

package com.timba.weatherapi.service;

import com.timba.weatherapi.domains.dto.FavoriteCityResponse;
import com.timba.weatherapi.domains.dto.WeatherResponse;

public interface IWeatherService {
    WeatherResponse getWeatherData(String city);
    FavoriteCityResponse getFavoriteCities();
    FavoriteCityResponse addFavoriteCity();
    void deleteFavoriteCity();
}

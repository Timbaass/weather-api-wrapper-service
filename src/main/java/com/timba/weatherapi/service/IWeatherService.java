package com.timba.weatherapi.service;

import com.timba.weatherapi.domains.WeatherResponse;

public interface IWeatherService {
    WeatherResponse getWeatherData(String city);
}

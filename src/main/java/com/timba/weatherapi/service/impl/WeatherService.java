package com.timba.weatherapi.service.impl;

import com.timba.weatherapi.config.WeatherConfig;
import com.timba.weatherapi.domains.dto.WeatherResponse;
import com.timba.weatherapi.exception.CityNotFoundException;
import com.timba.weatherapi.service.IWeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class WeatherService implements IWeatherService {
    private final RestTemplate restTemplate;
    private final WeatherConfig weatherConfig;

    @Override
    @Cacheable(value = "weather", key = "#city")
    public WeatherResponse getWeatherData(String city) {
        String date = LocalDate.now().toString();

        String url = String.format("%s/timeline/%s/%s?key=%s&unitGroup=metric",
                weatherConfig.getBaseUrl(),
                city,
                date,
                weatherConfig.getKey());

        try {
            return restTemplate.getForObject(url, WeatherResponse.class);
        } catch (HttpClientErrorException e) {
            throw new CityNotFoundException("City not found: " + city);
        }
    }
}

package com.timba.weatherapi.service.impl;

import com.timba.weatherapi.config.WeatherConfig;
import com.timba.weatherapi.domains.dto.FavoriteCityRequest;
import com.timba.weatherapi.domains.dto.FavoriteCityResponse;
import com.timba.weatherapi.domains.dto.WeatherResponse;
import com.timba.weatherapi.domains.entity.CityEntity;
import com.timba.weatherapi.exception.CityNotFoundException;
import com.timba.weatherapi.exception.ThreeFavoriteCityOnlyException;
import com.timba.weatherapi.mapper.CityMapper;
import com.timba.weatherapi.repository.CityRepository;
import com.timba.weatherapi.service.IWeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WeatherService implements IWeatherService {
    private final RestTemplate restTemplate;
    private final WeatherConfig weatherConfig;
    private final CityRepository cityRepository;
    private final CityMapper cityMapper;

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

    @Override
    public List<FavoriteCityResponse> getFavoriteCities() {
        List<CityEntity> cities = cityRepository.findAll();

        return cities.stream()
                .map(cityMapper::toResponse)
                .toList();
    }

    @Override
    public FavoriteCityResponse addFavoriteCity(FavoriteCityRequest request) {
        if (cityRepository.count() >= 3){
            throw new ThreeFavoriteCityOnlyException();
        }

        this.getWeatherData(request.getCityName());

        CityEntity city = cityMapper.toEntity(request);

        CityEntity addedCity = cityRepository.save(city);

        return cityMapper.toResponse(addedCity);
    }

    @Override
    public void deleteFavoriteCity(UUID cityId) {
        CityEntity city = cityRepository.findById(cityId)
                .orElseThrow(() -> new CityNotFoundException("City not found."));

        cityRepository.delete(city);
    }
}

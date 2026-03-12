package com.timba.weatherapi.serviceTest;

import com.timba.weatherapi.config.WeatherConfig;
import com.timba.weatherapi.domains.WeatherResponse;
import com.timba.weatherapi.exception.CityNotFoundException;
import com.timba.weatherapi.service.impl.WeatherService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class WeatherServiceTest {
    @Mock
    private WeatherConfig weatherConfig;
    @Mock
    private RestTemplate restTemplate;
    @InjectMocks
    private WeatherService weatherService;

    @BeforeEach
    public void setUp(){
        when(weatherConfig.getBaseUrl()).thenReturn("https://api.weather.com");
        when(weatherConfig.getKey()).thenReturn("test-key");
    }
    @Test
    void getWeatherData_whenCityIsValid_ShouldReturnWeatherResponse(){
        String city = "ValidCity";
        WeatherResponse mockResponse = new WeatherResponse();

        when(restTemplate.getForObject(anyString(), eq(WeatherResponse.class)))
                .thenReturn(mockResponse);

        WeatherResponse result = weatherService.getWeatherData(city);

        assertNotNull(result);
        verify(restTemplate, times(1)).getForObject(anyString(), eq(WeatherResponse.class));
    }

    @Test
    void getWeatherData_whenCityInvalid_shouldThrowCityNotFoundException(){
        String city = "InvalidCity";

        when(restTemplate.getForObject(anyString(), eq(WeatherResponse.class)))
                .thenThrow(new HttpClientErrorException(HttpStatus.NOT_FOUND));

        assertThrows(CityNotFoundException.class, () -> weatherService.getWeatherData(city));
    }
}

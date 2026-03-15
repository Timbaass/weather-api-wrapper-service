package com.timba.weatherapi.controllerTest;


import com.timba.weatherapi.controller.WeatherController;
import com.timba.weatherapi.domains.dto.WeatherResponse;
import com.timba.weatherapi.service.IWeatherService;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(WeatherController.class)
public class WeatherControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockitoBean
    private IWeatherService weatherService;

    @Test
    void getWeather_whenCityIsValid_shouldReturnWeatherResponse() throws Exception {
        String city = "ValidCity";
        WeatherResponse mockResponse = new WeatherResponse();
        mockResponse.setResolvedAddress(city);

        when(weatherService.getWeatherData(city)).thenReturn(mockResponse);

        mockMvc.perform(get("/api/v1/weather/" + city)) // GET isteği at
                .andExpect(status().isOk()) // 200 OK bekliyoruz
                .andExpect(jsonPath("$.resolvedAddress").exists()); //
    }
}

package com.timba.weatherapi.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "weather.api")
public class WeatherConfig {
    private String key;
    private String baseUrl;
}


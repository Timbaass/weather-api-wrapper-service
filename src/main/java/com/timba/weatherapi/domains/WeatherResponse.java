package com.timba.weatherapi.domains;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class WeatherResponse {
    private String resolvedAddress;
    private String description;

    @JsonProperty("currentConditions")
    private CurrentConditions currentConditions;

    @Data
    public static class CurrentConditions {
        private Double temp;
        private Double humidity;
        private Double windspeed;
    }
}
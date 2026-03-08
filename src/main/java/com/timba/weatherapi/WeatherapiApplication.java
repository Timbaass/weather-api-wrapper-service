package com.timba.weatherapi;

import com.timba.weatherapi.config.WeatherConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
public class WeatherapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeatherapiApplication.class, args);
	}

}

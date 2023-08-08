package com.nk.weather.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
public class WeatherController {

    @Autowired
    private RestTemplate restTemplate;

    private final String WEATHER_API_URL = "https://api.openweathermap.org/data/2.5/weather";
    private final String API_KEY = "ef6e1516f8b182bdea04bbc37849035b"; // Replace with your actual API key

    @GetMapping("weather/{location}")
    public ResponseEntity<String> getWeather(@PathVariable String location) throws Exception{
        
        try {
            
            String apiUrl = WEATHER_API_URL + "?q=" + location + "&appid=" + API_KEY;

            String weatherResponse = restTemplate.getForObject(apiUrl, String.class);
    
            return ResponseEntity.ok(weatherResponse);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    //sample url for this api: http://localhost:8080/weather/kolkata
}

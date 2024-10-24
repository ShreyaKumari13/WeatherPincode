package com.example.weatherinfo.controller;


import com.example.weatherinfo.model.WeatherInfo;
import com.example.weatherinfo.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/weather")
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @GetMapping
    public WeatherInfo getWeather(@RequestParam String pincode, @RequestParam String forDate) {
        return weatherService.getWeather(pincode, forDate);
    }
}

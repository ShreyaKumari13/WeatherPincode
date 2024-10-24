package com.example.weatherinfo.service;

import com.example.weatherinfo.model.WeatherInfo;
import com.example.weatherinfo.repository.WeatherRepository;
import com.example.weatherinfo.dto.WeatherApiResponse;  // Ensure correct package is imported
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class WeatherService {

    @Autowired
    private WeatherRepository weatherRepository;

    @Autowired
    private RestTemplate restTemplate;

    public WeatherInfo getWeather(String pincode, String forDate) {
        // Check if the weather data already exists in the DB
        Optional<WeatherInfo> cachedWeather = weatherRepository.findByPincodeAndForDate(pincode, forDate);
        if (cachedWeather.isPresent()) {
            return cachedWeather.get();
        }

        // Fetch weather data from OpenWeather API if not in DB
        String url = "https://api.openweathermap.org/data/2.5/weather?zip=" + pincode + ",IN&appid=YOUR_API_KEY";
        WeatherApiResponse response = restTemplate.getForObject(url, WeatherApiResponse.class);  // Ensure correct method usage

        // Create a new WeatherInfo object
        WeatherInfo weatherInfo = new WeatherInfo();
        weatherInfo.setPincode(pincode);
        weatherInfo.setForDate(forDate);
        weatherInfo.setWeatherDescription(response.getWeather()[0].getDescription());
        weatherInfo.setTemperature(response.getMain().getTemp());

        // Save the weather info in the database
        return weatherRepository.save(weatherInfo);
    }
}

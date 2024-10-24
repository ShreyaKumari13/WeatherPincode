package com.example.weatherinfo.repository;


import com.example.weatherinfo.model.WeatherInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WeatherRepository extends JpaRepository<WeatherInfo, Long> {
    Optional<WeatherInfo> findByPincodeAndForDate(String pincode, String forDate);
}

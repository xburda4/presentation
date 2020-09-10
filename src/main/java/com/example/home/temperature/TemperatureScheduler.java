package com.example.home.temperature;

import com.example.home.types.Temperature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TemperatureScheduler {

    private static List<Temperature> listOfTemperatures = new ArrayList<>();

    @Autowired
    JdbcTemplate jdbcTemplate;

    private void saveTempToDatabase(Temperature temp) {
        jdbcTemplate.execute("");
    }

    @Scheduled(cron = "*/5 * * * * ?")
    protected void saveAvgTempAndClearList() {
        double avgTemp = listOfTemperatures.stream().collect(Collectors.averagingDouble(Temperature::getTemperature));
        Temperature avgTemperature = new Temperature(avgTemp, listOfTemperatures.get(listOfTemperatures.size()/2).getDate());
        saveTempToDatabase(avgTemperature);
        listOfTemperatures = new ArrayList<>();
    }

    @Scheduled(cron = "*/1 * * * * ?")
    protected void saveTempToList() {
        listOfTemperatures.add(getTemperatureFromSensor());
    }

    private Temperature getTemperatureFromSensor() {
        return null;
    }


}

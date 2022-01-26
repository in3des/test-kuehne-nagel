package com.in3des.springboot.services;

import com.in3des.springboot.exceptions.CityNotFoundException;
import com.in3des.springboot.models.City;

import java.util.List;

public interface CityService {

    List<City> getAllCities();
    City updateCity(City city) throws CityNotFoundException;
//    City updateCity(City city);
    City getCityById(long id) throws CityNotFoundException;

}

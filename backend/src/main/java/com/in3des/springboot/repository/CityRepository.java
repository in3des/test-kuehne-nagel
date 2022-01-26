package com.in3des.springboot.repository;

import com.in3des.springboot.models.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CityRepository extends JpaRepository<City, Long> {

    List<City> findByName(String name);
    List<City> findByNameContainingIgnoreCase(String title);
    List<City> findAllByOrderByIdAsc();
}

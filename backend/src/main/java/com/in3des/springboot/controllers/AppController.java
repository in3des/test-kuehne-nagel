package com.in3des.springboot.controllers;

import com.in3des.springboot.dto.CityDto;
import com.in3des.springboot.dto.mappers.CityDtoCollectionMapper;
import com.in3des.springboot.dto.mappers.CityDtoMapper;
import com.in3des.springboot.exceptions.CityNotFoundException;
import com.in3des.springboot.models.City;
import com.in3des.springboot.repository.CityRepository;
import com.in3des.springboot.services.CityService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Tag(name = "Cities", description = "CityRestController API v1")
@CrossOrigin(origins = "http://localhost:8081", maxAge = 3600)
@RestController
@RequestMapping("/api/v1")
public class AppController {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private CityService cityService;

    @Autowired
    private CityDtoMapper mapper;

    @Autowired
    private CityDtoCollectionMapper collectionMapper;

    @GetMapping("/all")
    @ApiIgnore
    public String allAccess() {
        return "Public Content.";
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER') or hasRole('ALLOW_EDIT') or hasRole('ADMIN')")
    @ApiIgnore
    public String userAccess() {
        return "User Content Test City List.";
    }

    @GetMapping("/edit")
    @PreAuthorize("hasRole('ALLOW_EDIT')")
    @ApiIgnore
    public String editorAccess() {
        return "Editor Board.";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiIgnore
    public String adminAccess() {
        return "Admin Board.";
    }


    @GetMapping("/cities/test")
    @PreAuthorize("hasRole('USER') or hasRole('ALLOW_EDIT') or hasRole('ADMIN')")
    @ApiOperation(value = "deleteById", notes = "Test")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "401", description = "Authentication Data is missing or invalid"),
            @ApiResponse(responseCode = "403", description = "Forbidden operation"),
            @ApiResponse(responseCode = "404", description = "Controller not found")}
    )
    @ApiIgnore
    public String cityTest() {
        return "City test OK.";
    }

    @GetMapping("/cities")
    @PreAuthorize("hasRole('USER') or hasRole('ALLOW_EDIT') or hasRole('ADMIN')")
    @ApiOperation(value = "getAllCities", notes = "Get the list of all cities", tags = "Cities")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "401", description = "Authentication Data is missing or invalid"),
            @ApiResponse(responseCode = "403", description = "Forbidden operation"),
            @ApiResponse(responseCode = "404", description = "Controller not found")}
    )
    public ResponseEntity<List<CityDto>> getAllCities(@RequestParam(required = false) String title) {
        try {
            List<City> allCities = new ArrayList<>();

            if (title == null)
                allCities.addAll(cityRepository.findAllByOrderByIdAsc());
            else
                allCities.addAll(cityRepository.findByNameContainingIgnoreCase(title));

            if (allCities.isEmpty()) {
                allCities.add(new City("nothing found", "---"));
                return new ResponseEntity<>(collectionMapper.toCityDtoCollection(allCities), HttpStatus.OK);
            }

            return new ResponseEntity<>(collectionMapper.toCityDtoCollection(allCities), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping("/cities/{id}")
    @PreAuthorize("hasRole('ALLOW_EDIT')")
    @ApiOperation(value = "updateCity", notes = "Update city details", tags = "Cities")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "401", description = "Authentication Data is missing or invalid"),
            @ApiResponse(responseCode = "403", description = "Forbidden operation"),
            @ApiResponse(responseCode = "404", description = "Controller not found")}
    )
    public ResponseEntity<CityDto> updateCity(@PathVariable("id") long id, @RequestBody CityDto cityDto) throws CityNotFoundException {
        Optional<City> cityData = Optional.ofNullable(cityService.getCityById(id));

        if (cityData.isPresent()) {
            City city_tmp = cityData.get();
            city_tmp.setName(cityDto.getName());
            city_tmp.setPhoto(cityDto.getPhoto());
            return new ResponseEntity<>(mapper.toCityDto(cityService.updateCity(city_tmp)), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/cities/{id}")
    @PreAuthorize("hasRole('ALLOW_EDIT')")
    @ApiOperation(value = "getCityById", notes = "Get city by ID", tags = "Cities")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "401", description = "Authentication Data is missing or invalid"),
            @ApiResponse(responseCode = "403", description = "Forbidden operation"),
            @ApiResponse(responseCode = "404", description = "Controller not found")}
    )
    public ResponseEntity<CityDto> getCityById(@PathVariable("id") long id) throws CityNotFoundException {
        Optional<City> cityData = Optional.ofNullable(cityService.getCityById(id));

        if (cityData.isPresent()) {
            return new ResponseEntity<>(mapper.toCityDto(cityData.get()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}

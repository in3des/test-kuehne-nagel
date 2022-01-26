package com.in3des.springboot.dto.mappers;

import com.in3des.springboot.dto.CityDto;
import com.in3des.springboot.models.City;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface CityDtoCollectionMapper {

    List<CityDto> toCityDtoCollection(List<City> entities);

    List<City> toCityEntityCollection(List<CityDto> dtos);

}

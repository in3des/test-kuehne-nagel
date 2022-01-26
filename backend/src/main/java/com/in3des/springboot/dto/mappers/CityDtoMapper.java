package com.in3des.springboot.dto.mappers;

import com.in3des.springboot.dto.CityDto;
import com.in3des.springboot.models.City;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;


@Mapper(componentModel = "spring")
@Component
public interface CityDtoMapper {

    CityDto toCityDto(City entity);

    City toCityEntity(CityDto dto);

}

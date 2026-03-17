package com.timba.weatherapi.mapper;

import com.timba.weatherapi.domains.dto.FavoriteCityRequest;
import com.timba.weatherapi.domains.dto.FavoriteCityResponse;
import com.timba.weatherapi.domains.entity.CityEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CityMapper {

    FavoriteCityResponse toResponse(CityEntity favoriteCity);

    @Mapping(target = "id", ignore = true)
    CityEntity toEntity(FavoriteCityRequest request);
}

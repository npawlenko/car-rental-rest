package com.example.carrental.mapper;

import com.example.carrental.domain.Car;
import com.example.carrental.model.dto.CarDto;
import com.example.carrental.model.dto.DictionaryDto;
import com.example.carrental.model.enums.CarModel;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, builder = @Builder(disableBuilder = true))
public abstract class CarMapper implements EntityMapper<Car, CarDto> {

    @Mapping(source = "model", target = "name", qualifiedByName = "getName")
    public abstract DictionaryDto toDictionary(Car entity);

    @Named("getName")
    public String getCarName(CarModel carModel) {
        return generateNameFromCarModel(carModel);
    }

    protected static String generateNameFromCarModel(CarModel carModel) {
        String[] args = new String[]{
                carModel.getMake().name(),
                carModel.name(),
        };
        return String.join(" ", args);
    }
}
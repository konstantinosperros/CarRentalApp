package gr.aueb.cf.CarRentalApp.dto;

import gr.aueb.cf.CarRentalApp.entity.Car;
import lombok.Data;

import java.util.List;

@Data
public class CarDtoListDto {

    private List<CarDto> carDtoList;
}



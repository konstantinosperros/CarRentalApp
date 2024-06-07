package gr.aueb.cf.CarRentalApp.services.customer;

import gr.aueb.cf.CarRentalApp.dto.BookACarDto;
import gr.aueb.cf.CarRentalApp.dto.CarDto;
import gr.aueb.cf.CarRentalApp.dto.CarDtoListDto;
import gr.aueb.cf.CarRentalApp.dto.SearchCarDto;

import java.util.List;

public interface CustomerService {

    List<CarDto> getAllCars();

    boolean bookACar(BookACarDto bookACarDto);

    CarDto getCarById(Long carId);


    List<Object> getBookingsByUserId (Long userId);

    CarDtoListDto searchCar(SearchCarDto searchCarDto);

}

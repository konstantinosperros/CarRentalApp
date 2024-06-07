package gr.aueb.cf.CarRentalApp.services.admin;

import gr.aueb.cf.CarRentalApp.dto.BookACarDto;
import gr.aueb.cf.CarRentalApp.dto.CarDto;
import gr.aueb.cf.CarRentalApp.dto.CarDtoListDto;
import gr.aueb.cf.CarRentalApp.dto.SearchCarDto;

import java.io.IOException;
import java.util.List;

public interface AdminService {

    boolean postCar(CarDto carDto) throws IOException;

    List<CarDto> getAllCars();

    void deleteCar(Long id);

    CarDto getCarById(Long id);

    boolean updateCar(Long carId, CarDto carDto) throws IOException;

    List<BookACarDto> getBookings();

    boolean changeBookingStatus(Long bookingId,String status);

    CarDtoListDto searchCar(SearchCarDto searchCarDto);

}

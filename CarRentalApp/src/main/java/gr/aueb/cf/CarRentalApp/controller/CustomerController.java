package gr.aueb.cf.CarRentalApp.controller;

import gr.aueb.cf.CarRentalApp.dto.BookACarDto;
import gr.aueb.cf.CarRentalApp.dto.CarDto;
import gr.aueb.cf.CarRentalApp.dto.SearchCarDto;
import gr.aueb.cf.CarRentalApp.entity.BookACar;
import gr.aueb.cf.CarRentalApp.services.customer.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/cars")
    public ResponseEntity<List<CarDto>> getAllCars() {
        List<CarDto> carDtoList = customerService.getAllCars();
        return  ResponseEntity.ok(carDtoList);
    }

    @PostMapping("/car/book")
    public ResponseEntity<Void> bookACar(@RequestBody BookACar bookACar) {
        boolean success = customerService.bookACar(new BookACarDto());
        if (success) return ResponseEntity.status(HttpStatus.CREATED).build();
        return ResponseEntity.status((HttpStatus.BAD_REQUEST)).build();
    }

    @GetMapping("/car/{carId}")
    public ResponseEntity<CarDto> getCarById(@PathVariable Long carId) {
        CarDto carDto = customerService.getCarById(carId);
        if (carDto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(carDto);
    }

    @GetMapping("/car/bookings/{userId}")
    public ResponseEntity<List<Object>> getBookingsByUserId(@PathVariable Long userId) {
        return ResponseEntity. ok(customerService.getBookingsByUserId(userId));
    }

    @PostMapping("/car/search")
    public ResponseEntity<?> searchCar (@RequestBody SearchCarDto searchCarDto) {
        return ResponseEntity.ok(customerService.searchCar(searchCarDto));
    }
}

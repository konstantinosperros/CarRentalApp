package gr.aueb.cf.CarRentalApp.services.customer;

import gr.aueb.cf.CarRentalApp.dto.BookACarDto;
import gr.aueb.cf.CarRentalApp.dto.CarDto;
import gr.aueb.cf.CarRentalApp.dto.CarDtoListDto;
import gr.aueb.cf.CarRentalApp.dto.SearchCarDto;
import gr.aueb.cf.CarRentalApp.entity.BookACar;
import gr.aueb.cf.CarRentalApp.entity.Car;
import gr.aueb.cf.CarRentalApp.entity.User;
import gr.aueb.cf.CarRentalApp.enums.BookCarStatus;
import gr.aueb.cf.CarRentalApp.repository.BookACarRepository;
import gr.aueb.cf.CarRentalApp.repository.CarRepository;
import gr.aueb.cf.CarRentalApp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CarRepository carRepository;

    private final UserRepository userRepository;

    private final BookACarRepository bookACarRepository;


    @Override
    public List<CarDto> getAllCars() {
        return carRepository.findAll().stream().map(Car::getCarDto).collect(Collectors.toList());
    }

    @Override
    public boolean bookACar(BookACarDto bookACarDto) {
        Optional<Car> optionalCar = carRepository.findById(bookACarDto.getCardId());
        Optional<User> optionalUser = userRepository.findById(bookACarDto.getUserId());
        if (optionalCar.isPresent() && optionalUser.isPresent()){
            Car existingCar = optionalCar.get();
            BookACar bookACar = new BookACar();
            bookACar.setUser(optionalUser.get());
            bookACar.setCar(existingCar);
            bookACar.setBookCarStatus (BookCarStatus.PENDING);
            long diffInMilliSeconds = bookACarDto.getToDate().getTime() - bookACarDto.getFromDate().getTime();
            long days = TimeUnit.MICROSECONDS.toDays (diffInMilliSeconds);
            bookACar.setDays (days);
            bookACar.setPrice (existingCar.getPrice() * days);
            bookACarRepository.save(bookACar);
            return true;
        }
        return false;
    }

    @Override
    public CarDto getCarById(Long carId) {
        Optional<Car> optionalCar = carRepository.findById(carId);
        return optionalCar.map(Car::getCarDto).orElse(null);
    }

    @Override
    public List<Object> getBookingsByUserId(Long userId) {
        return bookACarRepository.findAllByUserId(userId).stream().map(BookACarDto::getBookACarDto).collect(Collectors.toList());
    }

    @Override
    public CarDtoListDto searchCar(SearchCarDto searchCarDto) {
        Car car = new Car();
        car.setBrand(searchCarDto.getBrand());
        car.setType(searchCarDto.getType());
        car.setTransmission (searchCarDto.getTransmission());
        car.setColor(searchCarDto.getColor());
        ExampleMatcher exampleMatcher =
                ExampleMatcher.matchingAll()
                        .withMatcher( "brand", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                        .withMatcher( "type", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                        .withMatcher( "transmission", ExampleMatcher. GenericPropertyMatchers.contains().ignoreCase())
                        .withMatcher ( "color", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());
        Example<Car> carExample = Example.of(car, exampleMatcher);
        List<Car> carList = carRepository.findAll(carExample);
        CarDtoListDto carDtoListDto = new CarDtoListDto();
        carDtoListDto.setCarDtoList(carList.stream().map(Car::getCarDto).collect(Collectors.toList()));
        return carDtoListDto;
    }


}

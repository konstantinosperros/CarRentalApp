package gr.aueb.cf.CarRentalApp.services.auth;

import gr.aueb.cf.CarRentalApp.dto.SignupRequest;
import gr.aueb.cf.CarRentalApp.dto.UserDto;

public interface AuthService {

    UserDto createCustomer(SignupRequest signupRequest);

    boolean hasCustomerWithEmail(String email);
}

package gr.aueb.cf.CarRentalApp.dto;

import gr.aueb.cf.CarRentalApp.enums.UserRole;
import lombok.Data;

@Data
public class AuthenticationResponse {

    private String jwt;

    private UserRole userRole;

    private Long userId;
}

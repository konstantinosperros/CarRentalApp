package gr.aueb.cf.CarRentalApp.repository;

import gr.aueb.cf.CarRentalApp.entity.User;
import gr.aueb.cf.CarRentalApp.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findFirstByEmail(String email);


    User findFirstUserRole(UserRole userRole);
}

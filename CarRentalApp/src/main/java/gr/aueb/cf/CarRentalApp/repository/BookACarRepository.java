package gr.aueb.cf.CarRentalApp.repository;

import gr.aueb.cf.CarRentalApp.dto.BookACarDto;
import gr.aueb.cf.CarRentalApp.entity.BookACar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Book;
import java.util.List;

@Repository
public interface BookACarRepository extends JpaRepository<BookACar, Long> {
    List<BookACarDto> findAllByUserId(Long userId);
}

package gr.aueb.cf.CarRentalApp.dto;

import gr.aueb.cf.CarRentalApp.enums.BookCarStatus;
import lombok.Data;

import java.util.Date;

@Data
public class BookACarDto {

    private Long id;

    private Date fromDate;

    private Date toDate;

    private Long days;

    private Long price;

    private BookCarStatus bookCarStatus;

    private Long cardId;

    private Long userId;

    private String username;

    private String email;


    public Object getBookACarDto()
    { return getBookACarDto();
    }

}


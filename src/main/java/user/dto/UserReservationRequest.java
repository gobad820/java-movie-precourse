package user.dto;

import java.util.List;
import screening.Screening;
import seat.enums.SeatGrade;

public record UserReservationRequest(
    Screening screening,
    List<String> seatNumber,
    SeatGrade seatGrade
) {

}

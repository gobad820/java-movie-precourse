package user.dto;

import java.util.Map;
import screening.Screening;
import seat.enums.SeatGrade;

public record UserReservationRequest(
    Screening screening,
    Map<String, Integer> seatNumber,
    SeatGrade seatGrade
) {

}

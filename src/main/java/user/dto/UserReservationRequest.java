package user.dto;

import screening.Screening;

public record UserReservationRequest(
    Screening screening,
    Payment payment,
    char seatRow,
    int seatCol
) {

}

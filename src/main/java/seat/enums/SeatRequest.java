package seat.enums;

import java.util.Map;

public record SeatRequest(
    String row,
    int col,
    SeatGrade seatGrade
) {
}

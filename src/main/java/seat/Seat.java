package seat;

import seat.enums.SeatGrade;

public class Seat {
    private char row;
    private int col;
    private boolean isOccupied;
    private Enum<SeatGrade> seatGrade;

    public Seat(char row, int col, boolean isOccupied, Enum<SeatGrade> seatGrade) {
        this.row = row;
        this.col = col;
        this.isOccupied = isOccupied;
        this.seatGrade = seatGrade;
    }
}

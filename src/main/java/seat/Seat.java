package seat;

import seat.enums.SeatGrade;

public class Seat {

    private String row;
    private int col;
    private boolean isOccupied;
    private SeatGrade seatGrade;

    public Seat(String row, int col, boolean isOccupied, SeatGrade seatGrade) {
        this.row = row;
        this.col = col;
        this.isOccupied = isOccupied;
        this.seatGrade = seatGrade;
    }

    public String getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public SeatGrade getSeatGrade() {
        return seatGrade;
    }
}

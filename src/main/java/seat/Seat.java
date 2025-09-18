package seat;

import seat.enums.SeatGrade;

public class Seat {

    private String seatNumber;
    private boolean isOccupied;
    private SeatGrade seatGrade;

    public Seat(String seatNumber, boolean isOccupied, SeatGrade seatGrade) {
        this.seatNumber = seatNumber;
        this.isOccupied = isOccupied;
        this.seatGrade = seatGrade;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public SeatGrade getSeatGrade() {
        return seatGrade;
    }
}

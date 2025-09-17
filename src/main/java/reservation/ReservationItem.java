package reservation;

import java.util.List;
import screening.Screening;
import seat.Seat;

public class ReservationItem {

    private Screening screening; // 영화 상영
    private List<Seat> seats; // 좌석

    public ReservationItem(Screening screening, List<Seat> seats) {
        this.screening = screening;
        this.seats = seats;
    }

    public Screening getScreening() {
        return screening;
    }

    public List<Seat> getSeats() {
        return seats;
    }

}

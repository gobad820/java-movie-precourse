package reservation;

import java.util.ArrayList;
import java.util.List;
import screening.Screening;
import seat.Seat;
import seat.enums.SeatRequest;

public class ReservationItem {

    private Screening screening; // 영화 상영
    private List<Seat> seats = new ArrayList<>(); // 좌석 위치
    private int price;

    public ReservationItem(Screening screening, List<SeatRequest> seatRequests, int price) {
        this.screening = screening;
        seatRequests.stream().forEach(x ->
            this.seats.add(new Seat(x.seatNumber(), true, x.seatGrade())));
        this.price = price;
    }


    public Screening getScreening() {
        return screening;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public int getPrice() {
        return price;
    }

    public void discountPrice(int price) {
        for(Seat s: seats){
            this.price -= price;
        }
    }
}

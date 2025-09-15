package reservation;

import discount.ReservationDiscount;
import java.time.Duration;
import java.time.LocalDateTime;
import payment.Payment;
import screening.Screening;
import seat.Seat;

public class Reservation {

    private Screening screening;
    private Payment payment;
    private Seat seat;
    private LocalDateTime reservedStartTime;
    private LocalDateTime resvedEndTime;
    private Duration reservationDuration;
    private ReservationDiscount reservationDiscount;

    public Reservation() {
    }

    public Reservation(Screening screening, Payment payment, Seat seat,
        LocalDateTime reservedStartTime, LocalDateTime resvedEndTime,
        Duration reservationDuration) {
        this.screening = screening;
        this.payment = payment;
        this.seat = seat;
        this.reservedStartTime = reservedStartTime;
        this.resvedEndTime = resvedEndTime;
        this.reservationDuration = reservationDuration;
    }
}

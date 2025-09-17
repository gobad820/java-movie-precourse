package reservation;

import java.util.List;
import payment.PaymentType;
import screening.Screening;
import seat.Seat;

public class Reservation {
    private Long userId;
    private List<ReservationItem> reservationItems;
    private boolean isPointUsed;
    private PaymentType paymentType;

    public Reservation(Long userId, List<ReservationItem> reservationItems, boolean isPointUsed,
        PaymentType paymentType) {
        this.userId = userId;
        this.reservationItems = reservationItems;
        this.isPointUsed = isPointUsed;
        this.paymentType = paymentType;
    }

    public Long getUserId() {
        return userId;
    }

    public List<ReservationItem> getReservationItems() {
        return reservationItems;
    }

    public boolean isPointUsed() {
        return isPointUsed;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }


}

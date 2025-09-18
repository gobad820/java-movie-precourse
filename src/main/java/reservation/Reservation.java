package reservation;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import payment.PaymentType;
import screening.Screening;
import seat.enums.SeatRequest;
import user.User;
import user.dto.UserReservationRequest;

public class Reservation {

    private final User user;
    private final List<ReservationItem> reservationItems = new ArrayList<>();

    private int isPointUsed;
    private PaymentType paymentType;
    private Screening screening;
    private LocalDateTime reservedAt;
    private int totalPrice;
    private static final int MORNING_AND_NIGHT_DISCOUNT_PRICE = 2000;

    public Reservation(User user, List<UserReservationRequest> userReservationRequests,
        int isPointUsed, PaymentType paymentType, Screening screening) {
        this.screening = screening;
        user.usePoint(isPointUsed);
        createReservationItem(userReservationRequests, screening);
        this.user = user;
        this.isPointUsed = isPointUsed;
        this.paymentType = paymentType;
        this.reservedAt = LocalDateTime.now();
    }

    private void createReservationItem(List<UserReservationRequest> userReservationRequests,
        Screening screening) {
        List<LocalDateTime> occupiedScreening = new ArrayList<>();
        for (UserReservationRequest r : userReservationRequests) {
            ArrayList<SeatRequest> seats = new ArrayList<>();
            if (!checkNoConflicts(screening, r, occupiedScreening)) {
                continue;
            }
            occupiedScreening.add(r.screening().getScreeningTime());
            int seatsPrice = r.seatGrade().getPrice() * r.seatNumber().size();
            r.seatNumber().forEach(k -> {
                seats.add(new SeatRequest(k, r.seatGrade()));
            });
            reservationItems.add(new ReservationItem(r.screening(), seats, seatsPrice));
        }
    }

    private static boolean checkNoConflicts(Screening screening, UserReservationRequest request,
        List<LocalDateTime> occupiedScreening) {
        return checkUnOccupiedSeat(screening, request) && checkNoTimeConflict(request,
            occupiedScreening);
    }

    private static boolean checkUnOccupiedSeat(Screening screening,
        UserReservationRequest request) {
        if (request.seatNumber().stream().anyMatch(
            screening::checkSeatOccupied)
        ) {
            throw new RuntimeException("이미 예약된 자석입니다.");
        }
        return true;
    }

    private static boolean checkNoTimeConflict(UserReservationRequest request,
        List<LocalDateTime> occupiedScreening) {
        if (occupiedScreening.contains(request.screening().getScreeningTime())) {
            throw new RuntimeException("동일 시간대를 중복 예매할 수 없습니다.");
        }
        return true;
    }

    public User getUser() {
        return user;
    }

    public List<ReservationItem> getReservationItems() {
        return reservationItems;
    }

    public int isPointUsed() {
        return isPointUsed;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void discountTotalPrice() {
        double totalPrice = getPriceTimeDiscount();
        totalPrice = getPriceDayDiscount(totalPrice);
        this.totalPrice = (int) getDiscountedPrice(totalPrice);

    }

    private double getPriceDayDiscount(double totalPrice) {
        if (screening.getScreeningTime().getDayOfMonth() % 10 == 0) {
            totalPrice *= 0.9;
        }
        return totalPrice;
    }

    private double getPriceTimeDiscount() {
        double totalPrice = reservationItems.stream()
            .mapToDouble(item -> {
                if (item.getScreening().getScreeningTime().getHour() < 11 ||
                    item.getScreening().getScreeningTime().getHour() >= 20) {
                    item.discountPrice(MORNING_AND_NIGHT_DISCOUNT_PRICE);
                }
                return item.getPrice();
            })
            .sum();
        return totalPrice;
    }

    private double getDiscountedPrice(double price) {
        if (paymentType == PaymentType.CREDIT_CARD) {
            price *= 0.95;
        }
        if (paymentType == PaymentType.CASH) {
            price *= 0.98;
        }
        return price;
    }

    public int getIsPointUsed() {
        return isPointUsed;
    }

    public Screening getScreening() {
        return screening;
    }

    public int getTotalPrice() {
        return this.totalPrice;
    }

}

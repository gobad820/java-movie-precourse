package discount;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ReservationDiscount {

    private Long Id;
    private LocalDateTime screeningStartTime;
    private int totalPrice;
    private int userPoint;
    private static final int TIME_DISCOUNT_PRICE = 2000;


    public int discountByTime(LocalDateTime screeningStartTime, int totalPrice) {
        LocalDateTime beforeNoonDiscountTime = LocalDateTime.of(screeningStartTime.getYear(),
            screeningStartTime.getMonth(), screeningStartTime.getDayOfMonth(), 11, 0, 0);
        if (screeningStartTime.isBefore(beforeNoonDiscountTime)
            || 20 <= screeningStartTime.getHour()) {
            totalPrice -= TIME_DISCOUNT_PRICE;
            return totalPrice;
        }
        return totalPrice;
    }

    public int discountByPoint(int totalPrice, int userPoint) {
        if (userPoint >= totalPrice) {
            return 0;
        }
        totalPrice -= userPoint;
        return totalPrice;
    }

    public int discountByMovieDay(int totalPrice) {
        if (LocalDate.now().getDayOfMonth() % 10 == 0) {
            double price = totalPrice;
            price *= 0.9;
            totalPrice = (int) price;
        }
        return totalPrice;
    }


}

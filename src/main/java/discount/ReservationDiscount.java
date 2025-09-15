package discount;

import java.time.LocalDateTime;

public class ReservationDiscount {

    private final Long userId;
    private final LocalDateTime screenTime;
    private int totalPrice;

    public ReservationDiscount(Long userId, LocalDateTime screenTime) {
        this.userId = userId;
        this.screenTime = screenTime;
    }

    public ReservationDiscount(Long userId, LocalDateTime screenTime, int totalPrice) {
        this.userId = userId;
        this.screenTime = screenTime;
        this.totalPrice = totalPrice;
    }

    // TODO: 조조할인 적용하여 정액 할인

    // TODO: 포인트 사용하여 정액 할인

    // TODO: 무비데이 10% 할인


}

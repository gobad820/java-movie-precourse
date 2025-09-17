package user;

import payment.PaymentType;

public class User {

    private Long userId;
    private int userPoint;
    private int balance;
    private PaymentType paymentType;

    public User(Long userId, int userPoint, int balance, PaymentType paymentType) {
        this.userId = userId;
        this.userPoint = userPoint;
        this.balance = balance;
        this.paymentType = paymentType;
    }

    public Long getUserId() {
        return userId;
    }

    public int getUserPoint() {
        return userPoint;
    }

    public int getBalance() {
        return balance;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public boolean usePoint(int point) {
        if (point > this.userPoint) {
            throw new RuntimeException("포인트가 부족합니다.");
        }
        this.userPoint -= point;
        return true;
    }
}

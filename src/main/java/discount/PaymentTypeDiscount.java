package discount;

import payment.PaymentType;

public class PaymentTypeDiscount {

    private PaymentType paymentType;
    private int totalPrice;

    public PaymentTypeDiscount() {
    }

    public PaymentTypeDiscount(PaymentType paymentType, int totalPrice) {
        this.paymentType = paymentType;
        this.totalPrice = totalPrice;
    }
}

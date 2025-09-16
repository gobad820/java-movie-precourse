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

    public int discountByPaymentType(PaymentType paymentType, int totalPrice) {
        if (paymentType == PaymentType.CASH) {
            double price = totalPrice;
            price *= 0.98;
            return (int) price;
        }

        if (paymentType == PaymentType.CREDIT_CARD) {
            double price = totalPrice;
            price *= 0.95;
            return (int) price;

        }
        return totalPrice;

    }

}

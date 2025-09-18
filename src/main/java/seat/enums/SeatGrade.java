package seat.enums;

public enum SeatGrade {
    B(12000), A(15000), S(18000);

    private int price;

    SeatGrade(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
}

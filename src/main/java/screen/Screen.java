package screen;

import java.time.LocalDateTime;
import java.util.List;
import movie.enums.ScreeningType;
import screen.screening.ScreeningSchedule;
import seat.Seat;

public class Screen {

    private Long code;
    private Enum<ScreeningType> screeningType;
    private List<Seat> seats;
    private LocalDateTime operatingTime;
    private int screenPrice;
    private ScreeningSchedule screeningSchedule;


    public Screen(ScreenBuilder builder) {
        this.code = builder.code;
        this.screeningType = builder.screeningType;
        this.seats = builder.seats;
        this.operatingTime = builder.operatingTime;
        this.screenPrice = builder.screenPrice;
        this.screeningSchedule = builder.screeningSchedule;
    }


    public static final class ScreenBuilder {

        private Long code;
        private Enum<ScreeningType> screeningType;
        private List<Seat> seats;
        private LocalDateTime operatingTime;
        private ScreeningSchedule screeningSchedule;
        private int screenPrice;

        public ScreenBuilder() {
        }

        public static ScreenBuilder aScreen() {
            return new ScreenBuilder();
        }

        public ScreenBuilder withCode(Long code) {
            this.code = code;
            return this;
        }

        public ScreenBuilder withScreeningType(Enum<ScreeningType> screeningType) {
            this.screeningType = screeningType;
            return this;
        }

        public ScreenBuilder withSeats(List<Seat> seats) {
            this.seats = seats;
            return this;
        }

        public ScreenBuilder withOperatingTime(LocalDateTime operatingTime) {
            this.operatingTime = operatingTime;
            return this;
        }

        public ScreenBuilder withScreenPrice(int screenPrice) {
            this.screenPrice = screenPrice;
            return this;
        }

        public ScreenBuilder withScreeningSchedule(ScreeningSchedule screeningSchedule) {
            this.screeningSchedule = screeningSchedule;
            return this;
        }

        public Screen build() {
            return new Screen(this);
        }
    }

    public Screen setScreeningType(Enum<ScreeningType> screeningType) {
        this.screeningType = screeningType;
        return this;
    }

    public Screen setSeats(List<Seat> seats) {
        this.seats = seats;
        return this;
    }

    public Screen setOperatingTime(LocalDateTime operatingTime) {
        this.operatingTime = operatingTime;
        return this;
    }

    public Screen setScreenPrice(int screenPrice) {
        this.screenPrice = screenPrice;
        return this;
    }

    public Screen setScreeningSchedule(ScreeningSchedule screeningSchedule) {
        this.screeningSchedule = screeningSchedule;
        return this;
    }

    public Long getCode() {
        return code;
    }

    public Enum<ScreeningType> getScreeningType() {
        return screeningType;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public LocalDateTime getOperatingTime() {
        return operatingTime;
    }

    public int getScreenPrice() {
        return screenPrice;
    }

    public ScreeningSchedule getScreeningSchedule() {
        return screeningSchedule;
    }
}

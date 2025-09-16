package screen;

import java.time.Duration;
import java.util.List;
import movie.enums.ScreenType;
import seat.Seat;

public class Screen {

    private Long code;
    private Enum<ScreenType> screenType;
    private List<Seat> seats;
    private Duration operatingTime;
    private ScreeningSchedule screeningSchedule;


    public Screen(ScreenBuilder builder) {
        this.code = builder.code;
        this.screenType = builder.screeningType;
        this.seats = builder.seats;
        this.operatingTime = builder.operatingTime;
        this.screeningSchedule = builder.screeningSchedule;
    }


    public static final class ScreenBuilder {

        private Long code;
        private Enum<ScreenType> screeningType;
        private List<Seat> seats;
        private Duration operatingTime;
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

        public ScreenBuilder withScreeningType(Enum<ScreenType> screeningType) {
            this.screeningType = screeningType;
            return this;
        }

        public ScreenBuilder withSeats(List<Seat> seats) {
            this.seats = seats;
            return this;
        }

        public ScreenBuilder withOperatingTime(Duration operatingTime) {
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

    public void changeMovieType(Enum<ScreenType> screenType) {
        this.screenType = screenType;
    }

    public void changeOperatingTime(Duration operatingTime) {
        this.operatingTime = operatingTime;
    }

    public void changeScreeningSchedule(ScreeningSchedule screeningSchedule) {
        this.screeningSchedule = screeningSchedule;
    }

    public Long getCode() {
        return code;
    }

    public Enum<ScreenType> getScreenType() {
        return screenType;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public Duration getOperatingTime() {
        return operatingTime;
    }

    public ScreeningSchedule getScreeningSchedule() {
        return screeningSchedule;
    }
}

package screen;

import java.time.Duration;
import java.util.List;
import movie.enums.MovieType;
import seat.Seat;

public class Screen {

    private Long code;
    private Enum<MovieType> screeningType;
    private List<Seat> seats;
    private Duration operatingTime;
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
        private Enum<MovieType> screeningType;
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

        public ScreenBuilder withScreeningType(Enum<MovieType> screeningType) {
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

    public Screen setScreeningType(Enum<MovieType> screeningType) {
        this.screeningType = screeningType;
        return this;
    }

    public Screen setSeats(List<Seat> seats) {
        this.seats = seats;
        return this;
    }

    public Screen setOperatingTime(Duration operatingTime) {
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

    public Enum<MovieType> getScreeningType() {
        return screeningType;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public Duration getOperatingTime() {
        return operatingTime;
    }

    public int getScreenPrice() {
        return screenPrice;
    }

    public ScreeningSchedule getScreeningSchedule() {
        return screeningSchedule;
    }
}

package screen;

import java.time.LocalDateTime;
import java.util.List;
import movie.enums.ScreeningType;
import seat.Seat;

public class Screen {

    private Long code;
    private Enum<ScreeningType> screeningType;
    private List<Seat> seats;
    private Long movieCode;
    private List<LocalDateTime> movieTimeTable;
    private LocalDateTime operatingTime;
    private int screenPrice;

    public Screen(ScreenBuilder builder) {
        this.code = builder.code;
        this.screeningType = builder.screeningType;
        this.seats = builder.seats;
        this.movieCode = builder.movieCode;
        this.movieTimeTable = builder.movieTimeTable;
        this.operatingTime = builder.operatingTime;
        this.screenPrice = builder.screenPrice;
    }


    public static final class ScreenBuilder {

        private Long code;
        private Enum<ScreeningType> screeningType;
        private List<Seat> seats;
        private Long movieCode;
        private List<LocalDateTime> movieTimeTable;
        private LocalDateTime operatingTime;
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

        public ScreenBuilder withMovieCode(Long movieCode) {
            this.movieCode = movieCode;
            return this;
        }

        public ScreenBuilder withMovieTimeTable(List<LocalDateTime> movieTimeTable) {
            this.movieTimeTable = movieTimeTable;
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

        public Screen build() {
            return new Screen(this);
        }
    }
}

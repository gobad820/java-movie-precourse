package cinema;

import java.util.List;
import screen.Screen;

public class Cinema {

    private final Long code;
    private String name;
    private String cinemaOperator;
    private String mainPhoneNumber;
    private String pageURL;
    private String address;
    private List<Screen> screens;

    public Cinema(CinemaBuilder builder) {
        validateBuilderParameter(builder);
        this.code = builder.code;
        this.name = builder.name;
        this.cinemaOperator = builder.cinemaOperator;
        this.mainPhoneNumber = builder.mainPhoneNumber;
        this.pageURL = builder.pageURL;
        this.address = builder.address;
        this.screens = builder.screens;
    }

    private static void validateBuilderParameter(CinemaBuilder builder) {
        if (builder.code == null || builder.code < 0) {
            throw new IllegalArgumentException("영화관 코드 번호는 필수값입니다.");
        }
        if (builder.name == null || builder.name.trim().isEmpty()) {
            throw new IllegalArgumentException("영화관 이름은 필수값입니다.");
        }
        if (builder.address == null || builder.address.trim().isEmpty()) {
            throw new IllegalArgumentException("영화관 주소는 필수값입니다.");
        }
    }

    public Long getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getCinemaOperator() {
        return cinemaOperator;
    }

    public String getMainPhoneNumber() {
        return mainPhoneNumber;
    }

    public String getPageURL() {
        return pageURL;
    }

    public String getAddress() {
        return address;
    }

    public List<Screen> getScreens() {
        return screens;
    }

    public static final class CinemaBuilder {

        private Long code;
        private String name;
        private String cinemaOperator;
        private String mainPhoneNumber;
        private String pageURL;
        private String address;
        private List<Screen> screens;

        public CinemaBuilder() {
        }

        public static CinemaBuilder aCinema() {
            return new CinemaBuilder();
        }

        public CinemaBuilder withCode(Long code) {
            this.code = code;
            return this;
        }

        public CinemaBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public CinemaBuilder withCinemaOperator(String cinemaOperator) {
            this.cinemaOperator = cinemaOperator;
            return this;
        }

        public CinemaBuilder withMainPhoneNumber(String mainPhoneNumber) {
            this.mainPhoneNumber = mainPhoneNumber;
            return this;
        }

        public CinemaBuilder withPageURL(String pageURL) {
            this.pageURL = pageURL;
            return this;
        }

        public CinemaBuilder withAddress(String address) {
            this.address = address;
            return this;
        }

        public CinemaBuilder withScreens(List<Screen> screens) {
            this.screens = screens;
            return this;
        }

        public Cinema build() {
            return new Cinema(this);
        }
    }
}

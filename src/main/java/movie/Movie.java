package movie;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import movie.enums.MovieGrade;
import movie.enums.ScreeningType;

public class Movie {

    private final Long code;
    private List<String> information;
    private Enum<MovieGrade> grade;
    private final String title;
    private final String director;
    private LocalDate releaseDate;
    private final String nation;
    private List<Enum<ScreeningType>> screeningTypes;
    private LocalDate crankIn;
    private LocalDate crankUp;
    private final Duration screenTime;


    public Movie(MovieBuilder builder) {
        validateMovieParameter(builder);
        this.code = builder.code;
        this.information = builder.information;
        this.grade = builder.grade;
        this.title = builder.title;
        this.director = builder.director;
        this.releaseDate = builder.releaseDate;
        this.nation = builder.nation;
        this.screeningTypes = builder.screeningTypes;
        this.crankIn = builder.crankIn;
        this.crankUp = builder.crankUp;
        this.screenTime = builder.screenTime;
    }

    public static final class MovieBuilder {

        private Long code;
        private List<String> information;
        private Enum<MovieGrade> grade;
        private String title;
        private String director;
        private LocalDate releaseDate;
        private String nation;
        private List<Enum<ScreeningType>> screeningTypes;
        private Duration screenTime;
        private LocalDate crankIn = LocalDate.of(1, 1, 1);
        private LocalDate crankUp = LocalDate.of(1, 1, 1);

        public MovieBuilder() {
        }

        public MovieBuilder(Long code, List<String> information, Enum<MovieGrade> grade,
            String title, String director, LocalDate releaseDate, String nation,
            List<Enum<ScreeningType>> screeningTypes, Duration screenTime) {
            this.code = code;
            this.information = information;
            this.grade = grade;
            this.title = title;
            this.director = director;
            this.releaseDate = releaseDate;
            this.nation = nation;
            this.screeningTypes = screeningTypes;
            this.screenTime = screenTime;
        }

        public MovieBuilder(Movie other) {
            this.code = other.code;
            this.information = other.information;
            this.grade = other.grade;
            this.title = other.title;
            this.director = other.director;
            this.releaseDate = other.releaseDate;
            this.nation = other.nation;
            this.screeningTypes = other.screeningTypes;
            this.crankIn = other.crankIn;
            this.crankUp = other.crankUp;
            this.screenTime = other.screenTime;
        }

        public static MovieBuilder aMovie() {
            return new MovieBuilder();
        }

        public MovieBuilder withCode(Long code) {
            this.code = code;
            return this;
        }

        public MovieBuilder withInformation(List<String> information) {
            this.information = information;
            return this;
        }

        public MovieBuilder withGrade(Enum<MovieGrade> grade) {
            this.grade = grade;
            return this;
        }

        public MovieBuilder withTitle(String title) {
            this.title = title;
            return this;
        }

        public MovieBuilder withDirector(String director) {
            this.director = director;
            return this;
        }

        public MovieBuilder withReleaseDate(LocalDate releaseDate) {
            this.releaseDate = releaseDate;
            return this;
        }

        public MovieBuilder withNation(String nation) {
            this.nation = nation;
            return this;
        }

        public MovieBuilder withScreeningTypes(List<Enum<ScreeningType>> screeningTypes) {
            this.screeningTypes = screeningTypes;
            return this;
        }

        public MovieBuilder withCrankIn(LocalDate crankIn) {
            this.crankIn = crankIn;
            return this;
        }

        public MovieBuilder withCrankUp(LocalDate crankUp) {
            this.crankUp = crankUp;
            return this;
        }

        public MovieBuilder withScreenTime(Duration screenTime) {
            this.screenTime = screenTime;
            return this;
        }


        public Movie build() {
            return new Movie(this);
        }
    }

    public Long getCode() {
        return code;
    }

    public List<String> getInformation() {
        return information;
    }

    public Enum<MovieGrade> getGrade() {
        return grade;
    }

    public String getTitle() {
        return title;
    }

    public String getDirector() {
        return director;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public String getNation() {
        return nation;
    }

    public List<Enum<ScreeningType>> getScreeningTypes() {
        return screeningTypes;
    }

    public LocalDate getCrankIn() {
        return crankIn;
    }

    public LocalDate getCrankUp() {
        return crankUp;
    }

    public Duration getScreenTime() {
        return screenTime;
    }

    private static void validateMovieParameter(MovieBuilder builder) {
        validateMovieCode(builder);
        validateMovieTitle(builder);
        validateMovieNation(builder);
        validateMovieDirector(builder);
        validateMovieScreenTime(builder);
    }

    private static void validateMovieScreenTime(MovieBuilder builder) {
        if(builder.screenTime == null){
            throw new IllegalArgumentException("영화 상영 시간은 필수값입니다.");
        }
        if (builder.screenTime.isNegative() || builder.screenTime.isZero()) {
            throw new IllegalArgumentException("영화 상영 시간은 음수 또는 0일 수 없습니다.");
        }
    }

    private static void validateMovieDirector(MovieBuilder builder) {
        if (builder.director == null || builder.director.trim().isEmpty()) {
            throw new IllegalArgumentException("영화 감독은 필수값입니다.");
        }
    }

    private static void validateMovieNation(MovieBuilder builder) {
        if (builder.nation == null || builder.nation.trim().isEmpty()) {
            throw new IllegalArgumentException("제작 국가는 필수값입니다.");
        }
    }

    private static void validateMovieTitle(MovieBuilder builder) {
        if (builder.title == null || builder.title.trim().isEmpty()) {
            throw new IllegalArgumentException("영화 제목은 필수값입니다.");
        }
    }

    private static void validateMovieCode(MovieBuilder builder) {
        if (builder.code == null || builder.code <= 0) {
            throw new IllegalArgumentException("영화 코드는 필수값입니다.");
        }
    }

}

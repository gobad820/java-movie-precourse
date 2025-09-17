package screening;

import java.time.LocalDateTime;
import java.util.List;
import movie.Movie;
import seat.Seat;

public class Screening {

    private Movie movie;
    private LocalDateTime ScreeningTime; // 영화 시간 + 상영 시작 시간
    private List<Seat> seats;

    public Screening(Movie movie, LocalDateTime screeningTime, List<Seat> seats) {
        this.movie = movie;
        ScreeningTime = screeningTime;
        this.seats = seats;
    }

    public Movie getMovie() {
        return movie;
    }

    public LocalDateTime getScreeningTime() {
        return ScreeningTime;
    }

    public List<Seat> getSeats() {
        return seats;
    }


}

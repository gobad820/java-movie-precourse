package screening;

import java.time.LocalDateTime;
import java.util.Map;
import movie.Movie;
import seat.Seat;

public class Screening {

    private Long id;
    private Movie movie;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Map<Seat, Boolean> seatReserved;
    private int price;

    public Screening() {
    }

    public Screening(Movie movie, LocalDateTime startTime, LocalDateTime endTime,
        Map<Seat, Boolean> seatReserved, int price) {
        this.movie = movie;
        this.startTime = startTime;
        this.endTime = endTime;
        this.seatReserved = seatReserved;
        this.price = price;
    }

    public Movie getMovie() {
        return movie;
    }

    public Screening setMovie(Movie movie) {
        this.movie = movie;
        return this;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public Screening setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
        return this;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public Screening setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
        return this;
    }

    public Map<Seat, Boolean> getSeatReserved() {
        return seatReserved;
    }

    public Screening setSeatReserved(Map<Seat, Boolean> seatReserved) {
        this.seatReserved = seatReserved;
        return this;
    }

    public int getPrice() {
        return price;
    }

    public Screening setPrice(int price) {
        this.price = price;
        return this;
    }

    public Long getId() {
        return id;
    }
}

package screening;

import java.time.LocalDateTime;
import java.util.List;
import movie.Movie;
import seat.Seat;
import seat.enums.SeatGrade;

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

    public Boolean checkSeatOccupied(String seatNumber) {
        for (Seat s : seats) {
            if (s.getSeatNumber().equals(seatNumber)) {
                return s.isOccupied();
            }
        }
        throw new RuntimeException("없는 좌석입니다.");
    }

    public void allocateSeats(int row, int col) {
        int totalSeat = row * col;
        int s = (int) (totalSeat * 0.2);
        int a = (int) (totalSeat * 0.3);
        int b = totalSeat - s - a;
        int [] ratio = {s, a, b};

        for (int i = 0; i < row; i++) {
            for (int j = 1; j <= col; j++) {
                String seatNumber = makeSeatNumberString(i, j);
                seats.add(createSeat(ratio, seatNumber));
            }
        }
    }

    private static Seat createSeat(int [] ratio, String seatNumber) {
        if (ratio[0]-- > 0) {
            return new Seat(seatNumber, false, SeatGrade.S);
        }
        if (ratio[1]-- > 0) {
            return new Seat(seatNumber, false, SeatGrade.A);
        }
        return new Seat(seatNumber, false, SeatGrade.B);
    }

    private static String makeSeatNumberString(int i, int j) {
        String seatRow = "A" + i;
        String seatNumber = seatRow + Integer.toString(j);
        return seatNumber;
    }


}

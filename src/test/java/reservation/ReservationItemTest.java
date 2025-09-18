package reservation;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import movie.Movie;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import screening.Screening;
import seat.Seat;
import seat.enums.SeatGrade;
import seat.enums.SeatRequest;

class ReservationItemTest {

    Movie movie = new Movie("기생충", Duration.ofHours(1));
    LocalDateTime screeningTime = LocalDateTime.of(2025, 9, 19, 15, 0);
    ArrayList<Seat> seats = new ArrayList<>(
        List.of(new Seat("A1", false, SeatGrade.S)));

    Screening screening = new Screening(movie, screeningTime, seats);

    @Test
    @DisplayName("단일 예약 테스트")
    public void creationReservationItem() throws Exception {
        // given

        // when
        ReservationItem reservationItem = new ReservationItem(screening,
            new ArrayList<>(List.of(new SeatRequest("A1", SeatGrade.S))),
            SeatGrade.S.getPrice());

        // then
        assertThat(reservationItem.getPrice()).isEqualTo(SeatGrade.S.getPrice());
        assertThat(reservationItem.getScreening()).isEqualTo(screening);
        assertThat(reservationItem.getSeats().getFirst().getSeatNumber()).isEqualTo(
            seats.getFirst().getSeatNumber());
    }
}
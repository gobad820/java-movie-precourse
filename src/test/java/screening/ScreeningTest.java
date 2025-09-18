package screening;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import movie.Movie;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import seat.Seat;
import seat.enums.SeatGrade;

class ScreeningTest {

    private static final Movie movie = new Movie("기생충", Duration.ofHours(2));
    private static final LocalDateTime screeningTime = LocalDateTime.of(2025, 9, 19, 16, 30);
    private final List<Seat> seats = Collections.emptyList();

    @Test
    @DisplayName("상영 객체 생성 테스트")
    public void screeningCreationTest() throws Exception {
        // given

        // when
        Screening screening = new Screening(movie, screeningTime, seats);

        // then
        assertAll(
            () -> assertThat(screening.getMovie()).isEqualTo(movie),
            () -> assertThat(screening.getScreeningTime()).isEqualTo(screeningTime),
            () -> assertThat(screening.getSeats()).isEqualTo(Collections.emptyList())
        );
    }

    @Test
    @DisplayName("상영 좌석 설정 테스트 ")
    public void allocateSeatsTest() throws Exception {
        // given
        int row = 8;
        int col = 24;
        Screening screening = new Screening(movie, screeningTime, new ArrayList<>());

        // when
        screening.allocateSeats(row, col);

        // then
        assertThat(screening.getSeats().size()).isEqualTo(row * col);
        int expectedSSeatNumber = (int) (row * col * 0.2);
        assertThat(screening.getSeats().stream().filter(s -> s.getSeatGrade() == SeatGrade.S)
            .count()).isEqualTo(expectedSSeatNumber);
        int expectedASeatNumber = (int) (row * col * 0.3);
        assertThat(screening.getSeats().stream().filter(s -> s.getSeatGrade() == SeatGrade.A)
            .count()).isEqualTo(expectedASeatNumber);
        assertThat(screening.getSeats().stream().filter(s -> s.getSeatGrade() == SeatGrade.B)
            .count()).isEqualTo(row * col - (expectedASeatNumber + expectedSSeatNumber));
    }


}
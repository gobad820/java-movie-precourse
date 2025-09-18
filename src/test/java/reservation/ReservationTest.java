package reservation;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import movie.Movie;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import payment.PaymentType;
import screening.Screening;
import seat.Seat;
import seat.enums.SeatGrade;
import user.User;
import user.dto.UserReservationRequest;

@DisplayName("예약 도메인 테스트")
class ReservationTest {

    User user = new User(1L, 10000, 50000, PaymentType.CREDIT_CARD);
    List<UserReservationRequest> userReservationRequests = Collections.emptyList();
    int isPointedUsed = 5000;
    PaymentType paymentType = PaymentType.CREDIT_CARD;
    Movie movie = new Movie("기생충", Duration.ofHours(1).plusMinutes(30));
    LocalDateTime localDateTime = LocalDateTime.of(2025, 9, 19, 15, 0);
    List<Seat> seats = new ArrayList<>(
        List.of(new Seat("A1", false, SeatGrade.S), new Seat("A2", false, SeatGrade.S),
            new Seat("A3", false, SeatGrade.S)));
    Screening screening = new Screening(movie, localDateTime, seats);

    @Test
    @DisplayName("예약 생성 테스트")
    public void reservationCreationTest() throws Exception {
        // given

        // when
        Reservation reservation = new Reservation(user, userReservationRequests, isPointedUsed,
            paymentType, screening);

        // then
        assertAll(() -> assertThat(user.getUserId()).isEqualTo(reservation.getUser().getUserId()),
            () -> assertThat(isPointedUsed).isEqualTo(reservation.getIsPointUsed()),
            () -> assertThat(paymentType).isEqualTo(reservation.getPaymentType()),
            () -> assertThat(screening).isEqualTo(reservation.getScreening()));
    }

    @ParameterizedTest
    @ValueSource(strings = {"A1", "A2", "A3"})
    @DisplayName("이미 예매된 좌석 예매 불가 테스트")
    public void reservationCreationWithAlreadyOccupiedSeat(String occupiedSeat) {
        // given
        ArrayList<Seat> seats = new ArrayList<>(
            List.of(new Seat("A1", true, SeatGrade.S), new Seat("A2", true, SeatGrade.S),
                new Seat("A3", true, SeatGrade.S)));
        screening = new Screening(movie, localDateTime, seats);

        // when & then
        assertThatThrownBy(() -> {
            new Reservation(user, new ArrayList<>(List.of(
                new UserReservationRequest(screening, new ArrayList<>(List.of(occupiedSeat)),
                    SeatGrade.S))), isPointedUsed, paymentType, screening);
        }).isInstanceOf(RuntimeException.class).hasMessage("이미 예약된 자석입니다.");
    }


    @ParameterizedTest
    @ValueSource(ints = {15, 16, 17})
    @DisplayName("동시간대 예매 불가 테스트")
    public void reservationCreationWithDuplicatedHour(int hour) {
        screening = new Screening(movie, LocalDateTime.of(2025, 9, 20, hour, 30),
            Collections.emptyList());
        UserReservationRequest userReservationRequest = new UserReservationRequest(screening,
            Collections.emptyList(), SeatGrade.S);
        UserReservationRequest userReservationRequest1 = new UserReservationRequest(screening,
            Collections.emptyList(), SeatGrade.A);

        assertThatThrownBy(() -> new Reservation(user,
            new ArrayList<>(List.of(userReservationRequest, userReservationRequest1)),
            isPointedUsed, paymentType, screening)).isInstanceOf(RuntimeException.class);
    }

    @Nested
    @DisplayName("할인 테스트")
    class DiscountTest {

        @ParameterizedTest
        @ValueSource(strings = {"A2", "A1", "A3"})
        @DisplayName("신용카드 할인 테스트")
        public void reservationCreationWithCreditCardDiscountTest(String seatNumber) {
            // given
            Reservation reservation = new Reservation(user, new ArrayList<>(List.of(
                new UserReservationRequest(screening, new ArrayList<>(List.of(seatNumber)),
                    SeatGrade.S))), isPointedUsed, paymentType, screening);

            // when
            reservation.discountTotalPrice();

            // then
            int price = (int) (SeatGrade.S.getPrice() * 0.95);
            assertAll(() -> assertThat(reservation.getTotalPrice()).isEqualTo(price));
        }

        @ParameterizedTest
        @ValueSource(strings = {"A2", "A1", "A3"})
        @DisplayName("현금 할인 테스트")
        public void reservationCreationWithCashDiscountTest(String seatNumber) {
            // given
            Reservation reservation = new Reservation(user, new ArrayList<>(List.of(
                new UserReservationRequest(screening, new ArrayList<>(List.of(seatNumber)),
                    SeatGrade.S))), isPointedUsed, PaymentType.CASH, screening);

            // when
            reservation.discountTotalPrice();

            // then
            int price = (int) (SeatGrade.S.getPrice() * 0.98);
            assertAll(() -> assertThat(reservation.getTotalPrice()).isEqualTo(price));
        }
    }

    @Nested
    @DisplayName("조조 및 야간 영화 활인 테스트")
    class morningAndNightTest {

        private static final int TIME_DISCOUNT = 2000;

        @ParameterizedTest
        @ValueSource(ints = {8, 9, 10})
        @DisplayName("조조 영화 할인 테스트")
        public void morningDiscountTest(int hour) {
            // given
            Screening discountScreening = new Screening(movie,
                LocalDateTime.of(2025, 9, 15, hour, 30), seats);
            UserReservationRequest reservationRequest = new UserReservationRequest(
                discountScreening, List.of("A1", "A2"), SeatGrade.S);
            Reservation reservation = new Reservation(user, List.of(reservationRequest),
                isPointedUsed, paymentType, discountScreening);
            // when
            int beforeDiscountPrice =
                reservationRequest.seatNumber().size() * reservationRequest.seatGrade().getPrice();
            int priceTimeDiscount = (int) reservation.getPriceTimeDiscount();
            // then
            assertThat(priceTimeDiscount).isEqualTo(
                beforeDiscountPrice - (TIME_DISCOUNT * reservationRequest.seatNumber().size()));
        }

        @ParameterizedTest
        @ValueSource(ints = {20, 21, 22, 23})
        @DisplayName("심야 영화 할인 테스트")
        public void nightDiscountTest(int hour) {
            // given
            Screening discountScreening = new Screening(movie,
                LocalDateTime.of(2025, 9, 15, hour, 30), seats);
            UserReservationRequest reservationRequest = new UserReservationRequest(
                discountScreening, List.of("A1", "A2"), SeatGrade.S);
            Reservation reservation = new Reservation(user, List.of(reservationRequest),
                isPointedUsed, paymentType, discountScreening);
            // when
            int beforeDiscountPrice =
                reservationRequest.seatNumber().size() * reservationRequest.seatGrade().getPrice();
            int priceTimeDiscount = (int) reservation.getPriceTimeDiscount();
            // then
            assertThat(priceTimeDiscount).isEqualTo(
                beforeDiscountPrice - (TIME_DISCOUNT * reservationRequest.seatNumber().size()));
        }
    }

    @Nested
    @DisplayName("중복 할인 테스트")
    class DuplicatedDiscountTestClass {

        private static final int TIME_DISCOUNT = 2000;

        @ParameterizedTest
        @ValueSource(ints = {8, 10})
        @DisplayName("신용카드 조조할인 무비데이 중복 테스트")
        public void morningAndCreditCardTest(int morningHour) {
            // given
            Screening discountScreening = new Screening(movie,
                LocalDateTime.of(2025, 9, 20, morningHour, 30), seats);
            UserReservationRequest reservationRequest = new UserReservationRequest(
                discountScreening, List.of("A1", "A2"), SeatGrade.S);
            Reservation reservation = new Reservation(user, List.of(reservationRequest),
                isPointedUsed, paymentType,
                discountScreening);
            // when
            reservation.discountTotalPrice();
            int expectedPrice = (int) ((SeatGrade.S.getPrice() - TIME_DISCOUNT)
                * reservationRequest.seatNumber().size() * 0.9 * 0.95);

            // then
            assertThat(reservation.getTotalPrice()).isEqualTo(expectedPrice);
        }

    }

}
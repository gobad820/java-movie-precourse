package cinema;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import cinema.Cinema.CinemaBuilder;
import java.util.Collections;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("영화관 테스트")
class CinemaTest {


    @Test
    @DisplayName("영화관 인스턴스 생성 테스트")
    public void cinemaCreationTest() throws Exception {
        // given
        Long code = 1L;
        String name = "메가박스";
        String mainPhoneNumber = "02-000-000";
        String cinemaOperator = "메가박스 홀딩스";
        String pageURL = "";
        String address = "서울특별시 강북구";

        // when
        var cinema = new CinemaBuilder().withCode(code).withName(name)
            .withMainPhoneNumber(mainPhoneNumber).withCinemaOperator(cinemaOperator)
            .withPageURL(pageURL).withAddress(address).withScreens(Collections.emptyList()).build();

        // then
        assertThat(cinema.getCode()).isEqualTo(code);
        assertThat(cinema.getAddress()).isEqualTo(address);
        assertThat(cinema.getCinemaOperator()).isEqualTo(cinemaOperator);
        assertThat(cinema.getScreens()).isEqualTo(Collections.emptyList());
        assertThat(cinema.getName()).isEqualTo(name);
        assertThat(cinema.getPageURL()).isEqualTo(pageURL);
        assertThat(cinema.getMainPhoneNumber()).isEqualTo(mainPhoneNumber);
    }

    @Nested
    @DisplayName("생성 시 파라미터 검증 테스트")
    class FieldValidation {

        @ParameterizedTest
        @DisplayName("영화관 코드 파라미터 검증 테스트")
        @NullSource
        @ValueSource(longs = {-1, -2}) // given
        public void cinemaCreationTestWithNullCinemaCode(Long invalidCode) {
            // when & then
            assertThatThrownBy(() -> {
                new CinemaBuilder().withCode(invalidCode).build();
            }).isInstanceOf(IllegalArgumentException.class).hasMessage("영화관 코드 번호는 필수값입니다.");
        }

        @ParameterizedTest
        @DisplayName("영화관 이름 파라미터 검증 테스트")
        @NullAndEmptySource
        @ValueSource(strings = {" ", "\t", "\n"})
        public void cinemaCreationTestWithNullAndEmptyName(String invalidName) {
            assertThatThrownBy(
                () -> new CinemaBuilder().withCode(1L).withName(invalidName).build()).isInstanceOf(
                IllegalArgumentException.class).hasMessage("영화관 이름은 필수값입니다.");
        }

        @ParameterizedTest
        @DisplayName("영화관 주소 파라미터 검증 테스트")
        @NullAndEmptySource
        @ValueSource(strings = {" ", "\t", "\n"})
        public void cinemaCreationTestWithNullAndEmptyAddress(String invalidAddress) {
            assertThatThrownBy(
                () -> new CinemaBuilder().withCode(1L).withName("메가박스").withAddress(invalidAddress)
                    .build()).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("영화관 주소는 필수값입니다.");
        }

    }

}
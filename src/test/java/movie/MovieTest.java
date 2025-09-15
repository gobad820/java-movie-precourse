package movie;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Collections;
import java.util.stream.Stream;
import movie.Movie.MovieBuilder;
import movie.enums.MovieGrade;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("영화 테스트")
public class MovieTest {

    @Test
    @DisplayName("영화 인스턴스 생성 테스트")
    void movieCreationTEst() {
        // given
        Long code = 1L;
        var director = "봉준호";
        var movieGrade = MovieGrade.FIFTEEN;
        var title = "기생충";
        var nation = "대한민국";
        var releaseDate = LocalDate.of(2018, 1, 1);
        var screenTime = Duration.ofHours(1).plusMinutes(30);
        var crankIn = LocalDate.of(2016, 1, 1);
        var crankUp = LocalDate.of(2017, 6, 30);

        // when
        var movie = new MovieBuilder().withCode(code).withDirector(director).withGrade(movieGrade)
            .withInformation(Collections.emptyList()).withNation(nation).withTitle(title)
            .withReleaseDate(releaseDate).withScreeningTypes(Collections.emptyList())
            .withScreenTime(screenTime).withCrankIn(crankIn).withCrankUp(crankUp).build();

        // then
        assertThat(movie.getCode()).isEqualTo(code);
        assertThat(movie.getNation()).isEqualTo(nation);
        assertThat(movie.getGrade()).isEqualTo(movieGrade);
        assertThat(movie.getDirector()).isEqualTo(director);
        assertThat(movie.getInformation()).isEqualTo(Collections.emptyList());
        assertThat(movie.getCrankIn()).isEqualTo(crankIn);
        assertThat(movie.getCrankUp()).isEqualTo(crankUp);
        assertThat(movie.getReleaseDate()).isEqualTo(releaseDate);
        assertThat(movie.getScreenTime()).isEqualTo(screenTime);
        assertThat(movie.getTitle()).isEqualTo(title);
        assertThat(movie.getScreeningTypes()).isEqualTo(Collections.emptyList());
    }

    @Nested
    @DisplayName("영화 생성자 검증 테스트")
    class MovieCreaterValidationTest {

        @ParameterizedTest
        @NullSource
        @ValueSource(longs = {-1, 0})// given
        @DisplayName("영화 코드 검증 테스트")
        void movieCreationTestWithInValidateCode(Long invalidateCode) {
            // when & Then
            assertThatThrownBy(
                () -> new MovieBuilder().withCode(invalidateCode).build()).isInstanceOf(
                IllegalArgumentException.class).hasMessage("영화 코드는 필수값입니다.");
        }

        @ParameterizedTest
        @NullSource
        @ValueSource(strings = {" ", "\t", "\n"})
        @DisplayName("영화 제목 검증 테스트")
        void movieCreationWithInvalidateTitle(String invalidateTitle) {
            // when & then
            assertThatThrownBy(
                () -> new MovieBuilder().withCode(1L).withTitle(invalidateTitle)
                    .build()).isInstanceOf(
                IllegalArgumentException.class).hasMessage("영화 제목은 필수값입니다.");
        }

        @ParameterizedTest
        @NullSource
        @ValueSource(strings = {" ", "\t", "\n"})
        @DisplayName("영화 제작 국가 검증 테스트")
        void movieCreationWithInvalidateNation(String invalidateNation) {
            assertThatThrownBy(() -> new MovieBuilder().withCode(1L).withTitle("기생충")
                .withNation(invalidateNation).build()).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("제작 국가는 필수값입니다.");

        }

        @ParameterizedTest
        @NullSource
        @ValueSource(strings = {" ", "\t", "\n"})
        @DisplayName("영화 감독 검증 테스트")
        void movieCreationWithInvalidateDirector(String invalidateDirector) {
            assertThatThrownBy(() -> new MovieBuilder().withCode(1L).withTitle("기생충")
                .withNation("대한민국").withDirector(invalidateDirector).build()).isInstanceOf(
                    IllegalArgumentException.class)
                .hasMessage("영화 감독은 필수값입니다.");

        }

        @ParameterizedTest
        @NullSource
        @DisplayName("영화 상영 시간 검증 테스트")
        void movieCreationWithNullScreenTime(
            Duration invalidateScreenTime) {
            assertThatThrownBy(
                () -> new MovieBuilder().withCode(1L).withTitle("기생충").withNation("대한민국")
                    .withDirector("봉준호").withScreenTime(invalidateScreenTime).build()).isInstanceOf(
                IllegalArgumentException.class).hasMessage("영화 상영 시간은 필수값입니다.");
        }

        @ParameterizedTest
        @MethodSource("invalidateDurationData")
        @DisplayName("영화 상영 시간 검증 테스트")
        void movieCreationWithInvalidateScreenTime(Duration invalidateScreenTime,
            String errorMessage) {
            assertThatThrownBy(
                () -> new MovieBuilder().withCode(1L).withTitle("기생충").withNation("대한민국")
                    .withDirector("봉준호").withScreenTime(invalidateScreenTime).build()).isInstanceOf(
                IllegalArgumentException.class).hasMessage(errorMessage);
        }

        public static Stream<Arguments> invalidateDurationData() {
            return Stream.of(
                Arguments.of(Duration.ZERO, "영화 상영 시간은 음수 또는 0일 수 없습니다."),
                Arguments.of(Duration.ofMinutes(-100), "영화 상영 시간은 음수 또는 0일 수 없습니다.")
            );
        }

    }
}

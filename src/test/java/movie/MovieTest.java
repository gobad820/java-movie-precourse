package movie;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Duration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MovieTest {

    @Test
    @DisplayName("영화 생성 테스트")
    public void movieCreationTest() throws Exception {
        // given
        String title = "기생충";
        Duration runningTime = Duration.ofHours(1).plusMinutes(30);

        // when
        Movie movie = new Movie(title, runningTime);

        // then
        assertThat(movie.getTitle()).isEqualTo(title);
        assertThat(movie.getRunningTime()).isEqualTo(runningTime);
    }
}
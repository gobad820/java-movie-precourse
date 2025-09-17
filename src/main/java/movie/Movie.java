package movie;

import java.time.Duration;

public class Movie {

    private String title;
    private Duration runningTime;

    public Movie(String title, Duration runningTime) {
        this.title = title;
        this.runningTime = runningTime;
    }


    public String getTitle() {
        return title;
    }

    public Duration getRunningTime() {
        return runningTime;
    }
}

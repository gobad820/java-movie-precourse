package screen.screening;

import java.util.List;
import java.util.NoSuchElementException;
import screen.Screen;

public class ScreeningSchedule {

    private final Long screenCode;
    private final List<Screen> screenings;

    public ScreeningSchedule(Long screenCode, List<Screen> screens) {
        this.screenCode = screenCode;
        this.screenings = screens;
    }

    public void addScreen(Screen screen) {
        screenings.add(screen);
    }

    public Screen retrieveScreenings(Screen screen) {
        return screenings.stream().filter((s) -> s.equals(screen)).findFirst()
            .orElseThrow(() -> new NoSuchElementException("없는 상영관입니다."));
    }

    public List<Screen> retrieveAllScreenings() {
        return screenings;
    }

    public void modifyScreen(Screen exScreen, Screen newScreen) {
        int index = screenings.indexOf(exScreen);
        if (index == -1) {
            return;
        }
        Screen existScreen = screenings.get(index);
        changeScreenFields(newScreen, existScreen); // TODO: DTO로 변경 요망
    }

    public Screen deleteScreen(Screen screen) {
        int index = screenings.indexOf(screen);
        if (index == -1) {
            throw new NoSuchElementException("없는 상영관입니다.");
        }
        Screen deletedScreening = screenings.get(index);
        screenings.remove(screen);
        return deletedScreening;
    }

    private static void changeScreenFields(Screen newScreen, Screen existScreen) {
        existScreen.setScreeningSchedule(newScreen.getScreeningSchedule());
        existScreen.setScreenPrice(newScreen.getScreenPrice());
        existScreen.setSeats(newScreen.getSeats());
        existScreen.setScreeningType(newScreen.getScreeningType());
        existScreen.setOperatingTime(newScreen.getOperatingTime());
    }


}

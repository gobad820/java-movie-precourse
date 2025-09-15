package screen;

import java.util.List;
import java.util.NoSuchElementException;
import screening.Screening;

public class ScreeningSchedule { // 상영관 계획표

    private final Long screenId; // 상영관 코드
    private final List<Screening> screenings; // 상영 목록

    public ScreeningSchedule(Long screenId, List<Screening> screenings) {
        this.screenId = screenId;
        this.screenings = screenings;
    }

    public void addScreen(Screening screening) {
        for (Screening s : screenings) {
            if (validateScreeningTime(screening, s)) {
                return;
            }
        }
        screenings.add(screening);
    }

    private static boolean validateScreeningTime(Screening screening, Screening s) {
        if (s.getStartTime().isBefore(screening.getStartTime())) {
            return true;
        }
        return s.getEndTime().isAfter(screening.getEndTime());
    }

    public Screening retrieveScreenings(Screen screen) {
        return screenings.stream().filter((s) -> s.equals(screen)).findFirst()
            .orElseThrow(() -> new NoSuchElementException("없는 상영관입니다."));
    }

    public List<Screening> retrieveAllScreenings() {
        return screenings;
    }

    public void modifyScreen(Screen exScreening, Screening newScreening) {
        int index = screenings.indexOf(exScreening);
        if (index == -1) {
            return;
        }
        Screening existScreening = screenings.get(index);
        changeScreenFields(newScreening, existScreening); // TODO: DTO로 변경 요망
    }

    public Screening deleteScreen(Screen screen) {
        int index = screenings.indexOf(screen);
        if (index == -1) {
            throw new NoSuchElementException("없는 상영관입니다.");
        }
        Screening deletedScreening = screenings.get(index);
        screenings.remove(screen);
        return deletedScreening;
    }

    private static void changeScreenFields(Screening newScreening, Screening existScreening) {
        existScreening.setMovie(newScreening.getMovie());
        existScreening.setPrice(newScreening.getPrice());
        existScreening.setStartTime(newScreening.getStartTime());
        existScreening.setEndTime(newScreening.getEndTime());
        existScreening.setSeatReserved(newScreening.getSeatReserved());
    }


}

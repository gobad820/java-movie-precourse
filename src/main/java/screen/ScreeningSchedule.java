package screen;

import java.util.Map;
import java.util.NoSuchElementException;
import screening.Screening;

public class ScreeningSchedule { // 상영관 계획표

    private final Long screenId; // 상영관 코드
    private final Map<Long, Screening> screenings; // 상영 목록
    private Long screeningId = 0L;

    public ScreeningSchedule(Long screenId, Map<Long, Screening> screenings) {
        this.screenId = screenId;
        this.screenings = screenings;
    }

    public void addScreen(Screening screening) {
        for (Map.Entry<Long, Screening> entry : screenings.entrySet()) {
            Screening selectedScreening = entry.getValue();
            if (validateScreeningTime(selectedScreening, screening)) {
                screenings.put(++screeningId, screening);
            }
        }
    }

    public Screening retrieveScreening(Long screeningId) {
        if(!screenings.containsKey(screeningId)){
            throw new RuntimeException("해당 상영 ID는 없는 Id입니다.");
        }
        return screenings.get(screeningId);
    }


    public Screening deleteScreen(Long screeningId) {
        Screening deletedScreening = retrieveScreening(screeningId);
        screenings.remove(screeningId);
        return deletedScreening;
    }

    private static boolean validateScreeningTime(Screening existScreening, Screening screening) {
        // 기존 영화와 겹치는 경우
        // 이미 시작한 기존 영화가 끝나기 전에 추가하려는 영화가 시작하거나
        if (existScreening.getStartTime().isBefore(screening.getStartTime())
            && existScreening.getEndTime().isAfter(screening.getStartTime())) {
            return false;
        }
        // 이미 시작한 새로운 영화가 기존 영화가 시작하기 전에 끝나지 않는다면
        // 새로운 영화의 Endtime
        if (screening.getStartTime().isBefore(existScreening.getStartTime())
            && screening.getEndTime().isAfter(existScreening.getStartTime())) {
            return false;
        }
        return true;
    }

    private static void changeScreenFields(Screening newScreening, Screening existScreening) {
        existScreening.setMovie(newScreening.getMovie());
        existScreening.setPrice(newScreening.getPrice());
        existScreening.setStartTime(newScreening.getStartTime());
        existScreening.setEndTime(newScreening.getEndTime());
        existScreening.setSeatReserved(newScreening.getSeatReserved());
    }


}

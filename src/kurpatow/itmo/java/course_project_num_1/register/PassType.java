package kurpatow.itmo.java.course_project_num_1.register;

import java.time.LocalTime;

public enum PassType {
    ONE_PASS (LocalTime.of(8 , 0),
            LocalTime.of(22,0),
            "Разовый абонемент"),
    DAY_PASS (LocalTime.of(8,0),
            LocalTime.of(16,0),
            "Дневной абонемент"),
    FULL_PASS (LocalTime.of(8,0),
            LocalTime.of(22,0),
            "Полный абонемент");

    private final LocalTime startWorkTime, endWorkTime;
    private final String passName;

    PassType(LocalTime startWorkTime, LocalTime endWorkTime, String passName) {
        this.startWorkTime = startWorkTime;
        this.endWorkTime = endWorkTime;
        this.passName = passName;
    }
    public LocalTime getStartWorkTime() {
        return startWorkTime;
    }
    public LocalTime getEndWorkTime() {
        return endWorkTime;
    }

    public String getPassName() {return passName;}
}

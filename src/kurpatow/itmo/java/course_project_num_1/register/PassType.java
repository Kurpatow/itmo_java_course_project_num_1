package kurpatow.itmo.java.course_project_num_1.register;

import java.time.LocalTime;

public enum PassType {

    ONE_PASS (LocalTime.of(8 , 0),
            LocalTime.of(22,0),
            "Разовый абонемент", new SportZone[] {SportZone.SWIMMING_POOL, SportZone.GYM}),
    DAY_PASS (LocalTime.of(8,0),
            LocalTime.of(16,0),
            "Дневной абонемент", new SportZone[] {SportZone.GROUP_SPORTS, SportZone.GYM}),
    FULL_PASS (LocalTime.of(8,0),
            LocalTime.of(22,0),
            "Полный абонемент", new SportZone[] {SportZone.SWIMMING_POOL, SportZone.GROUP_SPORTS
    , SportZone.GYM});

    private final LocalTime startWorkTime, endWorkTime;
    private final String passName;
    private final SportZone[] validMember;


    PassType(LocalTime startWorkTime, LocalTime endWorkTime, String passName, SportZone[] validMember) {
        this.startWorkTime = startWorkTime;
        this.endWorkTime = endWorkTime;
        this.passName = passName;
        this.validMember = validMember;
    }
    public LocalTime getStartWorkTime() {return startWorkTime;}
    public LocalTime getEndWorkTime() {return endWorkTime;}
    public String getPassName() {return passName;}

    // Проверяем, есть доступ в зону по абонементу или нет
    public boolean isAccess (SportZone sportZone ) {
        for (SportZone sportZone1 : validMember) {
            if (sportZone.equals(sportZone1)) {
                return true;
            }
        }
        return false;
    }
}

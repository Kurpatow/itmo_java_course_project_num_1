package kurpatow.itmo.java.course_project_num_1.register;


import kurpatow.itmo.java.course_project_num_1.sportClub.Members;

import java.time.LocalTime;

public enum PassType {

    ONE_PASS (LocalTime.of(8 , 0),
            LocalTime.of(22,0),
            "Разовый абонемент", new SportZones[] {SportZones.SWIMMING_POOL, SportZones.GYM}),
    DAY_PASS (LocalTime.of(8,0),
            LocalTime.of(16,0),
            "Дневной абонемент", new SportZones[] {SportZones.GROUP_SPORTS, SportZones.GYM}),
    FULL_PASS (LocalTime.of(8,0),
            LocalTime.of(22,0),
            "Полный абонемент", new SportZones[] {SportZones.SWIMMING_POOL, SportZones.GROUP_SPORTS
    , SportZones.GYM});

    private final LocalTime startWorkTime, endWorkTime;
    private final String passName;
    private final SportZones[] validMember;
    private final SportZones sportClubZoneName;

    PassType(LocalTime startWorkTime, LocalTime endWorkTime, String passName, SportZones[] validMember) {
        this.startWorkTime = startWorkTime;
        this.endWorkTime = endWorkTime;
        this.passName = passName;
        this.validMember = new SportZones[validMember.length];
        System.arraycopy(validMember, 0, this.validMember, 0, validMember.length);
        this.sportClubZoneName = SportZones.getSportZonesByZoneName(passName);
    }
    public LocalTime getStartWorkTime() {return startWorkTime;}
    public LocalTime getEndWorkTime() {return endWorkTime;}
    public String getPassName() {return passName;}

    // Проверяем, есть доступ в зону по абонементу или нет
    public boolean isAccess (Members members) {
        for (PassType passType : validMember) {
            if (passType.equals(members.getPassType())) {
                return true;
            }
        }
        return false;
    }
}

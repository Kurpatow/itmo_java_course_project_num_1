package kurpatow.itmo.java.course_project_num_1.register;

import  kurpatow.itmo.java.course_project_num_1.sportClub.Members;

import java.util.Locale;

public enum SportZones {
    SWIMMING_POOL (new PassType[] {PassType.ONE_PASS, PassType.FULL_PASS},
            "Зона: Бассейн"),
    GROUP_SPORTS (new PassType[] {PassType.DAY_PASS, PassType.FULL_PASS},
            "Зона: Групповые спортивные занятия"),
    GYM (new PassType[] {PassType.ONE_PASS, PassType.DAY_PASS, PassType.FULL_PASS},
            "Зона: Тренажерный зал");

    private PassType[] validMember;
    private String sportClubZoneName;

    SportZones(PassType[] validMember, String sportClubZoneName) {
        this.validMember = new PassType[validMember.length];
        for (int i = 0; i < validMember.length; i++) {
            this.validMember[i] = validMember[i];
        }
        this.sportClubZoneName = sportClubZoneName;
    }
    public String getSportClubZoneName() {
        return sportClubZoneName;
    }
    public static SportZones getSportZonesByZoneName(String sportClubZoneName) {
        for (SportZones zone : SportZones.values()) {
            if (sportClubZoneName.toLowerCase().equals(zone.sportClubZoneName.toLowerCase())) {
                return zone;
            }
        }
        return null;
    }
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

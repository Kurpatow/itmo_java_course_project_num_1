package kurpatow.itmo.java.course_project_num_1.register;

import  kurpatow.itmo.java.course_project_num_1.sportClub.Members;


public enum SportZones {
    SWIMMING_POOL (new PassType[] {PassType.ONE_PASS, PassType.FULL_PASS},
            "Бассейн"),
    GROUP_SPORTS (new PassType[] {PassType.DAY_PASS, PassType.FULL_PASS},
            "Групповые спортивные занятия"),
    GYM (new PassType[] {PassType.ONE_PASS, PassType.DAY_PASS, PassType.FULL_PASS},
            "Тренажерный зал");

    private final PassType[] validMember;
    private final String sportClubZoneName;

    SportZones(PassType[] validMember, String sportClubZoneName) {
        this.validMember = new PassType[validMember.length];
        System.arraycopy(validMember, 0, this.validMember, 0, validMember.length);
        this.sportClubZoneName = sportClubZoneName;
    }
    public String getSportClubZoneName() {
        return sportClubZoneName;
    }
    public static SportZones getSportZonesByZoneName(String sportClubZoneName) {
        for (SportZones zone : SportZones.values()) {
            if (sportClubZoneName.equalsIgnoreCase(zone.sportClubZoneName)) {
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

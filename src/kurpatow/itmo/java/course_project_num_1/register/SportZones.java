package kurpatow.itmo.java.course_project_num_1.register;

import kurpatow.itmo.java.course_project_num_1.register.PassType;
import kurpatow.itmo.java.course_project_num_1.sportClub.Members;


public enum SportZones {
    SWIMMING_POOL ("Бассейн"),
    GROUP_SPORTS ("Групповые спортивные занятия"),
    GYM ("Тренажерный зал");

    private final String sportClubZoneName;

    SportZones(String sportClubZoneName) {
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

}

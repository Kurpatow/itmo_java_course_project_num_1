package kurpatow.itmo.java.course_project_num_1.register;


public enum SportZone {
    SWIMMING_POOL ("Бассейн"),
    GROUP_SPORTS ("Групповые спортивные занятия"),
    GYM ("Тренажерный зал");

    private final String sportClubZoneName;

    SportZone(String sportClubZoneName) {
        this.sportClubZoneName = sportClubZoneName;
    }
    public String getSportClubZoneName() {
        return sportClubZoneName;
    }
    public static SportZone getSportZonesByZoneName(String sportClubZoneName) {
        for (SportZone zone : SportZone.values()) {
            if (sportClubZoneName.equalsIgnoreCase(zone.sportClubZoneName)) {
                return zone;
            }
        }
        return null;
    }

}

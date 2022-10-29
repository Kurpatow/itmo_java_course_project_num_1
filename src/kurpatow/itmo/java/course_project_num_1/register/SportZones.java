package kurpatow.itmo.java.course_project_num_1.register;

import  kurpatow.itmo.java.course_project_num_1.sportClub.Members;

public enum SportZones {
    SWIMMING_POOL (new PassType[] {PassType.ONE_PASS, PassType.FULL_PASS}, "Зона: Бассейн"),
    GROUP_SPORTS (new PassType[] {PassType.DAY_PASS, PassType.FULL_PASS}, "Зона: Групповые спортивные занятия"),
    GYM (new PassType[] {PassType.ONE_PASS, PassType.DAY_PASS, PassType.FULL_PASS}, "Зона: Тренажерный зал");

}

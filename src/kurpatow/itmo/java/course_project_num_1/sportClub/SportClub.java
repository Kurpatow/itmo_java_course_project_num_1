package kurpatow.itmo.java.course_project_num_1.sportClub;

import kurpatow.itmo.java.course_project_num_1.register.SportZones;
import java.time.LocalTime;
import java.time.LocalDate;
import java.util.Locale;

public class SportClub {

    private Members[] swimmingPollMembers;
    private Members[] groupSportsMembers;
    private Members[] gymMembers;
    
    private LocalDate workingDate;

    public SportClub() {
        clubActivation();
    }
    private void setWorkingDate(LocalDate workingDate) {
        if (workingDate == null) throw new IllegalArgumentException(
                "Ошибка! Необходимо указать корректную дату начала работы!");
    }
    private void clubActivation() {
        workingDate = null;
        swimmingPollMembers = new Members[20];
        groupSportsMembers = new Members[20];
        gymMembers = new Members[20];
    }
    public void startWorking(LocalDate workingDate) {
        System.out.println("Начало рабочего дня! " + "Дата: " + workingDate);
        setWorkingDate(workingDate);
    }
    public void endWorking() {
        System.out.println("Конец рабочего дня! " + "Дата: " + workingDate);
        clubActivation();
    }
    private boolean isSportClubWorking() {
        return workingDate != null;
    }
    private boolean isTrainingTimeCorrect(Members members, LocalTime trainingTime) {
        return trainingTime.isAfter(members.getPassType().getStartWorkTime()) &&
                trainingTime.isBefore(members.getPassType().getEndWorkTime());
    }
    private boolean isZoneAccessAllowed(SportZones sportZones, Members members) {
        return sportZones.isAccess(members);
    }
    private boolean  isTrainingZoneFull(Members[] trainingZone) {
        for (Members members : trainingZone) {
            if (members == null)
            return  false;
        }
        return true;
    }
    private boolean isMemberInZone(Members members, Members[] sportZoneMembers) {
        for (Members sportZoneMember : sportZoneMembers) {
            if (sportZoneMember == null) break;
            if (sportZoneMember.equals(members)) return true;
        }
        return false;
    }
    private void addTrainingZone(
            SportZones sportZones, Members members, LocalDate trainingDate, LocalTime trainingTime) {
        Members[] trainingZone;
        if (sportZones.equals(SportZones.SWIMMING_POOL)) trainingZone = swimmingPollMembers;
        else if (sportZones.equals(SportZones.GROUP_SPORTS)) trainingZone = groupSportsMembers;
        else trainingZone = gymMembers;

        for (int i = 0; i < trainingZone.length; i++) {
            if (trainingZone[i] == null) {
                trainingZone[i] = members;
                System.out.printf("Клиент: %s %s. Зона тренировки: %s",
                        members.getFirstName(),
                        members.getSecondName(),
                        sportZones.getSportClubZoneName().toLowerCase());
                System.out.println("Дата и время начала тренировки: " + trainingDate + "," + trainingTime);
                break;
            }

        }
    }
}

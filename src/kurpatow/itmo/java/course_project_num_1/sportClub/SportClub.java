package kurpatow.itmo.java.course_project_num_1.sportClub;

import kurpatow.itmo.java.course_project_num_1.register.SportZones;
import java.time.LocalTime;
import java.time.LocalDate;


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
        System.out.println("Конец рабочего дня! " + "Дата: " + workingDate); // что-то не так
        clubActivation();
    }
    private boolean isSportClubWorking() {
        return workingDate != null;
    }
    private boolean isTrainingTimeCorrect(Members members, LocalTime trainingTime) {
        return trainingTime.isAfter(members.getPassType().getStartWorkTime()) &&
                trainingTime.isBefore(members.getPassType().getEndWorkTime());
    }
    private boolean isMembersActive(Members members) {
        return members.getEndWorkTime().isAfter(workingDate);
    }

    private boolean isZoneAccessAllowed(SportZones sportZones, Members members) {
        return sportZones.isAccess(members);
    }
    private boolean  isTrainingZoneFull(Members[] trainingSportZone) {
        for (Members members : trainingSportZone) {
            if (members == null)
            return true;
        }
        return false;
    }
    private boolean isMemberInZone(Members members, Members[] sportZoneMembers) {
        for (Members sportZoneMember : sportZoneMembers) {
            if (sportZoneMember == null) break;
            if (sportZoneMember.equals(members)) return false;
        }
        return true;
    }
    private void addTrainingZone(
            SportZones sportZones, Members members, LocalDate trainingDate, LocalTime trainingTime) {
        Members[] trainingSportZone;
        if (sportZones.equals(SportZones.SWIMMING_POOL)) trainingSportZone = swimmingPollMembers;
        else if (sportZones.equals(SportZones.GROUP_SPORTS)) trainingSportZone = groupSportsMembers;
        else trainingSportZone = gymMembers;

        for (int i = 0; i < trainingSportZone.length; i++) {
            if (trainingSportZone[i] == null) {
                trainingSportZone[i] = members;
                System.out.printf("Клиент: %s %s. Зона тренировки: %s",
                        members.getFirstName(),
                        members.getSecondName(),
                        sportZones.getSportClubZoneName().toLowerCase());
                System.out.println("Дата и время начала тренировки: " + trainingDate + "," + trainingTime);
                break;
            }
        }
    }
    public void doTraining(Members members, String trainingSportZone, LocalTime trainingTime) {
        if (members == null) throw new IllegalArgumentException("Ошибка! Абонемент недействителен.");
        if (trainingSportZone == null) throw new IllegalArgumentException("Ошибка! Выбрана недоступная зона тренировки");
        if (trainingTime == null) throw new IllegalArgumentException(
                "Ошибка! Выбранное вами время тренировки не предусмотрено предоставленным абонементом");

        if (!isSportClubWorking()) {
            System.out.println("Спортивный клуб закрыт. Приходите в рабочее время нашего клуба:)");
            return;
        }
        SportZones sportZones = SportZones.getSportZonesByZoneName(trainingSportZone);
        if (!isMembersActive(members)) {
            System.out.println(
                    "Срок действия абонемента закончился: " + members.getEndWorkTime() + ". Оформите продление абонемента.");
            return;
        }
        if (isTrainingTimeCorrect(members, trainingTime)) {
            System.out.println("Абонемент не предусматривает посещение зала в данное время.");
            return;
        }
        assert sportZones != null;
        if (!isZoneAccessAllowed(sportZones, members)) {
            System.out.println("Тип вашего абонемента: " + members.getPassType().getPassName() +
                    "В данном типе абонемента не предусмотренно посещение зоны - " + sportZones.getSportClubZoneName());
            return;
        }
        if (isMemberInZone(members, swimmingPollMembers) &&
                isMemberInZone(members, gymMembers) &&
                isMemberInZone(members, gymMembers)) {
            System.out.println("Ошибка! Нельзя посетить одновременно две зоны тренировок.");
            return;
        }
        if (sportZones.equals(SportZones.SWIMMING_POOL) && isTrainingZoneFull(swimmingPollMembers)) {

            addTrainingZone(SportZones.SWIMMING_POOL, members, workingDate, trainingTime);
            System.out.println("Отправляйтесь на тренировку в бассейн.");

        }else if (sportZones.equals(SportZones.GROUP_SPORTS) && isTrainingZoneFull(groupSportsMembers)) {

            addTrainingZone(SportZones.GROUP_SPORTS, members, workingDate, trainingTime);
            System.out.println("Отправляйтесь на групповую тренировку.");

            }else if (sportZones.equals(SportZones.GYM) && isTrainingZoneFull(gymMembers)) {

            addTrainingZone(SportZones.GYM, members, workingDate, trainingTime);
            System.out.println("Отправляйтесь на тренировку в тренажерный зал.");

            }else{
            System.out.println("Ошибка! В выбранной зоне тренировок сейчас нет свободных мест.");
        }
    }
    private void trainingSportZoneInfo(SportZones sportZones) {
        Members[] trainingSportZone;

        if (sportZones.equals(SportZones.SWIMMING_POOL)) trainingSportZone = swimmingPollMembers;
        else if (sportZones.equals(SportZones.GROUP_SPORTS)) trainingSportZone = groupSportsMembers;
        else trainingSportZone = gymMembers;
        System.out.println("Зона тренировки: " + sportZones.getSportClubZoneName());

        int memberCount = 0;
        for (Members members: trainingSportZone) {
            if (members != null) {
                memberCount++;
                System.out.printf("Клиент %d. %s %s. Год рождения: %d. Абонемент %s, срок действия до %s \n",
                        memberCount,
                        members.getFirstName(),
                        members.getSecondName(),
                        members.getDateOfBirth(),
                        members.getPassType().getPassName(),
                        members.getEndWorkTime());
            }
        }
        if (memberCount == 0)
            System.out.println("Посетителей нет");
    }
    public void sportClubMembersInfo() {
        trainingSportZoneInfo(SportZones.SWIMMING_POOL);
        trainingSportZoneInfo(SportZones.GROUP_SPORTS);
        trainingSportZoneInfo(SportZones.GYM);
    }
}

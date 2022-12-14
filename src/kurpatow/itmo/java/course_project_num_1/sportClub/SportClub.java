package kurpatow.itmo.java.course_project_num_1.sportClub;

import kurpatow.itmo.java.course_project_num_1.register.SportZone;
import java.time.LocalTime;
import java.time.LocalDate;

public class SportClub {

    private Member[] swimmingPollMembers;
    private Member[] groupSportsMembers;
    private Member[] gymMembers;

    private LocalDate workingDate;

    public SportClub() {
        clubActivation();
    }

    private void setWorkingDate(LocalDate workingDate) {
        if (workingDate == null) throw new IllegalArgumentException(
                "Ошибка! Необходимо указать корректную дату начала работы!");
        this.workingDate = workingDate;
    }
    private void clubActivation() {
        workingDate = null;
        swimmingPollMembers = new Member[20];
        groupSportsMembers = new Member[20];
        gymMembers = new Member[20];
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
    private boolean isTrainingTimeCorrect(Member members, LocalTime trainingTime) {
        return trainingTime.isAfter(members.getPassType().getStartWorkTime()) &&
                trainingTime.isBefore(members.getPassType().getEndWorkTime());
    }
    private boolean isMembersActive(Member members) {
        return members.getEndWorkTime().isAfter(workingDate);
    }

    private boolean isTrainingZoneFull(Member[] trainingSportZone) {
        for (Member members : trainingSportZone) {
            if (members == null)
            return true;
        }
        return false;
    }
    private boolean isMemberInZone(Member members, Member[] sportZoneMembers) {
        for (Member sportZoneMember : sportZoneMembers) {
            if (sportZoneMember == null) break;
            if (sportZoneMember.equals(members)) return false;
        }
        return true;
    }
    private void addTrainingZone(Member member, Member[] trainingSportZone, LocalDate trainingDate, LocalTime trainingTime) {
        for (int i = 0; i < trainingSportZone.length; i++) {
            if (trainingSportZone[i] == null) {
                trainingSportZone[i] = member;
                System.out.print("Клиент: " + member.getClient().getFullName() + ". ");
                System.out.println("Дата и время начала тренировки: " + trainingDate + " " + trainingTime);
                break;
            }
        }
    }
    public void doTraining(Member members, String trainingSportZone, LocalTime trainingTime) {
        if (members == null) throw new IllegalArgumentException("Ошибка! Абонемент недействителен.");
        if (trainingSportZone == null) throw new IllegalArgumentException("Ошибка! Выбрана недоступная зона тренировки.");
        if (trainingTime == null) throw new IllegalArgumentException(
                "Ошибка! Выбранное вами время тренировки не предусмотрено предоставленным абонементом.");
        if (!isSportClubWorking()) {
            System.out.println("Спортивный клуб закрыт. Приходите в рабочее время нашего клуба:)");
            return;
        }

        SportZone sportZones = SportZone.getSportZonesByZoneName(trainingSportZone);
        if (!isMembersActive(members)) {
            System.out.println(
                    "Срок действия абонемента закончился: " + members.getEndWorkTime() + ". Оформите продление абонемента!!!");
            return;
        }
        if (!isTrainingTimeCorrect(members, trainingTime)) {
            System.out.println("Абонемент не предусматривает посещение зала в данное время.");
            return;
        }
        assert members.getPassType() != null;
        if (!members.getPassType().isAccess(sportZones)) {
            System.out.println("Тип вашего абонемента: " + members.getPassType().getPassName() +
                    ". В данном типе абонемента не предусмотренно посещение зоны - " + sportZones.getSportClubZoneName());
            return;
        }
        if (!isMemberInZone(members, swimmingPollMembers) &&
                isMemberInZone(members, gymMembers) &&
                isMemberInZone(members, gymMembers)) {
            System.out.println("Ошибка! Нельзя посетить одновременно две зоны тренировок.");
            return;
        }
        if (sportZones.equals(SportZone.SWIMMING_POOL) && isTrainingZoneFull(swimmingPollMembers)) {

            addTrainingZone
                    (members, swimmingPollMembers, workingDate, trainingTime);
            System.out.println("Отправляйтесь на тренировку в бассейн.");

        } else if (sportZones.equals(SportZone.GROUP_SPORTS) && isTrainingZoneFull(groupSportsMembers)) {

            addTrainingZone(members, groupSportsMembers, workingDate, trainingTime);
            System.out.println("Отправляйтесь на групповую тренировку.");

            } else if (sportZones.equals(SportZone.GYM) && isTrainingZoneFull(gymMembers)) {

            addTrainingZone(members, gymMembers, workingDate, trainingTime);
            System.out.println("Отправляйтесь на тренировку в тренажерный зал.");

            }else{
            System.out.println("Ошибка! В выбранной зоне тренировок сейчас нет свободных мест.");
        }
    }
    private void trainingSportZoneInfo() {
        for (int i = 0; i < swimmingPollMembers.length; i++) {
            if (swimmingPollMembers[i] != null ) {
                System.out.printf("Клиент: %s. Год рождения: %d. Абонемент: %s. Срок действия до: %s  \n",
                        swimmingPollMembers[i].getClient().getFullName(),
                        swimmingPollMembers[i].getClient().getAge(),
                        swimmingPollMembers[i].getPassType().getPassName(),
                        swimmingPollMembers[i].getEndWorkTime());
            }
            if (groupSportsMembers[i] != null) {
                System.out.printf("Клиент: %s. Год рождения: %d. Абонемент: %s. Срок действия до: %s  \n",
                        groupSportsMembers[i].getClient().getFullName(),
                        groupSportsMembers[i].getClient().getAge(),
                        groupSportsMembers[i].getPassType().getPassName(),
                        groupSportsMembers[i].getEndWorkTime());
            }
            if (gymMembers[i] != null) {
                System.out.printf("Клиент: %s. Год рождения: %d. Абонемент: %s. Срок действия до: %s  \n",
                        gymMembers[i].getClient().getFullName(),
                        gymMembers[i].getClient().getAge(),
                        gymMembers[i].getPassType().getPassName(),
                        gymMembers[i].getEndWorkTime());
            }
        }
    }
    public void sportClubMembersInfo() {trainingSportZoneInfo();}
}

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
        System.out.println("Конец рабочего дня! " + "Дата: " + workingDate);
        clubActivation();
    }
    private boolean sportClubWorking() {
        return workingDate != null;
    }
}

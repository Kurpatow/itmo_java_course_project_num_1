package kurpatow.itmo.java.course_project_num_1.sportClub;

import kurpatow.itmo.java.course_project_num_1.register.Client;
import kurpatow.itmo.java.course_project_num_1.register.PassType;

import java.time.LocalDate;

public class Member{

    private Client client;
    public Client getClient() {return client;}
    public void setClient(Client client) {this.client = client;}

    private PassType passType;
    private final LocalDate startWorkTime;
    private LocalDate endWorkTime;

    public Member(String firstName, String secondName, int dateOfBirth, String passType, int daysDuration) {


        setPassType(passType);
        this.startWorkTime = LocalDate.now();
        setEndWorkTime(daysDuration);
        System.out.println("Абонемент создан!");
    }

    public void setPassType(String passType) {
        if (passType == null) throw new IllegalArgumentException("Ошибка! Не задан тип абонемента");
        for (PassType sportType : PassType.values()) {
            if (sportType.getPassName().equalsIgnoreCase(passType)) {
                this.passType = sportType;
            }
        }
        if (this.passType == null)
            throw new IllegalArgumentException("Ошибка! Данного вида абонемента не существует.");
    }

    public void setEndWorkTime(int dayDuration) {
        if (dayDuration < 1) throw new IllegalArgumentException("Ошибка! Минимальный срок действия абонемента - один день");
        if (this.passType.equals(PassType.ONE_PASS) && dayDuration != 1) {
            System.out.println(
                    "ВНИМАНИЕ Минимальный срок Разового абонемента составляет один день. Количество выбранных дней исправлено на один день!");
            this.endWorkTime = this.startWorkTime.plusDays(1);
        } else {
            this.endWorkTime = this.startWorkTime.plusDays(dayDuration);
        }
    }
    public PassType getPassType() {return passType;}
    public LocalDate getEndWorkTime() {return endWorkTime;}
}
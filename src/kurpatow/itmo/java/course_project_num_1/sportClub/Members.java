package kurpatow.itmo.java.course_project_num_1.sportClub;

import kurpatow.itmo.java.course_project_num_1.register.PassType;
import java.time.LocalDate;

public class Members {

    private String firstName;
    private String secondName;
    private int dateOfBirth;
    private PassType passType;
    private final LocalDate startWorkTime;
    private LocalDate endWorkTime;

    public Members(String firstName, String secondName, int dateOfBirth, String passType, int daysDuration) {
        setFirstName(firstName);
        setSecondName(secondName);
        setDateOfBirth(dateOfBirth);
        setPassType(passType);
        this.startWorkTime = LocalDate.now();
        setEndWorkTime(daysDuration);
        System.out.println("Абонемент создан!");
    }
    public void setFirstName(String firstName) {
        if (firstName.length() < 3)
            throw new IllegalArgumentException(
                    "Ошибка! При регистрации абонемента ИМЯ держателя карты должно состоять минимум из трёх букв");
        this.firstName = firstName;
    }

    public void setSecondName(String secondName) {
        if (secondName.length() < 3)
            throw new IllegalArgumentException(
                    "Ошибка! При регистрации абонемента ФАМИЛИЯ держателя карты должна состоять минимум из трёх букв");
        this.secondName = secondName;
    }

    public void setDateOfBirth(int dateOfBirth) {
        int currentYear = LocalDate.now().getYear();
        if (currentYear - dateOfBirth > 80 || currentYear - dateOfBirth < 0)
            throw new IllegalArgumentException("Ошибка! Проверьте корректно ли введен год рождения.");
        this.dateOfBirth = dateOfBirth;
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

    public String getFirstName() {return firstName;}
    public String getSecondName() {return secondName;}
    public int getDateOfBirth() {return dateOfBirth;}
    public PassType getPassType() {return passType;}
    public LocalDate getEndWorkTime() {return endWorkTime;}
}

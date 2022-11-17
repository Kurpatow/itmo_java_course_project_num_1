package kurpatow.itmo.java.course_project_num_1.register;

import java.time.LocalDate;

public class Client {
    private String firstName;
    private String secondName;
    private int dateOfBirth;

    public Client setFirstName (String firstName){
        if (firstName.length() < 3)
            throw new IllegalArgumentException(
                    "Ошибка! При регистрации абонемента ИМЯ держателя карты должно состоять минимум из трёх букв");
        this.firstName = firstName;
        return null;
    }

    public void setSecondName (String secondName){
        if (secondName.length() < 3)
            throw new IllegalArgumentException(
                    "Ошибка! При регистрации абонемента ФАМИЛИЯ держателя карты должна состоять минимум из трёх букв");
        this.secondName = secondName;
    }

    public void setDateOfBirth ( int dateOfBirth){
        int currentYear = LocalDate.now().getYear();
        if (currentYear - dateOfBirth > 80 || currentYear - dateOfBirth < 0)
            throw new IllegalArgumentException("Ошибка! Проверьте корректно ли введен год рождения.");
        this.dateOfBirth = dateOfBirth;
    }

    public int getDateOfBirth () {return dateOfBirth;}
    public String getSecondName () {return secondName;}
    public String getFirstName () {return firstName;}
    }
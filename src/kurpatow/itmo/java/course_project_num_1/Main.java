package kurpatow.itmo.java.course_project_num_1;

import kurpatow.itmo.java.course_project_num_1.sportClub.Members;
import kurpatow.itmo.java.course_project_num_1.sportClub.SportClub;

import java.time.LocalTime;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        Members subscription1 = new Members("Екатерина", "Барсукова", 1997, "Полный абонемент", 17);
        Members subscription2 = new Members("Роберт", "Марутов", 1999, "Разовый абонемент", 1);
        Members subscription3 = new Members("Михаил", "Васильев",1998, "Полный абонемент", 20);
        Members subscription4 = new Members("Алина","Резвова",1995,"Дневной абонемент",13);
        Members subscription5 = new Members("Дмитрий","Ковязин",1992,"Разовый абонемент", 3);//Проверка. 1 день в разовом абонементе

        SportClub sportPlus = new SportClub();

        sportPlus.doTraining(subscription1,"Бассейн",LocalTime.of(6, 0)); //Закрыто. Проверка
        sportPlus.startWorking(LocalDate.now().plusDays(2)); //Открытие зала
        sportPlus.doTraining(subscription1,"Бассейн", LocalTime.of(14,20));
        sportPlus.doTraining(subscription2, "Бассейн",LocalTime.of(15,20));
        sportPlus.doTraining(subscription3, "Групповые спортивные занятия", LocalTime.of(16,20));
        sportPlus.doTraining(subscription4, "Тренажерный зал", LocalTime.of(16,20));
        sportPlus.doTraining(subscription5, "Групповые спортивные занятия", LocalTime.of(16,30));
        sportPlus.doTraining(subscription5,"Тренажерный зал", LocalTime.of(16,31)); //Проверка. Нельзя попасть на два занятия сразу.

        sportPlus.sportClubMembersInfo();//Проверка на количество человек в залах

        sportPlus.endWorking(); //Закрытие зала
        sportPlus.sportClubMembersInfo(); //Никого не осталось после закрытия
    }
}

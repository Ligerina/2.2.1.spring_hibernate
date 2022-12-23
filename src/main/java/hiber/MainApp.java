package hiber;

import hiber.config.AppConfig;
import hiber.model.*;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      //Создаем пользователей. Первые 2 будут с машинами, а остальные без
//      User user = new User("Anton3","Suhankin3","anto1.sukhankin3@mail.ru");
//      Car car = new Car("newModel3",123);
//      user.setCar(car);
//      userService.add(user);
//
//      User user2 = new User("Saul3","Goodman3","Tsaul3.good@man.ru");
//      Car car2 = new Car("Mers3",111);
//      user2.setCar(car2);
//      userService.add(user2);
//
//      userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
//      userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
//      userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
//      userService.add(new User("User4", "Lastname4", "user4@mail.ru"));

      //Выведем всех пользователей
      List<User> users = userService.listUsers();
      for (User user1 : users) {
         System.out.println("Id = "+user1.getId());
         System.out.println("First Name = "+user1.getFirstName());
         System.out.println("Last Name = "+user1.getLastName());
         System.out.println("Email = "+user1.getEmail());
         System.out.println();
      }

      System.out.println("--------");

      //Выведем все машины
      List<Car> cars = userService.listCars();
      for(Car car1 : cars){
         System.out.println("Id = " + car1.getId());
         System.out.println("Model name = " + car1.getModel());
         System.out.println("Series name = " + car1.getSeries());
      }

      //Найдем пользователя по модели и серии машины + выведем его
      User myUser = userService.getUserByCar("Mers3",111);
      System.out.println("myUser is: ");
      System.out.println("Id = "+myUser.getId());
      System.out.println("First Name = "+myUser.getFirstName());
      System.out.println("Last Name = "+myUser.getLastName());
      System.out.println("Email = "+myUser.getEmail());
      System.out.println();

      context.close();
   }
}

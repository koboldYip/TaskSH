package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);
        CarService carService = context.getBean(CarService.class);

        Car car1 = new Car("Tesla", 10);
        Car car2 = new Car("BMW", 1);
        Car car3 = new Car("Audi", 4);
        Car car4 = new Car("Reno", 8);

        User user1 = new User("User1", "Lastname1", "user1@mail.ru");
        User user2 = new User("User2", "Lastname2", "user2@mail.ru", car2);
        User user3 = new User("User3", "Lastname3", "user3@mail.ru", car3);
        User user4 = new User("User4", "Lastname4", "user4@mail.ru", car4);

        carService.add(car1);
        carService.add(car2);
        carService.add(car3);
        carService.add(car4);

        userService.add(user1);
        userService.add(user2);
        userService.add(user3);
        userService.add(user4);

        User userByCar = userService.getByCar(car1.getModel(), car1.getSeries());

        System.out.println("Id = " + userByCar.getId());
        System.out.println("First Name = " + userByCar.getFirstName());
        System.out.println("Last Name = " + userByCar.getLastName());
        System.out.println("Email = " + userByCar.getEmail());
        if (userByCar.getCar() != null) {
            System.out.println("Car model = " + userByCar.getCar().getModel());
            System.out.println("Car series = " + userByCar.getCar().getSeries());
        }
        System.out.println();

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            if (user.getCar() != null) {
                System.out.println("Car model = " + user.getCar().getModel());
                System.out.println("Car series = " + user.getCar().getSeries());
            }
            System.out.println();
        }

        List<Car> cars = carService.listCars();
        for (Car car : cars) {
            System.out.println("Model = " + car.getModel());
            System.out.println("Series = " + car.getSeries());
            System.out.println();
        }

        context.close();
    }
}

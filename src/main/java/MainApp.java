import java.util.*;
import java.util.concurrent.*;


public class MainApp {
	public static final int CARS_COUNT = 4 ;

	public static void main(String[] args) {
		System.out.println( "ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!" );
		Car[] cars = new Car[CARS_COUNT];
		Race race = new Race(new Road(60), new Tunnel(), new Road(40));
		for ( int i = 0 ; i < cars.length; i++) {
			cars[i] = new Car(race, 20 + (int)(Math.random() * 10));
		}
		for (Car car : cars) {
			new Thread(car).start();
		}
		try {
			Car.getCdlStart().await();
			System.out.println( "ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!" );
			System.out.println( "ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!" );
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

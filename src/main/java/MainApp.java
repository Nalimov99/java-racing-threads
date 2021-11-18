import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class MainApp {
	public static final int CARS_COUNT = 4 ;

	public static void main(String[] args) {
		System.out.println( "ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!\n" );
		CyclicBarrier barrier = new CyclicBarrier(CARS_COUNT + 1);
		Car[] cars = new Car[CARS_COUNT];
		Race race = new Race(CARS_COUNT, new Road(60), new Tunnel(), new Road(40));
		for ( int i = 0 ; i < cars.length; i++) {
			cars[i] = new Car(race, 20 + (int)(Math.random() * 10), barrier);
		}
		for (Car car : cars) {
			new Thread(car).start();
		}
		try {
			//WAITING FOR ALL CARS READY
			barrier.await();
			System.out.println("\nВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!\n");
			//WAITING FOR RACE START
			barrier.await();

			//FINISH
			barrier.await();
			System.out.println("\nВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
			int i = 1;
			for (Car c : race.getResults()) {
				if (i == 1) {
					System.out.println("\nПобедитель: " + race.getResults().peek().getName());
					System.out.println("\nВся сетка участников: ");
				}
				System.out.println("   " + i + " Место: " + race.getResults().take().getName());
				i++;
			}
		} catch (InterruptedException | BrokenBarrierException e) {
			e.printStackTrace();
		}
	}
}

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Car implements Runnable {
	private static int CARS_COUNT;
	static {
		CARS_COUNT = 0 ;
	}

	private final Race race;
	private final int speed;
	private final String name;
	private final CyclicBarrier barrier;

	public Car (Race race, int speed, CyclicBarrier barrier) {
		this.race = race;
		this.speed = speed;
		this.barrier = barrier;
		CARS_COUNT++;
		this.name = "Участник #" + CARS_COUNT;
	}

	public String getName () {
		return name;
	}

	public int getSpeed () {
		return speed;
	}

	@Override
	public void run () {
		try {
			System.out.println( this.name + " готовится" );
			Thread.sleep(500 + (int)(Math.random() * 800 ));
			System.out.println( this.name + " готов" );
			//WAITING FOR ALL CARS READY
			barrier.await();
			//WAITING FOR RACE START
			barrier.await();
			for (int i = 0 ; i < race.getStages().size(); i++) {
				race.getStages().get(i).go(this);
			}

			race.addResults(this);
			//FINISH
			barrier.await();
		} catch (BrokenBarrierException | InterruptedException e) {
			e.printStackTrace();
		}
	}
}

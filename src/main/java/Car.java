import java.util.concurrent.CountDownLatch;

public class Car implements Runnable {
	private static int CARS_COUNT;
	private static CountDownLatch cdlStart;
	private static CountDownLatch cdlFinish;
	static {
		CARS_COUNT = 0 ;
	}

	private final Race race;
	private final int speed;
	private final String name;

	public String getName () {
		return name;
	}

	public static int getCarsCount() {
		return CARS_COUNT;
	}

	public static CountDownLatch getCdlStart() {
		return  cdlStart;
	}

	public static CountDownLatch getCdlFinish() {
		return cdlFinish;
	}

	public int getSpeed () {
		return speed;
	}

	public Car (Race race, int speed) {
		this.race = race;
		this.speed = speed;
		CARS_COUNT++;
		this.name = "Участник #" + CARS_COUNT;
		cdlStart = new CountDownLatch(CARS_COUNT);
		cdlFinish = new CountDownLatch(CARS_COUNT);
	}

	@Override
	public void run () {
		try {
			System.out.println( this.name + " готовится" );
			Thread.sleep(500 + (int)(Math.random() * 800 ));
			System.out.println( this.name + " готов" );
			cdlStart.countDown();
		} catch (Exception e) {
			e.printStackTrace();
		}
		for ( int i = 0 ; i < race.getStages().size(); i++) {
			try {
				cdlStart.await();
				race.getStages().get(i).go(this);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		cdlFinish.countDown();
		race.addResults(this);
	}
}

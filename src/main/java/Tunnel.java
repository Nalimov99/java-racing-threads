import java.util.concurrent.Semaphore;

public class Tunnel extends Stage {
	private final Semaphore smpTunnel;

	public Tunnel () {
		this.length = 80 ;
		this.description = "Тоннель " + length + " метров";
		smpTunnel = new Semaphore(MainApp.CARS_COUNT / 2);
	}

	@Override
	public void go (Car c) {
		try {
			if (!smpTunnel.tryAcquire()) {
				System.out.println(c.getName() + " готовится к этапу(ждет): " + description);
				smpTunnel.acquire();
			}
			System.out.println(c.getName() + " начал этап: " + description);
			Thread.sleep((long)length / c.getSpeed() * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			System.out.println(c.getName() + " закончил этап: " + description);
			smpTunnel.release();
		}
	}
}

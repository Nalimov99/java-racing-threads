import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Tunnel extends Stage {
	private static Semaphore smpTunnel;

	public Tunnel () {
		this.length = 80 ;
		this.description = "Тоннель " + length + " метров";
	}

	@Override
	public void go (Car c) {
		try {
			try {
				if (smpTunnel == null) {
					smpTunnel = new Semaphore(Car.getCarsCount() / 2);
				}
				System.out.println(c.getName() + " готовится к этапу(ждет): " + description);
				smpTunnel.acquire();
				System.out.println(c.getName() + " начал этап: " + description);
				Thread.sleep((long)length / c.getSpeed() * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				System.out.println(c.getName() + " закончил этап: " + description);
				smpTunnel.release();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ArrayBlockingQueue;

public class Race {
	private final ArrayList<Stage> stages;
	public ArrayList<Stage> getStages () { return stages; }
	private final ArrayBlockingQueue<Car> results;

	public ArrayBlockingQueue<Car> getResults() {
		return results;
	}

	public void addResults(Car c) {
		this.results.add(c);
	}

	public Race (int CARS_COUNT, Stage... stages) {
		this.stages = new ArrayList<>(Arrays.asList(stages));
		results = new ArrayBlockingQueue<>(CARS_COUNT);
	}
}

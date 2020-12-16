package Simulator.Objects;

import Simulator.Kernel.Flyable;
import Simulator.Kernel.Writer;
import Simulator.Objects.Aircraft;
import Simulator.Controller.WeatherTower;
import Simulator.Objects.Coordinates;

import java.util.*;

public class JetPlane extends Aircraft implements Flyable {

	private WeatherTower weatherTower;
	ArrayList<Integer> weatherInterger;
	ArrayList<ArrayList<Integer>> weatherEffect = new ArrayList<ArrayList<Integer>>();
	ArrayList<String> weatherAnnonce = new ArrayList<String>();
	Writer writer = new Writer();

	JetPlane(String name, Coordinates coordinates) {
		
		super(name, coordinates);

		weatherInterger = new ArrayList<Integer>();
		weatherInterger.addAll(Arrays.asList(0, 10, 2));
		weatherEffect.add(weatherInterger);

		weatherInterger = new ArrayList<Integer>();
		weatherInterger.addAll(Arrays.asList(0, 5, 0));
		weatherEffect.add(weatherInterger);

		weatherInterger = new ArrayList<Integer>();
		weatherInterger.addAll(Arrays.asList(0, 1, 0));
		weatherEffect.add(weatherInterger);

		weatherInterger = new ArrayList<Integer>();
		weatherInterger.addAll(Arrays.asList(0, 0, -7));
		weatherEffect.add(weatherInterger);

		weatherAnnonce.addAll(Arrays.asList("I will melt", "I just see water", " It's raining. Better watch out for lightings.", "OMG! Winter is coming!"));
	}

	public void updateConditions() {

		String weather = this.weatherTower.getWeather(this.coordinates);
		String response = this.updateAircraftCoord(weather, weatherEffect, weatherAnnonce, "JetPlane#" + this.name + "(" + this.id + ")");

		if (response.equals("stop")) {

			writer.addLine("JetPlane#" + this.name + "(" + this.id + "): landing. [" + this.coordinates.getLongitude() + "," + this.coordinates.getLatitude() + "]");
			this.weatherTower.unregister(this);
			writer.addLine("Tower says: JetPlane#" + this.name + "(" + this.id + ") unregister to weather tower.");
		}
	}

	public void registerTower(WeatherTower weatherTower) {

		this.weatherTower = weatherTower;
		this.weatherTower.register(this);

		writer.addLine("Tower says: JetPlane#" + this.name + "(" + this.id + ") registered to weather tower.");
	}
}
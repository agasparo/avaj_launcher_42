package Simulator.Objects;

import Simulator.Kernel.Flyable;
import Simulator.Kernel.Writer;
import Simulator.Objects.Aircraft;
import Simulator.Controller.WeatherTower;
import Simulator.Objects.Coordinates;

import java.util.*;

public class Baloon extends Aircraft implements Flyable {

	private WeatherTower weatherTower;
	ArrayList<Integer> weatherInterger;
	ArrayList<ArrayList<Integer>> weatherEffect = new ArrayList<ArrayList<Integer>>();
	ArrayList<String> weatherAnnonce = new ArrayList<String>();
	Writer writer = new Writer();


	Baloon(String name, Coordinates coordinates) {
		
		super(name, coordinates);

		weatherInterger = new ArrayList<Integer>();
		weatherInterger.addAll(Arrays.asList(2, 0, 4));
		weatherEffect.add(weatherInterger);

		weatherInterger = new ArrayList<Integer>();
		weatherInterger.addAll(Arrays.asList(0, 0, -5));
		weatherEffect.add(weatherInterger);

		weatherInterger = new ArrayList<Integer>();
		weatherInterger.addAll(Arrays.asList(0, 0, -3));
		weatherEffect.add(weatherInterger);

		weatherInterger = new ArrayList<Integer>();
		weatherInterger.addAll(Arrays.asList(0, 0, -15));
		weatherEffect.add(weatherInterger);

		weatherAnnonce.addAll(Arrays.asList("Let's enjoy the good weather and take some pics.", "Damn you rain! You messed up my baloon.", "I see nothing!", "It's snowing. We're gonna crash."));
	}

	public void updateConditions() {

		String weather = this.weatherTower.getWeather(this.coordinates);
		String response = this.updateAircraftCoord(weather, weatherEffect, weatherAnnonce, "Baloon#" + this.name + "(" + this.id + ")");

		if (response.equals("stop")) {

			writer.addLine("Baloon#" + this.name + "(" + this.id + "): landing. [" + this.coordinates.getLongitude() + "," + this.coordinates.getLatitude() + "]");
			this.weatherTower.unregister(this);
			writer.addLine("Tower says: Baloon#" + this.name + "(" + this.id + ") unregister to weather tower.");
		}
	}

	public void registerTower(WeatherTower weatherTower) {

		this.weatherTower = weatherTower;
		this.weatherTower.register(this);

		writer.addLine("Tower says: Baloon#" + this.name + "(" + this.id + ") registered to weather tower.");
	}
}
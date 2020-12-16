package Simulator.Objects;

import Simulator.Kernel.Flyable;
import Simulator.Kernel.Writer;
import Simulator.Objects.Aircraft;
import Simulator.Controller.WeatherTower;
import Simulator.Objects.Coordinates;

import java.util.*;

public class Helicopter extends Aircraft implements Flyable {

	private WeatherTower weatherTower;
	ArrayList<Integer> weatherInterger;
	ArrayList<ArrayList<Integer>> weatherEffect = new ArrayList<ArrayList<Integer>>();
	ArrayList<String> weatherAnnonce = new ArrayList<String>();
	Writer writer = new Writer();

	Helicopter(String name, Coordinates coordinates) {
		
		super(name, coordinates);

		weatherInterger = new ArrayList<Integer>();
		weatherInterger.addAll(Arrays.asList(10, 0, 2));
		weatherEffect.add(weatherInterger);

		weatherInterger = new ArrayList<Integer>();
		weatherInterger.addAll(Arrays.asList(5, 0, 0));
		weatherEffect.add(weatherInterger);

		weatherInterger = new ArrayList<Integer>();
		weatherInterger.addAll(Arrays.asList(1, 0, 0));
		weatherEffect.add(weatherInterger);

		weatherInterger = new ArrayList<Integer>();
		weatherInterger.addAll(Arrays.asList(0, 0, -12));
		weatherEffect.add(weatherInterger);

		weatherAnnonce.addAll(Arrays.asList("This is hot.", "Rain ? i continue", "I see nothing, my radar is hs!", "My rotor is going to freeze!"));
	}

	public void updateConditions() {

		String weather = this.weatherTower.getWeather(this.coordinates);
		String response = this.updateAircraftCoord(weather, weatherEffect, weatherAnnonce, "Helicopter#" + this.name + "(" + this.id + ")");

		if (response.equals("stop")) {

			writer.addLine("Helicopter#" + this.name + "(" + this.id + "): landing. [" + this.coordinates.getLongitude() + "," + this.coordinates.getLatitude() + "]");
			this.weatherTower.unregister(this);
			writer.addLine("Tower says: Helicopter#" + this.name + "(" + this.id + ") unregister to weather tower.");
		}
	}

	public void registerTower(WeatherTower weatherTower) {

		this.weatherTower = weatherTower;
		this.weatherTower.register(this);

		writer.addLine("Tower says: Helicopter#" + this.name + "(" + this.id + ") registered to weather tower.");
	}
}
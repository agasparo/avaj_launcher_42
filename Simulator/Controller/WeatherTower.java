package Simulator.Controller;

import Simulator.Controller.Tower;
import Simulator.Objects.Coordinates;
import Simulator.Kernel.WeatherProvider;

public class WeatherTower extends Tower {

	public String getWeather(Coordinates coordinates) {

		return (WeatherProvider.getProvider().getCurrentWeather(coordinates));
	}

	public void changeWeather() {

		this.conditionsChanged();
	}
}
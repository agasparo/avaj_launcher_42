package Simulator.Kernel;

import Simulator.Controller.WeatherTower;

public interface Flyable {

	public void updateConditions();
	public void registerTower(WeatherTower weatherTower);
}
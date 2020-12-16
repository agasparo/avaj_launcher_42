package Simulator.Kernel;

import Simulator.Objects.Coordinates;

public class WeatherProvider {

	private String weather[] = { "RAIN", "SUN", "FOG", "SNOW" };
	private static WeatherProvider weatherProvider = new WeatherProvider();

	private  WeatherProvider() {}

	public static WeatherProvider getProvider() {

		return (WeatherProvider.weatherProvider);
	}

	public String getCurrentWeather(Coordinates coordinates) {

		int weatherCoordinate = coordinates.getLongitude() + coordinates.getLatitude() + coordinates.getHeight();

		if (weatherCoordinate < 0)
			weatherCoordinate = 0;

		return (this.weather[weatherCoordinate % 4]);
	}
}
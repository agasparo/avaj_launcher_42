package Simulator.Kernel;

import java.util.*;
import Simulator.Objects.AirCraftFractory;
import Simulator.Controller.WeatherTower;

public class Parser {

	ArrayList<String> Content;
	ArrayList<String> FlyableEngine = new ArrayList<String>();
	private static WeatherTower weatherTower;

	public Parser(ArrayList<String> fileContent) {

		this.Content = fileContent;
		weatherTower = new WeatherTower();
		FlyableEngine.addAll(Arrays.asList("Baloon", "JetPlane", "Helicopter", "994736b4f0aec72f6e5ae580051d012f", "554cd647d6b135f7e36ab1214c5e816a", "2ab8b43468e8b92b0fc5c81e70e35a2d"));
	}

	public Boolean init() {

		if (!checkTurn())
			return (false);
		for (int i = 1; i < this.Content.size(); i++) {

			String line = this.Content.get(i);
			if (line == null)
				return (false);
			if (!checkLines(line))
				return (false);
			String [] data = line.split(" ");

			AirCraftFractory airCraftFractory = new AirCraftFractory();
			Flyable flyable = airCraftFractory.newAircraft(data[0], data[1], Integer.parseInt(data[2]), Integer.parseInt(data[3]), Integer.parseInt(data[4]));
			
			flyable.registerTower(weatherTower);
		}
		return (true);
	}

	public WeatherTower getWeatherTower() {

		return (weatherTower);
	}

	private Boolean checkLines(String line) {

		if (line.indexOf(" ") == -1)
			return (false);
		String [] data = line.split(" ");
		if (data.length != 5)
			return (false);
		if (!FlyableEngine.contains(data[0]))
			return (false);
		if (!data[2].matches("-?\\d+") || !data[3].matches("-?\\d+") || !data[4].matches("-?\\d+"))
			return (false);

		int x = Integer.parseInt(data[2]);
		int y = Integer.parseInt(data[3]);
		int z = Integer.parseInt(data[4]);

		if (x < 0 || y < 0 || z < 0)
			return (false);
		return (true);
	}

	private Boolean checkTurn() {

		if (this.Content.get(0) == null)
			return (false);
		if (!this.Content.get(0).matches("-?\\d+"))
			return (false);

		int i = Integer.parseInt(this.Content.get(0));

		if (i < 0)
			return (false);
		return (true);
	}
}
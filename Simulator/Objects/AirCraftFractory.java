package Simulator.Objects;

import Simulator.Kernel.Flyable;
import Simulator.Objects.Coordinates;
import Simulator.Kernel.Writer;
import Simulator.Objects.Baloon;
import Simulator.Objects.Helicopter;
import Simulator.Objects.JetPlane;

public class AirCraftFractory {

	Writer writer = new Writer();

	public Flyable newAircraft(String type, String name, int longitude, int latitude, int height) {

		if (height > 100) {
			writer.addLine("Height must be between 0 and 100, have : " + height + ", heigth set automaticaly to 100");
			height = 100;
		}

		Coordinates coordinates = new Coordinates(longitude, latitude, height);
		type = type.toLowerCase();

		if (type.equals("baloon") || type.equals("994736b4f0aec72f6e5ae580051d012f"))
			return (new Baloon(name, coordinates));
		if (type.equals("helicopter") || type.equals("2ab8b43468e8b92b0fc5c81e70e35a2d"))
			return (new Helicopter(name, coordinates));
		return (new JetPlane(name, coordinates));
	}
}
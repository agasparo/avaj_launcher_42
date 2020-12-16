package Simulator.Objects;

import Simulator.Objects.Coordinates;
import Simulator.Kernel.Writer;

import java.util.*;

public class Aircraft {

	protected Long id;
	protected String name;
	protected Coordinates coordinates;
	Writer writer = new Writer();

	private static Long idCounter = new Long(1);

	protected Aircraft(String name, Coordinates coordinates) {

		this.name = name;
		this.coordinates = coordinates;
		this.id = this.nextId();
	}

	private Long nextId() {

		this.id = idCounter++;
		return (this.id);
	}

	public String updateAircraftCoord(String weather, ArrayList<ArrayList<Integer>> weatherEffect, ArrayList<String> weatherAnnonce, String name) {

		int newHeight = this.coordinates.getHeight();
		int newLongitude = this.coordinates.getLongitude();
		int newLatitude = this.coordinates.getLatitude();

		switch (weather) {

			case "SUN":
				newLongitude += weatherEffect.get(0).get(0);
				newLatitude += weatherEffect.get(0).get(1);
				newHeight += weatherEffect.get(0).get(2);
				writer.addLine(name + ": " + weatherAnnonce.get(0));
				break;
			case "RAIN":
				newLongitude += weatherEffect.get(1).get(0);
				newLatitude += weatherEffect.get(1).get(1);
				newHeight += weatherEffect.get(1).get(2);
				writer.addLine(name + ": " + weatherAnnonce.get(1));
				break;
			case "FOG":
				newLongitude += weatherEffect.get(2).get(0);
				newLatitude += weatherEffect.get(2).get(1);
				newHeight += weatherEffect.get(2).get(2);
				writer.addLine(name + ": " + weatherAnnonce.get(2));
				break;
			case "SNOW":
				newLongitude += weatherEffect.get(3).get(0);
				newLatitude += weatherEffect.get(3).get(1);
				newHeight += weatherEffect.get(3).get(2);
				writer.addLine(name + ": " + weatherAnnonce.get(3));
				break;
		}

		if (newLatitude < 0)
			newLatitude = 0;

		if (newLongitude < 0)
			newLongitude = 0;

		if (newHeight > 100)
			newHeight = 100;

		this.coordinates = new Coordinates(newLongitude, newLatitude, newHeight);
		if (this.coordinates.getHeight() <= 0)
			return ("stop");
		return ("continue");
	}
}
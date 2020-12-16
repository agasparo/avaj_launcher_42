package Simulator;


import java.io.*;
import java.util.*;

import Simulator.Kernel.Parser;
import Simulator.Errors.ParserExecption;
import Simulator.Errors.FileExecption;
import Simulator.Controller.WeatherTower;
import Simulator.Kernel.Writer;

public class Simulator {

	public static Writer writer = new Writer();

	public static void main(String[] args) {

		if (args.length == 0 || args.length > 1) {
			ShowUsage();
			return;
		}
		
		if (!is_TxtFile(args[0]))
			throw new FileExecption("Your file '" + args[0] + "' isn't a txt file");

		try {
			writer.removeFile();
	     	File myObj = new File(args[0]);
	    	Scanner myReader = new Scanner(myObj);
	    	readFile(myReader);
	    } catch (FileNotFoundException e) {
	      
	      	throw new FileExecption("Your file '" + args[0] + "' doesn't exist");
	    }
	}

	private static void readFile(Scanner myReader) {

		ArrayList<String> content = new ArrayList<String>();

	    while (myReader.hasNextLine()) {

	        String data = myReader.nextLine();
	       	content.add(data);
	    }
	    myReader.close();
	    Parser parser = new Parser(content);
	    if (!parser.init())
	    	throw new ParserExecption("Your file isn't valid");

	    int turn = Integer.parseInt(content.get(0));
	    WeatherTower weatherTower = parser.getWeatherTower();
	    System.out.println("[+] Begin simulation ...");
	    while (turn > 0) {

	    	weatherTower.changeWeather();
	    	turn--;
	    }
	    System.out.println("[+] Simulation ended, you can see it on simulation.txt");
	}

	private static Boolean is_TxtFile(String txtfile) {

		if (txtfile.indexOf(".") == -1)
			return (false);

		String[] parts = txtfile.split("\\.");
		if (parts[parts.length - 1].equals("txt"))
			return (true);
		return (false);
	}

	private static void ShowUsage() {

		System.out.println("Usage : java Simulator.Simulator [ file.txt ]");
	}
}
package Simulator.Kernel;

import java.io.*;
import Simulator.Errors.FileWriterExecption;

public class Writer {

	public void removeFile() {

		File f = new File("simulation.txt");
		if (f.exists()) {

			System.out.println("[+] Removing old file ...");
			f.delete();
		}

	}

	public void addLine(String line) {

		try {
      		FileWriter fileWriter = new FileWriter("simulation.txt", true);
    		PrintWriter printWriter = new PrintWriter(fileWriter);
    		printWriter.println(line);
    		printWriter.close();
    	} catch (IOException e) {
      		throw new FileWriterExecption("Can't write in this file");
    	}
	}
}
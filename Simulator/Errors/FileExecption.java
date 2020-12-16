package Simulator.Errors;

import java.io.*;

public class FileExecption extends RuntimeException {

	public FileExecption(String error_message) {

		super(error_message);
	}
}
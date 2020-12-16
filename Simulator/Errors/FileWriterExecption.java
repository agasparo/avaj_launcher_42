package Simulator.Errors;

import java.io.*;

public class FileWriterExecption extends RuntimeException {

	public FileWriterExecption(String error_message) {

		super(error_message);
	}
}
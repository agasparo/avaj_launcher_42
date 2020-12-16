package Simulator.Errors;

import java.io.*;

public class ParserExecption extends RuntimeException {

	public ParserExecption(String error_message) {

		super(error_message);
	}
}
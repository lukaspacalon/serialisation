package com.inetpsa.examples.log;

public class Log4jDemo extends AbstractBaseClass {

	public static void main(String[] args) {
		System.out.println("\n Hello World...! \n");
		logger.trace("This is a trace message");
		logger.info("This is an informative message");
		logger.error("This is an error message");
		final String parameter = "I_M_A_PARAMETER";
		logger.warn("This is a warning message [" + parameter + "]");
		logger.fatal("This is a fatal message [{}]", parameter);
		System.out.println("\n Completed \n");
	}
}

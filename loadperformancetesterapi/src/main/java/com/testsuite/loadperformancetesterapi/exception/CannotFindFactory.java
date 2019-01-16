package com.testsuite.loadperformancetesterapi.exception;

public class CannotFindFactory extends Exception {

	public CannotFindFactory(String name, Exception e) {
		super(name, e);
	}

}

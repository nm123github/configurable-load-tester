package com.testsuite.loadperformancetestercore.task.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.testsuite.loadperformancetesterapi.task.Task;

public class HelloTask extends Task {
	
	private String name;
	private static Log logger = LogFactory.getLog(HelloTask.class);	
	
	public HelloTask( String name, String producerClassName ) {
		super(producerClassName);
		this.name = name;
	}
	
	@Override
	public void runTask() throws Exception {
		logger.debug("Hello " + name + "!");
	}

	@Override
	public String toString() {
		return this.getClass().getName() + ": " + name;
	}

	@Override
	public String getDisplay() {
		return name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}

package com.testsuite.loadperformancetestercore.factories.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.testsuite.loadperformancetesterapi.producer.factories.ProducerFactory;
import com.testsuite.loadperformancetesterapi.task.Task;
import com.testsuite.loadperformancetestercore.task.impl.HelloTask;

public class HelloProducerFactory extends ProducerFactory {

	private String name;
	
	public HelloProducerFactory(String name) {
	
		this.name = name;
	
	}
	
	@Override
	public List<Task> produceTasks() {
		
		ArrayList<Task> tasks = new ArrayList<Task>();
		
		// Say Hello to 'name' 1000 times.
		for ( int i = 0 ; i < 1000 ; i++ ) {
			
			Task t = null;
			
			t = new HelloTask(name, this.getClass().getSimpleName());
			tasks.add(t);
		
		}
		
		return tasks;
	
	}

	@Override
	public ExecutorService getExecutor() {
		return Executors.newFixedThreadPool(50);
	}

}

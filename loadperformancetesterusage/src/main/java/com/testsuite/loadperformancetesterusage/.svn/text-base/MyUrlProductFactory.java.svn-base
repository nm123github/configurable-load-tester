package com.testsuite.loadperformancetesterusage;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.testsuite.loadperformancetesterapi.producer.factories.ProducerFactory;
import com.testsuite.loadperformancetesterapi.task.Task;
import com.testsuite.loadperformancetesterusage.tasks.GetUrl;

public class MyUrlProductFactory extends ProducerFactory {

	@Override
	public ExecutorService getExecutor() {
		return Executors.newFixedThreadPool(50);
	}

	@Override
	public List<Task> produceTasks() {

		ArrayList<Task> tasks = new ArrayList<Task>();
		
		// Access Google and Yahoo 10 times each
		for ( int i = 0 ; i < 3 ; i++ ) {
			
			Task t = null;
	
			t = new GetUrl("http", "80", "www.google.com", "/", this.getClass().getSimpleName());
			
			tasks.add(t);
					
			t = new GetUrl("http", "80", "www.yahoo.com", "/", this.getClass().getSimpleName());

			tasks.add(t);				
		}
		
		return tasks;		
	
	}

}

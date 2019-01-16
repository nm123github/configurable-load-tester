package com.testsuite.loadperformancetesterapi.producer;

import java.util.List;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.testsuite.loadperformancetesterapi.producer.factories.ProducerFactory;
import com.testsuite.loadperformancetesterapi.task.Task;
import com.testsuite.loadperformancetesterapi.task.TaskListener;

public class Producer implements Runnable {
	
	private ProducerFactory producerFactory;
	private List<TaskListener> listeners;
	
	private static Log logger = LogFactory.getLog(Producer.class);
	
	public Producer(ProducerFactory producerFactory, List<TaskListener> listeners) {
		this.producerFactory = producerFactory;
		this.listeners = listeners;
	}
	
	public void run() {
		
		try {
			
			logger.info("Starting producer: " + this.getClass().getSimpleName());
	
			//while ( true ) {
				
				logger.debug("Starting production of tasks");
				
				List<Task> tasks = producerFactory.produceTasks();
				
				logger.debug("Tasks are produced");
				
				for ( int i = 0 ; i < tasks.size() ; i++ ) {
					
					Task t = tasks.get(i);
					t.addListeners(listeners);
					
					logger.debug("Got task and added listeners: " + t.getClass().getSimpleName());
					producerFactory.getExecutor().submit(t);
					logger.debug("Sumitted task: " + t.getClass().getSimpleName());

				}
				
			//}
			
		} catch ( Exception e ) {
		
			logger.error( ExceptionUtils.getStackTrace(e) );
		
		}
	}
	
}

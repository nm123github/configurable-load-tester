package com.testsuite.loadperformancetestercore;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
	
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.testsuite.loadperformancetesterapi.displays.Display;
import com.testsuite.loadperformancetesterapi.exception.CannotFindFactory;
import com.testsuite.loadperformancetesterapi.producer.Producer;
import com.testsuite.loadperformancetesterapi.producer.factories.ProducerFactories;
import com.testsuite.loadperformancetesterapi.producer.factories.ProducerFactory;
import com.testsuite.loadperformancetesterapi.save.SaveTask;
import com.testsuite.loadperformancetesterapi.task.TaskListener;
import com.testsuite.loadperformancetestercore.factories.impl.DefaultProducerFactoriesImpl;

public class LoadPerformanceTest {
	
	private static Log logger = LogFactory.getLog(LoadPerformanceTest.class);
	
	public static void main(String args[]) throws InterruptedException, FileNotFoundException {
		
		logger.info("Started load performance test");
		
		LoadPerformanceTest lTest=new LoadPerformanceTest();
		lTest.init();
	}
	
	public void init() {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("loadperformancetester-beans.xml");
		List<TaskListener> listeners = (List<TaskListener>) context.getBean("taskListeners");
		Display display = (Display) context.getBean("threadDisplay");
		SaveTask save = (SaveTask) context.getBean("threadSave");
		
		//Get Producers using asbtract Factories!
		List<ProducerFactory> factories = null;
		try {
			ProducerFactories producerfactories = ProducerFactories.getInstance();
			factories = producerfactories.getFactories();
		} catch ( CannotFindFactory e ) {
			factories = new DefaultProducerFactoriesImpl().getFactories();
		}
		
		
		List<Producer> producers = new ArrayList<Producer>();
		for ( int i = 0 ; i < factories.size() ; i++ ) {
			Producer producer=new Producer(factories.get(i), listeners);
			producers.add(producer);
		}
		
		for ( Producer p : producers ) {
			Thread producerThread = new Thread(p);
			producerThread.start();
		}
		
		Thread displayThread = new Thread(display);
		displayThread.start();
		
		Thread saveThread = new Thread(save);
		saveThread.start();
		
		
	}
	
}

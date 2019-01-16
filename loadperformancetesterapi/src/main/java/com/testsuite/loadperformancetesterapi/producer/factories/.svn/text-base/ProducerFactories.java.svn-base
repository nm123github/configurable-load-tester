package com.testsuite.loadperformancetesterapi.producer.factories;

import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.testsuite.loadperformancetesterapi.exception.CannotFindFactory;

public abstract class ProducerFactories {
	
	public abstract List<ProducerFactory> getFactories();
	
	private static Log logger = LogFactory.getLog(ProducerFactories.class);
	
	public static ProducerFactories getInstance() throws CannotFindFactory {
		
		try {
			
			//try to load ProducerFactories loadperformance.producerfactories 
			Properties properties = new Properties();
			InputStream in = ProducerFactories.class.getResourceAsStream("/loadperformancetester.properties");
			properties.load(in);
			String impl = properties.getProperty("producerfactories");
						
			logger.info("System property loadperformance.producerfactories = " + impl);

			ProducerFactories factories = (ProducerFactories) Class.forName( impl ).newInstance();
			
			logger.info("Loaded producer factories " + impl + " successfully");
			
			return factories;
		
		} catch (Exception e) {
			
			logger.info("Loading default producer factories DefaultProducerFactoriesImpl");

			//check for ProducerFactories elsewhere also!
			throw new CannotFindFactory("Coudlnt find factory", e);

		}
		
	}
}

package com.testsuite.loadperformancetestercore.factories.impl;

import java.util.ArrayList;
import java.util.List;

import com.testsuite.loadperformancetesterapi.producer.factories.ProducerFactories;
import com.testsuite.loadperformancetesterapi.producer.factories.ProducerFactory;

public class DefaultProducerFactoriesImpl extends ProducerFactories {
	
	public List<ProducerFactory> getFactories() {
		
		List<ProducerFactory> factories=new ArrayList<ProducerFactory>();
		ProducerFactory helloFactory = new HelloProducerFactory("hello");
		factories.add(helloFactory);
		
		return factories;
		
	}

}

package com.testsuite.loadperformancetesterusage;

import java.util.ArrayList;
import java.util.List;

import com.testsuite.loadperformancetesterapi.producer.factories.ProducerFactories;
import com.testsuite.loadperformancetesterapi.producer.factories.ProducerFactory;

public class MyProducerFactories extends ProducerFactories {

	@Override
	public List<ProducerFactory> getFactories() {
		
		List<ProducerFactory> factories=new ArrayList<ProducerFactory>();
		factories.add(new MyUrlProductFactory());		
		return factories;
	
	}
	
}
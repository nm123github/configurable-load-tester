package com.testsuite.loadperformancetesterusage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.testsuite.loadperformancetesterapi.producer.factories.ProducerFactory;
import com.testsuite.loadperformancetesterapi.task.Task;
import com.testsuite.loadperformancetesterusage.tasks.GetUrl;

public class MyReplayProducerFactory extends ProducerFactory {
	
	@Override
	public ExecutorService getExecutor() {
		return Executors.newFixedThreadPool(50);
	}

	@Override
	public List<Task> produceTasks() {
		
		try {
			
			String folder = "C:\\loadtestertasks";
			
			ArrayList<Task> tasks = new ArrayList<Task>();
			
			File dir = new File(folder);
			
			if ( !dir.isDirectory() ) {
				throw new FileNotFoundException("Couldnt find tasks folder.");
			}
			
			File taskFiles[] = dir.listFiles();
			
			for ( int i = 0 ; i < taskFiles.length ; i++ ) {
				
				File taskFile = taskFiles[i];
				
				String ext = taskFile.getName().substring(taskFile.getName().lastIndexOf(".") + 1);
				if ( ! (taskFile.isFile() && ext.equals("task")) ) {
					// its not a .task file
					continue;
				}
							
				FileInputStream fin = new FileInputStream(taskFile);
				ObjectInputStream in = new ObjectInputStream(fin);
				Task t = (Task) in.readObject();
				
				if ( t instanceof GetUrl ) {
					GetUrl urlTask = (GetUrl) t;
					//update task object in anyway you like here (e.g. change the hostname, port, path or protocol
				}
					
				in.close();
				fin.close();
				
				tasks.add(t);
			}
		
			return tasks;
			
		}
		catch ( Exception e ) {
		
			return null;
		
		}
	}

}

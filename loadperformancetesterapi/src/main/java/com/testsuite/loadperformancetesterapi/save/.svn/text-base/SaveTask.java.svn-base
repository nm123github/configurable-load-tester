package com.testsuite.loadperformancetesterapi.save;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.testsuite.loadperformancetesterapi.task.Task;
import com.testsuite.loadperformancetesterapi.task.TaskListener;

public abstract class SaveTask implements Runnable, TaskListener {
	
	protected List<Task> tasks; // list of finished
	protected long delay;
	private static Log logger = LogFactory.getLog(SaveTask.class);
	
	public SaveTask(long delay) {
		
		this.tasks = Collections.synchronizedList(new ArrayList<Task>());
		this.delay = delay;
	}
	
	public void run() {
		
		try {
			
			while ( true ) {
				
				Thread.sleep(delay);
				
				ArrayList<SaveData> data = createData();
				
				if ( data==null ) {
					continue;
				}
				
				logger.info("Started serializing tasks for " + data.size() + " tasks to disk");
				
				for ( int i = 0 ; i < data.size() ; i++ ) {
					
					write( data.get(i).getOutputStream(), data.get(i).getData() );
				
				}
				
				logger.info("Finished serializing tasks for " + data.size() + " tasks to disk");

			}
			
		} catch (Exception e) {
			
			logger.error( ExceptionUtils.getStackTrace(e) );
			
		}
		
	}
	
	public boolean write(OutputStream o, byte[] data) {
		
		if ( data==null ) {
			
			return false;
		
		}
		
		try {
			
			o.write(data);
			
			return true;
			
		
		} catch (Exception e) {
		
			logger.error( ExceptionUtils.getStackTrace(e) );
			
			return false;
		}
		
	}
	
	public abstract ArrayList<SaveData> createData()  throws Exception;

	public long getDelay() {
		return delay;
	}

	public void setDelay(long delay) {
		this.delay = delay;
	}

}

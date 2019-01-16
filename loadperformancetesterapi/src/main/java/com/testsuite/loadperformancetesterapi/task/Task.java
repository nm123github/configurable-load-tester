package com.testsuite.loadperformancetesterapi.task;

import java.io.Serializable;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public abstract class Task implements Runnable, Serializable {
	
	//@Display
	private transient long cpuTimeElapsed;
	//@Display
	private transient long timeElapsed;
	//@Display
	private transient Exception exception;
	//@Display
	private String producerClassName;
	//@Display
	private transient String display;
	//@Display
	private transient List<TaskListener> listeners;

	/* Why dont we use Thread's State?
	 * Because if we add Thread object to the @com.bioimagene.iii.ims.utility.loadperformancetester.taskpostprocess.TaskPostProcessor Queue,
	 * there is no way to retrieve the Runnable from the Thread object */
	private Thread.State threadState;
	
	private static Log logger = LogFactory.getLog(Task.class);
	
	public Task( String producerClassName) {
		this.producerClassName = producerClassName;
		listeners = new ArrayList<TaskListener>();
	}
	
	public abstract String toString();
	public abstract String getDisplay();
	public abstract void runTask() throws Exception;
	
	//Add task listeners!
	public void addListener(TaskListener e) {
		listeners.add(e);
	}
	
	//Add task listeners!
	public void addListeners(List<TaskListener> e) {
		this.listeners = e;
	}
	
	@Override
	public void run() {
		
		ThreadMXBean threadBean = ManagementFactory.getThreadMXBean();

		long startThreadCpuTime = threadBean.getCurrentThreadCpuTime();
		long startTime = System.currentTimeMillis();
		
		threadState = Thread.State.RUNNABLE;
		
		logger.info("Starting task " + this);

		try {	
			
			runTask();
			
			this.display = getDisplay();
			
			this.exception = null;

		} catch ( Exception e ) {

			this.exception = e;
			
			logger.error( ExceptionUtils.getStackTrace(e) );
			
		} finally {
			
			threadState = Thread.State.TERMINATED;
			
			long endTime = System.currentTimeMillis();
			long endThreadCpuTime = threadBean.getCurrentThreadCpuTime();
			
			this.cpuTimeElapsed = endThreadCpuTime-startThreadCpuTime ;
			this.timeElapsed = endTime-startTime;
			
			for ( int i = 0 ; i < listeners.size() ; i++ ) {
				listeners.get(i).finishedTask(this);
			}
			
			logger.info("Finished task " + this);
		}
	}

	public long getCpuTimeElapsed() {
		return cpuTimeElapsed;
	}

	public void setCpuTimeElapsed(long cpuTimeElapsed) {
		this.cpuTimeElapsed = cpuTimeElapsed;
	}

	public long getTimeElapsed() {
		return timeElapsed;
	}

	public void setTimeElapsed(long timeElapsed) {
		this.timeElapsed = timeElapsed;
	}

	public Exception getException() {
		return exception;
	}

	public void setException(Exception exception) {
		this.exception = exception;
	}

	public String getProducerClassName() {
		return producerClassName;
	}

	public void setProducerClassName(String producerClassName) {
		this.producerClassName = producerClassName;
	}

	public Thread.State getThreadState() {
		return threadState;
	}

	public void setThreadState(Thread.State threadState) {
		this.threadState = threadState;
	}

	public void setDisplay(String display) {
		this.display = display;
	}
		
}

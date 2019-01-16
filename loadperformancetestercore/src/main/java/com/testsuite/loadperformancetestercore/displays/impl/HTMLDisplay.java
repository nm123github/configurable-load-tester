package com.testsuite.loadperformancetestercore.displays.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.testsuite.loadperformancetesterapi.displays.WriteData;
import com.testsuite.loadperformancetesterapi.displays.WriteableDisplay;
import com.testsuite.loadperformancetesterapi.task.Task;

public class HTMLDisplay extends WriteableDisplay {

	private static Log logger = LogFactory.getLog(HTMLDisplay.class);
	private String folder;
	private static int rollOvercnt = 0;

	public HTMLDisplay(long delay, String folder) {
		
		super(delay);
		this.folder = folder;
		
		// create folder is doesnt exist
		File f=new File(folder);
		if ( !f.isDirectory() ) {
			f.mkdir();
		}
		
	}	
	
	public String getCurrentDate() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		return dateFormat.format(date);
	}
	
	public String getCurrentTime() {
		Calendar calendar = new GregorianCalendar();
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);
		int second = calendar.get(Calendar.SECOND);
		int milisecond = calendar.get(Calendar.MILLISECOND);
		return hour + ":" + minute + ":" + second + ":" + milisecond;
	}
	

	@Override
	protected ArrayList<WriteData> createData()  throws Exception {
		
		logger.debug("Creating HTMLDisplay data");	
		
		String data = "";
		
		data = "<h1>Report Generated By " + this.getClass().getSimpleName() + " at " + getCurrentDate() + "</h1>\n\n";
		data = data + "<table border=1 cellspacing=5 cellpadding=5>\n";
		
		data=data+"<tr>\n";
		data=data+"<td>No.</td>\n";
		data=data+"<td>Task Class</td>\n";
		data=data+"<td>Producer Class</td>\n";
		data=data+"<td>Description</td>\n";
		data=data+"<td>Current Time</td>\n";
		data=data+"<td>Time (secs)</td>\n";
		data=data+"<td>Cpu Time (secs)</td>\n";
		data=data+"<td>Status</td>\n";
		data=data+"<td>Exception</td>\n";
		data=data+"</tr>\n";
		
		Object[] tasks = null;
		
		synchronized ( this.tasks ) {
			
			tasks = this.tasks.toArray();
			this.tasks.clear();
			
		}
		
		// dont write output if nothing to write
		if ( tasks.length == 0 ) {
			return null;
		}
							
		for ( int i = 0, cnt = 0 ; i < tasks.length ; i++, cnt++ ) {
								
			Task tCurr = (Task) tasks[i];
					
			data=data+"<tr>\n";
			data=data+"<td>"+(cnt+1)+"</td>\n";
			data=data+"<td>"+tCurr.getClass().getSimpleName()+"</td>\n";
			data=data+"<td>"+tCurr.getProducerClassName()+"</td>\n";
			data=data+"<td>"+tCurr.getDisplay()+"</td>\n";
			data=data+"<td>"+this.getCurrentTime()+"</td>\n";
			if ( tCurr.getException() != null ) {
				data=data+"<td><font color=red>" + tCurr.getTimeElapsed()/1000.0 +"</font></td>\n";
				data=data+"<td><font color=red>" + tCurr.getCpuTimeElapsed()/1000000000.0 +"</font></td>\n";
				data=data+"<td>fail</td>\n";
			} else {
				data=data+"<td><font color=green>" + tCurr.getTimeElapsed()/1000.0 +"</font></td>\n";
				data=data+"<td><font color=green>" + tCurr.getCpuTimeElapsed()/1000000000.0 +"</font></td>\n";
				data=data+"<td>pass</td>\n";
			}
						
			data=data+"<td>"+tCurr.getException()+"</td>\n";
			data=data+"</tr>\n";

		}
		
		logger.debug("Created Data for writing");
		
		data = data + "</table>\n";
		
		File f=new File(folder + "\\" + "perfout" + rollOvercnt++ + ".html");
		ArrayList<WriteData> writeDataList = new ArrayList<WriteData>();
		writeDataList.add(new WriteData(data.getBytes(), new FileOutputStream(f)));

		return writeDataList;
					
	}

	@Override
	public void finishedTask(Task t) {
		
		logger.debug("Adding task " + t + " to finished tasks list");
		tasks.add(t);
		
	}
			
}
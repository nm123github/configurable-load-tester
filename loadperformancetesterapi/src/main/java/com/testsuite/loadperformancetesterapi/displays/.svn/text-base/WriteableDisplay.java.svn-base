package com.testsuite.loadperformancetesterapi.displays;

import java.io.OutputStream;
import java.util.ArrayList;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public abstract class WriteableDisplay extends Display {

	private static Log logger = LogFactory.getLog(WriteableDisplay.class);
	protected long delay;
	
	public WriteableDisplay(long delay) {
				
		this.delay = delay;
		
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
	
	public void run() {
	
		try {
			
			while ( true ) {
				
				Thread.sleep(delay);
							
				ArrayList<WriteData> data = createData();
								
				if ( data==null ) {
					continue;
				}
				
				logger.info("Started writing performance data for " + data.size() + " tasks to disk");
				
				for ( int i = 0 ; i < data.size() ; i++ ) {
					write( data.get(i).getOutputStream(), data.get(i).getData() );
					data.get(i).getOutputStream().close();
				}
				
				logger.info("Finished writing performance data for " + data.size() + " tasks to disk");

			}
			
		} catch (Exception e) {
			
			logger.error( ExceptionUtils.getStackTrace(e) );
			
		}
		
	}
		
	protected abstract ArrayList<WriteData> createData() throws Exception ;

	public long getDelay() {
		return delay;
	}

	public void setDelay(long delay) {
		this.delay = delay;
	}

}

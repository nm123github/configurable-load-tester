package com.testsuite.loadperformancetestercore.save.impl;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import com.testsuite.loadperformancetesterapi.save.SaveData;
import com.testsuite.loadperformancetesterapi.save.SaveTask;
import com.testsuite.loadperformancetesterapi.task.Task;

public class SerializeTask extends SaveTask {
	
	private static int rollOvercnt = 0;
	
	private String folder;
	
	public SerializeTask(long delay, String folder) {
		super(delay);
		this.folder = folder;
		
		// create folder is doesnt exist
		File f=new File(folder);
		if ( !f.isDirectory() ) {
			f.mkdir();
		}
	}

	@Override
	public ArrayList<SaveData> createData() throws Exception {
		
		ArrayList<SaveData> saveDataList = new ArrayList<SaveData>();

		Object[] tasks = null;
		synchronized ( this.tasks ) {
			tasks = this.tasks.toArray();
			this.tasks.clear();
		}
		
		// dont write output if nothing to write
		if ( tasks.length == 0 ) {
			return null;
		}
		
		for ( int i = 0; i < tasks.length ; i++ ) {
			
			Task tCurr = (Task) tasks[i];
			
			File f=new File(folder + "\\" + "task" + rollOvercnt++ + ".task");
			FileOutputStream fos = new FileOutputStream(f);
			ByteArrayOutputStream bout = new ByteArrayOutputStream();
			ObjectOutputStream out = new ObjectOutputStream(bout);
			out.writeObject(tCurr);
			out.close();
			bout.close();
			
			saveDataList.add(new SaveData(bout.toByteArray(), fos));
			
		}
		
		return saveDataList;
	}
	
	@Override
	public void finishedTask(Task t) {
		
		this.tasks.add(t);
		
	}

}

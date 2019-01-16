package com.testsuite.loadperformancetesterapi.displays;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.testsuite.loadperformancetesterapi.task.Task;
import com.testsuite.loadperformancetesterapi.task.TaskListener;

public abstract class Display implements Runnable, TaskListener {

	protected List<Task> tasks; // list of finished
	
	public Display() {
		this.tasks = Collections.synchronizedList(new ArrayList<Task>());
	}

}

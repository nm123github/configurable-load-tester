package com.testsuite.loadperformancetesterusage.tasks;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import com.testsuite.loadperformancetesterapi.task.Task;

public class GetUrl extends Task {
	
	private String protocol;
	private String hostname;
	private String port;
	private String path;
	
	/* pass url and class name of producer thread that produced this Task  */
	public GetUrl( String protocol, String port, String hostname, String path, String producerClassName ) {
		super(producerClassName);
		this.protocol = protocol;
		this.hostname = hostname;
		this.port = port;
		this.path = path;
	}
	
	@Override
	/* task to be timed */
	public void runTask() throws Exception {
		
		URL urlObj = new URL(getUrl());
		URLConnection uconn = urlObj.openConnection();
		BufferedReader in = new BufferedReader(new InputStreamReader(uconn.getInputStream()));
		StringBuffer inputLine=new StringBuffer();
		String tmp;
		while ((tmp = in.readLine()) != null) {
			inputLine.append(tmp);
		}
		//System.out.println("***************************************************");
		//System.out.println("Length:- " + inputLine.length());
		//System.out.println("***************************************************");
		in.close();
	
	}
	
	private String getUrl() {
		StringBuilder sb = new StringBuilder();
		sb.append(protocol);
		sb.append("://");
		sb.append(hostname);
		sb.append(":");
		sb.append(port);
		sb.append(path);
		return sb.toString();
	}

	@Override
	/* could be used during logging */
	public String toString() {
		return this.getClass().getName() + ": " + getUrl();
	}

	@Override
	/* will be displayed in the HTML report */
	public String getDisplay() {
		return getUrl();
	}
	
}

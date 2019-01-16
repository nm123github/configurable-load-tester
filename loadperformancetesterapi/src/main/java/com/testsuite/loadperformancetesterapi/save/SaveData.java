package com.testsuite.loadperformancetesterapi.save;

import java.io.OutputStream;

public class SaveData {
	
	private OutputStream outputStream;
	private byte data[];
	
	public SaveData(byte[] data, OutputStream outputStream) {
		this.outputStream = outputStream;
		this.data = data;
	}

	public OutputStream getOutputStream() {
		return outputStream;
	}

	public void setOutputStream(OutputStream out) {
		this.outputStream = out;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}	

}

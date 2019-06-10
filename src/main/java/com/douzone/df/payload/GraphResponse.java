package com.douzone.df.payload;

import com.douzone.df.model.Status;

public class GraphResponse {
	private Status status;  
	private int count;
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public GraphResponse(Status status, int count) {
		super();
		this.status = status;
		this.count = count;
	}
	
	
}

package com.douzone.df.payload;

import com.douzone.df.model.Status;

public class GraphResponse {
	private Status status;  
	private Long count;
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public GraphResponse(Status status, Long count) {
		super();
		this.status = status;
		this.count = count;
	}
	
	
}

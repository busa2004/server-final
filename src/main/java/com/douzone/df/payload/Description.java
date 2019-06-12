package com.douzone.df.payload;

public class Description {
	private String description;
	private Long count;
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public Description(String description, Long count) {
		super();
		this.description = description;
		this.count = count;
	}
	
}

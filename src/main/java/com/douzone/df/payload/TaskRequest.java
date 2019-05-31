package com.douzone.df.payload;

public class TaskRequest {
	private Long userId;
	private String search;
	private String status;
	private String from;
	private String to;
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	@Override
	public String toString() {
		return "TaskRequest [userId=" + userId + ", search=" + search + ", status=" + status + ", from=" + from
				+ ", to=" + to + "]";
	}
	
}

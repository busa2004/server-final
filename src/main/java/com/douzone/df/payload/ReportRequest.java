package com.douzone.df.payload;

import java.util.Arrays;

public class ReportRequest {
	private String title;
	private String content;
	private Long userTaskId;
	private String search;
	private String status;
	private String from;
	private String to;
	private Long id;
	private String fileName[];
	private Long taskId;
	
	
	
	
	
	
	

	



	public Long getTaskId() {
		return taskId;
	}







	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}







	@Override
	public String toString() {
		return "ReportRequest [title=" + title + ", content=" + content + ", userTaskId=" + userTaskId + ", search="
				+ search + ", status=" + status + ", from=" + from + ", to=" + to + ", id=" + id + ", fileName="
				+ Arrays.toString(fileName) + "]";
	}







	public String[] getFileName() {
		return fileName;
	}







	public void setFileName(String[] fileName) {
		this.fileName = fileName;
	}







	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	public Long getUserTaskId() {
		return userTaskId;
	}
	public void setUserTaskId(Long userTaskId) {
		this.userTaskId = userTaskId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}

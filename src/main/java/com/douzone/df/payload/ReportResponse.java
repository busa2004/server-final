package com.douzone.df.payload;

import java.time.Instant;

import com.douzone.df.model.Status;

public class ReportResponse {
	private int key;
	private Long id;
	private String title;
	private String content;
	private String taskTitle;
	private String userName;
	private Status status;
	private Instant createdAt;
	private String fileName;
	public ReportResponse( Long id, String title, String content, String taskTitle, String userName,Instant createdAt,Status status,String fileName) {
		super();
		
		this.id = id;
		this.title = title;
		this.content = content;
		this.taskTitle = taskTitle;
		this.userName = userName;
		this.createdAt = createdAt;
		this.status = status;
		this.fileName = fileName;
	}
	
	
	public String getFileName() {
		return fileName;
	}


	public void setFileName(String fileName) {
		this.fileName = fileName;
	}


	public Instant getCreatedAt() {
		return createdAt;
	}


	public void setCreatedAt(Instant createdAt) {
		this.createdAt = createdAt;
	}


	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "ReportResponse [key=" + key + ", id=" + id + ", title=" + title + ", content=" + content
				+ ", taskTitle=" + taskTitle + ", userName=" + userName + "]";
	}
	public int getKey() {
		return key;
	}
	public void setKey(int key) {
		this.key = key;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	
	public String getTaskTitle() {
		return taskTitle;
	}
	public void setTaskTitle(String taskTitle) {
		this.taskTitle = taskTitle;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
	
}

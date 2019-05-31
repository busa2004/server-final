package com.douzone.df.payload;

import java.time.Instant;

public class TaskResponse {
	private Long id;
	private String title;
	private String content;
	private int key;
	private int status;
	private Instant createdAt;
	
	@Override
	public String toString() {
		return "TaskResponse [id=" + id + ", title=" + title + ", content=" + content + ", key=" + key + ", status="
				+ status + ", createdAt=" + createdAt + "]";
	}
	public TaskResponse(Long id, String title, String content,Instant createdAt) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;

		this.createdAt = createdAt;
	}
	public Instant getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Instant createdAt) {
		this.createdAt = createdAt;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public TaskResponse(Long id, String title, String content, int key) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.key = key;
	}
	public TaskResponse(Long id, String title, String content, int key,int status) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.key = key;
		this.status= status;
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
	public int getKey() {
		return key;
	}
	public void setKey(int key) {
		this.key = key;
	}
	
}

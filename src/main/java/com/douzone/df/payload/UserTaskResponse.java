package com.douzone.df.payload;

import java.time.Instant;
import java.util.Date;

public class UserTaskResponse {
	private Long id;
	private Long taskId;
	private String taskTitle;
	private String taskContent;
	private Long userId;
	private String userName;
	
	private String empty;
	private String title;
	private String content;
	private Date startDate;
	private Date endDate;
	private Instant createAt;
	private Instant updateAt;
	
	private int key;
	public UserTaskResponse(Long id,String title) {
		super();
		this.id = id;
		this.title = title;
	}
	public UserTaskResponse(Long id,String title,String content,Date startDate,Date endDate
			,Instant createAt,Instant updateAt,int key) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.startDate = startDate;
		this.endDate = endDate;
		this.createAt = createAt;
		this.updateAt = updateAt;
		this.key = key;
	}
	
	public String getEmpty() {
		return empty;
	}
	public void setEmpty(String empty) {
		this.empty = empty;
	}
	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public Instant getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Instant createAt) {
		this.createAt = createAt;
	}

	public Instant getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(Instant updateAt) {
		this.updateAt = updateAt;
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
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public UserTaskResponse(Long id, Long taskId, String taskTitle, String taskContent, Long userId, String userName) {
		super();
		this.id = id;
		this.taskId = taskId;
		this.taskTitle = taskTitle;
		this.taskContent = taskContent;
		this.userId = userId;
		this.userName = userName;
	}
	

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getTaskId() {
		return taskId;
	}
	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}
	public String getTaskTitle() {
		return taskTitle;
	}
	public void setTaskTitle(String taskTitle) {
		this.taskTitle = taskTitle;
	}
	public String getTaskContent() {
		return taskContent;
	}
	public void setTaskContent(String taskContent) {
		this.taskContent = taskContent;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}

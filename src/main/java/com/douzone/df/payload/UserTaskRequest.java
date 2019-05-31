package com.douzone.df.payload;

import java.util.Arrays;
import java.util.Map;

public class UserTaskRequest {
	private Long[] taskIds;
	private Long userId;
	private String startDate;
	private String endDate;
	private Long taskId;
	public Long[] getTaskIds() {
		return taskIds;
	}
	public void setTaskIds(Long[] taskIds) {
		this.taskIds = taskIds;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	public Long getTaskId() {
		return taskId;
	}
	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}
	@Override
	public String toString() {
		return "UserTaskRequest [taskIds=" + Arrays.toString(taskIds) + ", userId=" + userId + ", startDate="
				+ startDate + ", endDate=" + endDate + ", taskId=" + taskId + "]";
	}
	
	
	
	
}

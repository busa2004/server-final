package com.douzone.df.payload;

import java.util.ArrayList;

public class QuarterNHalfRankRequest {
	private ArrayList<Long> taskIds;
	private String startDate;
	private String endDate;
	
	public QuarterNHalfRankRequest() {}

	public QuarterNHalfRankRequest(ArrayList<Long> taskIds, String startDate, String endDate) {
		this.taskIds = taskIds;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public ArrayList<Long> getTaskIds() {
		return taskIds;
	}

	public void setTaskIds(ArrayList<Long> taskIds) {
		this.taskIds = taskIds;
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

	@Override
	public String toString() {
		return "QuarterRankRequest [taskIds=" + taskIds + ", startDate=" + startDate + ", endDate=" + endDate + "]";
	}
	

}

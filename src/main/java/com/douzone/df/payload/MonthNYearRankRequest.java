package com.douzone.df.payload;

import java.util.ArrayList;

public class MonthNYearRankRequest {
	private ArrayList<Long> taskIds;
	private String condition;
	
	public MonthNYearRankRequest() {}

	public MonthNYearRankRequest(ArrayList<Long> taskIds, String condition) {
		this.taskIds = taskIds;
		this.condition = condition;
	}

	public ArrayList<Long> getTaskIds() {
		return taskIds;
	}

	public void setTaskIds(ArrayList<Long> taskIds) {
		this.taskIds = taskIds;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	@Override
	public String toString() {
		return "MonthRankRequest [taskIds=" + taskIds + ", condition=" + condition + "]";
	}
	
}

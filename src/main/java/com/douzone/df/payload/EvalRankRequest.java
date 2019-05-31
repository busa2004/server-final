package com.douzone.df.payload;

import java.util.ArrayList;

public class EvalRankRequest {
	private ArrayList<Long> taskIds;
	private String year;
	private String condition;
	
	public EvalRankRequest() { }

	public EvalRankRequest(ArrayList<Long> taskIds, String year, String condition) {
		super();
		this.taskIds = taskIds;
		this.year = year;
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

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	@Override
	public String toString() {
		return "EvalRankRequest [taskIds=" + taskIds + ", year=" + year + ", condition=" + condition + "]";
	}


}

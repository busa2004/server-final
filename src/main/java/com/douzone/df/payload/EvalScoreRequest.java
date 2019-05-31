package com.douzone.df.payload;

import java.util.ArrayList;

import com.douzone.df.model.EvalItemScore;
import com.douzone.df.model.EvalItemVersion;
import com.douzone.df.model.UserTask;

public class EvalScoreRequest {
	private ArrayList<EvalItemScore> scores;
	private UserTask userTask;
	private Long userId;

	public EvalScoreRequest() { }

	public EvalScoreRequest(ArrayList<EvalItemScore> scores, UserTask userTask, Long userId) {
		this.scores = scores;
		this.userTask = userTask;
		this.userId = userId;
	}

	public ArrayList<EvalItemScore> getScores() {
		return scores;
	}

	public void setScores(ArrayList<EvalItemScore> scores) {
		this.scores = scores;
	}

	public UserTask getUserTask() {
		return userTask;
	}

	public void setUserTask(UserTask userTask) {
		this.userTask = userTask;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "EvalScoreRequest [scores=" + scores + ", userTask=" + userTask + ", userId=" + userId + "]";
	}
	
}

package com.douzone.df.payload;

public class EvalUserScore {
	private Long userTaskId;
	private Long score;
	
	public EvalUserScore() {}
	public EvalUserScore(Long userTaskId, Long score) {
		this.userTaskId = userTaskId;
		this.score = score;
	}
	public Long getUserTaskId() {
		return userTaskId;
	}
	public void setUserTaskId(Long userTaskId) {
		this.userTaskId = userTaskId;
	}
	public Long getScore() {
		return score;
	}
	public void setScore(Long score) {
		this.score = score;
	}
	@Override
	public String toString() {
		return "EvalUserScore [userTaskId=" + userTaskId + ", score=" + score + "]";
	}
}

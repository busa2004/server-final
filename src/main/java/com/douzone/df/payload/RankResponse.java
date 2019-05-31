package com.douzone.df.payload;

public class RankResponse {
	private Long userTaskId;
	private double score;
	
	public RankResponse() {}
	public RankResponse(Long userTaskId, Long score) {
		this.userTaskId = userTaskId;
		this.score = score;
	}
	public Long getUserTaskId() {
		return userTaskId;
	}
	public void setUserTaskId(Long userTaskId) {
		this.userTaskId = userTaskId;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	@Override
	public String toString() {
		return "EvalUserScore [userTaskId=" + userTaskId + ", score=" + score + "]";
	}
}

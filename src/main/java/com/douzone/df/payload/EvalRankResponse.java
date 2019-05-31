package com.douzone.df.payload;

import java.util.ArrayList;

public class EvalRankResponse {
	private ArrayList<EvalUserScore> userScores;
	
	public EvalRankResponse() { }
	public EvalRankResponse(ArrayList<EvalUserScore> userScores) {
		this.userScores = userScores;
	}

	public ArrayList<EvalUserScore> getUserScores() {
		return userScores;
	}

	public void setUserScores(ArrayList<EvalUserScore> userScores) {
		this.userScores = userScores;
	}

	@Override
	public String toString() {
		return "EvalRankResponse [userScores=" + userScores + "]";
	}

}

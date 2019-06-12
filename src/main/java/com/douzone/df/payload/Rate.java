package com.douzone.df.payload;

public class Rate {
	private String m;
	private Long hold;
	private Long progress;
	public String getM() {
		return m;
	}
	public void setM(String m) {
		this.m = m;
	}
	public Long getHold() {
		return hold;
	}
	public void setHold(Long hold) {
		this.hold = hold;
	}
	public Long getProgress() {
		return progress;
	}
	public void setProgress(Long progress) {
		this.progress = progress;
	}
	public Rate(String m, Long hold, Long progress) {
		super();
		this.m = m;
		this.hold = hold;
		this.progress = progress;
	}
	
	
	//	private Long rate;
//	public String getM() {
//		return m;
//	}
//	public void setM(String m) {
//		this.m = m;
//	}
//	public Long getRate() {
//		return rate;
//	}
//	public void setRate(Long rate) {
//		this.rate = rate;
//	}
//	public Rate(String m, Long rate) {
//		super();
//		this.m = m;
//		this.rate = rate;
//	}
	
	
	
	
}

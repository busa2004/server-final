package com.douzone.df.payload;

public class Slack {
	private String slackKey;
	private String slackChannel;
	public String getSlackKey() {
		return slackKey;
	}
	public void setSlackKey(String slackKey) {
		this.slackKey = slackKey;
	}
	public String getSlackChannel() {
		return slackChannel;
	}
	public void setSlackChannel(String slackChannel) {
		this.slackChannel = slackChannel;
	}
	public Slack(String slackKey, String slackChannel) {
		super();
		this.slackKey = slackKey;
		this.slackChannel = slackChannel;
	}
	
}

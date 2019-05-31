package com.douzone.df.model;

public class ItemVo {
	private Long no;
	private String content;
	private String version;
	private Long item_version_no;
	
	
	
	public Long getItem_version_no() {
		return item_version_no;
	}
	public void setItem_version_no(Long item_version_no) {
		this.item_version_no = item_version_no;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}

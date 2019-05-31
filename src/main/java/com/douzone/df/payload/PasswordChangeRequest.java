package com.douzone.df.payload;

public class PasswordChangeRequest {
	private String existPassword;
	private String password;
	private String confirm;
	private Long id;
	public String getExistPassword() {
		return existPassword;
	}
	public void setExistPassword(String existPassword) {
		this.existPassword = existPassword;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirm() {
		return confirm;
	}
	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
}

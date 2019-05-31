package com.douzone.df.payload;

public class UserResponse {
	private String name;
	private String email;
	private Long id;
	private int key;
	private String profile;
	
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getKey() {
		return key;
	}
	public void setKey(int key) {
		this.key = key;
	}
	public UserResponse(String name, String email, Long id, int key,String profile) {
		super();
		this.name = name;
		this.email = email;
		this.id = id;
		this.key = key;
		this.profile= profile;
	}
	
	
}

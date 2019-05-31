package com.douzone.df.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

import com.douzone.df.audit.DateAudit;

@Entity
@Table(name = "task")
public class Task extends DateAudit{
	private Long user_task_no;
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    
    
	private String title;
	@Lob 
	@Column(columnDefinition="text")
	private String content;
	

	
	
	
	@Enumerated(EnumType.STRING)
	private Status status;
 
	
	
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public Long getUser_task_no() {
		return user_task_no;
	}
	public void setUser_task_no(Long user_task_no) {
		this.user_task_no = user_task_no;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public String toString() {
		return "Task [user_task_no=" + user_task_no + ", id=" + id + ", title=" + title + ", content=" + content
				+ ", status=" + status + "]";
	}

	

}

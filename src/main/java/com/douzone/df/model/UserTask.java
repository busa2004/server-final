package com.douzone.df.model;

import java.time.Instant;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.douzone.df.audit.DateAudit;

@Entity
@Table(name = "user_task")
public class UserTask extends DateAudit{
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	 @ManyToOne(fetch = FetchType.EAGER, optional = false)
	 @JoinColumn(name = "task_id", nullable = false)
	private Task task;
	 @ManyToOne(fetch = FetchType.EAGER, optional = false)
	 @JoinColumn(name = "user_id", nullable = false)
	private User user;
	  
	 @Temporal(TemporalType.DATE)
	 private Date startDate;
	 @Temporal(TemporalType.DATE)
	 private Date endDate;
	 
	 @Enumerated(EnumType.STRING)
	 private Status status;  
	  
	  
	
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Task getTask() {
		return task;
	}
	public void setTask(Task task) {
		this.task = task;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return "UserTask [id=" + id + ", task=" + task + ", user=" + user + "]";
	}
	
	
	
	
}

package com.douzone.df.model;

import javax.persistence.*;

@Entity
@Table(name = "eval")
public class Eval {

	@Column(name = "eval_no", nullable = false)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increament를 위해 지정
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER, optional = true)
	@JoinColumn(name = "eval_item_score_no", referencedColumnName = "item_score_no", nullable = false)
	private EvalItemScore score;
	
	@ManyToOne(fetch = FetchType.EAGER, optional = true)
	@JoinColumn(name = "user_task", referencedColumnName = "id", nullable = false)
	private UserTask userTask;

	@Column(name="user_id", nullable=false)
	private Long userId;
	
	public Eval() { }
	public Eval(Long id, EvalItemScore score, UserTask userTask, Long userId) {
		this.id = id;
		this.score = score;
		this.userTask = userTask;
		this.userId = userId;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public EvalItemScore getScore() {
		return score;
	}
	public void setScore(EvalItemScore score) {
		this.score = score;
	}
	public UserTask getUserTask() {
		return userTask;
	}
	public void setUserTask(UserTask userTask) {
		this.userTask = userTask;
	}
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}


	@Override
	public String toString() {
		return "Eval [id=" + id + ", score=" + score + ", userTask=" + userTask + ", userId=" + userId + "]";
	}


}

package com.douzone.df.model;

import javax.persistence.*;

@Entity
@Table(name="eval_item_score")
public class EvalItemScore {

	@Id
	@Column(name="item_score_no", nullable=false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long itemScoreNo;

	@Column(name="score", nullable=false)
	private Long score;
	
	@ManyToOne(fetch=FetchType.EAGER, optional=true)
	@JoinColumn(name="eval_item_no", referencedColumnName="item_no", nullable=false)
	private EvalItem evalItem; // URL parameter 전송시에 <evalItem=작업할 번호 > 이렇게 보내면 된다.

	@Column(name="user_id", nullable=false)
	private Long userId;
	
	@Column(name="user_task_id", nullable=false)
	private Long userTaskId;

	public EvalItemScore() {
		
	}
	
	public EvalItemScore(Long score, Long userId, Long userTaskId) {
		this.score = score;
		this.userTaskId = userTaskId;
	}

	public Long getItemScoreNo() {
		return itemScoreNo;
	}

	public void setItemScoreNo(Long itemScoreNo) {
		this.itemScoreNo = itemScoreNo;
	}

	public Long getScore() {
		return score;
	}

	public void setScore(Long score) {
		this.score = score;
	}

	public EvalItem getEvalItem() {
		return evalItem;
	}

	public void setEvalItem(EvalItem evalItem) {
		this.evalItem = evalItem;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getUserTaskId() {
		return userTaskId;
	}

	public void setUserTaskId(Long userTaskId) {
		this.userTaskId = userTaskId;
	}

	@Override
	public String toString() {
		return "EvalItemScore [itemScoreNo=" + itemScoreNo + ", score=" + score + ", evalItem=" + evalItem + ", userId="
				+ userId + ", userTaskId=" + userTaskId + "]";
	}

	

}

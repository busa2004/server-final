package com.douzone.df.model;

import javax.persistence.*;

@Entity
@Table(name="eval_item_version")
public class EvalItemVersion {

	@Id
	@Column(name="item_version_no", nullable=false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long itemVersionNo;
	
	@Column(name="version", nullable=false)
	private String version;

	@ManyToOne(fetch=FetchType.EAGER, optional=true)
	@JoinColumn(name="eval_item_no", referencedColumnName="item_no", nullable=false)
	private EvalItem evalItem; // URL parameter 전송시에 <evalItem=작업할 번호 > 이렇게 보내면 된다.

	public EvalItemVersion() {
		
	}
	
	public EvalItemVersion(String version) {
		this.version = version;
	}

	public Long getItemVersionNo() {
		return itemVersionNo;
	}

	public void setItemVersionNo(Long itemVersionNo) {
		this.itemVersionNo = itemVersionNo;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public EvalItem getEvalItem() {
		return evalItem;
	}

	public void setEvalItem(EvalItem evalItem) {
		this.evalItem = evalItem;
	}

	@Override
	public String toString() {
		return "EvalItemVersion [itemVersionNo=" + itemVersionNo + ", version=" + version + ", evalItem=" + evalItem + "]";
	}
	
}

package com.douzone.df.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="eval_item")
public class EvalItem {
	
	@Column(name="item_no", nullable=false) // 컬럼명 & 길이 & notnull 지정
	@Id // primary key로 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increament를 위해 지정
    private Long itemNo;

    @Column(name="content", length=100, nullable=false) // nullable : db에 입력될 때 notnull
    @NotBlank // validation에서 null값을 허용하지 않는다.
	private String content;
    
    public EvalItem() {
    	
    }

	public EvalItem(String content) {
		this.content = content;
	}

	public Long getItemNo() {
		return itemNo;
	}

	public void setItemNo(Long itemNo) {
		this.itemNo = itemNo;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "EvalItem [itemNo=" + itemNo + ", content=" + content + "]";
	}    
	
}

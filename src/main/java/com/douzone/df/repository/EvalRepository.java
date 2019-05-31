package com.douzone.df.repository;


import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.douzone.df.model.Eval;

public interface EvalRepository extends JpaRepository<Eval, Long>{

	@Query(nativeQuery=true, value="select * from eval where user_task=:taskId") //  group by eval_item_version_no
	List<Eval> searchEval(@Param("taskId")Long taskId);

	@Query(nativeQuery=true, value="select distinct left(updated_at, 4) from report")
	ArrayList<String> searchYear();	
	

	@Query(nativeQuery=true, value="select count(*) from report where user_task_id =:taskId")
	public Long getTotalReport(@Param("taskId")Long taskId);

	@Query(nativeQuery=true, value="select count(*) from report where user_task_id=:taskId and status='HOLD'")
	public Long getHoldReport(@Param("taskId")Long taskId);

	@Query(nativeQuery=true, value="SELECT IF (EXISTS(select * from eval where user_task= :taskId), 1, 0) ")
	public Long isExistUserInEval(@Param("taskId")Long taskId);
	
	// 월간 & 년
	// 전체 보고서
	@Query(nativeQuery=true, value="select count(*) from report where user_task_id= :taskId and updated_at LIKE concat(:condition, '%') ")
	public Long getTotalMonthNYearRank(@Param("taskId") Long taskId, @Param("condition")String condition);
	// 반려보고서
	@Query(nativeQuery=true, value="select count(*) from report where user_task_id=:taskId and status='HOLD' and updated_at LIKE concat(:condition, '%')")
	public Long getHoldMonthNYearRank(@Param("taskId") Long taskId, @Param("condition")String condition);
	
	// 분기&반기
	// 전체 보고서
	@Query(nativeQuery=true, value="select count(*) from report where user_task_id=:taskId and DATE(updated_at) between :startDate and :endDate")
	public Long getTotalQuarterRank(@Param("taskId") Long taskId, @Param("startDate")String startDate, @Param("endDate")String endDate);
	// 반려 보고서
	@Query(nativeQuery=true, value="select count(*) from report where user_task_id=:taskId and status='HOLD' and DATE(updated_at) between :startDate and :endDate")
	public Long getHoldQuarterRank(@Param("taskId") Long taskId, @Param("startDate")String startDate, @Param("endDate")String endDate);

	
}

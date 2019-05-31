package com.douzone.df.repository;


import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.douzone.df.model.EvalItemScore;

@Repository
public interface EvalItemScoreReopsitory extends JpaRepository<EvalItemScore, Long> {


	@Query(nativeQuery = true, value="select item_score_no from eval_item_score order by item_score_no desc limit 1")
	public Long findRecentlySaved();

	@Query(nativeQuery=true, value="select item_score_no from eval_item_score "
									+ "where eval_item_no=:itemId "
									+ "and user_id=:userId "
									+ "and user_task_id=:userTaskId")
	public Long selectRow(@Param("itemId")Long itemId, 
						@Param("userId")Long userId, 
						@Param("userTaskId")Long userTaskId);
	
	@Modifying
	@Transactional
	@Query(nativeQuery=true, value="update eval_item_score set score=:score where item_score_no=:rowId")
	public void updateScore(Long rowId, Long score);

	@Query(nativeQuery=true, value="select score from eval_item_score where user_task_id= :taskId")
	public List<Long> findScoreByUserTaskId(@Param("taskId")Long taskId);

}

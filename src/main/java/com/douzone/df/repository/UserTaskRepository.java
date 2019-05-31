package com.douzone.df.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.douzone.df.model.Status;
import com.douzone.df.model.Task;
import com.douzone.df.model.User;
import com.douzone.df.model.UserTask;
import com.douzone.df.payload.UserTaskResponse;
@Repository
public interface UserTaskRepository extends JpaRepository<UserTask, Long> {
	 @Query("SELECT v FROM UserTask v WHERE v.user.id = :userId")
	List<UserTask> findAllByUserId(@Param("userId") Long userId);
	 
	 
	 Page<UserTask> findByStatus(Status progress, Pageable Pageable);


	 Page<UserTask> findByStatusAndUser(Status progress, User user, Pageable pageable);


	List<UserTask> findByUser(User user);

	 @Query("SELECT v FROM UserTask v WHERE v.user.id = :userId")
	List<UserTask> findByUserId(Long userId);

	 @Query("SELECT v.task.id FROM UserTask v WHERE v.user.id = :userId")
	List<Long> selectByUserId(Long userId);

	 @Query("SELECT v.task.id FROM UserTask v WHERE v.user.id = :userId and  v.status = :progress")
	List<Long> selectByUserId(@Param("progress") Status progress,@Param("userId") Long userId);
	 @Modifying
	 @Query("UPDATE UserTask v SET v.status = ?3 WHERE v.user.id = ?1 and  v.task.id = ?2")
	void deleteByUserIdAndArr(Long userId,Long taskId, Status end);
	 @Modifying
	 @Query("delete FROM UserTask v WHERE v.user.id = :userId and  v.task.id = :taskId")
	void deleteByUserIdAndTaskId(Long userId, Long taskId);

	 @Query("SELECT v FROM UserTask v WHERE v.user.id = ?1 and  v.status = ?2")
	List<UserTask> findByIdAndStatus(Long userId, Status progress);

	 @Query("SELECT NEW com.douzone.df.payload.UserTaskResponse(v.id,v.task.title) FROM UserTask v "
		 		+ "WHERE  v.status = ?2 and v.user.id = ?1 and v.endDate >= date(?3) ")
	List<UserTaskResponse> findByIdAndStatusAndDate(Long id, Status progress, String strToday);
	 
	 @Query("SELECT NEW com.douzone.df.payload.UserTaskResponse(v.id,v.task.title) FROM UserTask v "
		 		+ "WHERE  v.status = ?1")
	List<UserTaskResponse> findByState(Status progress);
	
	 @Query("SELECT v FROM UserTask v WHERE v.task.id = ?1")
	List<UserTask> findByTaskId(Long taskId);


	


	

}

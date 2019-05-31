package com.douzone.df.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.douzone.df.model.Status;
import com.douzone.df.model.Task;
import com.douzone.df.model.UserTask;
import com.douzone.df.payload.TaskResponse;


@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
	

	 Page<Task> findByStatus(Status progress, Pageable Pageable);

	





	List<Task> findByStatus(Status progress);








	List<Task> findAllByStatus(Status progress);






	@Query("SELECT v FROM Task v WHERE v.status = ?1 and(v.title LIKE %?2% or v.content LIKE %?2%)")
	List<Task> findAllByStatusAndSearch(Status progress, String search);






	 @Query("SELECT NEW com.douzone.df.payload.TaskResponse(v.id,v.title,v.content,v.createdAt) FROM Task v "
		 		+ "WHERE  v.status = ?1 and  v.title LIKE %?2% and v.createdAt >= date(?3) and v.createdAt <= date(?4) ORDER BY v.createdAt DESC")
	List<TaskResponse> findAllByStatus(Status valueOf, String search, String from, String to);






	


}

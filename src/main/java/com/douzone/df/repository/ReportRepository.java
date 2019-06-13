package com.douzone.df.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.douzone.df.model.Report;
import com.douzone.df.model.Status;
import com.douzone.df.model.User;
import com.douzone.df.model.UserTask;
import com.douzone.df.payload.Description;
import com.douzone.df.payload.GraphResponse;
import com.douzone.df.payload.Rate;
import com.douzone.df.payload.ReportResponse;
import com.douzone.df.payload.Slack;


@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {

	 @Query("SELECT v FROM Report v WHERE v.userTask.user.id = :userId")
	List<Report> findByUserId(@Param("userId") Long userId);

	List<Report> findByStatus(Status progress);
	
	
	 @Query("SELECT NEW com.douzone.df.payload.ReportResponse(v.id,v.title,v.content,v.userTask.task.title as taskTitle,v.userTask.user.name as userName,v.createdAt,v.status,v.fileName) FROM Report v "
	 		+ "WHERE  v.status = ?1 and  v.title LIKE %?2% and v.createdAt >= date(?3) and v.createdAt <= timestamp(?4) ORDER BY v.createdAt DESC")
	List<ReportResponse> findAllByStatus(Status status,String search,String from,String to);

	@Modifying
	@Query("UPDATE Report v SET v.status = ?2, v.description = ?3 WHERE v.id = ?1")
	void update(Long id, Status valueOf,String description);
	
	@Query("SELECT v FROM Report v "
	 		+ "WHERE  v.title LIKE %?1% and v.createdAt >= date(?2) and v.createdAt <= timestamp(?3) and v.userTask.user.id = ?4 and v.status!= ?5 ORDER BY v.createdAt DESC")
	List<Report> findAllById(String search, String from, String to, Long id,Status status);
	@Modifying
	@Query("UPDATE Report v SET  v.content = ?1 WHERE v.id = ?2")
	void update(String content, Long id);
	@Query("SELECT v FROM Report v "
	 		+ "WHERE  v.title LIKE %?1% and v.createdAt >= date(?2) and v.createdAt <= timestamp(?3) and v.userTask.id =?5 and v.userTask.user.id = ?4 and v.status!= ?6 ORDER BY v.createdAt DESC")
	List<Report> findAllById(String search, String from, String to, Long id, Long taskId,Status status);
	 @Query("SELECT NEW com.douzone.df.payload.ReportResponse(v.id,v.title,v.content,v.userTask.task.title as taskTitle,v.userTask.user.name as userName,v.createdAt,v.status,v.fileName) FROM Report v "
		 		+ "WHERE  v.status = ?1 and  v.title LIKE %?2% and v.createdAt >= date(?3) and v.createdAt <= timestamp(?4) and v.userTask.task.id = ?5 ORDER BY v.createdAt DESC")
	List<ReportResponse> findAllByStatus(Status valueOf, String search, String from, String to, Long userTaskId);
	@Query("SELECT NEW com.douzone.df.payload.Slack(v.userTask.user.slackKey,v.userTask.user.slackChannel) FROM Report v WHERE v.id = ?1")
	Slack slackFindById(Long reportId);

	@Query("SELECT NEW com.douzone.df.payload.GraphResponse(v.status,count(v.status) as count) FROM Report v WHERE v.userTask.user.id = ?1 AND v.status != ?2 AND v.status != ?3 group by v.status "
			+ "order by v.status desc")
	List<GraphResponse> graphFindById(Long id, Status wait, Status end);
	@Query("SELECT \r\n" + 
			"     NEW com.douzone.df.payload.Rate(DATE_FORMAT(a.createdAt, '%Y-%m') as m, COUNT(b.status) as hold, count(c.status) as progress) \r\n" + 
			"FROM \r\n" + 
			"    Report a \r\n" + 
			"        LEFT OUTER JOIN \r\n" + 
			"    Report b ON a.createdAt = b.createdAt\r\n" + 
			"        AND a.status = 'hold'\r\n" + 
			"   left outer join Report c on a.createdAt = c.createdAt and a.status = 'progress' \r\n" + 
			"WHERE a.userTask.user.id = ?1 "
			+
			"GROUP BY DATE_FORMAT(a.createdAt, '%Y-%m') ORDER BY DATE_FORMAT(a.createdAt, '%Y-%m') desc")
	List<Rate> RateFindByUserId(Long userId,PageRequest pageRequest);
	@Query("select NEW com.douzone.df.payload.Description(v.description,count(v.description)) FROM Report v WHERE v.userTask.user.id =?1 AND v.description != '' GROUP BY v.description ORDER BY count(v.description)")
	List<Description> descriptionFindByUserId(Long userId);
	


}

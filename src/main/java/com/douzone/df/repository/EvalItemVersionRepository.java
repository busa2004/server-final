package com.douzone.df.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.douzone.df.model.EvalItem;
import com.douzone.df.model.EvalItemVersion;


@Repository
public interface EvalItemVersionRepository extends JpaRepository<EvalItemVersion, Long>{

	@Query("SELECT v FROM EvalItemVersion v WHERE v.version= :version")
	List<EvalItemVersion> findByVersion(@Param("version") String version);

	@Query("SELECT evalItem FROM EvalItemVersion  WHERE version= :version")
	List<EvalItem> findEvalItemByVersion(@Param("version") String version);
	
	@Query("SELECT itemVersionNo FROM EvalItemVersion WHERE version = :version")
	List<Long> findNoByVersion(@Param("version") String version);	
	
	@Query("SELECT DISTINCT version FROM EvalItemVersion")
	List<String> findAllVersionName();

}

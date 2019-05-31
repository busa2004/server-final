package com.douzone.df.repository;

import com.douzone.df.model.User;
import com.douzone.df.payload.PasswordChangeRequest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Created by rajeevkumarsingh on 02/08/17.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Optional<User> findByUsernameOrEmail(String username, String email);

    List<User> findByIdIn(List<Long> userIds);
   
    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

	List<User> findAll();
	@Query("SELECT v FROM User v WHERE v.name LIKE %?1% or v.username LIKE %?1% or v.id LIKE %?1% or v.email LIKE %?1%")
	List<User> findAll(String search);

	 @Modifying
	 @Query("UPDATE User v SET v.profile = ?2 WHERE v.id = ?1")
	void update(Long id, String profile);
	 @Modifying
	 @Query("UPDATE User v SET v.password = ?2 WHERE v.id = ?3 AND v.password = ?1")
	void changePassword(String existPassword, String password, Long id);
	 @Modifying
	 @Query("UPDATE User v SET v.username = ?2,v.name =?3 , v.email = ?4, v.password= ?5 WHERE v.id = ?1")
	void modify(Long id, String username, String name, String email,String password);
	 @Modifying
	 @Query("UPDATE User v SET v.slackKey = ?2,v.slackChannel =?3 WHERE v.id = ?1")
	void slackUpdate(Long id, String slackKey, String slackChannel);
	

}

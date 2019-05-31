package com.douzone.df.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.douzone.df.model.User;
import com.douzone.df.model.UserTask;
import com.douzone.df.payload.Success;
import com.douzone.df.payload.UserTaskRequest;
import com.douzone.df.payload.UserTaskResponse;
import com.douzone.df.security.CurrentUser;
import com.douzone.df.security.UserPrincipal;
import com.douzone.df.service.UserTaskService;
import com.douzone.df.util.AppConstants;



@RestController
@RequestMapping("/api/usertask")
public class UserTaskController {
	 @Autowired
	 private UserTaskService UserTaskService;
	 	
	 	@PostMapping("/create")
	    public UserTask createUserTask(@Valid @RequestBody UserTaskRequest userTaskRequest) {
	 		UserTask test =UserTaskService.createUserTask(userTaskRequest);
	 		return test;
	    }
	 	
	 	@GetMapping("/allnopage")
	    public List<UserTask> getAllUserTaskNoPage(@CurrentUser UserPrincipal currentUser) {
	 		return  UserTaskService.getAllUserTaskNoPage(currentUser.getId());
	 	}
	 	
	 	
	 	@GetMapping("/get")
	    public List<User> getAllUserTask(Long taskId) {
	 		
	 		 List<UserTask> list =   UserTaskService.getUser(taskId);
	 		 List<User> user = new ArrayList<User>();
	 		 if(list != null) {
	 		 for(int i = 0 ; i < list.size();i++) {
	 			 user.add(list.get(i).getUser());
	 		 }
	 		 }
	
	 		 return user;
	 	}
	 	@GetMapping("/getByTask")
	 	 public List<UserTask> getAllUserTaskNoPage(Long taskId) {
	 		
	 		List<UserTask> list = UserTaskService.getByTask(taskId);
	 		
	 		return  list;
	 	}
	 	@GetMapping("/test")
	 	 public List<UserTask> test() {
	 		
	 		List<UserTask> list =null;
	 		
	 		return  list;
	 	}
	 	
	 	@GetMapping
	    public List<UserTaskResponse> getUserTask(@CurrentUser UserPrincipal currentUser) {
	 		return  UserTaskService.getUserTask(currentUser.getId());
	 	}
	 	@GetMapping("/getAll")
	    public List<UserTaskResponse> getAllUserTask() {
	 		return  UserTaskService.getAllUserTask();
	 	}
	 	@GetMapping("/date")
	    public List<UserTaskResponse> getUserTaskDate(@CurrentUser UserPrincipal currentUser) {
	 		return  UserTaskService.getUserTaskDate(currentUser.getId());
	 	}
	 	@PostMapping("/delete")
	    public Success deleteTask(@Valid @RequestBody UserTaskRequest userTaskRequest) {
	 		UserTaskService.deleteUserTask(userTaskRequest);
	 		Success success = new Success("ok");
	 		return success;
	    }
	 	
	 	
}

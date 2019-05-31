package com.douzone.df.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.douzone.df.exception.ResourceNotFoundException;
import com.douzone.df.model.Status;
import com.douzone.df.model.Task;
import com.douzone.df.model.User;
import com.douzone.df.model.UserTask;
import com.douzone.df.payload.UserTaskRequest;
import com.douzone.df.payload.UserTaskResponse;
import com.douzone.df.repository.TaskRepository;
import com.douzone.df.repository.UserRepository;
import com.douzone.df.repository.UserTaskRepository;
@Service
public class UserTaskService {
	 @Autowired
	  private TaskRepository taskRepository;
	 @Autowired
	  private UserRepository userRepository;
	 @Autowired
	  private UserTaskRepository userTaskRepository;

	public UserTask createUserTask(UserTaskRequest userTaskRequest) {
		User user = userRepository.findById(userTaskRequest.getUserId()).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", userTaskRequest.getUserId()));
		

		UserTask test = new UserTask();
		Long[] arr = userTaskRequest.getTaskIds();
		for(int i=0;i<arr.length;i++) {
		UserTask userTask = new UserTask();
		Task task = taskRepository.findById(arr[i]).orElseThrow(
                () -> new ResourceNotFoundException("Task", "id", userTaskRequest.getTaskId()));
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			userTask.setStartDate( transFormat.parse(userTaskRequest.getStartDate()));
			userTask.setEndDate(transFormat.parse(userTaskRequest.getEndDate()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		userTask.setTask(task);
		userTask.setUser(user);
		userTask.setStatus(Status.PROGRESS);
		test=userTaskRepository.save(userTask);
		}
		return test;
	}
	
	 @Transactional
	public void deleteUserTask(@Valid UserTaskRequest userTaskRequest) {


		Long[] arr = userTaskRequest.getTaskIds();
		for(int i=0;i<arr.length;i++) {
		userTaskRepository.deleteByUserIdAndArr(userTaskRequest.getUserId(),arr[i],Status.END);
		}
	
	}
	
	


	public List<UserTask> getAllUserTaskNoPage(Long userId) {
		return userTaskRepository.findAllByUserId(userId);
	}


	public List<UserTaskResponse> getUserTask(Long userId) {
		
		
		List<UserTask> list = userTaskRepository.findByIdAndStatus(userId,Status.PROGRESS);
		
		List<UserTaskResponse> responseList = new ArrayList<UserTaskResponse>();
		
		for(int i = 0 ; i < list.size(); i++) {
			responseList.add(new UserTaskResponse(
					list.get(i).getId(), 
					list.get(i).getTask().getTitle(), 
					list.get(i).getTask().getContent(), 
					list.get(i).getStartDate(), 
					list.get(i).getEndDate(),
					list.get(i).getCreatedAt(),
					list.get(i).getUpdatedAt(),i
					));
		}
		  
		  return responseList;
	}
	
	public List<UserTaskResponse> getUserTaskDate(Long id) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c1 = Calendar.getInstance();
		String strToday = sdf.format(c1.getTime());
		List<UserTaskResponse> responseList = userTaskRepository.findByIdAndStatusAndDate(id, Status.PROGRESS,strToday);
		for(int i = 0 ; i < responseList.size(); i++) {
			responseList.get(i).setKey(i);
		}
		return responseList;
	}
	
	public void deleteTask( UserTask userTaskRequest) {
		UserTask userTask = userTaskRepository.findById(userTaskRequest.getId()).orElseThrow(
	                () -> new ResourceNotFoundException("Task", "id", userTaskRequest.getId()));
		 userTask.setStatus(Status.END);
		  userTaskRepository.save(userTask);
	}





	public List<UserTask> getUser(Long taskId) {
		return userTaskRepository.findByTaskId(taskId);
	}

	public List<UserTaskResponse> getAllUserTask() {
		return userTaskRepository.findByState(Status.PROGRESS);
	}

	public List<UserTask> getByTask(Long taskId) {
		
		return userTaskRepository.findByTaskId(taskId);
	}

	

	
	

	



}





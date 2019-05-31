package com.douzone.df.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.douzone.df.exception.ResourceNotFoundException;
import com.douzone.df.model.Status;
import com.douzone.df.model.Task;
import com.douzone.df.payload.TaskRequest;
import com.douzone.df.payload.TaskResponse;
import com.douzone.df.repository.TaskRepository;
import com.douzone.df.repository.UserTaskRepository;

@Service
public class TaskService {
	  @Autowired
	  private TaskRepository taskRepository;
	  @Autowired
	  private UserTaskRepository usertaskRepository;
	  public Task createTask(Task taskRequest) {
		  Task task = new Task();
		  task.setTitle(taskRequest.getTitle());
		  task.setContent(taskRequest.getContent());
		  task.setStatus(Status.PROGRESS);
		  return taskRepository.save(task);
	  }
	  
	  public List<TaskResponse> getAllTaskNoPage() {
			
			List<Task> list  = taskRepository.findByStatus(Status.PROGRESS);
			List<TaskResponse> responseList = new ArrayList<TaskResponse>();
			
			for(int i = list.size()-1; i >-1;i--) {
				responseList.add(new TaskResponse(list.get(i).getId(),list.get(i).getTitle(),list.get(i).getContent(),i));
			}
			return responseList;
		}
	



	public void deleteTask(Long id) {
		  Task task = taskRepository.findById(id).orElseThrow(
	                () -> new ResourceNotFoundException("Task", "id", id));
		  task.setStatus(Status.END);
		  taskRepository.save(task);
	  }

	public void updateTask(Task taskRequest) {
		Task task = taskRepository.findById(taskRequest.getId()).orElseThrow(
                () -> new ResourceNotFoundException("Task", "id", taskRequest.getId()));
		  task.setTitle(taskRequest.getTitle());
		  task.setContent(taskRequest.getContent());
		taskRepository.save(task);
		
	}


	
	
	
	
	
	
	
	
	
	


	public List<TaskResponse> getTaskSelect(TaskRequest taskRequest) {
		List<Task> list = null;
		List<Long> removeList = usertaskRepository.selectByUserId(Status.PROGRESS,taskRequest.getUserId());
		if(taskRequest.getSearch()==null) {
		list = taskRepository.findAllByStatus(Status.PROGRESS);
		}else {
		list = taskRepository.findAllByStatusAndSearch(Status.PROGRESS,taskRequest.getSearch());
		}
		List<TaskResponse> responseList = new ArrayList<TaskResponse>();
		int status = 0;
		
		
		for(int i = list.size()-1; i >-1;i--) {

				if(removeList!= null && removeList.contains(list.get(i).getId())) {
					status = 0;
					
				}else {
					status = 1;
				}
				
			
			responseList.add(new TaskResponse(list.get(i).getId(),list.get(i).getTitle(),list.get(i).getContent(),i,status));
			
		}
		
		return responseList;
	}

	public List<TaskResponse> getAllTask(TaskRequest taskRequest) {
		List<TaskResponse> list = taskRepository.findAllByStatus(Status.valueOf(taskRequest.getStatus()),taskRequest.getSearch(),taskRequest.getFrom(),taskRequest.getTo());
		for(int i = 0 ; i < list.size();i++) {
			list.get(i).setKey(i);
		}
			
		return list;
	}

	

	public List<Task> getAll() {
		return taskRepository.findByStatus(Status.PROGRESS);
	}

	
	
	
	
	
	
	
	
	
	
}

package com.douzone.df.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.douzone.df.model.Task;
import com.douzone.df.payload.Success;
import com.douzone.df.payload.TaskRequest;
import com.douzone.df.payload.TaskResponse;
import com.douzone.df.repository.TaskRepository;
import com.douzone.df.service.TaskService;

@RestController
@RequestMapping("/api/task")
public class TaskController {
	 @Autowired
	 private TaskService TaskService;
	 @Autowired
	  private TaskRepository taskRepository;
	 	@PostMapping("/create")
	    public Task createTask( @RequestBody Task taskRequest) {
	        return  TaskService.createTask(taskRequest);
	    }
	 	
	 	@PostMapping("/all")
	    public List<TaskResponse> getAllTask( @RequestBody TaskRequest taskRequest) {
	        return TaskService.getAllTask(taskRequest);
	    }
	 	
	 	@PostMapping("/all/noSearch")
	    public List<Task> getAll() {
	        return TaskService.getAll();
	    }
	 	
	 	@PostMapping("/select")
	    public List<TaskResponse> getTaskSelect( @RequestBody TaskRequest taskRequest){
	 	
	 		List<TaskResponse> list = TaskService.getTaskSelect(taskRequest);
	 		for(int i = 0 ; i < list.size(); i++) {
	 			list.get(i);
	 		}
	    		 return list;
		}	
	 	
	 	@GetMapping("/all")
	    public List<TaskResponse> getAllTask(){
	    		 return TaskService.getAllTaskNoPage();
		}	
	 	
	 	@GetMapping("/update")
	    public void updateTask(@Valid @RequestBody Task taskRequest) {
	        TaskService.updateTask(taskRequest);
	    }
	 	
	 	@GetMapping("/delete")
	    public Success deleteTask(Long id) {
	        TaskService.deleteTask(id);
	        return new Success("ok");
	    }
	 	

	 	
}
package com.douzone.df.service;

import java.util.ArrayList;
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
import com.douzone.df.model.Report;
import com.douzone.df.model.Status;
import com.douzone.df.model.Task;
import com.douzone.df.model.User;
import com.douzone.df.model.UserTask;
import com.douzone.df.payload.ReportConverterRequest;
import com.douzone.df.payload.ReportRequest;
import com.douzone.df.payload.ReportResponse;
import com.douzone.df.payload.Slack;
import com.douzone.df.repository.ReportRepository;
import com.douzone.df.repository.UserRepository;
import com.douzone.df.repository.UserTaskRepository;

@Service
public class ReportService {
	 @Autowired
	  private UserTaskRepository userTaskRepository;

	   @Autowired
	   private ReportRepository reportRepository;
	
	   @Autowired
		  private UserRepository userRepository;

	public void createReport(ReportRequest reportRequest) {
		 Report report = new Report();
		 report.setTitle(reportRequest.getTitle());
		 report.setContent(reportRequest.getContent());
		 String fileNameArr="";
		 if(reportRequest.getFileName()!=null) {
		 for(String fileName : reportRequest.getFileName()) {
			 fileNameArr = fileNameArr.concat(fileName)+";";
		 }
		 fileNameArr = fileNameArr.substring(0,fileNameArr.length()-1);
		 }
		 report.setFileName(fileNameArr);
		 UserTask userTask = userTaskRepository.findById(reportRequest.getUserTaskId()).orElseThrow(
	                () -> new ResourceNotFoundException("report", "userTaskId", reportRequest.getUserTaskId()));
		 
		 report.setUserTask(userTask);
		 report.setStatus(Status.WAIT);
		 reportRepository.save(report);
	}
	public List<ReportResponse> getAllReport(ReportRequest reportRequest) {
		List<ReportResponse> list;
		if(reportRequest.getTaskId() == null) {
		 list = reportRepository.findAllByStatus(Status.valueOf(reportRequest.getStatus()),reportRequest.getSearch(),reportRequest.getFrom(),reportRequest.getTo());
		}else {
			list = reportRepository.findAllByStatus(Status.valueOf(reportRequest.getStatus()),reportRequest.getSearch(),reportRequest.getFrom(),reportRequest.getTo(),
					reportRequest.getTaskId());
		}
		for(int i = 0 ; i < list.size();i++) {
			list.get(i).setKey(i);
		}
		
		return list;
	}
	public List<Report> getUserReport(Long id, ReportRequest reportRequest) {
		List<Report> list;
		if(reportRequest.getTaskId()!=null) {
			list = reportRepository.findAllById(reportRequest.getSearch(),reportRequest.getFrom(),reportRequest.getTo(),id,reportRequest.getTaskId());
		}else {
			list = reportRepository.findAllById(reportRequest.getSearch(),reportRequest.getFrom(),reportRequest.getTo(),id);
		}
	
		return list;
	}
	
	
	@Transactional
	public void ReportConverter(ReportConverterRequest reportConverterRequest) {
		reportRepository.update(reportConverterRequest.getId(),Status.valueOf(reportConverterRequest.getState()),reportConverterRequest.getDescription());
	}

	public void deleteReport(Long id) {
		Report report = reportRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Report", "id", id));
		report.setStatus(Status.END);
		reportRepository.save(report);
	}

	
	

	@Transactional
	public void reportUpdate(String content,Long id) {
		reportRepository.update(content,id);		
	}
	public Slack getSlackInfo(Long reportId) {
		
		return reportRepository.slackFindById(reportId);
	}
	
	

	


	 
}

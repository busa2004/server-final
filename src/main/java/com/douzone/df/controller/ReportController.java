package com.douzone.df.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.douzone.df.model.Report;
import com.douzone.df.payload.FileUploadResponse;
import com.douzone.df.payload.ReportConverterRequest;
import com.douzone.df.payload.ReportRequest;
import com.douzone.df.payload.ReportResponse;
import com.douzone.df.payload.Slack;
import com.douzone.df.payload.Success;
import com.douzone.df.security.CurrentUser;
import com.douzone.df.security.UserPrincipal;
import com.douzone.df.service.FileUploadDownloadService;
import com.douzone.df.service.ReportService;
import com.douzone.df.util.AppConstants;

@RestController
@RequestMapping("/api/report")
public class ReportController {
	 private static final Logger logger = LoggerFactory.getLogger(ReportController.class);
	 @Autowired
	 private ReportService reportService;
	 @Autowired
	    private FileUploadDownloadService service;

	 	
	 	@PostMapping("/create")
	    public Success createReport(@Valid @RequestBody ReportRequest reportRequest) {
	 		
	        reportService.createReport(reportRequest);
	        Success success = new Success("ok");
	 		return success;
	    }
	 	//사용자 보고서
	 	@PostMapping("/all")
	    public List<Report> getAllReport(@CurrentUser UserPrincipal currentUser,
	    		@RequestBody ReportRequest reportRequest) {
	 		return reportService.getUserReport(currentUser.getId(),reportRequest);
	    }
	 	//모든 보고서
	 	@PostMapping("/al")
	    public List<ReportResponse> getAllReport(@RequestBody ReportRequest reportRequest) {
	        return reportService.getAllReport(reportRequest);
	    }
	 	
	 	//승인,반려,삭제
	 	@PostMapping("/update")
	    public Success ReportConverter(@RequestBody ReportConverterRequest reportConverterRequest) {
	         reportService.ReportConverter(reportConverterRequest);
	    	Success success = new Success("ok");
	 		return success;
	    }
	 	//수정
	 	@PostMapping("/modify")
	    public Success ReportUpdate(@RequestBody ReportRequest reportRequest) {
	         reportService.reportUpdate(reportRequest.getContent(),reportRequest.getId());
	    	Success success = new Success("ok");
	 		return success;
	    }
	 	@GetMapping("/slack")
	    public Slack getSlackInfo(Long reportId) {
	        
	 		return reportService.getSlackInfo(reportId);
	    }
	 	
	 	@GetMapping("/delete")
	    public Success deleteReport(Long id) {
	        reportService.deleteReport(id);
	        Success success = new Success("ok");
	 		return success;
	    }
	 	
	 	 @PostMapping("/uploadFile")
	     public FileUploadResponse uploadFile(@RequestParam("file") MultipartFile file) {
	 		
	        String fileName = service.storeFile(file);
	 		//String fileName =file.getOriginalFilename();
				System.out.println(file.getOriginalFilename());
			
	         String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
	                                 .path("/downloadFile/")
	                                 .path(fileName)
	                                 .toUriString();
	         
	         return new FileUploadResponse(fileName, fileDownloadUri, file.getContentType(), file.getSize());
	     }
	     
	     @PostMapping("/uploadMultipleFiles")
	     public List<FileUploadResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files){
	    	 System.out.println(files);
	         return Arrays.asList(files)
	                 .stream()
	                 .map(file -> uploadFile(file))
	                 .collect(Collectors.toList());
	     }
	     
	     @GetMapping("/downloadFile/{fileName:.+}")
	     public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request){
	          // Load file as Resource
	         Resource resource = service.loadFileAsResource(fileName);
	         System.out.println("fileName : "+ fileName);
	         // Try to determine file's content type
	         String contentType = null;
	         try {
	             contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
	         } catch (IOException ex) {
	             logger.info("Could not determine file type.");
	         }
	  
	         // Fallback to the default content type if type could not be determined
	         if(contentType == null) {
	             contentType = "application/octet-stream";
	         }
	  
	         return ResponseEntity.ok()
	                 .contentType(MediaType.parseMediaType(contentType))
	                 .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
	                 .body(resource);
	     }
	 	 
}

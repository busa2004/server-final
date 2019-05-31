package com.douzone.df.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
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

import com.douzone.df.exception.ResourceNotFoundException;
import com.douzone.df.model.Task;
import com.douzone.df.model.User;
import com.douzone.df.payload.FileUploadResponse;
import com.douzone.df.payload.PasswordChangeRequest;
import com.douzone.df.payload.Success;
import com.douzone.df.payload.UserIdentityAvailability;
import com.douzone.df.payload.UserProfile;
import com.douzone.df.payload.UserResponse;
import com.douzone.df.payload.UserSummary;
import com.douzone.df.repository.UserRepository;
import com.douzone.df.security.CurrentUser;
import com.douzone.df.security.UserPrincipal;
import com.douzone.df.service.FileUploadDownloadService;
import com.douzone.df.util.AppConstants;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserRepository userRepository;


    @Autowired
    PasswordEncoder passwordEncoder;
	 @Autowired
	    private FileUploadDownloadService service;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Transactional
    @PostMapping("/user/slack/update")
    public Success slackUpdate( @RequestBody User user,@CurrentUser UserPrincipal currentUser ) {
    	userRepository.slackUpdate(currentUser.getId(),user.getSlackKey(),user.getSlackChannel());
    	Success success = new Success("ok");
 		return success;
       
    }
 	
    @PostMapping("/uploadFile")
    public FileUploadResponse uploadFile(@RequestParam("file") MultipartFile file) {
        String fileName = service.storeFile(file);
        
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                                .path("/downloadFile/")
                                .path(fileName)
                                .toUriString();
        
        return new FileUploadResponse(fileName, fileDownloadUri, file.getContentType(), file.getSize());
    }
    @Transactional
    @RequestMapping(path = "/files", method = RequestMethod.POST)
    public ResponseEntity  handleFileUpload(@RequestParam("file") MultipartFile file,Long id) {
    	try {
            System.out.printf("File name=%s, size=%s\n", file.getOriginalFilename(),file.getSize());
            //creating a new file in some local directory
            //path
            File fileToSave = new File("/uploads/images/" + file.getOriginalFilename());
            //copy file content from received file to new local file
            file.transferTo(fileToSave);
            userRepository.update(id,file.getOriginalFilename());
        } catch (IOException ioe) {
            //if something went bad, we need to inform client about it
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        //everything was OK, return HTTP OK status (200) to the client
        return ResponseEntity.ok().build();
    }
    @Transactional
    @PostMapping("/user/changePassword")
    public Success changePassword( @RequestBody PasswordChangeRequest request) {
    	userRepository.changePassword(passwordEncoder.encode(request.getExistPassword()),passwordEncoder.encode(request.getPassword()),request.getId());
    	Success success = new Success("ok");
 		return success;
       
    }
 	
    
    
    @GetMapping("/user/me")
  //  @PreAuthorize("hasRole('USER')")
    public UserSummary getCurrentUser(@CurrentUser UserPrincipal currentUser) {
        UserSummary userSummary = new UserSummary(currentUser.getId(), currentUser.getUsername(), currentUser.getName(),currentUser.getAuthorities(),currentUser.getSlackKey(),currentUser.getSlackChannel());
        return userSummary;
    }
    @GetMapping("/user/profile")
    public Optional<User> getCurrentUser(Long userId) {
    	
        return userRepository.findById(userId);
    }
    @Transactional
    @PostMapping("/user/modify")
    public Success getCurrentUser( @RequestBody User user) {
    	 user.setPassword(passwordEncoder.encode(user.getPassword()));
         userRepository.modify(user.getId(),user.getUsername(),user.getName(),user.getEmail(),user.getPassword());
         Success success = new Success("ok");
	 	return success;
    }
    @GetMapping("user/all")
    public List<UserResponse> getAllUser(String search){
    	List<User> list = userRepository.findAll(search);
    	List<UserResponse> responseList = new ArrayList<UserResponse>();
		
		for(int i = list.size()-1; i >-1;i--) {
			responseList.add(new UserResponse(list.get(i).getName(),list.get(i).getEmail(),list.get(i).getId(),i,list.get(i).getProfile()));
		}
		
    		 return responseList;
	}
    
    @GetMapping("/user/checkUsernameAvailability")
    public UserIdentityAvailability checkUsernameAvailability(@RequestParam(value = "username") String username) {
        Boolean isAvailable = !userRepository.existsByUsername(username);
        return new UserIdentityAvailability(isAvailable);
    }

    @GetMapping("/user/checkEmailAvailability")
    public UserIdentityAvailability checkEmailAvailability(@RequestParam(value = "email") String email) {
        Boolean isAvailable = !userRepository.existsByEmail(email);
        return new UserIdentityAvailability(isAvailable);
    }

    @GetMapping("/users/{username}")
    public UserProfile getUserProfile(@PathVariable(value = "username") String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));

        UserProfile userProfile = new UserProfile(user.getId(), user.getUsername(), user.getName(), user.getCreatedAt(),user.getProfile());
        

        return userProfile;
    }



}

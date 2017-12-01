package com.activitytracker.springboot.util;

import java.nio.charset.Charset;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;

import com.activitytracker.springboot.model.User;
import com.activitytracker.springboot.service.UserService;

public class ApiPath {
	public static final String ADMIN_API = "/admin";
	public static final String USER_API = "/api";
	
	
	public User getUserForCredential(String authorization,UserService userService){
		
		User userExist = null;
		 if (authorization != null && authorization.startsWith("Basic")) {
		        // Authorization: Basic base64credentials
		        String base64Credentials = authorization.substring("Basic".length()).trim();
		        String credentials = new String(Base64.getDecoder().decode(base64Credentials),
		                Charset.forName("UTF-8"));
		        // credentials = username:password
		        final String[] values = credentials.split(":",2);
		        String emailId = values[0];
		        userExist = userService.isUserExistWithEmail(emailId);
		 }
		 return userExist;
	}
}

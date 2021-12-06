package com.example.spring.web.thedemoserver.Controller;


import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class WelcomeController {

	
@RequestMapping(value ="/", method=RequestMethod.GET)
public String LoginPage(ModelMap model) {
	model.put("name" , getLoggedinUserName());
	return "welcome";
}
public String getLoggedinUserName() {
	Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	if(principal instanceof UserDetails) {
		return ((UserDetails) principal).getUsername();
	}
	return principal.toString();
}
}

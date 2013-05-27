package com.transience.sandbox.controllers;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.transience.sandbox.commandobjects.*;
import com.transience.sandbox.domain.User;
import com.transience.sandbox.services.*;


@Controller
@RequestMapping("/user")
public class UserServicesController {
	
	@Autowired
	private IUserService userService; 
	 
	@RequestMapping(value = "showLogin", method = RequestMethod.GET)
	public ModelAndView showLogin(Model uiModel) { 
		
		ModelAndView mav = new ModelAndView("login", "loginFormCommand", new LoginFormCommand("admin", "admin"));		
		return mav; 
	   
	}
	
	@RequestMapping(value = "doLogin", method = RequestMethod.POST)
	public ModelAndView doLogin(Model uiModel) { 
		
		System.out.println("Inside doLogin method");
		User user = userService.authenticate("admin", "admin");
		System.out.println("Username: " + user.getUsername());
		
		ModelAndView mav = new ModelAndView("login", "loginFormCommand", new LoginFormCommand("admin", "admin"));		
		return mav; 
	   
	}

}

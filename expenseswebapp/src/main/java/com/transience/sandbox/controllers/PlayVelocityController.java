package com.transience.sandbox.controllers;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/velocity")
public class PlayVelocityController {

	protected final Log logger = LogFactory.getLog(getClass());
	
	@RequestMapping(value="/sampleView")
	public String getSampleMessageView() throws Exception {
		logger.info("Invoked velocity handler^%^%*");
 		Map<String, String> modelMap = new HashMap<String, String>();
		modelMap.put("message", "Hello world, welcome to spring mvc and velocity integration");
		return "sample";
	}
}

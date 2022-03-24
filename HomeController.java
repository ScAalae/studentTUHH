package com.spring.app.controller;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping(value= "/")
public class HomeController {

    private static Logger logger = LogManager.getLogger(HomeController.class);

	@RequestMapping(value= "/home", method= RequestMethod.GET)
	public ModelAndView home() {
		logger.debug("-------------------------Home Controller called.");

		ModelAndView modelview = new ModelAndView();
		modelview.addObject("welcome_msg", "Spring Mvc Internationalization Example");		
		modelview.setViewName("home");
		return modelview;
	}
}

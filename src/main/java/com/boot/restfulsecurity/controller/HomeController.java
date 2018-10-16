package com.boot.restfulsecurity.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class HomeController {

	@RequestMapping("/")
	public String home() {
		return "index";
	}

	/**
	 * an API to test exception handling
	 * e.g. url : http://localhost:8080/testerror/123
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/testerror/{id}")
	public String myerror(@PathVariable(value = "id") Integer id) throws Exception {
		String someData = null;
		if (someData == null) {
			throw new Exception("We couldn't find the post with ID: " + id);
		}
		return someData;
	}
	
	/**
	 * Replaced with global handlers
	 */
	/*@ExceptionHandler(Exception.class)
	public String handleException(HttpServletRequest req, Exception exception, Model model){
		model.addAttribute("errorMessage", exception.getMessage() );
		return "myException";
	}*/
}

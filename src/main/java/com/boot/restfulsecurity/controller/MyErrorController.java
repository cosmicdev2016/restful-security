package com.boot.restfulsecurity.controller;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;


@Controller
public class MyErrorController implements ErrorController {

	private static final String ERROR_PATH = "/error";
	private static final String ERROR_TEMPLATE = "myError";

	private final ErrorAttributes errorAttributes;

	@Autowired
	public MyErrorController(ErrorAttributes errorAttributes) {
		this.errorAttributes = errorAttributes;
	}

	/**
	 * 1. Overriding default error path and doing something else
	 * 2. Overrides error.html also
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(ERROR_PATH)
	public String myErrorHandler(Model model, HttpServletRequest request) {

		// {error={timestamp=Mon Nov 02 12:40:50 EST 2015, status=404, error=Not Found, message=No message available, path=/foo}}
		Map<String, Object> error = getErrorAttributes(request, true);

		model.addAttribute("timestamp", error.get("timestamp"));
		model.addAttribute("status", error.get("status"));
		model.addAttribute("error", error.get("error"));
		model.addAttribute("message", error.get("message"));
		model.addAttribute("path", error.get("path"));
		
		// extra logging, send notifications, etc...

		return ERROR_TEMPLATE;
	}

	@Override
	public String getErrorPath() {
		return ERROR_PATH;
	}

	private Map<String, Object> getErrorAttributes(HttpServletRequest request, boolean includeStackTrace) {
		RequestAttributes requestAttributes = new ServletRequestAttributes(request);
		return this.errorAttributes.getErrorAttributes(requestAttributes, includeStackTrace);
	}

	@RequestMapping("/404")
	public String pageNotFound(Model model, HttpServletRequest request) {
		model.addAttribute("error", getErrorAttributes(request, true));
		return "my404Error";
	}
}

package com.boot.restfulsecurity.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/posts")
public class PostController {

	/**
	 * Method level security
	 * @return
	 */

	@RequestMapping(value = "/list")
	public String list() {
		return "list posts..";
	}

	//@Secured("ROLE_USER")
	@RequestMapping(value = "/drafts")
	public String viewDrafts() {
		return "view drafts..";
	}

	//@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/add")
	public String addPost() {
		return "add posts..";
	}

	@RequestMapping(value = "/about")
	public String aboutPage() {
		return "about page..";
	}
}

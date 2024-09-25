package com.example.spring_boot.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;


@Controller
public class WebController {

	@GetMapping("/")
	public String home(Model model) {
		return "home";
	}

	@GetMapping("/calendar")
	public String calendar(Model model) {
		return "calendar";
	}

}
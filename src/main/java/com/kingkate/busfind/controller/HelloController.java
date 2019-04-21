package com.kingkate.busfind.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/hello")
@ApiIgnore
public class HelloController {
	
	@RequestMapping("hi")
	public String hi() {
		return "Hi,hello!!";
	}

	@RequestMapping("hi2")
	public void hi2() {
		System.out.println("Hi,hello!!");
	}
}

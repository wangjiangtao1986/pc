package com.wang.aishenhuo.pc.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@RequestMapping("/")
	@ResponseBody
	String home() {
		return "Hello World!";
	}
}
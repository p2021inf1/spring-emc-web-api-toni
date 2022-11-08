package com.emc.demo.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//RestController
//indica a springboot que es una bean de spring per injectar-lo 
//i al mateix temps que es una api rest que pot ser cridada amb els verbs hhtp de forma remota

//RequestMapping
//indicar la URL que executara aquest controlador

//slf4 implementa una facete pel framework de log

@RestController
@RequestMapping("HelloWorld")
public class HelloWorldController {
	
	static final Logger Log = LoggerFactory
			.getLogger(HelloWorldController.class);
	
	@GetMapping("/hello")
	@CrossOrigin
	public String helloWorld(
			@RequestParam(value = "name", defaultValue = "World") String name) {
		Log.info("helloWorld method started");
		return "Hello" + name + "!!";
	}

}

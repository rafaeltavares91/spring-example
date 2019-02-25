package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.FakeDataSource;
import com.example.service.AnimalService;

@Controller
@RequestMapping("/animal")
public class AnimalController {
	
	@Autowired
	private Environment env;

	private AnimalService animalService;
	
	private FakeDataSource fakeDataSource;
	
	public AnimalController(AnimalService animalService, FakeDataSource fakeDataSource) {
		this.animalService = animalService;
		this.fakeDataSource = fakeDataSource;
	}
	
	@RequestMapping("")
	public String animal() {
		animalService.findAll();
		System.out.println(fakeDataSource.getName()+fakeDataSource.getUsername());
		System.out.println(env.getProperty("minhaVariavel"));
		return "animal/index";
	}
}

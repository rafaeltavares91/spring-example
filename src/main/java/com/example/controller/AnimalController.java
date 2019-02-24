package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.service.AnimalService;

@Controller
@RequestMapping("/animal")
public class AnimalController {

	private AnimalService animalService;
	
	public AnimalController(AnimalService animalService) {
		this.animalService=animalService;
	}
	
	@RequestMapping("")
	public String animal() {
		animalService.findAll();
		return "animal/index";
	}
}

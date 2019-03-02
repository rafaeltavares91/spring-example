package com.example.demo.service.impl;

import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Animal;
import com.example.demo.repository.AnimalRepository;
import com.example.demo.service.AnimalService;

@Service
@Profile("prod")
@Primary
public class AnimalServiceSecondary implements AnimalService {

	private final AnimalRepository animalRepository;
	
	public AnimalServiceSecondary(AnimalRepository animalRepository) {
		this.animalRepository=animalRepository;
	}
	
	public Iterable<Animal> findAll(){
		return animalRepository.findAll();
	}
}

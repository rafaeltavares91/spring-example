package com.example.demo.service.impl;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Animal;
import com.example.demo.repository.AnimalRepository;
import com.example.demo.service.AnimalService;

@Service
@Profile("dev")
public class AnimalServiceImpl implements AnimalService {
	
	private final AnimalRepository animalRepository;
	
	public AnimalServiceImpl(AnimalRepository animalRepository) {
		this.animalRepository=animalRepository;
	}
	
	public Iterable<Animal> findAll(){
		return animalRepository.findAll();
	}
}

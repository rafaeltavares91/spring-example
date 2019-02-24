package com.example.service;

import org.springframework.stereotype.Service;

import com.example.domain.Animal;
import com.example.repository.AnimalRepository;

@Service
public class AnimalService {
	private AnimalRepository animalRepository;
	
	public AnimalService(AnimalRepository animalRepository) {
		this.animalRepository=animalRepository;
	}
	
	public Iterable<Animal> findAll(){
		return animalRepository.findAll();
	}
}

package com.example.service;

import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.example.domain.Animal;
import com.example.repository.AnimalRepository;

@Service
@Profile("prod")
@Primary
public class AnimalServiceSecondary implements AnimalService {

	private AnimalRepository animalRepository;
	
	public AnimalServiceSecondary(AnimalRepository animalRepository) {
		this.animalRepository=animalRepository;
	}
	
	public Iterable<Animal> findAll(){
		return animalRepository.findAll();
	}
}

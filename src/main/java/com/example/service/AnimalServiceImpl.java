package com.example.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.example.domain.Animal;
import com.example.repository.AnimalRepository;

@Service
@Profile("dev")
public class AnimalServiceImpl implements AnimalService {
	
	private AnimalRepository animalRepository;
	
	public AnimalServiceImpl(AnimalRepository animalRepository) {
		this.animalRepository=animalRepository;
	}
	
	public Iterable<Animal> findAll(){
		return animalRepository.findAll();
	}
}

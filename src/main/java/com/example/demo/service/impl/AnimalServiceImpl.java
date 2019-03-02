package com.example.demo.service.impl;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Animal;
import com.example.demo.repository.AnimalRepository;
import com.example.demo.service.AnimalService;

@Service
@Profile("dev")
public class AnimalServiceImpl extends CRUDService<Animal> implements AnimalService {

	public AnimalServiceImpl(AnimalRepository repository) {
		super(repository);
	}
	
}
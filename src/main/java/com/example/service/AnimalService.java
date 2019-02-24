package com.example.service;

import org.springframework.stereotype.Service;

import com.example.domain.Animal;

@Service
public interface AnimalService {

	public Iterable<Animal> findAll();
	
}

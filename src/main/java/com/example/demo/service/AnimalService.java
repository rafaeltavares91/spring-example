package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.domain.Animal;

@Service
public interface AnimalService {

	public Iterable<Animal> findAll();
	
}

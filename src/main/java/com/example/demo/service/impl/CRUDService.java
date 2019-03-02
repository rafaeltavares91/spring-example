package com.example.demo.service.impl;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.service.AbstractCRUDService;

public abstract class CRUDService<T> implements AbstractCRUDService<T> {

	private CrudRepository<T, Long> repository;
	
	public CRUDService(CrudRepository<T, Long> repository) {
		this.repository = repository;
	}
	
	public Iterable<T> findAll() {
		return repository.findAll();
	}
	
	@Override
	public T save(T t) {
		return repository.save(t);
	}

}
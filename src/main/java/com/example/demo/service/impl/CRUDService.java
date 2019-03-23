package com.example.demo.service.impl;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.service.AbstractCRUDService;
import com.google.common.collect.Sets;

public abstract class CRUDService<T> implements AbstractCRUDService<T> {

	private CrudRepository<T, Long> repository;
	
	public CRUDService(CrudRepository<T, Long> repository) {
		this.repository = repository;
	}
	
	@Override
	public Set<T> findAll() {
		return Sets.newHashSet(repository.findAll());
	}
	
	@Override
    public Optional<T> findById(Long id) {
        return repository.findById(id);
    }
	
	@Override
	public T save(T t) {
		return repository.save(t);
	}
	
	 @Override
    public void delete(T object) {
		repository.delete(object);
    }

    @Override
    public void deleteById(Long id) {
    	repository.deleteById(id);
    }

}
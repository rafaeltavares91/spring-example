package com.example.demo.service;

import java.util.Optional;
import java.util.Set;

public interface AbstractCRUDService<T> {

	Set<T> findAll();

    Optional<T> findById(Long id);

    T save(T object);

    void delete(T object);

    void deleteById(Long id);
	
}
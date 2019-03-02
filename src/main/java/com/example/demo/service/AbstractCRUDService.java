package com.example.demo.service;

public interface AbstractCRUDService<T> {

	T save(T t);
	
	Iterable<T> findAll();
	
}
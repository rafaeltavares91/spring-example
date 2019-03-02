package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Animal;

@Repository 
public interface AnimalRepository extends CrudRepository<Animal, Integer>{

}

package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Pessoa;

@Repository
public interface PessoaRepository extends CrudRepository<Pessoa, Long> {

	Optional<Pessoa> findByNome(String nome);
	
	List<Pessoa> findAllByNome(String nome);
	
	List<Pessoa> findAllByNomeLike(String nome);
	
}
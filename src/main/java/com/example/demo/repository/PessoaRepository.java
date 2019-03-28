package com.example.demo.repository;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Pessoa;

@Repository
public interface PessoaRepository extends CrudRepository<Pessoa, Long> {

	Optional<Pessoa> findByNome(String nome);
	
	Set<Pessoa> findByNomeContainingIgnoreCase(String nome);
	
	Pessoa findFirstByOrderBySalarioDesc();
	
	@Query("SELECT SUM(p.salario) from Pessoa p")
    double findSomaTodosSalarios();
	
}
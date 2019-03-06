package com.example.demo.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.domain.Pessoa;

@Service
public interface PessoaService extends AbstractCRUDService<Pessoa> {

	Optional<Pessoa> findByNome(String nome);
	
}
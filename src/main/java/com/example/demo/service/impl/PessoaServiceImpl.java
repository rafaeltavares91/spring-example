package com.example.demo.service.impl;

import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.example.demo.domain.Pessoa;
import com.example.demo.repository.PessoaRepository;
import com.example.demo.service.PessoaService;

@Service
public class PessoaServiceImpl extends CRUDService<Pessoa> implements PessoaService {

	private PessoaRepository repository;
	
	public PessoaServiceImpl(PessoaRepository repository) {
		super(repository);
		this.repository = repository;
	}

	@Override
	public Optional<Pessoa> findByNome(String nome) {
		return repository.findByNome(nome);
	}
	
	@Override
	public Set<Pessoa> findAllByNomeLike(String nome) {
		return repository.findByNomeContainingIgnoreCase(nome);
	}
	
}
package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;

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
	public List<Pessoa> findAllByNome(String nome) {
		return repository.findAllByNome(nome);
	}
	
	@Override
	public List<Pessoa> findAllByNomeLike(String nome) {
		return repository.findAllByNomeLike(nome);
	}
	
}
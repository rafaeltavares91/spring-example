package com.example.demo.service.impl;

import org.springframework.stereotype.Service;

import com.example.demo.domain.Pessoa;
import com.example.demo.repository.PessoaRepository;
import com.example.demo.service.PessoaService;

@Service
public class PessoaServiceImpl extends CRUDService<Pessoa> implements PessoaService {

	public PessoaServiceImpl(PessoaRepository repository) {
		super(repository);
	}
	
}
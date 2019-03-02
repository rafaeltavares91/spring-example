package com.example.demo.service.impl;

import org.springframework.stereotype.Service;

import com.example.demo.domain.Pessoa;
import com.example.demo.repository.PessoaRepository;
import com.example.demo.service.PessoaService;

@Service
public class PessoaServiceImpl implements PessoaService {

	private final PessoaRepository pessoaRepository;
	
	public PessoaServiceImpl(PessoaRepository pessoaRepository) {
		this.pessoaRepository=pessoaRepository;
	}
	
	public Iterable<Pessoa> findAll(){
		return pessoaRepository.findAll();
	}
	
}

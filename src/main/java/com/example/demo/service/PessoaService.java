package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.domain.Pessoa;

@Service
public interface PessoaService {

	public Iterable<Pessoa> findAll();
	
}
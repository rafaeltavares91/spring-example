package com.example.demo.service.impl;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.demo.domain.Pessoa;
import com.example.demo.repository.PessoaRepository;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

public class PessoaServiceImplTest {

	private PessoaServiceImpl pessoaService;
	
	@Mock
	private PessoaRepository pessoaRepository;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		pessoaService = new PessoaServiceImpl(pessoaRepository);
	}
	
	@Test
	public void findAll() {
		Pessoa p1 = new Pessoa();
		p1.setId(1l);
		p1.setNome("Rafael Tavares");
		Pessoa p2 = new Pessoa();
		p2.setId(2l);
		p2.setNome("Vanessa Dantas");
		
		Iterable<Pessoa> pessoasExpected = Lists.newArrayList(p1, p2);
		
		when(pessoaRepository.findAll()).thenReturn(pessoasExpected);
		
		Iterable<Pessoa> pessoasTest = pessoaService.findAll();
		
		assertEquals(Iterables.size(pessoasTest), Iterables.size(pessoasExpected));
		verify(pessoaRepository, times(1)).findAll();
	}
}

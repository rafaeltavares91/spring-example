package com.example.demo.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PessoaTest {
	
	private Pessoa pessoa;

	@BeforeEach
	public void setUp() {
		pessoa = new Pessoa();
	}
	
	@Test
	public void getId() {
		Long idValue = 1l;
		
		pessoa.setId(idValue);
		
		assertEquals(idValue, pessoa.getId());
	}
	
}

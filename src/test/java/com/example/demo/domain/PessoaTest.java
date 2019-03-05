package com.example.demo.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.example.demo.domain.Pessoa;

public class PessoaTest {
	
	private Pessoa pessoa;

	@Before
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

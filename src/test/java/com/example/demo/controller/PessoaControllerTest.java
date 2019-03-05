package com.example.demo.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.anyList;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import com.example.demo.service.PessoaService;

public class PessoaControllerTest {

	private PessoaController pessoaController;
	
	@Mock
	private PessoaService pessoaService;
	
	@Mock
	private Model model;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		pessoaController = new PessoaController(pessoaService);
	}
	
	@Test
	public void pessoa() {
		String viewName = pessoaController.pessoa(model);
		assertEquals("pessoa/list", viewName);
		verify(pessoaService, times(1)).findAll();
		verify(model, times(1)).addAttribute(eq("pessoas"), anyList());
	}
	
}

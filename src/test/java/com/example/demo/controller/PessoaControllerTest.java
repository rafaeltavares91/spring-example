package com.example.demo.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import com.example.demo.domain.Pessoa;
import com.example.demo.service.PessoaService;
import com.google.common.collect.Sets;

public class PessoaControllerTest {

	private PessoaController pessoaController;
	
	@Mock
	private PessoaService pessoaService;
	
	@Mock
	private Model model;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		pessoaController = new PessoaController(pessoaService);
	}
	
	@Test
	public void testMockMVC() throws Exception {
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(pessoaController).build();
		mockMvc.perform(get("/pessoa"))
			.andExpect(status().isOk())
			.andExpect(view().name("pessoa/list"));
	}
	
	@Test
	public void pessoa() {
		Pessoa p1 = new Pessoa();
		p1.setId(1l);
		p1.setNome("Rafael Tavares");
		Pessoa p2 = new Pessoa();
		p2.setId(2l);
		p2.setNome("Vanessa Dantas");
		Set<Pessoa> pessoas = Sets.newHashSet(p1, p2);
		
		when(pessoaService.findAll()).thenReturn(pessoas);
		
		@SuppressWarnings("unchecked")
		ArgumentCaptor<Set<Pessoa>> argumentoCaptor = ArgumentCaptor.forClass(Set.class);
		
		String viewName = pessoaController.pessoa(model);
		assertEquals("pessoa/list", viewName);
		verify(pessoaService, times(1)).findAll();
		verify(model, times(1)).addAttribute(eq("pessoas"), argumentoCaptor.capture());
		Set<Pessoa> listInController = argumentoCaptor.getValue();
		assertEquals(2, listInController.size());
	}
	
}

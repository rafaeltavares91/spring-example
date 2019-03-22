package com.example.demo.controller;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.demo.domain.Pessoa;
import com.example.demo.service.PessoaService;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

/**
 * 
 * @author Rafael Tavares
 *
 */
@ExtendWith(MockitoExtension.class)
public class PessoaControllerTest {

	@InjectMocks
	private PessoaController controller;
	
	@Mock
	private PessoaService pessoaService;
	
	private Set<Pessoa> pessoas;

	private MockMvc mockMvc;
	
	@BeforeEach
	public void setUp() {
		pessoas = Sets.newHashSet();
		pessoas.add(Pessoa.builder().id(1l).build());
		pessoas.add(Pessoa.builder().id(2l).build());
		
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}
	
	@Test
	public void list() throws Exception {
		when(pessoaService.findAll()).thenReturn(pessoas);
		
		mockMvc.perform(get("/pessoa"))
			.andExpect(status().isOk())
			.andExpect(view().name("pessoa/list"))
			.andExpect(model().attribute("pessoas", hasSize(2)));
	}
	
	@Test
	public void show() throws Exception {
		when(pessoaService.findById(anyLong())).thenReturn(Pessoa.builder().id(1l).build());
		
		mockMvc.perform(get("/pessoa/show/1231"))
			.andExpect(status().isOk())
			.andExpect(view().name("pessoa/show"))
			.andExpect(model().attribute("pessoa", hasProperty("id", is(1l))));
	}
	
	@Test
    void find() throws Exception {
        mockMvc.perform(get("/pessoa/find"))
                .andExpect(status().isOk())
                .andExpect(view().name("pessoa/find"))
                .andExpect(model().attributeExists("pessoa"));

        verifyZeroInteractions(pessoaService);
    }
	
	@Test
    void processFindReturnOne() throws Exception {
        when(pessoaService.findAllByNomeLike(anyString())).thenReturn(Lists.newArrayList(Pessoa.builder().id(1l).build()));

        mockMvc.perform(get("/pessoa/procesFind"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/pessoa/show/1"));
    }
	
	@Test
    void processFindReturnMany() throws Exception {
        when(pessoaService.findAllByNomeLike(anyString()))
                .thenReturn(Lists.newArrayList(
                		Pessoa.builder().id(1l).build(),
                		Pessoa.builder().id(2l).build()));

        mockMvc.perform(get("/pessoa/procesFind"))
                .andExpect(status().isOk())
                .andExpect(view().name("pessoa/list"))
                .andExpect(model().attribute("pessoas", hasSize(2)));
    }
	
	@Test
    void processFindEmptyReturnMany() throws Exception {
        when(pessoaService.findAllByNomeLike(anyString()))
                .thenReturn(Lists.newArrayList(
                		Pessoa.builder().id(1l).build(),
                		Pessoa.builder().id(2l).build()));

        mockMvc.perform(get("/pessoa/procesFind").param("nome",""))
                .andExpect(status().isOk())
                .andExpect(view().name("pessoa/list"))
                .andExpect(model().attribute("pessoas", hasSize(2)));
    }
	
	@Test
    void initCreationForm() throws Exception {
        mockMvc.perform(get("/pessoa/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("pessoa/form"))
                .andExpect(model().attributeExists("pessoa"));

        verifyZeroInteractions(pessoaService);
    }

    @Test
    void processCreationForm() throws Exception {
        when(pessoaService.save(ArgumentMatchers.any())).thenReturn(Pessoa.builder().id(1l).build());

        mockMvc.perform(post("/pessoa/new"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/pessoa/show/1"))
                .andExpect(model().attributeExists("pessoa"));

        verify(pessoaService).save(ArgumentMatchers.any());
    }
    
    @Test
    void initUpdateForm() throws Exception {
        when(pessoaService.findById(anyLong())).thenReturn(Pessoa.builder().id(1l).build());

        mockMvc.perform(get("/pessoa/1/edit"))
                .andExpect(status().isOk())
                .andExpect(view().name("pessoa/form"))
                .andExpect(model().attributeExists("pessoa"));

        verifyZeroInteractions(pessoaService);
    }

    @Test
    void processUpdateForm() throws Exception {
        when(pessoaService.save(ArgumentMatchers.any())).thenReturn(Pessoa.builder().id(1l).build());

        mockMvc.perform(put("/pessoa/1/edit"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/pessoa/show/1"))
                .andExpect(model().attributeExists("pessoa"));

        verify(pessoaService).save(ArgumentMatchers.any());
    }
	
    @Test
    void delete() throws Exception {
        mockMvc.perform(get("/pessoa/delete/1"))
        	.andExpect(status().is3xxRedirection())
        	.andExpect(view().name("redirect:/pessoa/list"));

        verify(pessoaService).deleteById(1l);
    }
    
}

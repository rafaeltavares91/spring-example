package com.example.demo.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.domain.Pessoa;
import com.example.demo.repository.PessoaRepository;

@ExtendWith(MockitoExtension.class)
class PessoaServiceImplTest {
	
	public static final String NOME = "Rafael Tavares";
	
    @Mock
    PessoaRepository repository;
    
    @InjectMocks
    PessoaServiceImpl service;

    Optional<Pessoa> pessoaRetornada;
	
	@BeforeEach
	void setUp() {
		pessoaRetornada = Optional.of(Pessoa.builder().id(1l).nome(NOME).build());
	}
	
	@Test
    void findByNome() {
        when(repository.findByNome(any())).thenReturn(pessoaRetornada);

        Optional<Pessoa> pessoa = service.findByNome(NOME);

        assertEquals(NOME, pessoa.get().getNome());
        verify(repository, times(1)).findByNome(any());
    }

    @Test
    void findAll() {
        Set<Pessoa> pessoasRetornadas = new HashSet<>();
        pessoasRetornadas.add(Pessoa.builder().id(1l).build());
        pessoasRetornadas.add(Pessoa.builder().id(2l).build());

        when(repository.findAll()).thenReturn(pessoasRetornadas);

        Set<Pessoa> pessoas = service.findAll();

        assertNotNull(pessoas);
        assertEquals(2, pessoas.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    void findById() {
        when(repository.findById(anyLong())).thenReturn(pessoaRetornada);

        Pessoa pessoa = service.findById(1L);

        assertNotNull(pessoa);
        assertEquals(pessoaRetornada.get(), pessoa);
        verify(repository, times(1)).findById(any());
    }

    @Test
    void findByIdNotFound() {
        when(repository.findById(anyLong())).thenReturn(Optional.empty());

        Pessoa pessoa = service.findById(999L);

        assertNull(pessoa);
        verify(repository, times(1)).findById(any());
    }

    @Test
    void save() {
        Pessoa pessoaParaSalvar = Pessoa.builder().id(1L).build();

        when(repository.save(any())).thenReturn(pessoaParaSalvar);

        Pessoa pessoaSalva = service.save(pessoaParaSalvar);

        assertNotNull(pessoaSalva);
        verify(repository).save(any());
        verify(repository, times(1)).save(any());
    }

    @Test
    void delete() {
        service.delete(pessoaRetornada.get());

        verify(repository, times(1)).delete(any());
    }

    @Test
    void deleteById() {
        service.deleteById(1L);

        verify(repository).deleteById(anyLong());
    }
    
}

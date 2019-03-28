package com.example.demo.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
import com.google.common.collect.Sets;

@ExtendWith(MockitoExtension.class)
class PessoaServiceImplTest {
	
	public static final String NOME = "Rafael Tavares";
	
    @Mock
    private PessoaRepository repository;
    
    @InjectMocks
    private PessoaServiceImpl service;

    private Optional<Pessoa> pessoaRetornada;
	
	@BeforeEach
	public void setUp() {
		pessoaRetornada = Optional.of(Pessoa.builder().id(1l).nome(NOME).build());
	}
	
    @Test
    public void findAll() {
        Set<Pessoa> pessoasRetornadas = Sets.newHashSet(
        		Pessoa.builder().id(1l).build(), 
        		Pessoa.builder().id(2l).build());

        when(repository.findAll()).thenReturn(pessoasRetornadas);

        Set<Pessoa> pessoas = service.findAll();

        assertNotNull(pessoas);
        assertEquals(2, pessoas.size());
        assertEquals(pessoasRetornadas, pessoas);
        verify(repository, times(1)).findAll();
    }

    @Test
    public void findById() {
        when(repository.findById(anyLong())).thenReturn(pessoaRetornada);

        Optional<Pessoa> pessoa = service.findById(1L);

        assertNotNull(pessoa);
        assertEquals(pessoaRetornada.get(), pessoa.get());
        verify(repository, times(1)).findById(any());
    }

    @Test
    public void findByIdNotFound() {
    	Optional<Pessoa> optEmpty = Optional.empty();
        when(repository.findById(anyLong())).thenReturn(optEmpty);

        Optional<Pessoa> pessoa = service.findById(999L);

        assertEquals(pessoa, optEmpty);
        verify(repository, times(1)).findById(any());
    }

    @Test
    public void save() {
        Pessoa pessoaParaSalvar = Pessoa.builder().id(1L).build();

        when(repository.save(any())).thenReturn(pessoaParaSalvar);

        Pessoa pessoaSalva = service.save(pessoaParaSalvar);

        assertNotNull(pessoaSalva);
        verify(repository).save(any());
        verify(repository, times(1)).save(any());
    }

    @Test
    public void delete() {
        service.delete(pessoaRetornada.get());

        verify(repository).delete(any());
        verify(repository, times(1)).delete(any());
    }

    @Test
    public void deleteById() {
        service.deleteById(1L);

        verify(repository).deleteById(anyLong());
        verify(repository, times(1)).deleteById(any());
    }
    
    @Test
	public void findByNome() {
        when(repository.findByNome(any())).thenReturn(pessoaRetornada);

        Optional<Pessoa> pessoa = service.findByNome(NOME);

        assertEquals(NOME, pessoa.get().getNome());
        verify(repository, times(1)).findByNome(any());
    }
    
    @Test
	public void findAllByNomeLike() {
    	Set<Pessoa> pessoasRetornadas = Sets.newHashSet(
        		Pessoa.builder().id(1l).build(), 
        		Pessoa.builder().id(2l).build());
    	
        when(repository.findByNomeContainingIgnoreCase(any())).thenReturn(pessoasRetornadas);

        Set<Pessoa> pessoas = service.findAllByNomeLike(NOME);

        assertEquals(pessoasRetornadas, pessoas);
        verify(repository, times(1)).findByNomeContainingIgnoreCase(any());
    }
    
}

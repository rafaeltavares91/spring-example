package com.example.demo.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.demo.domain.Pessoa;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class PessoaRepositoryTest {

	@Autowired
	private PessoaRepository pessoaRepository;
	
	@BeforeEach
	public void setUp() throws Exception {
	}
	
	@Test
	public void findByNome() {
		Optional<Pessoa> pessoa = pessoaRepository.findByNome("Vanessa Dantas");
		assertEquals("Vanessa Dantas", pessoa.get().getNome());
	}
	
}

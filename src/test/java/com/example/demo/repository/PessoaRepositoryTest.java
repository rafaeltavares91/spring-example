package com.example.demo.repository;

import static org.junit.Assert.assertEquals;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.domain.Pessoa;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PessoaRepositoryTest {

	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Before
	public void setUp() throws Exception {
	}
	
	@Test
	public void findByNome() {
		Optional<Pessoa> pessoa = pessoaRepository.findByNome("Vanessa Dantas");
		assertEquals("Vanessa Dantas", pessoa.get().getNome());
	}
	
}

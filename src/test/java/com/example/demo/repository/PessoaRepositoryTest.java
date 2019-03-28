package com.example.demo.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.Set;

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
	
	private Pessoa rafael;
	
	private Pessoa vanessa;
	
	@BeforeEach
	public void setUp() throws Exception {
		rafael = Pessoa.builder().id(1l).nome("Rafael Tavares").salario(new BigDecimal(1700)).build();
		vanessa = Pessoa.builder().id(2l).nome("Vanessa Dantas").salario(new BigDecimal(2000)).build();
	}
	
	@Test
	public void findByNome() {
		Pessoa pessoaDB = pessoaRepository.findByNome("Vanessa Dantas").get();
		assertEquals(vanessa.getId(), pessoaDB.getId());
		assertEquals(vanessa.getNome(), pessoaDB.getNome());
	}
	
	@Test
	public void findByNomeContainingIgnoreCase() {
		Set<Pessoa> pessoasDB = pessoaRepository.findByNomeContainingIgnoreCase("a");
		assertEquals(2, pessoasDB.size());
		assertTrue(pessoasDB.contains(rafael));
		assertTrue(pessoasDB.contains(vanessa));
	}
	
	@Test
	public void findFirstByOrderBySalarioDesc() {
		Pessoa pessoDB = pessoaRepository.findFirstByOrderBySalarioDesc();
		assertEquals(vanessa, pessoDB);
	}
	
	@Test
	public void findSomaTodosSalarios() {
		double valorEsperadoSoma = 3700;
		double resultadoSomaBD = pessoaRepository.findSomaTodosSalarios();
		assertEquals(valorEsperadoSoma, resultadoSomaBD);
	}
	
}

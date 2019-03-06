package com.example.demo.dbinitializer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.demo.domain.Pessoa;
import com.example.demo.service.PessoaService;

@Component
public class DataLoader implements CommandLineRunner {

	private final PessoaService pessoaService;
	
	public DataLoader(PessoaService pessoaService) {
		this.pessoaService = pessoaService;
	}
	
	@Override
	public void run(String... args) throws Exception {
		loadData();
	}

	private void loadData() {
		pessoaService.save(Pessoa.builder().nome("Rafael Tavares").build());
		pessoaService.save(Pessoa.builder().nome("Miguel").build());
	}
	
}

package com.example.demo.dbinitializer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.demo.domain.Pessoa;
import com.example.demo.service.AnimalService;
import com.example.demo.service.PessoaService;

@Component
public class DataLoader implements CommandLineRunner {

	private final PessoaService pessoaService;
	private final AnimalService animalService;
	
	public DataLoader(PessoaService pessoaService, AnimalService animalService) {
		this.pessoaService = pessoaService;
		this.animalService = animalService;
	}
	
	@Override
	public void run(String... args) throws Exception {
		loadData();
	}

	private void loadData() {
		Pessoa p = new Pessoa();
		p.setNome("Rafael Tavares");
	}
	
}

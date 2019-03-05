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
		Pessoa p1 = new Pessoa();
		p1.setNome("Rafael Tavares");
		pessoaService.save(p1);
		Pessoa p2 = new Pessoa();
		p2.setNome("Miguel");
		pessoaService.save(p2);
	}
	
}

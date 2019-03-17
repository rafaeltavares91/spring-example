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
		pessoaService.save(Pessoa.builder().nome("Rafael Tavares").cidade("Natal").endereco("R. Amapá N. 123").telefone("99994-8484").build());
		pessoaService.save(Pessoa.builder().nome("Vanessa Dantas").cidade("Caicó").endereco("R. Beatriz Pinheiro N. 321").telefone("99995-8585").build());
		pessoaService.save(Pessoa.builder().nome("Wellington Miguel").cidade("Recife").endereco("Rua dos Juvenis N. 3").telefone("99294-4278").build());
		pessoaService.save(Pessoa.builder().nome("Patrik França").cidade("Mossoró").endereco("Rua dos chupetas N. 4").telefone("9993-2469").build());
	}
	
}

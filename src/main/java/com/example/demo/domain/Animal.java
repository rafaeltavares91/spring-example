package com.example.demo.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Builder;

@Builder
@Entity
public class Animal extends EntidadeBase {
	
	private static final long serialVersionUID = 1L;

	private String nome;
	
	@ManyToOne
	@JoinColumn(name = "id_raca")
	private Raca raca;
	
	@ManyToOne
	@JoinColumn(name = "id_pessoa")
	private Pessoa pessoa;
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Raca getRaca() {
		return raca;
	}
	
	public void setRaca(Raca raca) {
		this.raca = raca;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
}
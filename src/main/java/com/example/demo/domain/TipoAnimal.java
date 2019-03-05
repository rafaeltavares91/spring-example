package com.example.demo.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class TipoAnimal extends EntidadeBase {
	
	private static final long serialVersionUID = 1L;
	
	private String descricao;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoAnimal")
	private Set<Raca> racas;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public Set<Raca> getRacas() {
		return racas;
	}

	public void setRacas(Set<Raca> racas) {
		this.racas = racas;
	}
	
}
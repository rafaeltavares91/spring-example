package com.example.demo.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Raca extends EntidadeBase {

	private static final long serialVersionUID = 1L;
	
	private String descricao;
	
	@ManyToOne
	@JoinColumn(name = "id_tipo_animal")
	private TipoAnimal tipoAnimal;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public TipoAnimal getTipoAnimal() {
		return tipoAnimal;
	}

	public void setTipoAnimal(TipoAnimal tipoAnimal) {
		this.tipoAnimal = tipoAnimal;
	}
	
}
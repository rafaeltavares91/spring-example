package com.example.demo.domain;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Visita extends EntidadeBase {
	
	private static final long serialVersionUID = 1L;

	private LocalDate data;
	
	private String descricao;
	
	@ManyToOne
    @JoinColumn(name = "id_animal")
	private Animal animal;

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Animal getAnimal() {
		return animal;
	}

	public void setAnimal(Animal animal) {
		this.animal = animal;
	}
	
}

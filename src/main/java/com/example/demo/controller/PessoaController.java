package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.service.PessoaService;

@Controller
@RequestMapping("/pessoa")
public class PessoaController {
	
	private final PessoaService pessoaService;
	
	public PessoaController(PessoaService pessoaService) {
		this.pessoaService = pessoaService;
	}
	
	@RequestMapping("")
	public String pessoa(Model model) {
		model.addAttribute("pessoas", pessoaService.findAll());
		return "pessoa/list";
	}
	
}
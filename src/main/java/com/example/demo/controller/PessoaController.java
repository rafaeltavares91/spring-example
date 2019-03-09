package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.service.PessoaService;

/**
 * 
 * @author Rafael Tavares
 *
 */
@Controller
@RequestMapping("/pessoa")
public class PessoaController {
	
	private final PessoaService pessoaService;
	
	public PessoaController(PessoaService pessoaService) {
		this.pessoaService = pessoaService;
	}
	
	@RequestMapping({"", "/", "/list"})
	public String list(Model model) {
		model.addAttribute("pessoas", pessoaService.findAll());
		return "pessoa/list";
	}
	
	@RequestMapping("/find")
	public String find() {
		return "notimplemented";
	}
	
	@GetMapping({"/{pessoaId}", "/show/{pessoaId}"})
	public ModelAndView show(@PathVariable("pessoaId") Long pessoaId) {
		ModelAndView mav = new ModelAndView("pessoa/show");
		mav.addObject(pessoaService.findById(pessoaId));
		return mav;
	}
	
}
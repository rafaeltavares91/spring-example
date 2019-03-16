package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.domain.Pessoa;
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
	
	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
	
	@RequestMapping({"", "/", "/list"})
	public String list(Model model) {
		model.addAttribute("pessoas", pessoaService.findAll());
		return "pessoa/list";
	}
	
	@RequestMapping("/find")
	public String find(Model model) {
		model.addAttribute("pessoa", Pessoa.builder().build());
		return "pessoa/find";
	}
	
	@GetMapping("/procesFind")
	public String processFindForm(Pessoa pessoa, BindingResult result, Model model) {
		if (pessoa.getNome() == null) {
			pessoa.setNome("");
		}
		List<Pessoa> pessoas = pessoaService.findAllByNomeLike(pessoa.getNome());
		if (pessoas.isEmpty()) {
			result.rejectValue("nome", "notFound", "Não foi encontrado");
			return "pessoa/find";
		} else if (pessoas.size() == 1) {
			pessoa = pessoas.get(0);
			return "redirect:/pessoa/show/" + pessoa.getId();
		} else {
			model.addAttribute("pessoas", pessoas);
			return "pessoa/list";
		}
	}
	
	@GetMapping({"/{pessoaId}", "/show/{pessoaId}"})
	public ModelAndView show(@PathVariable("pessoaId") Long pessoaId) {
		ModelAndView mav = new ModelAndView("pessoa/show");
		mav.addObject(pessoaService.findById(pessoaId));
		return mav;
	}
	
}
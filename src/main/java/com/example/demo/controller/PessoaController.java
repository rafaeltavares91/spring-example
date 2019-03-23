package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.domain.Pessoa;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.service.PessoaService;

import lombok.extern.log4j.Log4j2;

/**
 * 
 * @author Rafael Tavares
 *
 */
@Controller
@RequestMapping("/pessoa")
@Log4j2
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
	
	@GetMapping("/show/{pessoaId}")
	public ModelAndView show(@PathVariable("pessoaId") Long pessoaId) {
		Optional<Pessoa> pessoa = pessoaService.findById(pessoaId);
		if (!pessoa.isPresent()) {
			throw new ResourceNotFoundException("Pessoa não encontrada.");
		}
		ModelAndView mav = new ModelAndView("pessoa/show");
		mav.addObject(pessoa.get());
		return mav;
	}
	
	@RequestMapping("/find")
	public String findForm(Model model) {
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
	
	@GetMapping("/new")
    public String initCreationForm(Model model) {
        model.addAttribute("pessoa", Pessoa.builder().build());
        return "pessoa/form";
    }
	
	@PostMapping("/new")
	public String processCreationForm(@Valid Pessoa pessoa, BindingResult result) {
		if (result.hasErrors()) {
			return "pessoa/form";
		} else {
			Pessoa p = pessoaService.save(pessoa);
			return "redirect:/pessoa/show/" + p.getId();
		}
	}
	
	@GetMapping("/{pessoaId}/edit")
    public String initUpdateForm(@PathVariable Long pessoaId, Model model) {
		Optional<Pessoa> pessoa = pessoaService.findById(pessoaId);
		if (!pessoa.isPresent()) {
			throw new ResourceNotFoundException("Pessoa não encontrada.");
		}
        model.addAttribute("pessoa", pessoa.get());
        return "pessoa/form";
    }

	@PutMapping("/{pessoaId}/edit")
    public String processUpdateForm(@Valid Pessoa pessoa, BindingResult result, @PathVariable Long pessoaId) {
        if (result.hasErrors()) {
            return "redirect:/pessoa/form";
        } else {
        	pessoa.setId(pessoaId);
            pessoaService.save(pessoa);
            return "redirect:/pessoa/show/" + pessoa.getId();
        }
    }
    
    @GetMapping("/delete/{pessoaId}")
	public String delete(@PathVariable("pessoaId") Long pessoaId) {
		pessoaService.deleteById(pessoaId);
		return "redirect:/pessoa/list";
	} 
	
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({EmptyResultDataAccessException.class, ResourceNotFoundException.class})
    public ModelAndView handleNotFound(Exception e) {
    	log.error("Handling ResourceNotFoundException");
    	log.error(e.getMessage());
    	ModelAndView mav = new ModelAndView();
    	mav.setViewName("404error");
    	mav.addObject("exception", e);
    	return mav;
    }
    
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NumberFormatException.class)
    public ModelAndView handleBadRequest(Exception e) {
    	log.error("Handling NumberFormatException");
    	log.error(e.getMessage());
    	ModelAndView mav = new ModelAndView();
    	mav.setViewName("400error");
    	mav.addObject("exception", e);
    	return mav;
    }
    
}
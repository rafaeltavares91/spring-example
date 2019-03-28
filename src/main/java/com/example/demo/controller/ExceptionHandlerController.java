package com.example.demo.controller;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.exception.ResourceNotFoundException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class ExceptionHandlerController {

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

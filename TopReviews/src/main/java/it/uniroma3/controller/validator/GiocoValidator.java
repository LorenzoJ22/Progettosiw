package it.uniroma3.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.model.Gioco;
import it.uniroma3.repository.GiocoRepository;

@Component
public class GiocoValidator implements Validator {
	@Autowired
	private GiocoRepository movieRepository;

	@Override
	public void validate(Object o, Errors errors) {
		Gioco gioco = (Gioco)o;
		if (gioco.getTitle()!=null && gioco.getYear()!=null 
				&& movieRepository.existsByTitleAndYear(gioco.getTitle(), gioco.getYear())) {
			errors.reject("gioco.duplicate");
		}
	}
	@Override
	public boolean supports(Class<?> aClass) {
		return Gioco.class.equals(aClass);
	}
}
package it.uniroma3.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.model.Credentials;

import it.uniroma3.model.User;
import it.uniroma3.repository.GiocoRepository;
import it.uniroma3.repository.UserRepository;

@Component
public class UserValidator implements Validator{

	@Autowired
	private UserRepository userRepository;

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		User user = (User)target;
		if(user.getName()!=null && user.getSurname()!=null && 
				userRepository.existsByNameAndSurname(user.getName(), user.getSurname())) {
			errors.reject("user.duplicate");
		}
		
	}

}

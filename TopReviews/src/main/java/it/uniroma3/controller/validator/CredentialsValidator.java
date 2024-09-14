package it.uniroma3.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.model.Credentials;
import it.uniroma3.service.CredentialsService;

@Component
public class CredentialsValidator implements Validator{
	
	@Autowired
	private CredentialsService credentialservice;
	

	@Override
	public void validate(Object o, Errors errors) {
		Credentials p = (Credentials)o;
		if (this.credentialservice.existsByUsernameAndPassword(p.getUsername())) {
			errors.reject("credentials.duplicate");
		}
		
	}
	
	@Override
	public boolean supports(Class<?> aClass) {
		return Credentials.class.equals(aClass);
	}
	
}

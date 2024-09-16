package it.uniroma3.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.model.Credentials;
import it.uniroma3.service.CredentialsService;

@Component
public class CredentialsValidator implements Validator{
	
	@Autowired
	private CredentialsService credentialservice;
	@Autowired
    private PasswordEncoder passwordEncoder;

	@Override
	public void validate(Object o, Errors errors) {
		Credentials p = (Credentials)o;
		 Credentials existingCredentials = credentialservice.getCredentials(p.getUsername());
		 
			 if (existingCredentials != null) {
		            // Usa passwordEncoder.matches() per confrontare la password in chiaro con l'hash
		            if (passwordEncoder.matches(p.getPassword(), existingCredentials.getPassword())) {
		                errors.reject("credentials.duplicate");
		            }
		        }
//		if (this.credentialservice.existsByUsername(p.getUsername())) {
//			errors.reject("credentials.duplicate");
//		}
		
	}
	
	@Override
	public boolean supports(Class<?> aClass) {
		return Credentials.class.equals(aClass);
	}
	
}

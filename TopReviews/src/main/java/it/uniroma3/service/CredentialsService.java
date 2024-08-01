package it.uniroma3.service;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;

import it.uniroma3.model.Credentials;

import it.uniroma3.repository.CredentialsRepository;


@Service
public class CredentialsService {

	 @Autowired
	 protected PasswordEncoder passwordEncoder;
	
	@Autowired
	private CredentialsRepository credentialsRepository;
	

	public Credentials getCredentials(Long id) {
	return credentialsRepository.findById(id).get();
	}
	
	@Transactional
    public Credentials getCredentials(String username) {
        Optional<Credentials> result = this.credentialsRepository.findByUsername(username);
        return result.orElse(null);
    }
	
	@Transactional
	public Credentials saveCredentials(Credentials credentials){
		credentials.setRole(Credentials.DEFAULT_ROLE);
        credentials.setPassword(this.passwordEncoder.encode(credentials.getPassword()));
        return this.credentialsRepository.save(credentials);
        
	}
	@Transactional
	public Credentials saveCredentialsAdmin(Credentials credentials){
		credentials.setRole(Credentials.ADMIN_ROLE);
        credentials.setPassword(this.passwordEncoder.encode(credentials.getPassword()));
        return this.credentialsRepository.save(credentials);
        
	}
	
	public Boolean existsByUsernameAndPassword(String username, String password) {
		return credentialsRepository.existsByUsernameAndPassword(username,  password);
	}
	
	
	public void deleteById(Long id) {
		credentialsRepository.deleteById(id);
	}
	
	public Long TrovaCredentialsId(Long id) {
		return this.credentialsRepository.TrovaCredentialsId(id);
	}
	
	
	
	}

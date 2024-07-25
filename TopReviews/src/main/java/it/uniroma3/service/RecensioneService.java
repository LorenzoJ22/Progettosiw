package it.uniroma3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.model.Credentials;
import it.uniroma3.model.Recensione;
import it.uniroma3.repository.RecensioneRepository;

@Service
public class RecensioneService {
	@Autowired
	RecensioneRepository recensioneRepository;
	public Recensione getRecensioni(Long id) {
		return recensioneRepository.findById(id).get();
		}
}

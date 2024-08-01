package it.uniroma3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.uniroma3.model.Recensione;
import java.util.*;
import it.uniroma3.repository.RecensioneRepository;

@Service

public class RecensioneService {

	@Autowired RecensioneRepository recensionerepository;

	public List<Recensione>FindRecensioniById(Long id){
		return recensionerepository.findRecensioneByGiocoId(id);
	}
	public Recensione findRecensioneById(Long id) {
		return recensionerepository.findRecensioneById(id);
	}

	public void save(Recensione recensione) {
		recensionerepository.save(recensione);
	}
	public Iterable<Recensione>GetAllRecensioni(){
		return recensionerepository.findAll();
	}
	
	public Iterable<Recensione>TrovaRecensioniId(Long id){
		return recensionerepository.TrovaRecensioniId(id);
	}
	
}

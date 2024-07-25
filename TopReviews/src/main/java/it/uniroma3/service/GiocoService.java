package it.uniroma3.service;

import org.springframework.stereotype.Service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import it.uniroma3.model.Gioco;
import it.uniroma3.repository.GiocoRepository;

@Service
public class GiocoService {

	@Autowired		
	private GiocoRepository giocoRepository;
	
	public Gioco findById(Long id) {
		return giocoRepository.findById(id).get();
	}

	public Iterable<Gioco> findAll() {
		return giocoRepository.findAll();
	}
	
	public Gioco save(Gioco m) {
		return giocoRepository.save(m);
	}
	
	public List<Gioco> searchByYear(Integer year) {
		return giocoRepository.findByYear(year);
		
	}
	
}

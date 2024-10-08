package it.uniroma3.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import it.uniroma3.model.Gioco;

//import it.uniroma3.model.Image;
import it.uniroma3.repository.GiocoRepository;
//import it.uniroma3.repository.ImageRepository;
//import it.uniroma3.repository.ImageRepository;

@Service
public class GiocoService {

	@Autowired		
	private GiocoRepository giocoRepository;
	
//	@Autowired 
//	private ImageRepository imageRepository;
	
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
	public Iterable<Gioco>findByUserId(Long id){
		return giocoRepository.FindGiocoByUserId(id);
	}

	public void deleteById(Long id) {
		giocoRepository.deleteById(id);
	}
	
}

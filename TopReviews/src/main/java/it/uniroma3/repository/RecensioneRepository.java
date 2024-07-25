package it.uniroma3.repository;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.model.Recensione;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface RecensioneRepository extends CrudRepository<Recensione, Long> {
	public List<Recensione>findRecensioneByGiocoId(Long id);
	public Recensione findRecensioneById(Long id);


}

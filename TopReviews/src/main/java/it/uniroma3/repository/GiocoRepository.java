package it.uniroma3.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import it.uniroma3.model.Gioco;

public interface GiocoRepository extends CrudRepository<Gioco, Long> {

	 public List<Gioco> findByYear(Integer year);

	    public boolean existsByTitleAndYear(String title, Integer year); 
}

package it.uniroma3.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import it.uniroma3.model.Gioco;
import it.uniroma3.model.Recensione;


public interface GiocoRepository extends CrudRepository<Gioco, Long> {

	 public List<Gioco> findByYear(Integer year);

	    public boolean existsByTitleAndYear(String title, Integer year);

	    @Query(value = "SELECT * FROM gioco r WHERE r.userg_id = :idUser", nativeQuery = true)
	    Iterable<Gioco> FindGiocoByUserId(@Param("idUser") Long id);
		
		
		public Iterable<Gioco> findByTitle(String title);
		
		public List<Gioco> findByTitleIgnoreCase(String title);
		
}
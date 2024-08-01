package it.uniroma3.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import it.uniroma3.model.Recensione;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface RecensioneRepository extends CrudRepository<Recensione, Long> {
	public List<Recensione>findRecensioneByGiocoId(Long id);
	public Recensione findRecensioneById(Long id);

	@Query(value = "SELECT * FROM recensione WHERE user_id = :fid", nativeQuery = true)
    Iterable<Recensione> TrovaRecensioniId(@Param("fid")Long id);
	


}

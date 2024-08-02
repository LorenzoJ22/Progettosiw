package it.uniroma3.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import it.uniroma3.model.Recensione;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface RecensioneRepository extends CrudRepository<Recensione, Long> {
	@Query(value = "SELECT * FROM recensione r where r.gioco_id=:fid ", nativeQuery = true)
	public List<Recensione>findRecensioneByGiocoId(@Param("fid")Long id);
	
	@Query(value = "SELECT * FROM recensione r where r.user_id=:fid", nativeQuery = true)
	public List<Recensione>findRecensioneByUserId(@Param("fid")Long id);
	
	public Recensione findRecensioneById(Long id);

	@Query(value = "SELECT * FROM recensione WHERE user_id = :fid", nativeQuery = true)
    Iterable<Recensione> TrovaRecensioniId(@Param("fid")Long id);
	


}

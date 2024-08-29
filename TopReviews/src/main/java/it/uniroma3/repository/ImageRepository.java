package it.uniroma3.repository;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.model.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {
	
	@Query(value = "SELECT i.* FROM image i JOIN gioco gi ON i.id = gi.immagine_id where gi.id=:fid", nativeQuery = true)
	public Optional<Image> findImageByGiocoid (@Param("fid") Long id);
	
}
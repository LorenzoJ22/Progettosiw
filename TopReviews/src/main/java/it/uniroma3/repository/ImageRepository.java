package it.uniroma3.repository;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.model.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {
	
//	@Query(value = "SELECT * FROM image WHERE gioco_id = :fid", nativeQuery = true)
//	public Optional<Image> findImageByGiocoid (@Param("fid") Long id);
	
	
	@Query(value = "SELECT i.* FROM image i LEFT JOIN gioco gi ON i.id = gi.immagine_id LEFT JOIN users ur ON i.id = ur.image_id WHERE gi.id =:fid OR ur.id =:fid", nativeQuery = true)
	public Optional<Image> findImageByid (@Param("fid") Long id);
	
	@Query(value = "SELECT i.* FROM image i JOIN gioco gi ON i.id = gi.immagine_id WHERE gi.id =:fid", nativeQuery = true)
	public Optional<Image> findImageByGiocoid (@Param("fid") Long id);
}
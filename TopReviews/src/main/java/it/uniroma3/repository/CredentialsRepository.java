package it.uniroma3.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import it.uniroma3.model.Credentials;

public interface CredentialsRepository extends CrudRepository<Credentials, Long> {

	public Optional<Credentials> findByUsername(String username);
	public Boolean existsByUsername(String username);

	@Query(value = "SELECT c.id FROM credentials c JOIN users ci ON c.user_id = ci.id WHERE c.user_id = :fid", nativeQuery = true)
	Long TrovaCredentialsId(@Param("fid")Long id);


}
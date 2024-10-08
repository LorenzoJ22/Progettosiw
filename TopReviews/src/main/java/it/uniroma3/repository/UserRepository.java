package it.uniroma3.repository;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.model.User;

public interface UserRepository extends CrudRepository<User, Long> {

	  public User findUserById(Long Id);
	   
	   public Boolean existsByNameAndSurname(String name, String surname);
}
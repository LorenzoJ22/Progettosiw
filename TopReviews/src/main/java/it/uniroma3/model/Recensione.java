package it.uniroma3.model;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Recensione {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	 private Long id;
	 private String data;
	 
	 @ManyToOne
	 private User user;		//autore della recensione

	@ManyToOne
	 private Gioco gioco;
	
	 
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Gioco getGioco() {
		return gioco;
	}
	public void setGioco(Gioco gioco) {
		this.gioco = gioco;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	
	private String title;
	
    @Override
	public int hashCode() {
		return Objects.hash(id, title, data);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Recensione other = (Recensione) obj;
		return  Objects.equals(data, other.data);
	}

}

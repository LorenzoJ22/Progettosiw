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
	 private String titolo;
	 private String testo;
	 private Integer numeroStelle;
	 
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
	
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	
	
    @Override
	public int hashCode() {
		return Objects.hash(titolo, data, gioco, id,user,testo);
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
		return Objects.equals(titolo, other.titolo) && Objects.equals(data, other.data)
				&& Objects.equals(gioco, other.gioco) && Objects.equals(id, other.id)
				&& Objects.equals(user, other.user)
				&& Objects.equals(testo, other.testo);
	}
	public String getTitolo() {
		return titolo;
	}
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	
	public String getTesto() {
		return testo;
	}
	public void setTesto(String testo) {
		this.testo = testo;
	}
	public Integer getNumeroStelle() {
		return numeroStelle;
	}
	public void setNumeroStelle(Integer numeroStelle) {
		this.numeroStelle = numeroStelle;
	}

}

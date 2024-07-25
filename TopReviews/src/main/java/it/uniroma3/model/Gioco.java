package it.uniroma3.model;
import java.util.List;

import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

//vediamo se non lo vede 2

@Entity
public class Gioco {
		
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		 private Long id;
		 private Integer year;
		 private String urlImage;
		 private String title;
		 
		 
		 @OneToMany(mappedBy = "gioco",cascade = CascadeType.ALL)
		 private List<Recensione>recensioni;
		 

		// @ManyToOne(cascade = CascadeType.ALL)

//		 public List<Recensione> getRecensioni() {
//			return recensioni;
//		}
//		public void setRecensioni(List<Recensione> recensioni) {
//			this.recensioni = recensioni;
//		}
//		public User getUserg() {
//			return userg;
//		}
//		public void setUserg(User userg) {
//			this.userg = userg;
//		}
		@ManyToOne(cascade = CascadeType.ALL)
		 private User userg;
		 
	    public List<Recensione> getRecensioni() {
			return recensioni;
		}
		public void setRecensioni(List<Recensione> recensioni) {
			this.recensioni = recensioni;
			
		}
		public User getUserg() {
			return userg;
		}
		public void setUserg(User userg) {
			this.userg = userg;
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
		public Integer getYear() {
			return year;
		}
		public void setYear(Integer year) {
			this.year = year;
		}
		public String getUrlImage() {
			return urlImage;
		}
		public void setUrlImage(String urlImage) {
			this.urlImage = urlImage;
		}
		
		
	    @Override
		public int hashCode() {
			return Objects.hash(id, title, urlImage, year);
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Gioco other = (Gioco) obj;
			return  Objects.equals(title, other.title) && Objects.equals(year, other.year);
		}

	}

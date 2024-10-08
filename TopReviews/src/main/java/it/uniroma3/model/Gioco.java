package it.uniroma3.model;
import java.util.List;

import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
		 private String title;
		 
		 
		 @OneToMany(mappedBy = "gioco",cascade = CascadeType.ALL)
		 private List<Recensione>recensioni;

		@ManyToOne
		 private User userg;

     	@OneToOne
     	private Image immagine;
     	
     	
     	
        public Image getImmagine() {
    			return immagine;
    		}
        public void setImmagine(Image immagine){
        	this.immagine=immagine;
        }

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
	
		
		
	    @Override
		public int hashCode() {
			return Objects.hash(id, title, year);
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

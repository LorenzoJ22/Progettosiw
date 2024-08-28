package it.uniroma3.controller;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.model.Credentials;
import it.uniroma3.model.Gioco;
import it.uniroma3.model.Recensione;
import it.uniroma3.model.User;
import it.uniroma3.service.CredentialsService;
import it.uniroma3.service.RecensioneService;

import jakarta.servlet.http.HttpSession;

@Controller
public class RecensioneController {
	
	@Autowired CredentialsService credentialsService;
	
	@Autowired RecensioneService recensioneService;
	//@Autowired GiocoService giocoService;
	
    @GetMapping("/recensioni")
    
	public String GetAllRecensioni(Model model) {
		model.addAttribute("recensioni",this.recensioneService.GetAllRecensioni());
		return "recensioni.html";
		
	}

	
	@GetMapping("/recensione/{id}")
	@Transactional
	public String GetRecensione(@PathVariable("id") Long id, Model model) {
		Recensione recensione = recensioneService.findRecensioneById(id);
		model.addAttribute("recensione",recensione);
		model.addAttribute("rating",recensione.getNumeroStelle());
		return "recensione.html";
		
	}
	//oorknrnvor3nopp
	@GetMapping("/gioco/{id}/recensioni")
	@Transactional
	public String getRecensioniPerGioco(@PathVariable("id") Long id, Model model) {
		model.addAttribute("recensioni", this.recensioneService.FindRecensioniByGiocoId(id));
		return "recensioni.html";
	}
	

	@GetMapping("/recensione/user/{id}")
	@Transactional
	public String FindRecensioniByUsergId(Model model,@PathVariable("id") Long id){
		model.addAttribute("recensioni",recensioneService.FindRecensioniByUserId(id));
		return "user/DeleteRecensioni.html";
		
	}
	
	@GetMapping("/aggiungiRecensione")
	@Transactional
	public String AddRecensione(Model model,@ModelAttribute("user") User user) {
		Recensione r=new Recensione();
		model.addAttribute("recensione",r);
		model.addAttribute(user);
		return "user/formNewRecensione.html";
		
	}
	
	@PostMapping("/addRecensione")
	@Transactional
		public String newRecensione(
				@ModelAttribute("recensione")Recensione recensione, Model model,HttpSession session,
			     @RequestParam("rating") Integer rating, @RequestParam("data")Date data) {
	    	UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			Credentials credentials = credentialsService.getCredentials(userDetails.getUsername());
			User user = credentials.getUser();
			Gioco gioco= (Gioco)session.getAttribute("giocoMem");
			if (rating == null) {//da finire errore
		        // Aggiungi un messaggio di errore al modello
		        model.addAttribute("error", "Devi selezionare un punteggio per la recensione.");
		        // Ritorna alla pagina di inserimento recensione
		        return "recensioneForm"; // Assicurati che questo sia il nome corretto del tuo template di inserimento
		    }
			recensione.setGioco(gioco);
			recensione.setNumeroStelle(rating);
			recensione.setData(data);
			recensione.setUser(user);
			
		    this.recensioneService.save(recensione);
		    user.getRecensioni().add(recensione);
			model.addAttribute("recensioni", this.recensioneService.FindRecensioniByUserId(user.getId()));
//			return "redirect:/recensioni";
			return "redirect:/gioco/"+gioco.getId()+"/recensioni";
		}
	
	@GetMapping("/User/CancellaRecensione/{id}")
	@Transactional
    public String cancellaRecensione(Model model,@PathVariable("id") Long id) {  
        recensioneService.deleteById(id);
        UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Credentials credentials = credentialsService.getCredentials(userDetails.getUsername());
		User user = credentials.getUser();
	   	model.addAttribute("recensioni", recensioneService.FindRecensioniByUserId(user.getId()));
		return "user/DeleteRecensioni.html";
    }
	

}

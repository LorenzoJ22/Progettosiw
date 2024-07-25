package it.uniroma3.controller;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import it.uniroma3.model.Credentials;
import it.uniroma3.model.Gioco;
import it.uniroma3.model.Recensione;
import it.uniroma3.model.User;
import it.uniroma3.service.CredentialsService;

import it.uniroma3.service.RecensioneService;

@Controller
public class RecensioneController {
	
	@Autowired CredentialsService credentialsService;
	
	@Autowired RecensioneService recensioneService;
	
	
	
@GetMapping("/recensioni")
	public String GetAllRecensioni(Model model) {
		model.addAttribute("recensioni",this.recensioneService.GetAllRecensioni());
		return "recensioni.html";
	}

	
	@GetMapping("/recensione{id}")
	public String GetRecensione(@PathVariable("id") Long id, Model model) {
		model.addAttribute("recensione",this.recensioneService.findRecensioneById(id));
		return "recensione.html";
		
	}
	@GetMapping("/gioco/{id}/recensioni")
	public String getRecensioniPerGioco(@PathVariable("id") Long id, Model model) {
		model.addAttribute("recensioni", this.recensioneService.FindRecensioniById(id));
		return "recensioni.html";
	}
	
	@GetMapping("/aggiungiRecensione")
	public String AddRicetta(Model model,@ModelAttribute("user") User user) {
		model.addAttribute("recensione",new Recensione());
		model.addAttribute(user);
		return "formNewRecensione.html";
	}
	
	@PostMapping("/addRecensione")
	public String newRicetta(
			@ModelAttribute("recensione")Recensione recensione, Model model) {
		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Credentials credentials = credentialsService.getCredentials(userDetails.getUsername());
		User user = credentials.getUser();
		System.out.println("l'user e': " +user + "e id:"+ user.getId());
		 recensione.setUser(user);
		 
		    recensione.getUser().setId(user.getId());
			user.getRecensioni().add(recensione);
			this.recensioneService.save(recensione);
		model.addAttribute("recensioni", this.recensioneService.FindRecensioniById(user.getId()));
		return "recensioni.html";
	}
	
	

}

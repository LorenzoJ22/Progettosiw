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

import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.repository.GiocoRepository;


import it.uniroma3.model.Credentials;
import it.uniroma3.model.Gioco;
import it.uniroma3.model.Recensione;
import it.uniroma3.model.User;
import it.uniroma3.service.CredentialsService;

import it.uniroma3.service.GiocoService;
import jakarta.servlet.http.HttpSession;

@Controller
public class GiocoController {
	@Autowired GiocoService giocoService;

	@Autowired GiocoRepository giocoRepository;
	

	@Autowired CredentialsService credentialsService;

	
	@GetMapping("/gioco/{id}")
	public String getGioco(@PathVariable("id") Long id, Model model,HttpSession session) {
		Gioco gioco = this.giocoService.findById(id);
		model.addAttribute("gioco", gioco);
		session.setAttribute("giocoMem", gioco);
		return "gioco.html";
	}
	
	@GetMapping("/giochi")
	public String showGiochi(Model model) {
		model.addAttribute("giochi", this.giocoService.findAll());
		return "giochi.html"; //da chi viene generata la risposta
	}
	

	 /*SearchGioco form da IndexAdmin*/
    @GetMapping("/formSearchGioco")
	public String formSearchGioco() {
		return "formSearchGioco.html";
	}
    /*fare in modo che quando scrivo qualcosa lo prenda sia da maiuscolo che da minuscolo*/
	@PostMapping("/searchGioco")
	public String searchRicetta(Model model, @RequestParam("title")String title) {
//		model.addAttribute("ricette", this.ricettaService.findByName(name));
		model.addAttribute("giochi", giocoRepository.findByTitleIgnoreCase(title));
		return "foundGioco.html";
//		return "redirect:/foundRicetta";
	}
	
	

	@GetMapping("/aggiungiGioco")
	public String AddGioco(Model model) {
		model.addAttribute("gioco",new Gioco());
		
		return "formNewGioco.html";
	}
	@GetMapping("/gioco/user/{id}")
	public String GetgiocoByUsergId(Model model,@PathVariable("id") Long id){
		model.addAttribute("giochi",giocoService.findByUserId(id));
		return "giochi.html";
	}
	
	@PostMapping("/addGioco")
	public String newGioco(
				@ModelAttribute("gioco")Gioco gioco, Model model) {
	    	UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			Credentials credentials = credentialsService.getCredentials(userDetails.getUsername());
			User user = credentials.getUser();
			gioco.setUserg(user);
		    this.giocoService.save(gioco);
			model.addAttribute("giochi", this.giocoService.findAll());
			return "giochi.html";
		
}

	
	
}

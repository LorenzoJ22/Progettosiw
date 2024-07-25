package it.uniroma3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import it.uniroma3.service.UserService;
import it.uniroma3.service.RecensioneService;

@Controller
public class UserController {
	@Autowired UserService userService;
	@Autowired RecensioneService ricettaService;
	
	 @GetMapping("/Utente/{id}")
		public String getUtente(@PathVariable("id")Long id ,Model model) {
			model.addAttribute("Utente", this.userService.findById(id));
			return "utente.html";
		}
	 
	 @GetMapping("/Utenti")
	 public String getUtenti(Model model) {
		 model.addAttribute("utenti", this.userService.findAll());
		 return "utenti.html";
	 }
}

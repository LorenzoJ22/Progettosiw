package it.uniroma3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import it.uniroma3.service.GiocoService;

@Controller
public class GiocoController {
	@Autowired GiocoService giocoService;
	
	
	@GetMapping("/gioco/{id}")
	public String getGioco(@PathVariable("id") Long id, Model model) {
		model.addAttribute("gioco", this.giocoService.findById(id));
		return "gioco.html";
	}
	
	@GetMapping("/gioco")
	public String showGiochi(Model model) {
		model.addAttribute("giochi", this.giocoService.findAll());
		return "giochi.html"; //da chi viene generata la risposta
	}
}

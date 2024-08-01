package it.uniroma3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.repository.GiocoRepository;
import it.uniroma3.service.GiocoService;

@Controller
public class GiocoController {
	@Autowired GiocoService giocoService;
	@Autowired GiocoRepository giocoRepository;
	
	
	@GetMapping("/gioco/{id}")
	public String getGioco(@PathVariable("id") Long id, Model model) {
		model.addAttribute("gioco", this.giocoService.findById(id));
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
	
	
	
	
}

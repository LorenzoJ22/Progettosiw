package it.uniroma3.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.uniroma3.repository.GiocoRepository;
import java.util.*;

import it.uniroma3.model.Credentials;
import it.uniroma3.model.Gioco;
import it.uniroma3.model.Image;
import it.uniroma3.model.User;
import it.uniroma3.service.CredentialsService;

import it.uniroma3.service.GiocoService;

import it.uniroma3.service.RecensioneService;

import it.uniroma3.service.ImageService;

import jakarta.servlet.http.HttpSession;

@Controller
public class GiocoController {
	@Autowired GiocoService giocoService;

	@Autowired GiocoRepository giocoRepository;
	

	@Autowired CredentialsService credentialsService;

	@Autowired RecensioneService recensioneService;

	
	@Autowired ImageService imageservice;
	//,HttpSession session
	

	
	@GetMapping("/gioco/{id}")
	public String getGioco(@PathVariable("id") Long id, Model model,HttpSession session) {
		Gioco gioco = this.giocoService.findById(id);
		model.addAttribute("gioco", gioco);
        session.setAttribute("giocoMem", gioco);
		
		return "gioco.html";
	}
	
	/*vedere le recensioni del gioco meglio ancora non usato*/
	@GetMapping("/giocoStile/{id}")
	public String getGiocoStile(@PathVariable("id") Long id, Model model,HttpSession session) {
		Gioco gioco = this.giocoService.findById(id);
		model.addAttribute("gioco", gioco);
		model.addAttribute("recensioni", recensioneService.FindRecensioniByGiocoId(id));
		return "giocoStile.html";
	}
	
	
	
	@GetMapping("/giochi")
	public String showGiochi(Model model) {
		model.addAttribute("giochi", this.giocoService.findAll());
		return "giochi.html"; //da chi viene generata la risposta
	}
	
	

	 /*SearchGioco form da IndexAdmin*/
    @GetMapping("/formSearchGioco")
    @Transactional
	public String formSearchGioco() {
		return "formSearchGioco.html";
	}
    
    /*fare in modo che quando scrivo qualcosa lo prenda sia da maiuscolo che da minuscolo*/
	@PostMapping("/searchGioco")
	@Transactional
	public String searchGioco(Model model, @RequestParam("title")String title, HttpSession session) {
//		model.addAttribute("giochi", giocoRepository.findByTitleIgnoreCase(title));
//		 model.addAttribute("searchedTitle", title);
		List<Gioco> giochi = giocoRepository.findByTitleIgnoreCase(title);
	    
	    // Memorizza i risultati della ricerca nella sessione
	    session.setAttribute("giochi", giochi);
	    session.setAttribute("searchedTitle", title);
//		return "foundGioco.html";
		return "redirect:/foundGioco";
	}
	@GetMapping("/foundGioco")
	public String foundGioco(Model model, HttpSession session) {
	    // Recupera i dati dalla sessione
	    List<Gioco> giochi = (List<Gioco>) session.getAttribute("giochi");
	    String searchedTitle = (String) session.getAttribute("searchedTitle");
	    
	    // Aggiungi i dati al modello
	    model.addAttribute("giochi", giochi);
	    model.addAttribute("searchedTitle", searchedTitle);
	    
	    // Rimuovi i dati dalla sessione se non li vuoi persistere tra le richieste
//	    session.removeAttribute("giochi");
//	    session.removeAttribute("searchedTitle");
	    
	    return "foundGioco.html";
	}
	
	


	@GetMapping("/gioco/user/{id}")
	public String GetgiocoByUsergId(Model model,@PathVariable("id") Long id){
		model.addAttribute("giochi",giocoService.findByUserId(id));
		return "giochi.html";
	}
	
	@GetMapping("/aggiungiGioco")
	@Transactional
	public String AddGioco(Model model) {
		model.addAttribute("gioco",new Gioco());
		return "formNewGioco.html";
	}
	
	@PostMapping("/addGioco")
	public String newGioco(
			@ModelAttribute("gioco")Gioco gioco, Model model,@RequestParam("image") MultipartFile imageFile) throws IOException {
		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Credentials credentials = credentialsService.getCredentials(userDetails.getUsername());
		User user = credentials.getUser();
		gioco.setUserg(user);
		Image ImmagineSalvata= imageservice.saveImage(imageFile);
		//ImmagineSalvata.setGioco(gioco);
		gioco.setImmagine(ImmagineSalvata);
		this.giocoService.save(gioco);
		model.addAttribute("giochi", this.giocoService.findAll());
		return "redirect:giochi";
	}
	
	
	
}

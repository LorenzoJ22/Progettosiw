package it.uniroma3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import it.uniroma3.service.UserService;

import it.uniroma3.controller.validator.UserValidator;
import it.uniroma3.controller.validator.CredentialsValidator;
import it.uniroma3.service.CredentialsService;
import it.uniroma3.service.GiocoService;
import it.uniroma3.controller.validator.*;
import it.uniroma3.model.Credentials;
import it.uniroma3.model.Gioco;
import it.uniroma3.model.User;

@Controller
public class AdminController {
	
	@Autowired
	 private GiocoService giocoService;
	
	@Autowired
	 private UserService userService;
	@Autowired
	CredentialsService credentialsService;
	@Autowired 
	private CredentialsValidator credentialsValidator;
	@Autowired
	private UserValidator userValidator;
	
	@GetMapping(value = "/registerAdmin") 
	public String showRegisterFormAdmin (Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("credentials", new Credentials());
//		model.addAttribute("role",Credentials.ADMIN_ROLE);
		return "registerAdmin.html";
	}
	
	
	@PostMapping(value = { "/registerAdmin" })
    public String registerAdmin(
    						    @ModelAttribute("user") User user, 
                 BindingResult userBindingResult, 
                 @ModelAttribute("credentials") Credentials credentials,
                 BindingResult credentialsBindingResult,
                 Model model) {
    	
		this.credentialsValidator.validate(credentials, credentialsBindingResult);
		this.userValidator.validate(user, userBindingResult);
		
		// se user e credential hanno entrambi contenuti validi, memorizza User e the Credentials nel DB
        if(!credentialsBindingResult.hasErrors() && !userBindingResult.hasErrors()) {
        	userService.saveUser(user);
        	credentialsService.saveCredentialsAdmin(credentials);
            
            model.addAttribute("user", user);           
            userService.saveUser(user);
            System.out.println("L'utente registrato e': "+credentials.getRole());
            
            return "formLogin.html";
        }
        return "registerAdmin.html";
    }
	
	/*Aggiungi gioco da Admin*/
	@GetMapping("/NewGiocoAdmin")
    public String addGiochiAdmin( @ModelAttribute("gioco") Gioco gioco, 
    		Model model) {    	
    	model.addAttribute("gioco", new Gioco());
    	return "admin/formNewGiocoAdmin.html";
    }
	
	@PostMapping("/giochiAdmin")
	public String newGiocoAdmin(
			@ModelAttribute("gioco")Gioco gioco, Model model) {
		
		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Credentials credentials = credentialsService.getCredentials(userDetails.getUsername());
		User user = credentials.getUser();
	    gioco.setUserg(user);
	    gioco.getUserg().setId(user.getId());
		user.getGiochi().add(gioco);
		giocoService.save(gioco);
		
		model.addAttribute("giochi", this.giocoService.findAll());
		return "giochi.html";
		//return "redirect:Ricetta/"+ricetta.getId();
	}
	
	/*Elenco degli utenti che puoi eliminare uno o aggiungere*/
    @GetMapping("/UtentiAdmin")
	public String getUtentiAdmin(Model model) {
    		model.addAttribute("credentials",new Credentials());
			model.addAttribute("utenti", userService.findAll());
			return "admin/utentiAdmin.html";
		}
	
	
	
	
	
	
	
	
}

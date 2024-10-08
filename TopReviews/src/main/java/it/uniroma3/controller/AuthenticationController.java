package it.uniroma3.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import it.uniroma3.controller.validator.CredentialsValidator;
import it.uniroma3.controller.validator.UserValidator;
import it.uniroma3.model.Credentials;
import it.uniroma3.model.Image;
import it.uniroma3.model.User;
import it.uniroma3.service.CredentialsService;
import it.uniroma3.service.ImageService;
import it.uniroma3.service.UserService;


@Controller
public class AuthenticationController {
	
	@Autowired
	private CredentialsService credentialsService;

    @Autowired
	private UserService userService;
    @Autowired 
	private CredentialsValidator credentialsValidator;
	@Autowired
	private UserValidator userValidator;
	
    @GetMapping("/SceltaRegistrazione")
	public String sceltaRegistrazione(Model model) {
		return "SceltaRegistrazione.html";
	}
    
    
	@GetMapping(value = "/register") 
	public String showRegisterForm (Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("credentials", new Credentials());
		return "user/formRegisterUser";
	}
	
	@GetMapping(value = "/login") 
	public String showLoginForm (Model model) {
		return "formLogin";
	}

	@GetMapping(value = "/") 
	public String index(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication instanceof AnonymousAuthenticationToken) {
	        return "index.html";
		}
		else {		
			UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			Credentials credentials = credentialsService.getCredentials(userDetails.getUsername());
			if (credentials.getRole().equals(Credentials.ADMIN_ROLE)) {
//				model.addAttribute("userId", credentials.getUser().getId());
//				model.addAttribute("user", credentials.getUser());
				return "admin/indexAdmin.html";
			}
			if (credentials.getRole().equals(Credentials.DEFAULT_ROLE)) {
//				 model.addAttribute("cuocoId", credentials.getUser().getId());
				model.addAttribute("userId", credentials.getUser().getId());
				model.addAttribute("user", credentials.getUser());
				return "user/indexUser.html";
			}
			if(credentials.getRole().equals(null)) {
				return "index.html";
			}
		}
        return "index.html";
	}
		
    @GetMapping(value = "/success")
    public String defaultAfterLogin(Model model,@AuthenticationPrincipal User user) {
        
    	UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	Credentials credentials = credentialsService.getCredentials(userDetails.getUsername());
    	if (credentials.getRole().equals(Credentials.ADMIN_ROLE)) {
    		model.addAttribute("userId", credentials.getUser().getId());
       		model.addAttribute("user", credentials.getUser());
            return "admin/indexAdmin.html";
        }
    	if (credentials.getRole().equals(Credentials.DEFAULT_ROLE)) {
//   		 model.addAttribute("cuocoId", credentials.getUser().getId());
   		model.addAttribute("userId", credentials.getUser().getId());
   		model.addAttribute("user", credentials.getUser());
           return "user/indexUser.html";
   	}
        return "index.html";
    }

	@PostMapping(value = { "/register" })
    public String registerUser(@ModelAttribute("user") User user,
                 BindingResult userBindingResult, /*@Valid*/
                 @ModelAttribute("credentials") Credentials credentials,
                 BindingResult credentialsBindingResult,
                 Model model){
		
		this.credentialsValidator.validate(credentials, credentialsBindingResult);
		
		this.userValidator.validate(user, userBindingResult);
		
		// se user e credential hanno entrambi contenuti validi, memorizza User e the Credentials nel DB
        if(!userBindingResult.hasErrors() && !credentialsBindingResult.hasErrors()) {
        	userService.saveUser(user);
            credentials.setUser(user);
            credentialsService.saveCredentials(credentials);
            model.addAttribute("user", user);
            return "registrationSuccessful.html";
        }
        return "user/formRegisterUser.html";
    }
}
package it.uniroma3.controller;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import it.uniroma3.model.Image;
import it.uniroma3.service.ImageService;



@RestController
public class ImageController {

    @Autowired
    private ImageService imageService;
 
    
     @GetMapping("/image/{id}")
     public ResponseEntity<byte[]> getImage(@PathVariable Long id) {
         Optional<Image> imageData = imageService.getImage(id);

         if (imageData.isPresent()) {
             Image image = imageData.get();
             return ResponseEntity.ok()
                     .contentType(MediaType.IMAGE_JPEG) // o il tipo MIME corretto
                     .body(image.getData());
         } else {
             return ResponseEntity.notFound().build();
         }
     }
    	 
 }


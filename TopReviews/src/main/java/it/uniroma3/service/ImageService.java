package it.uniroma3.service;
import java.io.IOException;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import it.uniroma3.model.Image;
import it.uniroma3.repository.ImageRepository;


@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    public Image saveImage(MultipartFile file) throws IOException {
        Image image = new Image();
        image.setName(file.getOriginalFilename());
        image.setData(file.getBytes());
        image.setContentType(file.getContentType());
       
        return imageRepository.save(image);
    }
    
    public Optional<Image> getImage(Long id) {
        return imageRepository.findById(id);
    }
    
    public Optional<Image> getImageByGiocoid(Long id){
    	return imageRepository.findImageByGiocoid(id);
    	
    }
}

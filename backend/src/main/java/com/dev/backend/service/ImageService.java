package com.dev.backend.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dev.backend.entities.Image;
import com.dev.backend.entities.Product;
import com.dev.backend.repository.ImageRepository;
import com.dev.backend.repository.ProductRepository;


@Service
public class ImageService {
  
  @Autowired
  private ImageRepository repository;

  @Autowired
  private ProductRepository productRepository;

  public List<Image> findAll(){
    return repository.findAll();
  }

  public Image insert(Long id, MultipartFile file){
    Product product = productRepository.findById(id).get();
    Image image = new Image();

    try {
      if(!file.isEmpty()){
        byte[] fileContent = file.getBytes();
        String pathImage = String.valueOf(product.getId()) + file.getOriginalFilename();

        Path path = Paths.get("/mnt/c/imagens/lojavirtual/" + pathImage);
        Files.write(path, fileContent);
        image.setName(pathImage); //Nome da imagem

      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    image.setCreatDate(Instant.now()); //Data de criação
    image.setProduct(product); //Associar ao produto
    image = repository.saveAndFlush(image);
    productRepository.saveAndFlush(product);
    
    return image;
  }

  public Image update(Image obj){
    obj.setUpdateDate(Instant.now());
    return repository.saveAndFlush(obj);
  }

  public void delete(Long id) {
    repository.deleteById(id);
  }

}

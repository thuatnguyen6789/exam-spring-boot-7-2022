package com.example.examspringboot.controller;

import com.example.examspringboot.entity.Product;
import com.example.examspringboot.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/products")
@CrossOrigin("*")
public class ProductApi {
    @Autowired
    private ProductRepository productRepository;

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Product> findAll(){
        return productRepository.findAll();
    }

    @RequestMapping(path = "{id}", method = RequestMethod.GET)
    public Product findById(@PathVariable String prodId) {
        Optional<Product> optionalProduct = productRepository.findById(String.valueOf(prodId));
        if(optionalProduct.isPresent()){
            return optionalProduct.get();
        }
        return null;
    }

    @RequestMapping(method = RequestMethod.POST)
    public Product create(@RequestBody Product product){
        productRepository.save(product);
        return product;
    }

    @RequestMapping(path = "{id}", method = RequestMethod.DELETE)
    public boolean deleteById(@PathVariable String prodId){
        Optional<Product> optionalProduct = productRepository.findById(String.valueOf(prodId));
        if(optionalProduct.isPresent()){
            Product product = optionalProduct.get();
            productRepository.delete(product);
        }
        return false;
    }

    @RequestMapping(path = "{id}", method = RequestMethod.PUT)
    public Product updateById(@RequestBody Product updateProduct, @PathVariable String prodId){
        Optional<Product> optionalProduct = productRepository.findById(String.valueOf(prodId));
        if(optionalProduct.isPresent()){
            Product existingProduct = optionalProduct.get();
            existingProduct.setProdName(updateProduct.getProdName());
            existingProduct.setDescription(updateProduct.getDescription());
            existingProduct.setDateOfMan(updateProduct.getDateOfMan());
            existingProduct.setPrice(updateProduct.getPrice());
            productRepository.save(existingProduct);
        }
        return null;
    }
}

package com.example.examspringboot.controller;

import com.example.examspringboot.entity.Product;
import com.example.examspringboot.entity.Sale;
import com.example.examspringboot.repository.ProductRepository;
import com.example.examspringboot.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/sales")
@CrossOrigin("*")
public class SaleApi {
    @Autowired
    private SaleRepository saleRepository;

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Sale> findAll(){
        return saleRepository.findAll();
    }

    @RequestMapping(path = "{id}", method = RequestMethod.GET)
    public Sale findById(@PathVariable Integer siNo) {
        Optional<Sale> optionalSale = saleRepository.findById(siNo);
        if(optionalSale.isPresent()){
            return optionalSale.get();
        }
        return null;
    }
}

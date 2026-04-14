package com.example.productvalidator.controller;

import com.example.productvalidator.model.Product;
import com.example.productvalidator.validator.ProductValidator;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductValidator productValidator;

    public ProductController(ProductValidator productValidator) {
        this.productValidator = productValidator;
    }

    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody Product product) {
        Errors errors = new BeanPropertyBindingResult(product, "product");
        productValidator.validate(product, errors);

        if (errors.hasErrors()) {
            List<String> errorMessages = errors.getAllErrors()
                    .stream()
                    .map(error -> error.getDefaultMessage())
                    .toList();

            return ResponseEntity.badRequest().body(errorMessages);
        }

        return ResponseEntity.ok().build();
    }
}

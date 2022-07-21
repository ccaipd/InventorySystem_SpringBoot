package com.spring.demo.controller;

import java.util.List;

import com.spring.demo.entities.Product;
import com.spring.demo.services.ProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    @Autowired
    private ProductServices productServices;

    // insert a product into db
    @PostMapping("/product")
    public ResponseEntity<Product> insertProduct(@RequestBody Product product) {
        Product savedProduct = productServices.saveProduct(product);
        return new ResponseEntity<Product>(savedProduct, HttpStatus.CREATED);
    }

    // get a single product by id
    @GetMapping("/product")
    public ResponseEntity<Product> getProduct(@RequestParam(name = "productId") long productId) {
        Product product = productServices.getProduct(productId);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    // get all products in the table in our db
    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productServices.getAllProducts();
    }

    // update an existing product in db
    @PutMapping("/product")
    public ResponseEntity<Product> updateProduct(@RequestParam(name = "productId") long productId, @RequestBody Product product) {
        Product updatedProduct = productServices.updateProduct(productId, product);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }

    // delete an existing product in db
    @DeleteMapping("/product")
    public ResponseEntity<Product> deleteProduct(@RequestParam(name = "productId") long productId) {
        Product deletedProduct = productServices.deleteProduct(productId);
        return new ResponseEntity<>(deletedProduct, HttpStatus.OK);
    }

    // get product by name using a raw SQL statement
    @GetMapping("/productsByName")
    public List<Product> getProductsByName(@RequestParam(name = "productName") String productName) {
        return productServices.getProductsByName(productName);
    }
}

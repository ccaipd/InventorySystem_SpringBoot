package com.spring.demo.services;


import java.util.List;

import com.spring.demo.dao.ProductRepository;
import com.spring.demo.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServices {

    @Autowired
    private ProductRepository productRepository;

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public Product getProduct(long productId) {
        try {
            Product product = productRepository.findById(productId).get();
            return product;
        } catch(RuntimeException exc) {
            throw new RuntimeException("No product found");
        }
    }

    public List<Product> getAllProducts() {
        try {
            List<Product> products = productRepository.findAll();
            return products;
        } catch(RuntimeException exc) {
            throw new RuntimeException("None products found");
        }
    }

    public Product updateProduct(long productId, Product product) {
        try {
            Product existingProduct = productRepository.findById(productId).get();
            existingProduct.setProductName(product.getProductName());
            existingProduct.setColor(product.getColor());
            existingProduct.setPrice(product.getPrice());
            productRepository.save(existingProduct);
            return existingProduct;
        } catch(RuntimeException exc) {
            throw new RuntimeException("Fail to update a product");
        }
    }

    public Product deleteProduct(long productId) {
        try {
            Product existingProduct = productRepository.findById(productId).get();
            productRepository.deleteById(productId);
            return existingProduct;
        } catch(RuntimeException exc) {
            throw new RuntimeException("Fail to delete a product");
        }
    }

    public List<Product> getProductsByName(String productName) {
        try {
            List<Product> products = productRepository.getProductsByName(productName);
            return products;
        } catch(RuntimeException exc) {
            throw new RuntimeException("Fail to get product by name");
        }
    }
}

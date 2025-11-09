package com.india.railway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.india.railway.model.mysql.CardSelectionRequest;
import com.india.railway.model.mysql.Product_Mysql;
import com.india.railway.service.mysql.ProductService_Mysql;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController_Mysql {

    @Autowired
    private ProductService_Mysql productService_Mysql;

    // Create a new product
    @PostMapping
    public ResponseEntity<Product_Mysql> createProduct(@Valid @RequestBody Product_Mysql product) {
        Product_Mysql savedProduct = productService_Mysql.saveProduct(product);
        return ResponseEntity.ok(savedProduct);
    }

    // Get all products
    @GetMapping
    public ResponseEntity<List<Product_Mysql>> getAllProducts() {
        return ResponseEntity.ok(productService_Mysql.getAllProducts());
    }

    // Get product by ID
    @GetMapping("/{id}")
    public ResponseEntity<Product_Mysql> getProductById(@PathVariable Long id) {
        Optional<Product_Mysql> product = productService_Mysql.getProductById(id);
        return product.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Update product
    @PutMapping("/{id}")
    public ResponseEntity<Product_Mysql> updateProduct(@PathVariable Long id,
            @Valid @RequestBody Product_Mysql productDetails) {
        Product_Mysql updatedProduct = productService_Mysql.updateProduct(id, productDetails);
        return ResponseEntity.ok(updatedProduct);
    }

    // Delete product
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService_Mysql.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/cards")
    public String receiveSelectedCards(@RequestBody CardSelectionRequest request) {
        System.out.println("Customer ID: " + request.getCustomerId());
        System.out.println("Received Cards: ");
        for (CardSelectionRequest.Card card : request.getSelectedCards()) {
            System.out.println(
                    "ID: " + card.getId() + ", Title: " + card.getTitle() + ", Quantity: " + card.getQuantity());
        }
        return "Received " + request.getSelectedCards().size() + " cards for Customer ID: " + request.getCustomerId();
    }

    // Delete product

}

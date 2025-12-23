package com.india.railway.service.mysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.india.railway.model.mysql.Product_Mysql;


import com.india.railway.repository.mysql.ProductRepository_Mysql;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService_Mysql {

    @Autowired
    private ProductRepository_Mysql productRepository_mysql;



    // Create a new product
    public Product_Mysql saveProduct(Product_Mysql product) {
        Product_Mysql ps = productRepository_mysql.save(product);

        return ps;
    }

    // Get all products
    public List<Product_Mysql> getAllProducts() {
        return productRepository_mysql.findAll();
    }

    // Get product by ID
    public Optional<Product_Mysql> getProductById(Long id) {
        return productRepository_mysql.findById(id);
    }

    // Update product
    public Product_Mysql updateProduct(Long id, Product_Mysql productDetails) {
        return productRepository_mysql.findById(id).map(product -> {
            product.setName(productDetails.getName());
            product.setDescription(productDetails.getDescription());
            product.setPrice(productDetails.getPrice());
            product.setStockQuantity(productDetails.getStockQuantity());
            return productRepository_mysql.save(product);
        }).orElseThrow(() -> new RuntimeException("Product not found with ID: " + id));
    }

    // Delete product
    public void deleteProduct(Long id) {
        productRepository_mysql.deleteById(id);
    }
}

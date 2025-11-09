package com.india.railway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.india.railway.model.elastic.Product_Elastic;
import com.india.railway.service.elastic.ProductService;

import java.util.List;

@RestController
@RequestMapping("/products_elastic")
public class ProductController_Elastic {

    @Autowired
    private ProductService productService;

    /*
     * @PostMapping
     * public Product_Elastic createProduct(@RequestBody Product_Elastic product) {
     * return productService.saveProduct(product);
     * }
     */

    @GetMapping
    public Iterable<Product_Elastic> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/byName")
    public List<Product_Elastic> searchProducts(@RequestParam String name) {
        return productService.searchProductsByName(name);
    }

    @DeleteMapping
    public String deleteall() {
        productService.deleteAll();
        return "deleted all the documents";
    }

    @GetMapping("/suggest")
    public List<String> getSuggestions(@RequestParam String query) {
        return productService.getAutoSuggestionsOnName(query);
    }

    @GetMapping("/fuzzysearch")
    public List<Product_Elastic> fuzzySearchByNameAndPrice(@RequestParam String name) {
        return productService.fuzzySearchByMultipleNames(name, name);

    }

    @GetMapping("/fuzzymatchallfields")
    public List<Product_Elastic> fuzzyMultiFieldSearch(@RequestParam String name) {
        return productService.fuzzyMultiFieldSearch(name, name);

    }

}

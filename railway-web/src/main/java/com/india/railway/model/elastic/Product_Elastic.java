package com.india.railway.model.elastic;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.CompletionField;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import lombok.Data;
import java.util.Date;

// @Setting(shards = 3, replicas = 2)

@Document(indexName = "newproduct")
public class Product_Elastic {
    @Id
    private Long id;

    @Field(type = FieldType.Text)
    private String name;

    @Field(type = FieldType.Text)
    private String description;

    @Field(type = FieldType.Double)
    private Double price;

    @Field(type = FieldType.Boolean)
    private Boolean available;

    @Field(type = FieldType.Date)
    private Date createdAt;

    @CompletionField
    private List<String> suggestions; // This field will store autocomplete suggestions

    // Constructors
    public Product_Elastic() {
    }

    public Product_Elastic(Long id, String name, String description, Double price, Boolean available, Date createdAt,
            List<String> suggestions) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.available = available;
        this.createdAt = createdAt;
        this.suggestions = suggestions;
    }

    // Getters and Setters

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }

    public Boolean getAvailable() {
        return available;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public String getName() {
        return name;
    }

    public List<String> getSuggestions() {
        return suggestions;
    }

    public void setSuggestions(List<String> suggestions) {
        this.suggestions = suggestions;
    }

}
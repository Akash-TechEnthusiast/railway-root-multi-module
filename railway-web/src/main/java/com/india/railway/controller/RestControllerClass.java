package com.india.railway.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestControllerClass {

    @GetMapping("/api/users/{id}")
    public String greetUser(@PathVariable("id") Long id) {
        return "Hello, User " + id;
    }
}

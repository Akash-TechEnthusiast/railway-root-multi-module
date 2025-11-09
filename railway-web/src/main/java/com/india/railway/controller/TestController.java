package com.india.railway.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/test")
public class TestController {
    
    @GetMapping("/hello")
    public ResponseEntity<String> test() {
        return new ResponseEntity<String>("I AM AN INDIAN", HttpStatus.OK);
    }
}
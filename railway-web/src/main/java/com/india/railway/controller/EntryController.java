package com.india.railway.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.india.railway.model.mysql.User;
import com.india.railway.service.mysql.UserServiceTestImpl;

@RestController
@RequestMapping("/userstest")
public class EntryController {

    @Autowired
    private UserServiceTestImpl userService;

    @PostMapping("/save1")
    public ResponseEntity<User> createUserTest(@RequestBody User user) {
        User savedUser = userService.saveUsertest(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @GetMapping("/list")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        String fileName = file.getOriginalFilename();
        return ResponseEntity.ok("File uploaded: " + fileName);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable Long id) {
        String response = userService.deleteUser(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getId/{id}")
    public ResponseEntity<String> getUser(@PathVariable Long id) {
        return ResponseEntity.ok("User with ID: " + id);
    }
}

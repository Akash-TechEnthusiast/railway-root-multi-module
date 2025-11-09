package com.india.railway.controller.user;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.india.railway.model.mysql.ResetPasswordRequest;
import com.india.railway.model.mysql.User;
import com.india.railway.model.mysql.UserProfile;
import com.india.railway.service.mysql.UserService;

import reactor.core.publisher.Flux;

@RequestMapping(path = "/user")
@RestController
public class UserController {

	@Autowired
	private UserService userService;

	// @Autowired
	// private PasswordEncoder passwordEncoder;

	@PostMapping(path = "/adduser")
	public @ResponseBody String addUsers(@RequestBody User user) {

		User newuser = new User();
		newuser.setUserName(user.getUserName());

		newuser.setMobileNumber(user.getMobileNumber());
		newuser.setEmail(user.getEmail());
		newuser.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		newuser.setConfirmPassword(user.getConfirmPassword());

		UserProfile up = new UserProfile();
		up.setEmail("user@gmail.com");
		up.setLastName("gandham");
		newuser.setUserProfile(up);

		userService.saveUser(newuser);
		return "Details got Saved";
	}

	@GetMapping(path = "/users")
	public @ResponseBody Iterable<User> getAllUsers() {
		// This returns a JSON or XML with the Book
		return userService.getAllUsers();
	}

	@PostMapping
	public User createUser(@RequestBody User user) {
		return userService.saveUser(user);
	}

	@GetMapping("/{id}")
	public User getUserById(@PathVariable Long id) {
		return userService.getUserById(id);
	}

	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable Long id) {
		userService.deleteUser(id);
	}

	// @RequestParam("email") String email
	@PostMapping("/forgot-password")
	public ResponseEntity<String> forgotPassword(@RequestBody Map<String, String> request) {

		String email = request.get("email");
		if (email == null || email.isEmpty()) {
			return ResponseEntity.badRequest().body("Email is required");
		}
		userService.processForgotPassword(email);
		return ResponseEntity.ok("Password reset link sent to email.");
	}

	@PostMapping("/reset-password")
	public ResponseEntity<String> resetPassword(@RequestBody ResetPasswordRequest resetPasswordRequest) {

		userService.resetPassword(resetPasswordRequest.getToken(), resetPasswordRequest.getPassword());
		return ResponseEntity.ok("Password has been reset.");
	}

	@GetMapping(path = "/flux")
	public Flux<String> getFlux() {
		return Flux.just("Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 6", "Item 7", "Item 8", "Item 9",
				"Item 10");
	}

	@GetMapping("/greet")
	public ResponseEntity<String> greetUser(@RequestParam String name) {
		return ResponseEntity.ok("Hello, " + name);
	}
}

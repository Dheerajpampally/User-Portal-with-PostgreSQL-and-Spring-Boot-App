package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.UserLogin;
import com.example.demo.model.UserRegistration;
import com.example.demo.repository.UserRepository;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String showHomePage() {
        return "home"; // This corresponds to "home.html" in the "templates" directory
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> registerUser(@RequestBody UserRegistration userRegistration) {
        try {
            // Save the user registration data to the database
            userRepository.save(userRegistration);
            Map<String, String> response = new HashMap<>();
            response.put("message", "User registered successfully.");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> response = new HashMap<>();
            response.put("error", "Registration failed: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> loginUser(@RequestBody UserLogin userLogin) {
        try {
            // Check if the provided email and password match a user in the database
            UserRegistration user = userRepository.findByEmail(userLogin.getEmail());

            if (user != null && user.getPassword().equals(userLogin.getPassword())) {
                Map<String, String> response = new HashMap<>();
                response.put("message", "Login successful.");
                return ResponseEntity.ok(response);
            } else {
                Map<String, String> response = new HashMap<>();
                response.put("error", "Login failed: Invalid email or password.");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            }
        } catch (Exception e) {
            Map<String, String> response = new HashMap<>();
            response.put("error", "Login failed: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/profile.html") // Add this mapping for profile.html
    public ModelAndView showProfilePage() {
        return new ModelAndView("profile"); // This corresponds to "profile.html" in the "templates" directory
    }

}

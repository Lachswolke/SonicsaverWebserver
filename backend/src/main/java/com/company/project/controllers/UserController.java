package com.company.project.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.company.project.dto.UserDTo;
import com.company.project.entity.Sonicsaver;
import com.company.project.entity.UserEntity;
import com.company.project.repository.SonicsaverRepository;
import com.company.project.repository.UserRepository;
import com.company.project.services.AuthService;
import com.company.project.util.JwtUtil;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private AuthService authService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SonicsaverRepository sonicsaverRepository;

    @PostMapping("/addingSonicsaver")
    public ResponseEntity<?> addSonicsaver(@RequestParam Long userId, @RequestParam Long sonicsaverId) {
        Optional<UserEntity> userOptional = userRepository.findById(userId);
        Optional<Sonicsaver> sonicsaverOptional = sonicsaverRepository.findById(sonicsaverId);

        if (userOptional.isPresent() && sonicsaverOptional.isPresent()) {
            UserEntity user = userOptional.get();
            Sonicsaver sonicsaver = sonicsaverOptional.get();

            user.getIds().add(sonicsaver.getId());
            userRepository.save(user);

            return ResponseEntity.ok("Sonicsaver added successfully to user");
        } else {
            return ResponseEntity.status(404).body("User or Sonicsaver not found");
        }
    }

    @GetMapping("/getUser/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id) {
        Optional<UserEntity> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()) {
            return ResponseEntity.ok(userOptional.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }

    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers() {
        List<UserEntity> users = userRepository.findAll();
        return ResponseEntity.ok(users);
    }

    @PutMapping("/updateUser/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody UserDTo userDto) {
        Optional<UserEntity> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()) {
            UserEntity user = userOptional.get();
            user.setUsername(userDto.getUsername());
            user.setPassword(userDto.getPassword()); // Make sure to encode the password
            userRepository.save(user);
            return ResponseEntity.ok("User updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        Optional<UserEntity> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()) {
            userRepository.deleteById(id);
            return ResponseEntity.ok("User deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleExceptions(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + ex.getMessage());
    }
}

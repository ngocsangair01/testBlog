package com.example.demedung.controllers;

import com.example.demedung.dtos.UserDTO;
import com.example.demedung.exceptions.DuplicateException;
import com.example.demedung.exceptions.NotFoundException;
import com.example.demedung.models.User;
import com.example.demedung.repositories.BlogRepository;
import com.example.demedung.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BlogRepository blogRepository;

    @GetMapping
    public ResponseEntity<?> findAllUser() {
        return ResponseEntity.status(200).body(userRepository.findAll());
    }

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody UserDTO userDTO) {
        User oldUser = userRepository.findByName(userDTO.getName());
        if (oldUser != null) {
            throw new DuplicateException("User already exists");
        }
        User user = new User();
        user.setName(userDTO.getName());
        user.setAge(userDTO.getAge());
        user.setAddress(userDTO.getAddress());
        User newUser = userRepository.save(user);
        return ResponseEntity.status(201).body(newUser);
    }
    @GetMapping("/{idUser}")
    public ResponseEntity<?> findUserById(@PathVariable("idUser")Integer idUser){
        Optional<User> user = userRepository.findById(idUser);
        if (user.isEmpty()) {
            throw new NotFoundException("User does not exists");
        }
        return ResponseEntity.status(201).body(user.get());
    }
}

package com.example.login_auth_api.controllers;

import com.example.login_auth_api.domain.user.User;
import com.example.login_auth_api.repositories.UserRepository;
import com.example.login_auth_api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    //--> Método de busca por id
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id){
        Optional<User> user =  userService.findById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    //--> Método de busca por todos os usuários
    @GetMapping("/users")
    public ResponseEntity<Iterable<User>> getAllUsers(){
        return ResponseEntity.ok(userService.findAll());
    }
}

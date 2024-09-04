package com.example.login_auth_api.controllers;

import com.example.login_auth_api.domain.User;
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


    /*---------------------------------------*/
    //          LISTANDO USUÁRIO POR ID      //
    /*---------------------------------------*/
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id){
        Optional<User> user =  userService.findById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /*---------------------------------------*/
    //       LISTANDO TODOS OS USUÁRIOS      //
    /*---------------------------------------*/
    @GetMapping("/users")
    public ResponseEntity<Iterable<User>> getAllUsers(){
        return ResponseEntity.ok(userService.findAll());
    }
}

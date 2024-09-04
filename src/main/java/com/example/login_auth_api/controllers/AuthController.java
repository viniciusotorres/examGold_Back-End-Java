package com.example.login_auth_api.controllers;

import com.example.login_auth_api.domain.School;
import com.example.login_auth_api.domain.User;
import com.example.login_auth_api.dto.LoginRequestDTO;
import com.example.login_auth_api.dto.RegisterRequestDTO;
import com.example.login_auth_api.dto.ResponseDTO;
import com.example.login_auth_api.infra.security.TokenService;
import com.example.login_auth_api.repositories.SchollRepository;
import com.example.login_auth_api.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;
    private final SchollRepository schollRepository;

    /*---------------------------------------*/
    //        FAZENDO O LOGIN DO USUÁRIO     //
    /*---------------------------------------*/
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequestDTO loginRequestDTO){
        // Busca o usuário pelo email
        var user = userRepository.findByEmail(loginRequestDTO.email())
                .orElseThrow(() -> new RuntimeException("User Not Found"));

        // Verifica se o usuário é um professor
        if (user.getItsTeacher() != loginRequestDTO.itsTeacher()){
            return ResponseEntity.badRequest().body(new ResponseDTO(null, null, null, "Você está usando credencial incorreta.", HttpStatus.BAD_REQUEST.value(), null, false));
        }

        // Verifica se a senha está correta
        if(passwordEncoder.matches(loginRequestDTO.password(), user.getPassword())){
            var token = tokenService.generateToken(user);
            return ResponseEntity.ok(new ResponseDTO(user.getUsername(), user.getBirthDate(), token, "Login efetuado com sucesso", HttpStatus.OK.value(), user.getId(), user.getItsTeacher()));
        }
        return ResponseEntity.badRequest().body(new ResponseDTO(null, null, null, "Login falhou", HttpStatus.BAD_REQUEST.value(), null, false));
    }

    /*---------------------------------------*/
    //      FAZENDO O REGISTRO DO USUÁRIO    //
    /*---------------------------------------*/
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterRequestDTO registerRequestDTO){
        Optional<User> user = userRepository.findByEmail(registerRequestDTO.email());


        if(user.isEmpty()){
            var newUser = new User();
            newUser.setEmail(registerRequestDTO.email());
            newUser.setUsername(registerRequestDTO.name());
            newUser.setPassword(passwordEncoder.encode(registerRequestDTO.password()));
            newUser.setBirthDate(registerRequestDTO.birthDate());
            newUser.setItsTeacher(registerRequestDTO.itsTeacher());

            School scholl = schollRepository.findById(registerRequestDTO.schollId())
                    .orElseThrow(() -> new RuntimeException("Escola não encontrada!"));

            newUser.setScholl(scholl);

            this.userRepository.save(newUser);

            String token = tokenService.generateToken(newUser);
            return ResponseEntity.ok(new ResponseDTO(newUser.getUsername(), newUser.getBirthDate(),token, "Usuário registrado com sucesso", HttpStatus.OK.value(), newUser.getId(), newUser.getItsTeacher()));
        }
        return ResponseEntity.badRequest().body(new ResponseDTO(null, null, null,"Registro falhou", HttpStatus.BAD_REQUEST.value(), null, false));
    }

}

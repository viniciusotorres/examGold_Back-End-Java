package com.example.login_auth_api.controllers;

import com.example.login_auth_api.domain.User;
import com.example.login_auth_api.dto.LoginRequestDTO;
import com.example.login_auth_api.dto.ResponseDTO;
import com.example.login_auth_api.infra.security.SecurityConfig;
import com.example.login_auth_api.infra.security.TokenService;
import com.example.login_auth_api.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class AuthControllerTest {

    @InjectMocks // Injeta o mock no controller
    private AuthController authController; // Instância do controller

    @Mock // Cria um mock do TokenService
    private TokenService tokenService; // Instância do TokenService

    @Mock // Cria um mock do UserRepository
    private UserRepository userRepository; // Instância do UserRepository

    @Mock
    private PasswordEncoder passwordEncoder;

    public AuthControllerTest() {
        MockitoAnnotations.openMocks(this); // Inicializa os mocks
    }


    @Test
    public void testLogin_Success() {
//        // Arrange
//        LoginRequestDTO loginRequestDTO = new LoginRequestDTO("viniciustorres29@gmail.com", "gremionavida", true);
//
//        User user = new User();
//        user.setEmail("viniciustorres29@gmail.com");
//        user.setPassword("encodedPassword");
//        user.setItsTeacher(true);
//
//        when(userRepository.findByEmail("viniciustorres29@gmail.com")).thenReturn(Optional.of(user));
//        when(passwordEncoder.matches("gremionavida", "encodedPassword")).thenReturn(true);
//        when(tokenService.generateToken(user)).thenReturn("token");
//
//        // Act
//        ResponseEntity<ResponseDTO> response = authController.login(loginRequestDTO);
//
//        // Assert
//        ResponseDTO responseBody = response.getBody();
//        assertEquals(HttpStatus.OK.value(), responseBody.status());
//        assertEquals("Login efetuado com sucesso", responseBody.message());
//        assertEquals("token", responseBody.token());
    }
}

package com.example.login_auth_api.dto;

import org.springframework.http.HttpStatus;

public record ResponseDTO(String name, String birthDate,String token, String message, int status, Long id, boolean itsTeacher) {
}

package com.example.login_auth_api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL) // Ignore null fields when serializing
public record ResponseCreate(String name, String message, int status, String type) {
}
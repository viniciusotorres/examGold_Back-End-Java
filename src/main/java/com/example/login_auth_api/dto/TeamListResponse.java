package com.example.login_auth_api.dto;

import com.example.login_auth_api.domain.Team;

import java.util.List;

public record TeamListResponse(List<Team> teams, String message, int status, String type) {
}

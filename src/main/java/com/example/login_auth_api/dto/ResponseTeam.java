package com.example.login_auth_api.dto;

import com.example.login_auth_api.domain.School;

public record ResponseTeam(Long id_team, String name_team, String descriptionTeam, Long school_id) {

}

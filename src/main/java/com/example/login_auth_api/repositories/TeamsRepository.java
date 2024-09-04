package com.example.login_auth_api.repositories;

import com.example.login_auth_api.domain.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeamsRepository extends JpaRepository<Team, Long> {

    Optional<Team> findByNameTeam(String nameTeam);
    Optional<Team> findByNameTeamAndSchoolId(String nameTeam, Long schoolId);
}
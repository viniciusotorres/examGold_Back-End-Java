package com.example.login_auth_api.services;

import com.example.login_auth_api.domain.Team;
import com.example.login_auth_api.repositories.TeamsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TeamService {

    @Autowired
    private TeamsRepository teamsRepository;

    public void create(Team team){
        teamsRepository.save(team);
    }

    public Iterable<Team> findAll(){
        return teamsRepository.findAll();
    }

    public Optional<Team> findById(Long id){
        return teamsRepository.findById(id);
    }

    public void deleteById(Long id){
        teamsRepository.deleteById(id);
    }


}

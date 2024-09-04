package com.example.login_auth_api.controllers;

import com.example.login_auth_api.domain.Team;
import com.example.login_auth_api.dto.ResponseCreate;
import com.example.login_auth_api.dto.ResponseTeam;
import com.example.login_auth_api.dto.TeamListResponse;
import com.example.login_auth_api.repositories.SchollRepository;
import com.example.login_auth_api.repositories.TeamsRepository;
import com.example.login_auth_api.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teams")
public class TeamController {

    @Autowired
    private TeamService teamService;

    @Autowired
    private TeamsRepository teamsRepository;

    @Autowired
    private SchollRepository schollRepository;

    /*---------------------------------------*/
    //          CRIANDO UM TIME              //
    /*---------------------------------------*/
    @PostMapping("/create")
    public ResponseEntity<ResponseCreate> create(@RequestBody ResponseTeam team) {

        var school = schollRepository.findById(team.school_id())
                .orElseThrow(() -> new RuntimeException("Escola não existe!"));

        if (teamsRepository.findByNameTeamAndSchoolId(team.name_team(), team.school_id()).isPresent()) {
            return ResponseEntity.badRequest().body(new ResponseCreate(null, "Time já existe dentro da escola inserida!", 400, "Error"));
        }

        if (teamsRepository.findByNameTeam(team.name_team()).isPresent()) {
            return ResponseEntity.badRequest().body(new ResponseCreate(null, "Time já existe!", 400, "Error"));
        }

        Team teamNew = new Team();
        teamNew.setNameTeam(team.name_team());
        teamNew.setDescriptionTeam(team.descriptionTeam());
        teamNew.setSchool(school);
        teamService.create(teamNew);

        return ResponseEntity.ok(new ResponseCreate(null, "Time criado com sucesso!", 200, "Success"));
    }

    /*---------------------------------------*/
    //          LISTANDO TODOS OS TIMES      //
    /*---------------------------------------*/
    @GetMapping("/list")
    public ResponseEntity<TeamListResponse> listTeam() {
        List<Team> teams = (List<Team>) teamService.findAll();
        TeamListResponse teamListResponse = new TeamListResponse(teams, "Esses são todos os times cadastrados", 200, "Success");
        return ResponseEntity.ok(teamListResponse);
    }
    /*---------------------------------------*/
    //          BUSCANDO TIME POR ID         //
    /*---------------------------------------*/
//    @GetMapping("/list/{id_team}")
//    public ResponseEntity<Team> getTeamById(@PathVariable Long id_team){
//        teamService.findById(id_team);
//        return ResponseEntity.ok().build();
//    }

    /*---------------------------------------*/
    //          DELETANDO TIME POR ID        //
    /*---------------------------------------*/
//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<Team> deleteTeamById(@PathVariable Long id){
//        teamService.deleteById(id);
//        return ResponseEntity.ok().build();
//    }

}
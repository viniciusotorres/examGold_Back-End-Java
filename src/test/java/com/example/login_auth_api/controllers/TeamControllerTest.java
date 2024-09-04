package com.example.login_auth_api.controllers;

import com.example.login_auth_api.domain.Team;
import com.example.login_auth_api.domain.School;
import com.example.login_auth_api.dto.ResponseCreate;
import com.example.login_auth_api.dto.ResponseTeam;
import com.example.login_auth_api.dto.TeamListResponse;
import com.example.login_auth_api.repositories.SchollRepository;
import com.example.login_auth_api.repositories.TeamsRepository;
import com.example.login_auth_api.services.TeamService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
public class TeamControllerTest {

    @InjectMocks
    private TeamController teamController;

    @Mock
    private TeamService teamService;

    @Mock
    private TeamsRepository teamsRepository;

    @Mock
    private SchollRepository schollRepository;

    public TeamControllerTest() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void testCreateTeam_Success(){
        ResponseTeam responseTeam = new ResponseTeam(null, "Team A", "Description A", 1L); // Configurando um ResponseTeam

        School school = new School(); // Configurando uma escola
        school.setId(1l); // Configurando o id da escola

        when(schollRepository.findById(1l)).thenReturn(Optional.of(school)); // Mockando o comportamento do repositório para retornar a escola
        when(teamsRepository.findByNameTeamAndSchoolId("Team A", 1l)).thenReturn(Optional.empty()); // Mockando o comportamento do repositório para retornar Optional.empty()
        when(teamsRepository.findByNameTeam("Team A")).thenReturn(Optional.empty()); // Mockando o comportamento do repositório para retornar Optional.empty()

        ResponseEntity<ResponseCreate> response = teamController.create(responseTeam); // Chamando o método create do controller

        assertEquals(200, response.getBody().status()); // Verificando se o status é 200
        assertEquals("Success", response.getBody().type());  // Verificando se o tipo é Success
        assertEquals("Time criado com sucesso!", response.getBody().message()); // Verificando se a mensagem é "Time criado com sucesso!"

    }

    @Test
    public void testCreateTeam_SchoolNotFound() {
        // Mockando o comportamento do repositório para retornar Optional.empty()
        when(schollRepository.findById(1L)).thenReturn(Optional.empty());

        // Configurando um ResponseTeam
        ResponseTeam responseTeam = new ResponseTeam(null, "Team A", "Description A", 1L);

        // Verificando se a exceção é lançada
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            teamController.create(responseTeam);
        });

        assertEquals("Escola não existe!", exception.getMessage());
    }
    @Test
    public void testListTeam() {
        List<Team> teams = List.of(new Team());
        when(teamService.findAll()).thenReturn(teams);

        ResponseEntity<TeamListResponse> response = teamController.listTeam();

        assertEquals(200, response.getBody().status());
        assertEquals("Success", response.getBody().type());
        assertEquals("Esses são todos os times cadastrados", response.getBody().message());
    }
}
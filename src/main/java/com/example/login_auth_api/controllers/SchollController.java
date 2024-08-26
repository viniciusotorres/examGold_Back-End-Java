package com.example.login_auth_api.controllers;

import com.example.login_auth_api.domain.user.Scholl;
import com.example.login_auth_api.dto.ResponseCreateSchoolDTO;
import com.example.login_auth_api.repositories.SchollRepository;
import com.example.login_auth_api.services.SchollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/school")
public class SchollController {

    @Autowired
    private SchollService schollService;
    @Autowired
    private SchollRepository schollRepository;

    //--> Método de busca por todos os usuários
    @GetMapping("/all")
    public ResponseEntity<Iterable<Scholl>> getAllSchools(){
        return ResponseEntity.ok(schollService.findAll());
    }

    //--> Método de busca por id
    @GetMapping("{id}")
    public ResponseEntity<Scholl> getSchoolById(@PathVariable Long id){
        Optional<Scholl> scholl = schollService.findById(id);
        return scholl.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    //--> Método para criar uma nova escola
    @PostMapping("/create")
    public ResponseEntity createSchool(@RequestBody Scholl school){
        Optional<Scholl> scholl = schollRepository.findByCnpj(school.getCnpj());

        if(scholl.isEmpty()){
            var newScholl = new Scholl();
            newScholl.setName(school.getName());
            newScholl.setCnpj(school.getCnpj());
            newScholl.setAddress(school.getAddress());
            newScholl.setPhone(school.getPhone());
            newScholl.setEmail(school.getEmail());
            this.schollRepository.save(newScholl);

            return ResponseEntity.ok(new ResponseCreateSchoolDTO(newScholl.getName(), "Escola cadastrada com sucesso.", HttpStatus.OK.value()));
        }
        return ResponseEntity.badRequest().body("Escola já cadastrada");
    }

    //--> Método para deletar uma escola
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteSchool(@PathVariable Long id){
        Optional<Scholl> scholl = schollService.findById(id);

        if(scholl.isPresent()){
            schollService.delete(id);
            return ResponseEntity.ok(new ResponseCreateSchoolDTO(scholl.get().getName(), "Escola deletada com sucesso.", HttpStatus.OK.value()));
        }
        return ResponseEntity.badRequest().body(new ResponseCreateSchoolDTO(null, "Escola não encontrada.", HttpStatus.BAD_REQUEST.value()));
    }

    //--> Método para atualizar uma escola
    @PutMapping("/update/{id}")
    public ResponseEntity updateSchool(@RequestBody Scholl scholl, @PathVariable Long id){
        Optional<Scholl> schollNew = schollService.findById(id);

        if(schollNew.isPresent()){
            schollService.update(id, scholl);
            return ResponseEntity.ok(new ResponseCreateSchoolDTO(scholl.getName(), "Escola atualizada com sucesso.", HttpStatus.OK.value()));
        }
        return ResponseEntity.badRequest().body(new ResponseCreateSchoolDTO(null, "Escola não encontrada.", HttpStatus.BAD_REQUEST.value()));
    }
}

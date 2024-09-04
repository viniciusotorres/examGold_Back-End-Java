package com.example.login_auth_api.controllers;

import com.example.login_auth_api.domain.School;
import com.example.login_auth_api.dto.ResponseCreate;
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

    /*---------------------------------------*/
    //          LISTANDO TODOS AS ESCOLAS    //
    /*---------------------------------------*/
    @GetMapping("/all")
    public ResponseEntity<Iterable<School>> getAllSchools(){
        return ResponseEntity.ok(schollService.findAll());
    }

    /*---------------------------------------*/
    //          LISTANDO ESCOLA POR ID       //
    /*---------------------------------------*/
    @GetMapping("{id}")
    public ResponseEntity<School> getSchoolById(@PathVariable Long id){
        Optional<School> scholl = schollService.findById(id);
        return scholl.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /*---------------------------------------*/
    //          CRIANDO UMA ESCOLA           //
    /*---------------------------------------*/
    @PostMapping("/create")
    public ResponseEntity createSchool(@RequestBody School school){
        Optional<School> scholl = schollRepository.findByCnpj(school.getCnpj());

        if(scholl.isEmpty()){
            var newScholl = new School();
            newScholl.setName(school.getName());
            newScholl.setCnpj(school.getCnpj());
            newScholl.setAddress(school.getAddress());
            newScholl.setPhone(school.getPhone());
            newScholl.setEmail(school.getEmail());
            this.schollRepository.save(newScholl);

            return ResponseEntity.ok(new ResponseCreate(newScholl.getName(), "Escola cadastrada com sucesso.", HttpStatus.OK.value(), "Success"));
        }
        return ResponseEntity.badRequest().body("Escola já cadastrada");
    }

    /*---------------------------------------*/
    //          DELETANDO UMA ESCOLA         //
    /*---------------------------------------*/
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteSchool(@PathVariable Long id){
        Optional<School> scholl = schollService.findById(id);

        if(scholl.isPresent()){
            schollService.delete(id);
            return ResponseEntity.ok(new ResponseCreate(scholl.get().getName(), "Escola deletada com sucesso.", HttpStatus.OK.value(), "Success"));
        }
        return ResponseEntity.badRequest().body(new ResponseCreate(null, "Escola não encontrada.", HttpStatus.BAD_REQUEST.value(), "Error"));
    }

    /*---------------------------------------*/
    //          ATUALIZANDO UMA ESCOLA        //
    /*---------------------------------------*/
    @PutMapping("/update/{id}")
    public ResponseEntity updateSchool(@RequestBody School scholl, @PathVariable Long id){
        Optional<School> schollNew = schollService.findById(id);

        if(schollNew.isPresent()){
            schollService.update(id, scholl);
            return ResponseEntity.ok(new ResponseCreate(scholl.getName(), "Escola atualizada com sucesso.", HttpStatus.OK.value(), "Success"));
        }
        return ResponseEntity.badRequest().body(new ResponseCreate(null, "Escola não encontrada.", HttpStatus.BAD_REQUEST.value(), "Error"));
    }
}

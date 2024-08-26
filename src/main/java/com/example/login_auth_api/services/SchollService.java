package com.example.login_auth_api.services;

import com.example.login_auth_api.domain.user.Scholl;
import com.example.login_auth_api.repositories.SchollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SchollService {

    @Autowired
    private SchollRepository schollRepository;

    public Iterable<Scholl> findAll(){
        return schollRepository.findAll();
    }

    public Optional<Scholl> findById(Long id){
        return schollRepository.findById(id);
    }

    public void delete(Long id){
        schollRepository.findById(id).ifPresent(scholl -> schollRepository.deleteById(id));
    }

    public void update(Long id, Scholl scholl){
        Optional<Scholl> schollOptional = schollRepository.findById(id);
        if(schollOptional.isPresent()){
            Scholl scholl1 = schollOptional.get();
            scholl1.setName(scholl.getName());
            scholl1.setCnpj(scholl.getCnpj());
            scholl1.setAddress(scholl.getAddress());
            scholl1.setPhone(scholl.getPhone());
            scholl1.setEmail(scholl.getEmail());
            schollRepository.save(scholl1);
        }
    }





}

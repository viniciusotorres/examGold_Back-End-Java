package com.example.login_auth_api.domain.user;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "schools")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Scholl {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    private String phone;
    private String email;
    private String cnpj;

    @OneToMany(mappedBy = "scholl") // Isso quer dizer que uma escola tem muitos usuários (um para muitos)
    @JsonManagedReference // Evita recursividade infinita
    private List<User> users; // Usuários que pertencem a escola
}

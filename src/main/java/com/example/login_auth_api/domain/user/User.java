package com.example.login_auth_api.domain.user;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;
    private String password;
    private String birthDate;
    private Boolean itsTeacher;

    @ManyToOne // Isso quer dizer que um usuário pertence a uma escola (muitos para um)
    @JoinColumn(name = "school_id") // Nome da coluna que faz a relação
    @JsonBackReference // Evita recursividade infinita
    private Scholl scholl; // Escola que o usuário pertence


}

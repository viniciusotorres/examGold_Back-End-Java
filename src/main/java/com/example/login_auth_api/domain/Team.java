package com.example.login_auth_api.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "teams")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_team;

    @Column(name = "name_team")
    private String nameTeam;

    @Column(name = "description_team")
    private String descriptionTeam;

    @Column(name = "school_id")
    private Long school_id;

    @ManyToOne
    @JoinColumn(name = "school_id", insertable = false, updatable = false)
    private School school;

    @OneToMany(mappedBy = "team")
    @JsonManagedReference
    private List<User> users;
}
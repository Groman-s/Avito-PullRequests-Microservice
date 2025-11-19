package com.goyanov.avitoprmanager.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "teams", indexes = @Index(name = "idx_team_name", columnList = "name"))
@Data
public class Team
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "team")
    private List<User> members = new ArrayList<>();
}
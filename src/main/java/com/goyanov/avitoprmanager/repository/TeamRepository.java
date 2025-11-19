package com.goyanov.avitoprmanager.repository;

import com.goyanov.avitoprmanager.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Integer>
{
    Team findByName(String name);
}

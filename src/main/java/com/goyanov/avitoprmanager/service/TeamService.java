package com.goyanov.avitoprmanager.service;

import com.goyanov.avitoprmanager.model.Team;
import com.goyanov.avitoprmanager.model.dto.TeamWithMembersDTO;
import com.goyanov.avitoprmanager.model.dto.mappers.TeamMapper;
import com.goyanov.avitoprmanager.repository.TeamRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TeamService
{
    private final TeamRepository teamRepository;
    private final TeamMapper teamMapper;
    private final UserService userService;

    public TeamService(TeamRepository teamRepository, TeamMapper teamMapper, UserService userService)
    {
        this.teamRepository = teamRepository;
        this.teamMapper = teamMapper;
        this.userService = userService;
    }

    public Team findByName(String name)
    {
        return teamRepository.findByName(name);
    }

    @Transactional
    public Team saveTeamAndCreateOrUpdateUsers(TeamWithMembersDTO teamDTO)
    {
        Team team = teamMapper.toTeam(teamDTO);
        teamRepository.save(team);
        team.getMembers().forEach(u -> {
            u.setTeam(team);
            userService.saveOrUpdate(u);
        });
        return team;
    }
}

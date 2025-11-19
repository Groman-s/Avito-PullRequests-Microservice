package com.goyanov.avitoprmanager.controller;

import com.goyanov.avitoprmanager.controller.responses.UnsuccessfulResponse;
import com.goyanov.avitoprmanager.model.Team;
import com.goyanov.avitoprmanager.model.dto.TeamWithMembersDTO;
import com.goyanov.avitoprmanager.model.dto.mappers.TeamMapper;
import com.goyanov.avitoprmanager.service.TeamService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/team")
public class TeamsController
{
    private final TeamService teamService;
    private final TeamMapper teamMapper;

    public TeamsController(TeamService teamService, TeamMapper teamMapper)
    {
        this.teamService = teamService;
        this.teamMapper = teamMapper;
    }

    private record AddedTeamResponse(TeamWithMembersDTO team) {}

    @PostMapping("/add")
    public ResponseEntity<?> addTeam(@RequestBody TeamWithMembersDTO teamDTO)
    {
        Team team = teamService.findByName(teamDTO.getName());
        if (team != null)
        {
            return ResponseEntity.status(HttpStatus.valueOf(400)).body(
                    UnsuccessfulResponse.withError("TEAM_EXISTS", "team_name already exists")
            );
        }

        team = teamService.saveTeamAndCreateOrUpdateUsers(teamDTO);
        return ResponseEntity.status(HttpStatus.valueOf(201)).body(
                new AddedTeamResponse(teamMapper.toTeamWithMembersDTO(team))
        );
    }

    @GetMapping("/get")
    public ResponseEntity<?> getTeamByName(@RequestParam String name)
    {
        Team team = teamService.findByName(name);
        if (team == null)
        {
            return ResponseEntity.status(HttpStatus.valueOf(404)).body(
                    UnsuccessfulResponse.withError("NOT_FOUND", "resource not found")
            );
        }
        return ResponseEntity.status(HttpStatus.valueOf(200)).body(teamMapper.toTeamWithMembersDTO(team));
    }
}

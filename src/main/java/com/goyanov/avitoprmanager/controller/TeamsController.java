package com.goyanov.avitoprmanager.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.goyanov.avitoprmanager.controller.exceptions.ResourceNotFoundException;
import com.goyanov.avitoprmanager.controller.responses.UnsuccessfulResponse;
import com.goyanov.avitoprmanager.model.Team;
import com.goyanov.avitoprmanager.model.dto.TeamWithMembersDTO;
import com.goyanov.avitoprmanager.model.dto.mappers.TeamMapper;
import com.goyanov.avitoprmanager.service.TeamService;
import com.goyanov.avitoprmanager.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/team")
public class TeamsController
{
    private final TeamService teamService;
    private final TeamMapper teamMapper;
    private final UserService userService;

    public TeamsController(TeamService teamService, TeamMapper teamMapper, UserService userService)
    {
        this.teamService = teamService;
        this.teamMapper = teamMapper;
        this.userService = userService;
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

        if (team == null) throw new ResourceNotFoundException();

        return ResponseEntity.status(HttpStatus.valueOf(200)).body(teamMapper.toTeamWithMembersDTO(team));
    }

    public record PullRequestCountResponse(@JsonProperty("pr_count") int count) {}

    @GetMapping("/pullRequestsCount")
    public ResponseEntity<?> getPullRequestsCount(@RequestParam(name = "team_name") String teamName)
    {
        Team team = teamService.findByName(teamName);

        if (team == null) throw new ResourceNotFoundException();

        int count = team.getMembers().stream().mapToInt(m -> m.getPullRequests().size()).sum();

        return ResponseEntity.status(HttpStatus.valueOf(200)).body(
                new PullRequestCountResponse(count)
        );
    }

    @Transactional
    @PostMapping("/deactivateAllUsers")
    public ResponseEntity<?> deactivateAll(@RequestBody HashMap<String, String> request)
    {
        String teamName = request.get("team_name");
        Team team = teamService.findByName(teamName);

        if (team == null) throw new ResourceNotFoundException();

        team.getMembers().forEach(user ->
        {
            user.setActive(false);
            userService.saveOrUpdate(user);
        });

        return ResponseEntity.status(HttpStatus.valueOf(200)).body(teamMapper.toTeamWithMembersDTO(team));
    }
}

package com.goyanov.avitoprmanager.model.dto.mappers;

import com.goyanov.avitoprmanager.model.Team;
import com.goyanov.avitoprmanager.model.dto.TeamWithMembersDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = UserMapper.class)
public interface TeamMapper
{
    @Mapping(source = "name", target = "name")
    @Mapping(source = "members", target = "members")
    TeamWithMembersDTO toTeamWithMembersDTO(Team team);

    @Mapping(source = "name", target = "name")
    @Mapping(source = "members", target = "members")
    Team toTeam(TeamWithMembersDTO teamWithMembersDTO);
}

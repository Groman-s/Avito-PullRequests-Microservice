package com.goyanov.avitoprmanager.model.dto.mappers;

import com.goyanov.avitoprmanager.model.User;
import com.goyanov.avitoprmanager.model.dto.UserFullDTO;
import com.goyanov.avitoprmanager.model.dto.UserFullWithTeamNameDTO;
import com.goyanov.avitoprmanager.model.dto.UserWithIdAndActivityDTO;
import com.goyanov.avitoprmanager.model.dto.UserWithIdAndReviewsDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = PullRequestMapper.class)
public interface UserMapper
{
    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "active", target = "active")
    UserFullDTO toFullDto(User user);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "team.name", target = "teamName")
    @Mapping(source = "active", target = "active")
    UserFullWithTeamNameDTO toFullWithTeamNameDto(User user);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "reviewedPullRequests", target = "pullRequests")
    UserWithIdAndReviewsDTO toWithIdAndReviewsDto(User user);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "active", target = "active")
    UserWithIdAndActivityDTO toWithIdAndActivityDto(User user);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "active", target = "active")
    User toUser(UserFullDTO userDTO);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "active", target = "active")
    User toUser(UserWithIdAndActivityDTO userDTO);
}

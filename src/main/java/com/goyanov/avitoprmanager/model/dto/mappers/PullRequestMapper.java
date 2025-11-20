package com.goyanov.avitoprmanager.model.dto.mappers;

import com.goyanov.avitoprmanager.model.PullRequest;
import com.goyanov.avitoprmanager.model.Team;
import com.goyanov.avitoprmanager.model.User;
import com.goyanov.avitoprmanager.model.dto.PullRequestFullDTO;
import com.goyanov.avitoprmanager.model.dto.PullRequestFullWithMergeTimeDTO;
import com.goyanov.avitoprmanager.model.dto.PullRequestWithIdNameAndAuthorIdDTO;
import com.goyanov.avitoprmanager.model.dto.PullRequestWithIdNameStatusAndAuthorIdDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = UserMapper.class)
public interface PullRequestMapper
{
    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "author.id", target = "authorId")
    PullRequestWithIdNameAndAuthorIdDTO toPullRequestWithIdNameAndAuthorIdDTO(PullRequest pr);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "author.id", target = "authorId")
    @Mapping(source = "status", target = "status")
    PullRequestWithIdNameStatusAndAuthorIdDTO toPullRequestWithIdNameStatusAndAuthorIdDTO(PullRequest pr);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "author.id", target = "authorId")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "reviewers", target = "reviewers", qualifiedByName = "usersToIds")
    PullRequestFullDTO toFullPullDTO(PullRequest pr);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "author.id", target = "authorId")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "mergedAt", target = "mergedAt")
    @Mapping(source = "reviewers", target = "reviewers", qualifiedByName = "usersToIds")
    PullRequestFullWithMergeTimeDTO toFullPullWithMergeTimeDTO(PullRequest pr);

    @Named("usersToIds")
    default List<String> usersToIds(List<User> users)
    {
        return users.stream().map(User::getId).collect(Collectors.toList());
    }

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    PullRequest toPullRequest(PullRequestWithIdNameAndAuthorIdDTO prDTO);
}

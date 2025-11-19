package com.goyanov.avitoprmanager.model.dto.mappers;

import com.goyanov.avitoprmanager.model.User;
import com.goyanov.avitoprmanager.model.dto.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper
{
    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "active", target = "active")
    UserDTO toDto(User user);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "active", target = "active")
    User toUser(UserDTO userDTO);
}

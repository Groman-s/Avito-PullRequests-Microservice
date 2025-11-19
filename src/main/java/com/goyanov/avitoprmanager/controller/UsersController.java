package com.goyanov.avitoprmanager.controller;

import com.goyanov.avitoprmanager.controller.exceptions.ResourceNotFoundException;
import com.goyanov.avitoprmanager.model.User;
import com.goyanov.avitoprmanager.model.dto.UserFullWithTeamNameDTO;
import com.goyanov.avitoprmanager.model.dto.UserWithIdAndActivityDTO;
import com.goyanov.avitoprmanager.model.dto.mappers.UserMapper;
import com.goyanov.avitoprmanager.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UsersController
{
    private final UserService userService;
    private final UserMapper userMapper;

    public UsersController(UserService userService, UserMapper userMapper)
    {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    public record UpdateUserIsActiveResponse(UserFullWithTeamNameDTO user) {}

    @PostMapping("/setIsActive")
    public ResponseEntity<?> setIsActive(@RequestBody UserWithIdAndActivityDTO userDTO)
    {
        User user = userService.findById(userDTO.getId());

        if (user == null) throw new ResourceNotFoundException();

        user.setActive(userDTO.isActive());
        userService.saveOrUpdate(user);

        return ResponseEntity.status(HttpStatus.valueOf(200)).body(
                new UpdateUserIsActiveResponse(userMapper.toFullWithTeamNameDto(user))
        );
    }

    @GetMapping("/getReview")
    public ResponseEntity<?> getActiveReviews(@RequestParam(name = "user_id") String userId)
    {
        User user = userService.findById(userId);

        if (user == null) throw new ResourceNotFoundException();

        return ResponseEntity.status(HttpStatus.valueOf(200)).body(
                userMapper.toWithIdAndReviewsDto(user)
        );
    }
}

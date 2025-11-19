package com.goyanov.avitoprmanager.service;

import com.goyanov.avitoprmanager.model.User;
import com.goyanov.avitoprmanager.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService
{
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    public User findById(String id)
    {
        return userRepository.findById(id).orElse(null);
    }

    public void saveOrUpdate(User user)
    {
        userRepository.save(user);
    }
}

package com.goyanov.avitoprmanager.repository;

import com.goyanov.avitoprmanager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String>
{

}

package com.goyanov.avitoprmanager.repository;

import com.goyanov.avitoprmanager.model.PullRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PullRequestRepository extends JpaRepository<PullRequest, String>
{

}

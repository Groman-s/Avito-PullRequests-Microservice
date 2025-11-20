package com.goyanov.avitoprmanager.service;

import com.goyanov.avitoprmanager.controller.exceptions.ResourceNotFoundException;
import com.goyanov.avitoprmanager.model.PullRequest;
import com.goyanov.avitoprmanager.model.User;
import com.goyanov.avitoprmanager.repository.PullRequestRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PullRequestService
{
    private final PullRequestRepository pullRequestRepository;

    public PullRequestService(PullRequestRepository pullRequestRepository)
    {
        this.pullRequestRepository = pullRequestRepository;
    }

    public PullRequest findById(String id)
    {
        return pullRequestRepository.findById(id).orElse(null);
    }

    public void save(PullRequest pr)
    {
        pullRequestRepository.save(pr);
    }

    public List<User> findReviewers(User user)
    {
        return user
                .getTeam()
                .getMembers()
                .stream()
                .filter(member -> !member.equals(user) && member.isActive())
                .sorted(Comparator.comparingInt(m -> m.getReviewedPullRequests().size()))
                .limit(2)
                .collect(Collectors.toList());
    }

    public User findAnotherReviewer(PullRequest pr)
    {
        List<User> reviewers = findReviewers(pr.getAuthor());
        reviewers.removeAll(pr.getReviewers());

        if (reviewers.isEmpty())
        {
            throw new ResourceNotFoundException();
        }

        return reviewers.get(0);
    }
}

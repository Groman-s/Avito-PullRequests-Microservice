package com.goyanov.avitoprmanager.controller;

import com.goyanov.avitoprmanager.controller.exceptions.ResourceNotFoundException;
import com.goyanov.avitoprmanager.controller.responses.UnsuccessfulResponse;
import com.goyanov.avitoprmanager.model.PullRequest;
import com.goyanov.avitoprmanager.model.User;
import com.goyanov.avitoprmanager.model.dto.PullRequestFullDTO;
import com.goyanov.avitoprmanager.model.dto.PullRequestFullWithMergeTimeDTO;
import com.goyanov.avitoprmanager.model.dto.PullRequestWithIdNameAndAuthorIdDTO;
import com.goyanov.avitoprmanager.model.dto.mappers.PullRequestMapper;
import com.goyanov.avitoprmanager.service.PullRequestService;
import com.goyanov.avitoprmanager.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/pullRequest")
public class PullRequestsController
{
    private final PullRequestService pullRequestService;
    private final UserService userService;
    private final PullRequestMapper pullRequestMapper;

    public PullRequestsController(PullRequestService pullRequestService, UserService userService, PullRequestMapper pullRequestMapper)
    {
        this.pullRequestService = pullRequestService;
        this.userService = userService;
        this.pullRequestMapper = pullRequestMapper;
    }

    public record PullRequestCreatedResponse(PullRequestFullDTO pr) {}

    @Transactional
    @PostMapping("/create")
    public ResponseEntity<?> createPullRequest(@RequestBody PullRequestWithIdNameAndAuthorIdDTO prDTO)
    {
        PullRequest pr = pullRequestService.findById(prDTO.getId());

        if (pr != null)
        {
            return ResponseEntity.status(HttpStatus.valueOf(409)).body(
                    UnsuccessfulResponse.withError("PR_EXISTS", "PR id already exists")
            );
        }

        User user = userService.findById(prDTO.getAuthorId());
        if (user == null || user.getTeam() == null)
        {
            throw new ResourceNotFoundException();
        }

        pr = pullRequestMapper.toPullRequest(prDTO);
        pr.setAuthor(user);
        pr.setStatus(PullRequest.Status.OPEN);

        List<User> reviewers = pullRequestService.findReviewers(user);
        pr.setReviewers(reviewers);

        pullRequestService.save(pr);

        return ResponseEntity.status(HttpStatus.valueOf(201)).body(
                new PullRequestCreatedResponse(pullRequestMapper.toFullPullDTO(pr))
        );
    }

    public record MergedPullRequestResponse(PullRequestFullWithMergeTimeDTO pr) {}

    @PostMapping("/merge")
    public ResponseEntity<?> mergeRequest(@RequestBody HashMap<String, String> request)
    {
        String prId = request.get("pull_request_id");
        PullRequest pr = pullRequestService.findById(prId);

        if (pr == null) throw new ResourceNotFoundException();

        if (pr.getStatus() != PullRequest.Status.MERGED)
        {
            pr.setStatus(PullRequest.Status.MERGED);
            pr.setMergedAt(Instant.now());
            pullRequestService.save(pr);
        }

        return ResponseEntity.status(HttpStatus.valueOf(200)).body(
                new MergedPullRequestResponse(pullRequestMapper.toFullPullWithMergeTimeDTO(pr))
        );
    }
}

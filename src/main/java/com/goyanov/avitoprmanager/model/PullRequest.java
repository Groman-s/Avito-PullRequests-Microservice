package com.goyanov.avitoprmanager.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "pull_requests")
@Data
public class PullRequest
{
    public enum Status {OPEN, CLOSED}

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "title", nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status = Status.OPEN;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    @ManyToMany
    @JoinTable(
            name = "pull_request_reviewers",
            joinColumns = @JoinColumn(name = "pull_request_id"),
            inverseJoinColumns = @JoinColumn(name = "reviewer_id")
    )
    private List<User> reviewers = new ArrayList<>();
}

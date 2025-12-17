package com.branchapp.githelp.github.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.net.URI;
import java.time.Instant;

public record GithubUserDto (
        String login,
        String name,
        @JsonProperty("avatar_url") URI avatarUrl,
        String location,
        String email,
        URI url,
        @JsonProperty("created_at") Instant createdAt
){}

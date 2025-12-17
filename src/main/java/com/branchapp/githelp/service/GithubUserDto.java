package com.branchapp.githelp.service;

import java.time.OffsetDateTime;

public record GithubUserDto (
        String login,
        String name,
        String avatar_url,
        String location,
        String email,
        String url,
        OffsetDateTime created_at
){}

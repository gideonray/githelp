package com.branchapp.githelp.github.dto;

import java.net.URI;

public record GithubRepoDto(
        String name,
        URI url
) {}

package com.branchapp.githelp.service;

import java.net.URI;

public record GithubRepoDto(
        String name,
        URI url
) {}

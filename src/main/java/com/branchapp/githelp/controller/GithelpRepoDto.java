package com.branchapp.githelp.controller;

import java.net.URI;

public record GithelpRepoDto (
        String name,
        URI url
) {}
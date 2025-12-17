package com.branchapp.githelp.controller;

import com.branchapp.githelp.service.GithubService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GithelpController {

    private final GithubService githubService;

    public GithelpController(GithubService githubService) {
        this.githubService = githubService;
    }

    @GetMapping("/api/users/{username}")
    public Object getUserInfo(@PathVariable String username) {
        return githubService.getUser(username);
    }

}

package com.branchapp.githelp.controller;

import com.branchapp.githelp.service.GithubRepoDto;
import com.branchapp.githelp.service.GithubService;
import com.branchapp.githelp.service.GithubUserDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GithelpController {

    private final GithubService githubService;

    public GithelpController(GithubService githubService) {
        this.githubService = githubService;
    }

    @GetMapping("/api/users/{username}")
    public GithelpUserDto getUserInfo(@PathVariable String username) {
        GithubUserDto user = githubService.getUser(username);
        List<GithubRepoDto> repos = githubService.getRepos(username);
        return GithelpDtoMapper.toUserDto(user, repos);
    }

}

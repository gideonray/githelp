package com.branchapp.githelp.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class GithubService {

    private final RestClient restClient;

    GithubService(RestClient restClient) {
        this.restClient = restClient;
    }

    public GithubUserDto getUser(String username) {
        return restClient.get().uri("/users/{username}", username).retrieve().body(GithubUserDto.class);
    }

    public List<GithubRepoDto> getRepos(String username) {
        GithubRepoDto[] repos = restClient.get().uri("/users/{username}/repos", username).retrieve().body(GithubRepoDto[].class);
        return repos != null ? List.of(repos) : List.of();
    }

}

package com.branchapp.githelp.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class GithubService {

    private final RestClient restClient;

    GithubService(RestClient restClient) {
        this.restClient = restClient;
    }

    public GithubUserDto getUser(String username) {
        return restClient.get().uri("/users/{username}", username).retrieve().body(GithubUserDto.class);
    }
}

package com.branchapp.githelp.api;

import com.branchapp.githelp.api.dto.UserDto;
import com.branchapp.githelp.github.GithubClient;
import com.branchapp.githelp.github.dto.GithubRepoDto;
import com.branchapp.githelp.github.dto.GithubUserDto;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class UserService {

    private final GithubClient githubClient;


    public UserService(GithubClient githubClient) {
        this.githubClient = githubClient;
    }

    public UserDto getUser(String username) {
        GithubUserDto user = githubClient.getUser(username);
        List<GithubRepoDto> repos = githubClient.getRepos(username);
        return ApiMapper.toUserDto(user, repos);
    }

}

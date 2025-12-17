package com.branchapp.githelp.api;

import com.branchapp.githelp.api.dto.RepoDto;
import com.branchapp.githelp.api.dto.UserDto;
import com.branchapp.githelp.github.dto.GithubRepoDto;
import com.branchapp.githelp.github.dto.GithubUserDto;

import java.util.Date;
import java.util.List;

public final class ApiMapper {

    private ApiMapper() {}

    public static UserDto toUserDto(GithubUserDto githubUser, List<GithubRepoDto> githubRepos) {
        return new UserDto(
                githubUser.login(),
                githubUser.name(),
                githubUser.avatarUrl(),
                githubUser.location(),
                githubUser.email(),
                githubUser.url(),
                Date.from(githubUser.createdAt()),
                githubRepos.stream().map(ApiMapper::toRepoDto).toList()
        );
    }

    public static RepoDto toRepoDto(GithubRepoDto githubRepo) {
        return new RepoDto(githubRepo.name(), githubRepo.url());
    }
}

package com.branchapp.githelp.controller;

import com.branchapp.githelp.service.GithubRepoDto;
import com.branchapp.githelp.service.GithubUserDto;

import java.util.Date;
import java.util.List;

public final class GithelpDtoMapper {

    private GithelpDtoMapper() {}

    public static GithelpUserDto toUserDto(GithubUserDto githubUser, List<GithubRepoDto> githubRepos) {
        return new GithelpUserDto(
                githubUser.login(),
                githubUser.name(),
                githubUser.avatarUrl(),
                githubUser.location(),
                githubUser.email(),
                githubUser.url(),
                Date.from(githubUser.createdAt()),
                githubRepos.stream().map(GithelpDtoMapper::toRepoDto).toList()
        );
    }

    public static GithelpRepoDto toRepoDto(GithubRepoDto githubRepo) {
        return new GithelpRepoDto(githubRepo.name(), githubRepo.url());
    }
}

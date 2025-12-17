package com.branchapp.githelp.github;

import com.branchapp.githelp.github.dto.GithubRepoDto;
import com.branchapp.githelp.github.dto.GithubUserDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class GithubClient {

    private final RestClient restClient;

    public GithubClient(RestClient restClient) {
        this.restClient = restClient;
    }

    public GithubUserDto getUser(String username) {
        try {
            return restClient.get()
                    .uri("/users/{username}", username)
                    .retrieve()
                    .body(GithubUserDto.class);
        } catch (HttpClientErrorException e) {
            throw handleException(e, username);
        } catch (HttpServerErrorException e) {
            throw new GithubUpstreamException("Github server error", e);
        }
    }

    public List<GithubRepoDto> getRepos(String username) {
        try {
            GithubRepoDto[] repos = restClient.get()
                    .uri("/users/{username}/repos", username)
                    .retrieve()
                    .body(GithubRepoDto[].class);
            return repos != null ? List.of(repos) : List.of();
        } catch (HttpClientErrorException e) {
            throw handleException(e, username);
        } catch (HttpServerErrorException e) {
            throw new GithubUpstreamException("Github server error", e);
        }
    }

    private RuntimeException handleException(HttpClientErrorException e, String username) {
        if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
            return new GithubUserNotFoundException(username);
        }
        if (e.getStatusCode() == HttpStatus.FORBIDDEN || e.getStatusCode() == HttpStatus.TOO_MANY_REQUESTS) {
            return new GithubRateLimitException();
        }
        return new GithubUpstreamException("GitHub client error", e);
    }

}

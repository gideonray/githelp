package com.branchapp.githelp.github;

public class GithubRateLimitException extends RuntimeException {
    public GithubRateLimitException() {
        super("Github api rate limit exceeded");
    }
}

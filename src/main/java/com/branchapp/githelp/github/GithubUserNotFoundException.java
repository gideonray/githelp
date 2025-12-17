package com.branchapp.githelp.github;

public class GithubUserNotFoundException extends RuntimeException {
    public GithubUserNotFoundException(String username) {
        super("Github user not found: " + username);
    }
}

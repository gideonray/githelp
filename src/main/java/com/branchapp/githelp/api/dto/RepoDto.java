package com.branchapp.githelp.api.dto;

import java.net.URI;

public record RepoDto(
        String name,
        URI url
) {}
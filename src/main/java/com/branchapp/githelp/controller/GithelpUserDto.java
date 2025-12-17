package com.branchapp.githelp.controller;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.net.URI;
import java.util.Date;
import java.util.List;

public record GithelpUserDto(
        @JsonProperty("user_name") String username,
        @JsonProperty("display_name") String displayName,
        URI avatar,
        @JsonProperty("geo_location") String geoLocation,
        String email,
        URI url,
        @JsonProperty("created_at") Date createdAt,
        List<GithelpRepoDto> repos
) {}

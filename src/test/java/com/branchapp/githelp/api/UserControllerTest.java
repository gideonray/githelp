package com.branchapp.githelp.api;

import com.branchapp.githelp.api.dto.RepoDto;
import com.branchapp.githelp.api.dto.UserDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.net.URI;
import java.time.Instant;
import java.util.Arrays;
import java.util.Date;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(UserController.class)
@Import(ApiExceptionHandler.class)
public class UserControllerTest {

    @Autowired
    MockMvc mvc;

    @MockitoBean UserService userService;

    @Test
    void invalidUsernameReturns400() throws Exception {
        mvc.perform(get("/api/users/{username}", "some@bad@name"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void validUsernameReturns200() throws Exception {
        String createdAtStr = "2011-01-25T18:44:36Z";
        Instant createdAt = Instant.parse(createdAtStr);
        UserDto dto = new UserDto(
                "octocat",
                "The Octocat",
                URI.create("https://localhost/avatar"),
                "Austin",
                "octocat@gmail.com",
                URI.create("https://localhost/users/octocat"),
                Date.from(createdAt),
                Arrays.asList(
                        new RepoDto("repo1", URI.create("https://repo1.com")),
                        new RepoDto("repo2", URI.create("https://repo2.com"))
                )
        );

        when(userService.getUser("octocat")).thenReturn(dto);

        mvc.perform(get("/api/users/{username}", "octocat"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.user_name").value("octocat"))
                .andExpect(jsonPath("$.display_name").value("The Octocat"))
                .andExpect(jsonPath("$.avatar").value("https://localhost/avatar"))
                .andExpect(jsonPath("$.geo_location").value("Austin"))
                .andExpect(jsonPath("$.email").value("octocat@gmail.com"))
                .andExpect(jsonPath("$.url").value("https://localhost/users/octocat"))
                .andExpect(jsonPath("$.created_at").value("Tue, 25 Jan 2011 18:44:36 GMT"))
                .andExpect(jsonPath("$.repos").isArray())
                .andExpect(jsonPath("$.repos.length()").value(2))
                .andExpect(jsonPath("$.repos[0].name").value("repo1"))
                .andExpect(jsonPath("$.repos[0].url").value("https://repo1.com"))
                .andExpect(jsonPath("$.repos[1].name").value("repo2"))
                .andExpect(jsonPath("$.repos[1].url").value("https://repo2.com"));
    }

}

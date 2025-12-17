package com.branchapp.githelp.api;

import com.branchapp.githelp.api.dto.UserDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/api/users/{username}")
    public UserDto getUserInfo(
            @PathVariable
            @NotBlank
            @Size(max = 39)
            @Pattern(regexp = "^[a-zA-Z0-9-]+$")
            String username) {
        return userService.getUser(username);
    }

}

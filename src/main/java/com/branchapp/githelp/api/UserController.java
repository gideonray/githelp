package com.branchapp.githelp.api;

import com.branchapp.githelp.api.dto.UserDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/api/users/{username}")
    public UserDto getUserInfo(@PathVariable String username) {
        return userService.getUser(username);
    }

}

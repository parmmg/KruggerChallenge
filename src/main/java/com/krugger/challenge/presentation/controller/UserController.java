package com.krugger.challenge.presentation.controller;

import com.krugger.challenge.presentation.presenter.UserPresenter;
import com.krugger.challenge.service.UserService;
import lombok.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Generated
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/app/addRoleToUser")
    public UserPresenter addRoleToUser (@RequestParam @NotNull @NotBlank UUID userId,
                                        @RequestParam @NotNull @NotBlank UUID roleId) {
        return userService.addRoleToUser(userId, roleId);
    }

    @GetMapping("/app/deleteRoleToUser")
    public UserPresenter deleteRoleToUser (@RequestParam @NotNull @NotBlank UUID userId,
                                        @RequestParam @NotNull @NotBlank UUID roleId) {
        return userService.addRoleToUser(userId, roleId);
    }

    @GetMapping("/getAllUser")
    public List<UserPresenter> getAllUsers () {
        return userService.getUsers();
    }

}

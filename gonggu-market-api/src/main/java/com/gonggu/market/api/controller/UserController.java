package com.gonggu.market.api.controller;

import com.gonggu.market.api.config.auth.PrincipalDetails;
import com.gonggu.market.api.domain.user.User;
import com.gonggu.market.api.dto.user.UserUpdateDto;
import com.gonggu.market.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(@Autowired UserService userService) {
        this.userService = userService;
    }

    @PutMapping("/{userId}")
    public User update(@PathVariable Long userId,
                         @RequestBody UserUpdateDto dto,
                         @AuthenticationPrincipal PrincipalDetails principalDetails) {
        User userEntity = userService.update(userId, dto);
        principalDetails.setUser(userEntity);
        return userEntity;
    }
}

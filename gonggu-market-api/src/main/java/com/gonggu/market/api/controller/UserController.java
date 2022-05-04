package com.gonggu.market.api.controller;

import com.gonggu.market.api.config.auth.PrincipalDetails;
import com.gonggu.market.api.domain.user.User;
import com.gonggu.market.api.dto.user.UserProfileDto;
import com.gonggu.market.api.dto.user.UserUpdateDto;
import com.gonggu.market.api.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    public UserController(@Autowired UserService userService) {
        this.userService = userService;
    }

    @PutMapping("/{userId}")
    public ResponseEntity<?> update(@PathVariable Long userId,
                                 @RequestBody UserUpdateDto dto,
                                 @AuthenticationPrincipal PrincipalDetails principalDetails) {
        User userEntity = userService.update(userId, dto);
        principalDetails.setUser(userEntity);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserProfileDto> profile(
            @PathVariable Long userId,
            @RequestHeader("Authorization") String token
            ) {
        UserProfileDto result = userService.profile(userId, token);
        return ResponseEntity.ok(result);
    }

}

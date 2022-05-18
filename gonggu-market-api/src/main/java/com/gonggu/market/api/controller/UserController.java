package com.gonggu.market.api.controller;

import com.gonggu.market.api.controller.dto.user.UserProfileDto;
import com.gonggu.market.api.controller.dto.user.UserUpdateDto;
import com.gonggu.market.api.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    public UserController(@Autowired UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{pageUserId}")
    public ResponseEntity<UserProfileDto> profile(
            @PathVariable String pageUserId ){
        UserProfileDto result = userService.profile(pageUserId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @GetMapping("/{userId}/update")
    public ResponseEntity<UserUpdateDto> updateForm(
            @PathVariable String userId,
            @RequestHeader("Authorization") String token
            ) {
        UserUpdateDto result = userService.detail(userId, token);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


}

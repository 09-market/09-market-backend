package com.gonggu.market.api.controller.auth;


import com.gonggu.market.api.domain.user.User;
import com.gonggu.market.api.dto.auth.SignupDto;
import com.gonggu.market.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(@Autowired UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/singup")
    public ResponseEntity<User> signup(@RequestBody SignupDto dto) {
        User result = userService.saveTest(dto);
        return ResponseEntity.ok(result);
    }
}

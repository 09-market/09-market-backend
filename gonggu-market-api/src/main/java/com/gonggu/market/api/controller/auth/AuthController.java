package com.gonggu.market.api.controller.auth;


import com.gonggu.market.api.config.auth.PrincipalDetails;
import com.gonggu.market.api.domain.user.User;
import com.gonggu.market.api.dto.auth.SignupDto;
import com.gonggu.market.api.dto.user.UserUpdateDto;
import com.gonggu.market.api.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(@Autowired AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity<SignupDto> signup(@RequestBody SignupDto dto) {
        SignupDto result = authService.signup(dto);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{userId}/update")
    public ResponseEntity<?> update(@PathVariable Long userId,
                                    @RequestBody UserUpdateDto dto,
                                    @AuthenticationPrincipal PrincipalDetails principalDetails) {
        User userEntity = authService.update(userId, dto);
        principalDetails.setUser(userEntity);
        return ResponseEntity.noContent().build();
    }
}

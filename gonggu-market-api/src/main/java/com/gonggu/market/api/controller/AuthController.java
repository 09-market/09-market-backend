package com.gonggu.market.api.controller;


import com.gonggu.market.api.domain.user.User;
import com.gonggu.market.api.controller.dto.auth.SignupDto;
import com.gonggu.market.api.controller.dto.user.UserUpdateDto;
import com.gonggu.market.api.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
                                    @RequestPart(value = "file", required = false) MultipartFile file) {
        User userEntity = authService.update(userId, dto , file);
        return new ResponseEntity<>(userEntity, HttpStatus.NO_CONTENT);
    }

}

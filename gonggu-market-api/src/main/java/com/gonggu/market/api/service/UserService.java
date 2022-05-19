package com.gonggu.market.api.service;

import com.gonggu.market.api.domain.user.User;
import com.gonggu.market.api.domain.user.UserRepository;
import com.gonggu.market.api.controller.dto.user.UserProfileDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    public UserService(@Autowired UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserProfileDto profile(String userId) {
        User userEntity = userRepository.findById(Long.valueOf(userId)).get();
        return new UserProfileDto(
                userEntity.getNickname(),
                userEntity.getUserImageUrl(),
                userEntity.getUserInfo(),
                userEntity.getItems()
        );
    }


}

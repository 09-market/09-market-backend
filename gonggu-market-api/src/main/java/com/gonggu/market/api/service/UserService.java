package com.gonggu.market.api.service;

import com.auth0.jwt.JWT;
import com.gonggu.market.api.config.jwt.JwtProperties;
import com.gonggu.market.api.domain.user.User;
import com.gonggu.market.api.domain.user.UserRepository;
import com.gonggu.market.api.controller.dto.item.ItemDto;
import com.gonggu.market.api.controller.dto.user.UserProfileDto;
import com.gonggu.market.api.controller.dto.user.UserUpdateDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

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

    public UserUpdateDto detail(String userId, String token) {
        token = token.replace(JwtProperties.TOKEN_PREFIX, "");
        Long userIdFromToken = JWT.decode(token).getClaim("id").asLong();
        logger.info(userIdFromToken.toString());
        if (!Long.valueOf(userId).equals(userIdFromToken)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        User userEntity = userRepository.findById(Long.valueOf(userId)).get();
        String address = userEntity.getAddress().getCitytown() + " " +
                userEntity.getAddress().getProvince() + " " +
                userEntity.getDetailAddress();
        List<ItemDto> itemList = new ArrayList<>();
        userEntity.getItems().forEach(item -> {
            itemList.add(new ItemDto(
                    item.getName(),
                    item.getItemImageUrl(),
                    item.getItemInfo(),
                    item.getPrice(),
                    item.getAmount(),
                    item.getCategory().getCategoryName(),
                    item.getInstagramUrl()
            ));
        });
        return new UserUpdateDto(
                userEntity.getEmail(),
                "******",
                userEntity.getNickname(),
                userEntity.getMobile(),
                userEntity.getUserImageUrl(),
                userEntity.getUserInfo(),
                address,
                userEntity.getAddress().getZipcode(),
                userEntity.getPoint(),
                itemList
        );
    }
}

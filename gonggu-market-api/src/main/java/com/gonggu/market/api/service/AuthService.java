package com.gonggu.market.api.service;

import com.auth0.jwt.JWT;
import com.gonggu.market.api.config.jwt.JwtProperties;
import com.gonggu.market.api.controller.dto.item.ItemDto;
import com.gonggu.market.api.controller.dto.item.ItemRequestDto;
import com.gonggu.market.api.domain.address.Address;
import com.gonggu.market.api.domain.address.AddressRepository;
import com.gonggu.market.api.domain.user.User;
import com.gonggu.market.api.domain.user.UserRepository;
import com.gonggu.market.api.controller.dto.auth.SignupDto;
import com.gonggu.market.api.controller.dto.user.UserUpdateDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class AuthService {
    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);

    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public AuthService(@Autowired UserRepository userRepository,
                       @Autowired AddressRepository addressRepository,
                       @Autowired BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
        this.passwordEncoder = bCryptPasswordEncoder;
    }

    @Value("${file.path}/user/")
    private String uploadFolder;

    @Transactional
    public SignupDto signup(SignupDto dto) {
        User user = new User();
        user.setEmail(dto.getEmail());
        String rawPassword = dto.getPassword();
        String encPassword = passwordEncoder.encode(rawPassword);
        user.setPassword(encPassword);
        user.setNickname(dto.getNickname());
        user.setMobile(dto.getMobile());

        Address address = addressRepository.save(Address.fromUserToAddress(dto));
        user.setAddress(address);
        String[] addArray = dto.getAddress().split(" ");
        String detailAddress = "";
        for (int i = 2; i < addArray.length; i++) {
            detailAddress += addArray[i] + " ";
        }
        user.setDetailAddress(detailAddress);
        user.setRole("ROLE_USER");
        User userEntity = userRepository.save(user);

        return new SignupDto(
                userEntity.getEmail(),
                userEntity.getNickname(),
                userEntity.getMobile(),
                userEntity.getAddress().getCitytown() + " " +
                        userEntity.getAddress().getProvince() + " " +
                        userEntity.getDetailAddress(),
                userEntity.getAddress().getZipcode()
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
        List<ItemRequestDto> itemList = new ArrayList<>();
        userEntity.getItems().forEach(item -> {
            itemList.add(new ItemRequestDto(
                    item
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

    public User update(Long id, UserUpdateDto dto, MultipartFile file) {
        if (!this.userRepository.existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        User userEntity = userRepository.findById(id).get();

        if (dto.getPassword() != null) {
            String rawPassword = dto.getPassword();
            String encPassword = passwordEncoder.encode(rawPassword);
            userEntity.setPassword(encPassword);
        }
        userEntity.setNickname(dto.getNickname() == null ? userEntity.getNickname() : dto.getNickname());
        userEntity.setMobile(dto.getMobile() == null ? userEntity.getMobile() : dto.getMobile());

        if (file != null) {
            UUID uuid = UUID.randomUUID();
            String imageFileName = uuid + "_" + file.getOriginalFilename();

            Path imageFilePath = Paths.get(uploadFolder + imageFileName);
            try {
                Files.write(imageFilePath, file.getBytes());
            } catch (Exception e) {
                e.printStackTrace();
            }
            userEntity.setUserImageUrl(imageFileName);
        }

        userEntity.setUserInfo(dto.getUserInfo() == null ? userEntity.getUserInfo() : dto.getUserInfo());

        if (dto.getAddress() != null) {
            Address address = addressRepository.save(Address.fromUserToAddress(dto));
            userEntity.setAddress(dto.getAddress() == null ? userEntity.getAddress() : address);
            String[] addArray = dto.getAddress().split(" ");
            String detailAddress = "";
            for (int i = 2; i < addArray.length; i++) {
                detailAddress += addArray[i] + " ";
            }
            userEntity.setDetailAddress(detailAddress);
        }

        userEntity = userRepository.save(userEntity);
        return userEntity;
    }
}

package com.gonggu.market.api.service;

import com.gonggu.market.api.config.auth.PrincipalDetails;
import com.gonggu.market.api.domain.address.Address;
import com.gonggu.market.api.domain.address.AddressRepository;
import com.gonggu.market.api.domain.user.User;
import com.gonggu.market.api.domain.user.UserRepository;
import com.gonggu.market.api.dto.user.UserProfileDto;
import com.gonggu.market.api.dto.user.UserUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(@Autowired UserRepository userRepository,
                       @Autowired AddressRepository addressRepository,
                       @Autowired BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User update(Long id, UserUpdateDto dto){
        User userEntity = userRepository.findById(id).orElseThrow(() -> {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        });


        String rawPassword = dto.getPassword();
        String encPassword = passwordEncoder.encode(rawPassword);
        userEntity.setPassword(encPassword);
        userEntity.setNickname(dto.getNickname() == null ? userEntity.getNickname() : dto.getNickname());
        userEntity.setMobile(dto.getMobile() == null ? userEntity.getMobile() : dto.getMobile());
        userEntity.setDetailAddress(dto.getAddress() == null ? userEntity.getDetailAddress() : dto.getAddress());
        if (dto.getZipcode() != null) {
            Address address = addressRepository.findByZipcode(dto.getZipcode());
            if (address == null) {
                System.out.println("address : " + address.toString());
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }
            userEntity.setAddress(address);
        }
        userEntity = userRepository.save(userEntity);
        return userEntity;
    }

    public UserProfileDto profile(Long userId, PrincipalDetails principalDetails) {
        User userEntity = userRepository.findById(userId).get();
        if (!principalDetails.getUser().getEmail().equals(userEntity.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
            return new UserProfileDto(
                userEntity.getEmail(),
                userEntity.getNickname(),
                userEntity.getMobile(),
                userEntity.getAddress().getZipcode(),
                userEntity.getDetailAddress(),
                userEntity.getPoint()
        );
    }
}
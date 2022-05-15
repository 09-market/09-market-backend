package com.gonggu.market.api.service;

import com.auth0.jwt.JWT;
import com.gonggu.market.api.config.jwt.JwtProperties;
import com.gonggu.market.api.domain.address.Address;
import com.gonggu.market.api.domain.address.AddressRepository;
import com.gonggu.market.api.domain.user.User;
import com.gonggu.market.api.domain.user.UserRepository;
import com.gonggu.market.api.dto.auth.SignupDto;
import com.gonggu.market.api.dto.user.UserUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthService {
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

    public User update(Long id, UserUpdateDto dto) {
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
}

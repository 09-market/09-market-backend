package com.gonggu.market.api.service;

import com.gonggu.market.api.domain.address.Address;
import com.gonggu.market.api.domain.address.AddressRepository;
import com.gonggu.market.api.domain.user.User;
import com.gonggu.market.api.domain.user.UserRepository;
import com.gonggu.market.api.dto.auth.SignupDto;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
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

    public User saveTest(SignupDto dto) {
        Address address = addressRepository.findByZipcode(dto.getZipcode());
        if (address == null) {
            System.out.println("address : " + address.toString());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        User user = new User();
        user.setEmail(dto.getEmail());
        String rawPassword = dto.getPassword();
        String encPassword = passwordEncoder.encode(rawPassword);
        user.setPassword(encPassword);
        user.setNickname(dto.getNickname());
        user.setMobile(dto.getMobile());
        user.setDetailAddress(dto.getAddress());
        user.setAddress(address);
        user.setRole("ROLE_USER");
        user = userRepository.save(user);
        return user;
    }
}

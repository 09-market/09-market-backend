package com.gonggu.market.api.service;

import com.gonggu.market.api.domain.address.Address;
import com.gonggu.market.api.domain.address.AddressRepository;
import com.gonggu.market.api.domain.user.User;
import com.gonggu.market.api.domain.user.UserRepository;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UserServiceTest {
    @Autowired UserService userService;
    @Autowired UserRepository userRepository;
    @Autowired AddressRepository addressRepository;

    @BeforeEach
    void beforeEach() {
        Address address = new Address();
        address.setId(1L);
        address.setZipcode("12345");
        address.setCitytown("서울시");
        address.setProvince("강남구");
        addressRepository.save(address);
    }

    @Test
    void 새로운유저등록테스트() {
        // given
        User newUser = new User();
        newUser.setId(1L);
        newUser.setEmail("test@test.com");
        newUser.setPassword("1234");
        newUser.setNickname("test");
        newUser.setMobile("01012345678");
        newUser.setAddress(addressRepository.findById(1L).get());
        newUser.setDetailAddress("태헤란로 108길 14");
        newUser.setPoint(0);

        // when
        Long saveId = userService.saveTest(newUser);

        // then
        User findUser = userRepository.findById(saveId).get();
        assertThat(newUser.getEmail()).isEqualTo(findUser.getEmail());
    }
}
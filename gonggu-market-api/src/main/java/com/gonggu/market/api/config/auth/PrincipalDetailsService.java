package com.gonggu.market.api.config.auth;

import com.gonggu.market.api.domain.user.User;
import com.gonggu.market.api.domain.user.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class PrincipalDetailsService implements UserDetailsService {
    private static final Logger logger = LoggerFactory.getLogger(PrincipalDetailsService.class);

    private final UserRepository userRepository;

    public PrincipalDetailsService(@Autowired UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        logger.info("email : " + email);
        User userEntity = userRepository.findByEmail(email);
        if (email != null) {
            return new PrincipalDetails(userEntity);
        }
        return null;
    }
}

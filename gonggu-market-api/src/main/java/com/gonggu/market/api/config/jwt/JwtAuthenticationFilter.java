package com.gonggu.market.api.config.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gonggu.market.api.config.auth.PrincipalDetails;
import com.gonggu.market.api.dto.auth.SigninRequestDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
    private final AuthenticationManager authenticationManager;

    public JwtAuthenticationFilter(@Autowired AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    // login 요청 시 실행되는 함수
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        logger.info("JwtAuthenticationFilter : 진입");

        ObjectMapper om = new ObjectMapper();
        SigninRequestDto signinRequestDto = null;
        try {
            signinRequestDto = om.readValue(request.getInputStream(), SigninRequestDto.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info(signinRequestDto.toString());

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(
                        signinRequestDto.getEmail(),
                        signinRequestDto.getPassword());

        logger.info("JwtAuthenticationFilter : 토큰생성완료");
        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        logger.info("이메일 : " + principalDetails.getUser().getEmail());

        return authentication;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain
            chain, Authentication authResult) throws IOException, ServletException {
        PrincipalDetails principalDetails = (PrincipalDetails) authResult.getPrincipal();

        String jwtToken = JWT.create()
                .withSubject(principalDetails.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + JwtProperties.EXPIRATION_TIME))
                .withClaim("id", principalDetails.getUser().getId())
                .withClaim("email", principalDetails.getUser().getEmail())
                .sign(Algorithm.HMAC512(JwtProperties.SECRET));
        response.addHeader(JwtProperties.HEADER_STRING, JwtProperties.TOKEN_PREFIX + jwtToken);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("{\"token\":\"" + jwtToken + "\", \"userId\":\""+principalDetails.getUser().getId() +"\"}");
    }
}

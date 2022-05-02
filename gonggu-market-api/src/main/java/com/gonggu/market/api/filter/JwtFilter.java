package com.gonggu.market.api.filter;

import com.gonggu.market.api.config.jwt.JwtProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class JwtFilter implements Filter {
    private static final Logger logger = LoggerFactory.getLogger(JwtFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        // Token 인증
        // 로그인 완료 시 토큰 생성 후 응답, 요청마다  header의 Authorization 값 확인
        if (req.getMethod().equals("POST")) {
            String headerAuth = req.getHeader("Authorization");
            logger.info("header's Authorization : " + headerAuth);

            if (headerAuth.equals(JwtProperties.SECRET)) {
                chain.doFilter(req, res);
            } else {
                PrintWriter out = res.getWriter();
                out.println("인증 안됨");
            }
        }
    }
}

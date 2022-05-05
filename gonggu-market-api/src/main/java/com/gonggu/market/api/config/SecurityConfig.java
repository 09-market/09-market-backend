package com.gonggu.market.api.config;

import com.gonggu.market.api.config.jwt.JwtAuthenticationFilter;
import com.gonggu.market.api.config.jwt.JwtAuthorizationFilter;
import com.gonggu.market.api.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.filter.CorsFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    private final CorsConfig corsConfig;
    private final UserRepository userRepository;

    public SecurityConfig(
            @Autowired CorsConfig corsConfig,
            @Autowired UserRepository userRepository) {
        this.corsConfig = corsConfig;
        this.userRepository = userRepository;
    }

    @Bean public BCryptPasswordEncoder encode() {
        return new BCryptPasswordEncoder();
    }

    public JwtAuthenticationFilter getJwtAuthenticationFilter() throws Exception {
        final JwtAuthenticationFilter filter = new JwtAuthenticationFilter(authenticationManager());
        filter.setFilterProcessesUrl("/api/auth/signin");
        return filter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().configurationSource(corsConfig.corsFilter())
        .and()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
                .formLogin().disable()
                .httpBasic().disable()
                .addFilter(getJwtAuthenticationFilter())
                .addFilter(new JwtAuthorizationFilter(authenticationManager(), userRepository))
                .authorizeRequests()
                .antMatchers("/api/user/**")
                .access("hasRole('ROLE_USER')")
                .anyRequest().permitAll();
    }
}

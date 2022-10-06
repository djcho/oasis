package com.oasis.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfiguration {
    
    // TODO :: Security 임시. PasswordEncoder bean등록을 위해 적용
    
    @Bean
    public WebSecurityCustomizer configure() {
        // TODO :: 모든 요청 filter ignore. 추후 static resource에 대해서만 적용
        return (web) -> web.ignoring().mvcMatchers("/**");
    }
    
    @Bean
    public PasswordEncoder encodePassword() {
        return new BCryptPasswordEncoder();
    }

}

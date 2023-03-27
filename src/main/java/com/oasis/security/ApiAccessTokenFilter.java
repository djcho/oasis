package com.oasis.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class ApiAccessTokenFilter extends OncePerRequestFilter {
    private final ApiAccessTokenProvider apiAccessTokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String apiAccessToken = apiAccessTokenProvider.resolveToken(request);

        if(apiAccessToken != null && apiAccessTokenProvider.validateToken(apiAccessToken)){
            Authentication authentication = apiAccessTokenProvider.getAuthentication(apiAccessToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }
}

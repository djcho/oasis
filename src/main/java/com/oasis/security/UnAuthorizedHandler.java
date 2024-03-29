package com.oasis.security;

import com.google.gson.Gson;
import com.oasis.data.dto.ResponseDtoTemplate;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.oasis.common.constant.ErrorCode.INVALID_ACCESS_TOKEN;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@Component
public class UnAuthorizedHandler implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        Gson gson = new Gson();
        response.setStatus(UNAUTHORIZED.value());
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(gson.toJson(ResponseDtoTemplate.toResponseEntity(INVALID_ACCESS_TOKEN).getBody()));
    }
}

package com.oasis.security;

import com.google.gson.Gson;
import com.oasis.data.dto.ResponseDtoTemplate;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.oasis.common.constant.ErrorCode.ACCESS_DENIED;
import static org.springframework.http.HttpStatus.FORBIDDEN;

@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        Gson gson = new Gson();
        response.setStatus(FORBIDDEN.value());
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(gson.toJson(ResponseDtoTemplate.toResponseEntity(ACCESS_DENIED).getBody()));
    }
}

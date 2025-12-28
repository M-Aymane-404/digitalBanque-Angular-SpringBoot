package com.example.banquedigitale.Security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException, ServletException {

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");

        String path = request.getRequestURI();
        String json;
        if ("/auth/login".equals(path)) {
            json = "{\"code\":\"BAD_CREDENTIALS\",\"message\":\"Nom d’utilisateur ou mot de passe incorrect\"}";
        } else {
            json = "{\"code\":\"UNAUTHORIZED\",\"message\":\"Unauthorized\"}";
        }

        response.getWriter().write(json);
    }
}

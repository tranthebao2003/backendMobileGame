package com.webgame.webgame.service.userLogin;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import java.io.IOException;


//điều hướng role

@Service
public class CustomSuccessLoginHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        //authentication được tạo ra khi xác thực thành công
        var authorities = authentication.getAuthorities();
        var roles = authorities.stream().map(r -> r.getAuthority()).findFirst();

        if (roles.orElse("").equals("admin")) {
            response.sendRedirect("/admin");
        } else if (roles.orElse("").equals("user")) {
            response.sendRedirect("/");
        } else {
            response.sendRedirect("/error");
        }
    }
}

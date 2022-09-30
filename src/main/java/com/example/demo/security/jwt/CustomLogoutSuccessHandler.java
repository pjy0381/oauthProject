package com.example.demo.security.jwt;

import com.example.demo.utils.CookieUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
@RequiredArgsConstructor
public class CustomLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {

    private CookieUtils cookieUtils;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        Cookie refreshToken = new Cookie("refreshToken", null);
        refreshToken.setMaxAge(0);
        Cookie accessToken = new Cookie("accessToken", null);
        accessToken.setMaxAge(0);
        response.addCookie(refreshToken);
        response.addCookie(accessToken);

        response.sendRedirect("/");
    }
}

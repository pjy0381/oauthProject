package com.example.demo.security.jwt;

import com.example.demo.utils.CookieUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
@RequiredArgsConstructor
public class CustomLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {
    private Environment env;

    private CookieUtils cookieUtils;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {

        String frontendAppEntryPage = env.getProperty("frontend-app.entry");

        response.addCookie(cookieUtils.generateRemoveJwtCookie(env.getProperty("jwt.token-name"), ""));
        response.addCookie(cookieUtils.generateRemoveJwtCookie(env.getProperty("jwt.token-name") + "-flag", ""));

        getRedirectStrategy().sendRedirect(request, response, frontendAppEntryPage);

    }
}

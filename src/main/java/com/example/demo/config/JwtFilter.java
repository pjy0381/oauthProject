package com.example.demo.config;

import com.example.demo.dto.UserDto;
import com.example.demo.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private final TokenService tokenService;
    private final Logger log = LoggerFactory.getLogger(getClass());
    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String refreshToken = getRefreshToken(request);
        String accessToken = getAccessToken(request);
        if(!tokenService.verifyToken(refreshToken)){
            try {
                throw new Exception("예외를 강제로 발생시켰습니다.");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        if(!tokenService.verifyToken(accessToken) && tokenService.verifyToken(refreshToken)) {
            tokenService.generateAccessToken(refreshToken);
        }
        filterChain.doFilter(request, response);
    }


    private String getAccessToken(HttpServletRequest request) {
        if (request.getCookies() != null) {
            return Arrays.stream(request.getCookies())
                    .filter(cookie -> cookie.getName().equals("accessToken"))
                    .findFirst()
                    .map(Cookie::getValue)
                    .orElse(null);
        }
        return null;
    }

    private String getRefreshToken(HttpServletRequest request) {
        if (request.getCookies() != null) {
            return Arrays.stream(request.getCookies())
                    .filter(cookie -> cookie.getName().equals("refreshToken"))
                    .findFirst()
                    .map(Cookie::getValue)
                    .orElse(null);
        }
        return null;
    }
    
    




}

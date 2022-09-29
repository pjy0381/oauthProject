package com.example.demo.security.jwt;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.Token;
import com.example.demo.security.oauth.UserRequestMapper;
import com.example.demo.service.TokenService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Slf4j
@Component
@RequiredArgsConstructor
public class CustomOAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final TokenService tokenService;
    private final UserRequestMapper userRequestMapper;
    private final ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        OAuth2User oAuth2User = (OAuth2User)authentication.getPrincipal();
        UserDto userDto = userRequestMapper.toDto(oAuth2User);

        Token token = tokenService.generateToken(userDto.getEmail(), "USER");
        log.info("{}", token);
        String targetUrl;
        targetUrl = UriComponentsBuilder.fromUriString("/")
                .queryParam("token", "token")
                .build().toUriString();
        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }

}

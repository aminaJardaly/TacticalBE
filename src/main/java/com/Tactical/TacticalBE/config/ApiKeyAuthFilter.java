package com.Tactical.TacticalBE.config;

import com.Tactical.TacticalBE.exception.UnauthorizedException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import java.io.IOException;

public class ApiKeyAuthFilter extends AbstractAuthenticationProcessingFilter {

    private final String apiKey;

    public ApiKeyAuthFilter(String apiKey) {
        super("/api/**");
        this.apiKey = apiKey;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        String requestApiKey = request.getHeader("x-api-key");

        if (requestApiKey == null || !requestApiKey.equals(apiKey)) {
            throw new UnauthorizedException("Invalid or missing API key.");
        }

        return new ApiKeyAuthentication(apiKey);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult)
            throws IOException, ServletException {
        SecurityContextHolder.getContext().setAuthentication(authResult);
        chain.doFilter(request, response);
    }
}

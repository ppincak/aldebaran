package com.aldebaran.security.authentication.filter;

import com.aldebaran.security.authentication.JwtAuthentication;
import com.aldebaran.security.authentication.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
public class JwtAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    public JwtAuthenticationFilter() {
        super("/**");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {

        String authorization = request.getHeader("Authorization");
        if(authorization == null) {
            throw new UnauthorizedException();
        }

        Authentication authentication = getAuthenticationManager()
                .authenticate(new JwtAuthentication(authorization));

        return authentication;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        super.successfulAuthentication(request, response, chain, authResult);

        chain.doFilter(request, response);
    }

    @Autowired
    @Override
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        super.setAuthenticationManager(authenticationManager);
    }

    @Autowired
    @Override
    public void setAuthenticationSuccessHandler(AuthenticationSuccessHandler successHandler) {
        super.setAuthenticationSuccessHandler(successHandler);
    }

    @Autowired
    @Override
    public void setAuthenticationFailureHandler(AuthenticationFailureHandler failureHandler) {
        super.setAuthenticationFailureHandler(failureHandler);
    }
}
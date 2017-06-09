package com.aldebaran.server.authentication;

import com.aldebaran.chassis.hystrix.RestCall;
import com.aldebaran.chassis.hystrix.RestCallCommand;
import com.aldebaran.rest.error.GeneralErrorCodes;
import com.aldebaran.rest.error.codes.ApplicationException;
import com.aldebaran.security.authentication.JwtAuthenticatedUser;
import com.aldebaran.security.jwt.TokenInfo;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import javax.ws.rs.core.Response;


@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        final JwtAuthentication jwtAuthentication = (JwtAuthentication) authentication;

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", jwtAuthentication.getJwt());
        HttpEntity<Object> httpEntity = new HttpEntity<>(httpHeaders);

        RestCall<TokenInfo> restCall =
                new RestCall<TokenInfo>()
                    .setUrl("http://localhost:8079/oauth2/tokenInfo")
                    .setMethod(HttpMethod.GET)
                    .setResponseType(TokenInfo.class)
                    .setRequestEntity(httpEntity);

        new RestCallCommand<>(restCall)
                .toObservable()
                .toBlocking()
                .subscribe(
                    (tokenInfo) -> {
                        jwtAuthentication.setAuthenticated(true);
                        jwtAuthentication.setDetails(
                                new JwtAuthenticatedUser(jwtAuthentication.getJwt(), tokenInfo));

                    }, (throwable) -> {
                        jwtAuthentication.setAuthenticated(false);
                    });

        if(jwtAuthentication.isAuthenticated() == false) {
            throw new UnauthorizedException();
        }
        return jwtAuthentication;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.isAssignableFrom(JwtAuthentication.class);
    }
}
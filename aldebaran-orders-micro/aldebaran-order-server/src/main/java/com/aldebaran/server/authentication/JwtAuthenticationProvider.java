package com.aldebaran.server.authentication;

import com.aldebaran.chassis.discovery.RibbonServiceDiscovery;
import com.aldebaran.chassis.discovery.ServiceDescription;
import com.aldebaran.chassis.discovery.ServiceDiscovery;
import com.aldebaran.security.authentication.JwtAuthentication;
import com.aldebaran.security.authentication.UnauthorizedException;
import com.aldebaran.chassis.hystrix.RestCall;
import com.aldebaran.chassis.hystrix.RestCallCommand;
import com.aldebaran.security.authentication.JwtAuthenticatedUser;
import com.aldebaran.security.jwt.TokenInfo;
import com.netflix.loadbalancer.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;


@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationProvider.class);

    @Autowired
    private ServiceDiscovery loadBalancing;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        final JwtAuthentication jwtAuthentication = (JwtAuthentication) authentication;

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", jwtAuthentication.getJwt());
        HttpEntity<Object> httpEntity = new HttpEntity<>(httpHeaders);

        ServiceDescription serviceDescription = loadBalancing.discover("aldebaran-auth");

        if(serviceDescription == null) {
            logger.error("Service aldebaran-auth not found");

            throw new UnauthorizedException();
        }

        RestCall<TokenInfo> restCall =
                new RestCall<TokenInfo>()
                    .setUrl(serviceDescription.assembleUrl() + "/oauth2/tokenInfo")
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
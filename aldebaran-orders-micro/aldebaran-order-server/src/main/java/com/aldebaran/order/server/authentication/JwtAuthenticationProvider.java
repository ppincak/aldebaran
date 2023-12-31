package com.aldebaran.order.server.authentication;

import com.aldebaran.chassis.discovery.ServiceDescription;
import com.aldebaran.chassis.discovery.ServiceDiscovery;
import com.aldebaran.chassis.hystrix.RestCall;
import com.aldebaran.chassis.hystrix.RestCallCommand;
import com.aldebaran.rest.device.DeviceConstants;
import com.aldebaran.rest.device.DeviceContext;
import com.aldebaran.security.authentication.JwtAuthenticatedUser;
import com.aldebaran.security.authentication.JwtAuthentication;
import com.aldebaran.security.authentication.UnauthorizedException;
import com.aldebaran.security.jwt.TokenInfo;
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
    private ServiceDiscovery serviceDiscovery;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        final JwtAuthentication jwtAuthentication = (JwtAuthentication) authentication;

        DeviceContext deviceContext = DeviceContext.getContext();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", jwtAuthentication.getJwt());
        httpHeaders.add(DeviceConstants.API_KEY_HEADER, deviceContext.getApiKey());
        httpHeaders.add(DeviceConstants.API_LANG_HEADER, deviceContext.getLang());
        HttpEntity<Object> httpEntity = new HttpEntity<>(httpHeaders);

        ServiceDescription serviceDescription =
                serviceDiscovery.discover("aldebaran-auth");

        if(serviceDescription == null) {
            logger.error("No instance of service aldebaran-auth found");

            throw new UnauthorizedException();
        }

        RestCall<TokenInfo> restCall =
                new RestCall<TokenInfo>()
                    .setUrl(serviceDescription.assembleUrl() + "/oauth2/tokenInfo")
                    .setMethod(HttpMethod.GET)
                    .setResponseType(TokenInfo.class)
                    .setRequestEntity(httpEntity);

        new RestCallCommand<>(restCall, "jwtAuthentication")
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
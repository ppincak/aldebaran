package com.aldebaran.security.jwt;

import com.aldebaran.security.authentication.AuthenticatedUser;
import com.aldebaran.security.authentication.SimpleGrantedAuthority;
import com.google.common.base.Charsets;
import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class TokenUtils {

    public static String generateJti(AuthenticatedUser authenticatedUser) {
        HashFunction hf = Hashing.murmur3_128();
        HashCode hc =
                hf.newHasher()
                    .putLong(System.currentTimeMillis())
                    .putString(authenticatedUser.getUsername(), Charsets.UTF_8)
                    .putString(authenticatedUser.getEmail(), Charsets.UTF_8)
                    .hash();
        return hc.toString();
    }

    public static String extractJwt(String authorization, String tokenType) {
        int index = authorization.lastIndexOf(tokenType);
        if(index == -1) {
            return null;
        }
        return authorization
                .substring(index + tokenType.length() + 1, authorization.length());
    }

    @SuppressWarnings("unchecked")
    public static Collection<GrantedAuthority> createAuthorities(Object roles) {
        if(roles instanceof ArrayList == false) {
            return new ArrayList<>();
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        for(String role: (ArrayList<String>) roles) {
            if(role.isEmpty() == false) {
                authorities.add(new SimpleGrantedAuthority(role));
            }
        }
        return authorities;
    }

    public static ArrayList<String> extractRoles(Collection<? extends GrantedAuthority> authorities) {
        ArrayList<String> result = new ArrayList<>();
        if(authorities == null) {
            return result;
        }
        for(GrantedAuthority authority: authorities) {
            result.add(authority.getAuthority());
        }
        return result;
    }
}
package com.aldebaran.security;

import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class TokenUtils {


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
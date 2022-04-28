package com.vacancy.vacancy.auth;

import com.vacancy.vacancy.entity.User;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthUserConfig {
    protected User getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!(authentication instanceof AnonymousAuthenticationToken)){
            User authUser = (User) authentication.getPrincipal();
            return authUser;
        }
        return null;
    }
}

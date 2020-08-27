package com.applaudo.movies.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthServiceImpl {

    public boolean hasAccess(String path) {

        boolean response = false;

        String roleMethod = "";

        switch (path) {
            case "listRoles":
                roleMethod = "ADMIN";
                break;
            case "listById":
                roleMethod = "ADMIN,USER,DBA";
                break;
        }

        String[] rolesMethod = roleMethod.split(",");

        Authentication loggedUser = SecurityContextHolder.getContext().getAuthentication();

        log.info(loggedUser.getName());

        for (GrantedAuthority auth : loggedUser.getAuthorities()) {
            String userRole = auth.getAuthority();
            for (String roleMeth : rolesMethod) {
                if (userRole.equalsIgnoreCase(roleMeth)) {
                    response = true;
                    break;
                }
            }
        }
        return response;
    }

}

package com.ljc.resource.server.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.introspection.OAuth2IntrospectionAuthenticatedPrincipal;

public class SecurityUtil {

    public static OAuth2IntrospectionAuthenticatedPrincipal getUser() {
        return (OAuth2IntrospectionAuthenticatedPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public static Long getUserId() {
        return getUser().getAttribute("user_id");
    }

    public static String getUsername() {
        return getUser().getName();
    }

    public static String getClientId() {
        return getUser().getClientId();
    }

}

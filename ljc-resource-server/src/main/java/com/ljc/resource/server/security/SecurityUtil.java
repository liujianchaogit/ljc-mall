package com.ljc.resource.server.security;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.introspection.OAuth2IntrospectionAuthenticatedPrincipal;

public class SecurityUtil {

    public static OAuth2IntrospectionAuthenticatedPrincipal getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken)
            return null;
        return (OAuth2IntrospectionAuthenticatedPrincipal) authentication.getPrincipal();
    }

    public static Long getUserId() {
        return getUser() == null ? null : getUser().getAttribute("user_id");
    }

    public static String getUsername() {
        return getUser() == null ? null : getUser().getName();
    }

    public static String getClientId() {
        return getUser() == null ? null : getUser().getClientId();
    }

    public static Long getIntegration() {
        return getUser() == null ? null : getUser().getAttribute("integration");
    }


}

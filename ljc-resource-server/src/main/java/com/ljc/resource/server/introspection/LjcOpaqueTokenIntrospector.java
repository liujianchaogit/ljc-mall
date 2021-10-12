package com.ljc.resource.server.introspection;

import net.minidev.json.JSONArray;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.server.resource.introspection.NimbusOpaqueTokenIntrospector;
import org.springframework.security.oauth2.server.resource.introspection.OAuth2IntrospectionAuthenticatedPrincipal;

import java.util.Collection;

public class LjcOpaqueTokenIntrospector extends NimbusOpaqueTokenIntrospector {

    public LjcOpaqueTokenIntrospector(String introspectionUri, String clientId, String clientSecret) {
        super(introspectionUri, clientId, clientSecret);
    }

    @Override
    public OAuth2AuthenticatedPrincipal introspect(String token) {
        OAuth2AuthenticatedPrincipal principal = super.introspect(token);
        return new OAuth2IntrospectionAuthenticatedPrincipal(
                principal.getAttribute("user_name"),
                principal.getAttributes(),
                getAuthorities(principal.getAttribute("authorities"))
        );
    }

    private Collection<GrantedAuthority> getAuthorities(JSONArray authorities) {
        return authorities == null ? AuthorityUtils.NO_AUTHORITIES : AuthorityUtils.createAuthorityList(authorities.toArray(new String[0]));
    }

}

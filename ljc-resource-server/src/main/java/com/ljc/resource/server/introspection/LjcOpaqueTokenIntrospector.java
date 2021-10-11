package com.ljc.resource.server.introspection;

import net.minidev.json.JSONArray;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.core.DefaultOAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.server.resource.introspection.NimbusOpaqueTokenIntrospector;
import org.springframework.security.oauth2.server.resource.introspection.OAuth2IntrospectionAuthenticatedPrincipal;

import java.util.Map;

public class LjcOpaqueTokenIntrospector extends NimbusOpaqueTokenIntrospector {

    public LjcOpaqueTokenIntrospector(String introspectionUri, String clientId, String clientSecret) {
        super(introspectionUri, clientId, clientSecret);
    }

    @Override
    public OAuth2AuthenticatedPrincipal introspect(String token) {
        OAuth2AuthenticatedPrincipal principal = super.introspect(token);
        String name = principal.getAttribute("user_name");
        Map<String, Object> attributes = principal.getAttributes();
        JSONArray authorities = principal.getAttribute("authorities");
        return new OAuth2IntrospectionAuthenticatedPrincipal(name, attributes, authorities == null ? null : AuthorityUtils.createAuthorityList(authorities.toArray(new String[0])));
    }

}

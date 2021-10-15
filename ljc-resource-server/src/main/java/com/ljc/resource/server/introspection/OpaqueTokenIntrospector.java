package com.ljc.resource.server.introspection;

import net.minidev.json.JSONArray;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.server.resource.introspection.NimbusOpaqueTokenIntrospector;
import org.springframework.security.oauth2.server.resource.introspection.OAuth2IntrospectionAuthenticatedPrincipal;
import org.springframework.web.client.RestOperations;

public class OpaqueTokenIntrospector extends NimbusOpaqueTokenIntrospector {

    public OpaqueTokenIntrospector(String introspectionUri, RestOperations restOperations) {
        super(introspectionUri, restOperations);
    }

    @Override
    public OAuth2AuthenticatedPrincipal introspect(String token) {
        OAuth2AuthenticatedPrincipal principal = super.introspect(token);
        JSONArray authorities = principal.getAttribute("authorities");
        return new OAuth2IntrospectionAuthenticatedPrincipal(
                principal.getAttribute("user_name"),
                principal.getAttributes(),
                authorities == null ? AuthorityUtils.NO_AUTHORITIES : AuthorityUtils.createAuthorityList(authorities.toArray(new String[0]))
        );
    }

}

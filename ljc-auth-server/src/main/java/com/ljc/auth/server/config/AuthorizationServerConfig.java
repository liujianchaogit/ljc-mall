package com.ljc.auth.server.config;

import com.ljc.auth.server.security.User;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableAuthorizationServer
@RequiredArgsConstructor
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final RedisConnectionFactory connectionFactory;
    private final AuthenticationManager authenticationManager;

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        security.checkTokenAccess("permitAll()");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients
                .inMemory()
                .withClient("admin")
                .authorizedGrantTypes("password", "refresh_token")
                .secret(passwordEncoder.encode("ljc"))
                .scopes("admin")
                .and()
                .withClient("app")
                .authorizedGrantTypes("password", "refresh_token")
                .secret(passwordEncoder.encode("ljc"))
                .scopes("app");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints
                .tokenStore(new RedisTokenStore(connectionFactory))
                .tokenEnhancer((accessToken, authentication) -> {
                    Map<String, Object> additionalInformation = new HashMap<>();
                    additionalInformation.put("user_id", ((User) authentication.getPrincipal()).getUserId());
                    ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInformation);
                    return accessToken;
                })
                .authenticationManager(authenticationManager);
    }

}

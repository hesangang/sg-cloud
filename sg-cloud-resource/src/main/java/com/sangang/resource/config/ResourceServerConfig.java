package com.sangang.resource.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;


@EnableWebFluxSecurity
@Configuration(proxyBeanMethods = false)
public class ResourceServerConfig {

    @Bean
    SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http
                .authorizeExchange((authorize) -> authorize
                        .pathMatchers(HttpMethod.GET, "/message/**").hasAuthority("SCOPE_message.read")
                        .pathMatchers(HttpMethod.POST, "/message/**").hasAuthority("SCOPE_message.write")
                        .anyExchange().authenticated()
                )
                .oauth2ResourceServer((resourceServer) -> resourceServer
                        .jwt(Customizer.withDefaults())
                )
        ;
        return http.build();
    }

}


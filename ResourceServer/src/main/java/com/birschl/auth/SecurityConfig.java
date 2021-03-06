package com.birschl.auth;


import com.azure.spring.autoconfigure.b2c.AADB2CJwtBearerTokenAuthenticationConverter;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests((requests) -> requests.anyRequest().authenticated())
                .oauth2ResourceServer()
                .jwt()
                .jwtAuthenticationConverter(new AADB2CJwtBearerTokenAuthenticationConverter());
    }
}
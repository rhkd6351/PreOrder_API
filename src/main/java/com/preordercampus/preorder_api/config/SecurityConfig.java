package com.preordercampus.preorder_api.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.filter.CorsFilter;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CorsFilter corsFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable(); //cors, csrf 사용안함
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) //세션 사용안함
                .and()
                .addFilter(corsFilter)
                .formLogin().disable()
                .httpBasic().disable();

        http.authorizeRequests()
                .antMatchers("/api/v1/user")
                .access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
                .antMatchers("/api/v1/admin")
                .access("hasAnyRole('ROLE_ADMIN')")
                .anyRequest().permitAll();
    }
}

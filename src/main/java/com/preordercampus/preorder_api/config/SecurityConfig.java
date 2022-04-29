package com.preordercampus.preorder_api.config;

import com.preordercampus.preorder_api.jwt.JwtAccessDeniedHandler;
import com.preordercampus.preorder_api.jwt.JwtAuthenticationEntryPoint;
import com.preordercampus.preorder_api.jwt.JwtSecurityConfig;
import com.preordercampus.preorder_api.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.filter.CorsFilter;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CorsFilter corsFilter;

    private final TokenProvider tokenProvider;

    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable(); //csrf 사용안함
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) //세션 사용안함
                .and()
                .addFilter(corsFilter)
                .formLogin().disable() //폼태그 로그인 사용안함
                .httpBasic().disable(); //http Authorization id,pw 인증방식 제거

        http.authorizeRequests()

                //회원가입
                .antMatchers(HttpMethod.POST,"/api/v1/user")
                .permitAll()
                .antMatchers(HttpMethod.POST,"/api/v1/user/authentication")
                .permitAll()
                .antMatchers(HttpMethod.GET,"/api/v1/user/schools")
                .permitAll()
                .antMatchers(HttpMethod.GET,"/api/v1/user/verify/**")
                .permitAll()

                .antMatchers("/api/v1/user/**")
                .access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
                .antMatchers("/api/v1/admin/**")
                .access("hasAnyRole('ROLE_ADMIN')")

                .anyRequest().permitAll();

        //JWT 설정
        http
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)
                .and()
                .apply(new JwtSecurityConfig(tokenProvider));

    }
}

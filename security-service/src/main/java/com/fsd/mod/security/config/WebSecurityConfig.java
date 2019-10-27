package com.fsd.mod.security.config;

import com.fsd.mod.security.handler.RestAccessDeniedEntryPoint;
import com.fsd.mod.security.handler.RestAuthenticationEntryPoint;
import com.fsd.mod.security.filter.JwtAuthenticationFilter;
import com.fsd.mod.security.filter.JwtLoginFilter;
import com.fsd.mod.security.handler.RestAuthenticationFailureHandler;
import com.fsd.mod.security.handler.RestAuthenticationSuccessHandler;
import com.fsd.mod.security.service.RbacService;
import com.fsd.mod.security.service.impl.RbacServiceImpl;
import com.fsd.mod.security.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService())
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterAt(jwtLoginFilter(), UsernamePasswordAuthenticationFilter.class);
        http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                .anyRequest().authenticated()
                .anyRequest().access("@rbacService.hasPermission(request, authentication)")
                .and()
                .addFilter(jwtAuthenticationFilter());

        http.exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint());
        http.exceptionHandling().accessDeniedHandler(restAccessDeniedEntryPoint());
    }

    @Bean
    public UsernamePasswordAuthenticationFilter jwtLoginFilter() throws Exception {
        UsernamePasswordAuthenticationFilter filter = new JwtLoginFilter();
        filter.setAuthenticationFailureHandler(restAuthenticationFailureHandler());
        filter.setAuthenticationSuccessHandler(restAuthenticationSuccessHandler());
        filter.setAuthenticationManager(authenticationManagerBean());
        return filter;
    }

    @Bean
    public BasicAuthenticationFilter jwtAuthenticationFilter() throws Exception {
        return new JwtAuthenticationFilter(authenticationManagerBean());
    }

    @Bean
    public RbacService rbacService() {
        return new RbacServiceImpl();
    }

    @Bean
    public AuthenticationFailureHandler restAuthenticationFailureHandler() {
        return new RestAuthenticationFailureHandler();
    }

    @Bean
    public AuthenticationSuccessHandler restAuthenticationSuccessHandler() {
        return new RestAuthenticationSuccessHandler();
    }

    @Bean
    public AuthenticationEntryPoint restAuthenticationEntryPoint() {
        return new RestAuthenticationEntryPoint();
    }

    @Bean
    public AccessDeniedHandler restAccessDeniedEntryPoint() {
        return new RestAccessDeniedEntryPoint();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

}

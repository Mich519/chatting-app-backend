package com.example.demo.configuration;

import com.example.demo.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.session.SessionManagementFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserService userService;
    private final CustomAuthenticationProvider authenticationProvider;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public SecurityConfiguration(CustomAuthenticationProvider authenticationProvider,
                                 UserService userService,
                                 PasswordEncoder passwordEncoder) {
        this.authenticationProvider = authenticationProvider;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
        auth.authenticationProvider(authenticationProvider);
        auth.inMemoryAuthentication().getUserDetailsService();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().configurationSource(request -> {
                        var cors = new CorsConfiguration();
                        cors.setAllowedOrigins(List.of("http://localhost:3000"));
                        cors.setAllowedMethods(List.of("GET","POST", "PUT", "DELETE", "OPTIONS"));
                        cors.setAllowedHeaders(List.of("*"));
                        return cors;
                })
                .and().authorizeRequests().antMatchers("/**").permitAll()
                .and().csrf().disable()
                .formLogin().defaultSuccessUrl("/api/users")
                .and().logout();
    }
}


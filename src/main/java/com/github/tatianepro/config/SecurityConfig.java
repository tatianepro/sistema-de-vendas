package com.github.tatianepro.config;

import com.github.tatianepro.rest.service.impl.UsuarioServiceImpl;
import com.github.tatianepro.security.jwt.JwtAuthFilter;
import com.github.tatianepro.security.jwt.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UsuarioServiceImpl usuarioServiceImpl;

    @Autowired
    private JwtService jwtService;

    @Bean
    public OncePerRequestFilter jwtFilter() {
        return new JwtAuthFilter(jwtService, usuarioServiceImpl);
    }

    @Bean   // password encryptor and decryptor (can implement custom)
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override   // Authentication (it configures user, passw and profile)
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(usuarioServiceImpl)
                .passwordEncoder(passwordEncoder());
    }

    @Override   // Authorization
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                    .antMatchers("/h2-console/**")
                        .permitAll()
                    .antMatchers(HttpMethod.POST, "/api/usuarios/**")
//                        .hasAnyRole("USER", "ADMIN")
                        .permitAll()
                    .antMatchers("/api/clientes/**")
                        .hasAnyRole("USER", "ADMIN")
                    .antMatchers("/api/pedidos/**")
                        .hasAnyRole("USER", "ADMIN")
                    .antMatchers("/api/produtos/**")
                        .hasRole("ADMIN")
                    .anyRequest()
                        .authenticated()
                .and()
                    .headers().frameOptions().disable()
                .and()
                    .sessionManagement()
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // stateless doesn't keep sessions therefore TOKEN is required
                .and()
                    .addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class);  // the filter will validate the user and place him in the security context, so the user can have access to the API
    }
}

package com.rafael.apiagendamento.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{

        return httpSecurity
                        .csrf(AbstractHttpConfigurer::disable)
                        .authorizeHttpRequests(auth-> auth
                                .requestMatchers(HttpMethod.POST,"/api/client").permitAll()
                        .requestMatchers("/api/users/**").hasRole("ADMIN")
                        .requestMatchers("/api/users").hasRole("ADMIN")
                        .requestMatchers("/api/servicos/**").hasAnyRole("ADMIN","PROFISSIONAIS")
                        .requestMatchers("/api/servicos").hasAnyRole("ADMIN","PROFISSIONAIS")
                        .requestMatchers("/api/profissionais/**").hasAnyRole("ADMIN","PROFISSIONAIS")
                        .requestMatchers("/api/profissionais").hasAnyRole("ADMIN","PROFISSIONAIS")
                        .anyRequest()
                        .authenticated()
                ).httpBasic(Customizer.withDefaults()).build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}

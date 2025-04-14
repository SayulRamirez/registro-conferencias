package com.registroconferencias.config;

import com.registroconferencias.jwt.JwtAuthenticationFilter;
import com.registroconferencias.model.Rol;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request ->
                    request.requestMatchers(HttpMethod.POST, "/api/v1/auth/login").permitAll()
                    .requestMatchers(HttpMethod.POST, "/api/v1/auth").permitAll()

                    .requestMatchers(HttpMethod.POST, "/api/v1/auth/admin").hasAuthority(Rol.ADMIN.toString())

                    .requestMatchers(HttpMethod.POST, "/api/v1/participant").hasAuthority(Rol.PARTICIPANTE.toString())
                    .requestMatchers(HttpMethod.GET, "/api/v1/participant/{idSession}").hasAuthority(Rol.ADMIN.toString())
                    .requestMatchers(HttpMethod.PUT, "/api/v1/participant").hasAuthority(Rol.ADMIN.toString())

                    .requestMatchers(HttpMethod.POST, "/api/v1/room").hasAuthority(Rol.ADMIN.toString())
                    .requestMatchers(HttpMethod.DELETE, "/api/v1/room/{id}").hasAuthority(Rol.ADMIN.toString())
                    .requestMatchers(HttpMethod.GET, "/api/v1/room/all/{idUser}").authenticated()
                    .requestMatchers(HttpMethod.GET, "/api/v1/room/{idRoom}").authenticated()

                    .requestMatchers(HttpMethod.POST, "/api/v1/session").hasAuthority(Rol.ADMIN.toString())
                    .requestMatchers(HttpMethod.DELETE, "/api/v1/session/{idSession}").hasRole(Rol.ADMIN.toString())
                    .requestMatchers(HttpMethod.GET, "/api/v1/session/all/{idRoom}").authenticated()
                    .requestMatchers(HttpMethod.GET, "/api/v1/session/{idSession}").authenticated()
                )
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter,
                        UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}

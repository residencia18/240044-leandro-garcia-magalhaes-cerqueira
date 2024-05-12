package com.test.fakebook.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.resource.web.BearerTokenAuthenticationEntryPoint;
import org.springframework.security.oauth2.server.resource.web.access.BearerTokenAccessDeniedHandler;
import org.springframework.security.web.SecurityFilterChain;

import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;

import lombok.RequiredArgsConstructor;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfiguration {

    // Injecting JwtConfigProperties for JWT configuration
    private final JwtConfigProperties jwtConfigProperties;

    // Configuring security filter chain
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
        
                // Configuring Cross-Origin Resource Sharing (CORS)
                .cors(Customizer.withDefaults())
                
                // Disabling Cross-Site Request Forgery (CSRF) protection
                .csrf(csrf -> csrf.disable())
                
                // Configuring authorization rules
                .authorizeHttpRequests(authorize -> authorize
                
                        // Allowing access to Swagger UI endpoints and API documentation
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/swagger-ui.html").permitAll()
                        
                        // Allowing access to authentication API endpoints
                        .requestMatchers("/api/auth/**").permitAll()
                        
                        // Allowing access to dashboard for users with USER role
                        .requestMatchers(HttpMethod.GET, "/dashboard/**").hasRole("USER")
                        
                        // Requiring authentication for any other request
                        .anyRequest().authenticated())
                
                // Configuring OAuth2 Resource Server with JWT token
                .oauth2ResourceServer(oAuth2ResourceServerConfigurer
                        -> oAuth2ResourceServerConfigurer.jwt(Customizer.withDefaults()))
                
                // Configuring session management to be stateless
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                
                // Configuring exception handling for authentication and access denial
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint(new BearerTokenAuthenticationEntryPoint())
                        .accessDeniedHandler(new BearerTokenAccessDeniedHandler()))
                
                // Building the security filter chain
                .build();
    }
    
    // Bean for password encoder
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Bean for JWT decoder
    @Bean
    JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withPublicKey(jwtConfigProperties.getPublicKey()).build();
    }

    // Bean for JWT encoder
    @Bean
    JwtEncoder jwtEncoder() {
        // Creating JWK using RSA public and private keys
        JWK jwk = new RSAKey.Builder(jwtConfigProperties.getPublicKey())
                .privateKey(jwtConfigProperties.getPrivateKey())
                .build();
        JWKSource<SecurityContext> jwks = new ImmutableJWKSet<>(new JWKSet(jwk));
        return new NimbusJwtEncoder(jwks);
    }

    // Bean for authentication manager
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

}

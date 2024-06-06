package br.com.techie.shoppingstore.AP003.infra.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import br.com.techie.shoppingstore.AP003.infra.jwt.JwtAuthenticationEntryPoint;
import br.com.techie.shoppingstore.AP003.infra.jwt.JwtAuthorizationFilter;

@EnableMethodSecurity
@EnableWebMvc
@Configuration
public class SpringSecurityFilter {

        private static final String[] DOCUMENTATION_OPENAPI = {
                        "/docs/index.html",
                        "/docs-shoppingstore.html", "/docs-shoppingstore/**",
                        "/v3/api-docs/**",
                        "/swagger-ui-custom.html", "/swagger-ui.html", "/swagger-ui/**",
                        "/**.html", "/webjars/**", "/configuration/**", "/swagger-resources/**"
        };

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
                return http
                        .csrf(csrf -> csrf.disable())
                        .formLogin(form -> form.disable())
                        .httpBasic(basic -> basic.disable())
                        .authorizeHttpRequests((authorize) -> authorize
                                .requestMatchers(HttpMethod.POST, "/api/v1/users").permitAll()
                                .requestMatchers(HttpMethod.GET, "/api/v1/users/confirm/register").permitAll()
                                .requestMatchers(HttpMethod.POST, "/api/v1/auth").permitAll()
                                .requestMatchers(HttpMethod.POST, "/api/v1/password-recovery").permitAll()
                                .requestMatchers(HttpMethod.PATCH, "/api/v1/password-reset").permitAll()
                                .requestMatchers(DOCUMENTATION_OPENAPI).permitAll()
                                .anyRequest().authenticated())
                        .sessionManagement(
                                session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                        .addFilterBefore(
                                jwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
                        .exceptionHandling(ex -> ex
                                .authenticationEntryPoint(new JwtAuthenticationEntryPoint()))
                        .build();
        }

        @Bean
        public JwtAuthorizationFilter jwtAuthorizationFilter() {
                return new JwtAuthorizationFilter();
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }

        @Bean
        public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
                return authenticationConfiguration.getAuthenticationManager();
        }

}
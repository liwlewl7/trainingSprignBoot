package com.example.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    //    private final TokenService tokenService;
//    public SecurityConfig(TokenService tokenService) {
//        this.tokenService = tokenService;
//    }
    private final String[] PUBLIC = {
            "/actuator/**",
            "/user/register",
            "/user/login",
            "/user/activate",
            "/user/resend-activation-email",
            "/socket/**"
    };

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeRequests()
                        .requestMatchers(PUBLIC).anonymous()
                        .anyRequest().authenticated()
                ;
        return http.build();
    }
//    @Bean
//    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
//        return http.cors(config -> {
//                    CorsConfiguration cors = new CorsConfiguration();
//                    cors.setAllowCredentials(true);
//                    cors.setAllowedOriginPatterns(Collections.singletonList("http://*"));
//                    cors.addAllowedHeader("*");
//                    cors.addAllowedMethod("GET");
//                    cors.addAllowedMethod("POST");
//                    cors.addAllowedMethod("PUT");
//                    cors.addAllowedMethod("DELETE");
//                    cors.addAllowedMethod("OPTIONS");
//
//                    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//                    source.registerCorsConfiguration("/**", cors);
//
//                    config.configurationSource(source);
//        }).csrf().disable()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and().authorizeRequests().requestMatchers(PUBLIC).anonymous()
//                .anyRequest().authenticated()
//                .and().build();
//    }
}

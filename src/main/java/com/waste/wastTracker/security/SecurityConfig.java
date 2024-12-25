package com.waste.wastTracker.security;

import com.waste.wastTracker.repo.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration // to tell spring that this class is for configuration purposes
@EnableWebSecurity // activate spring security features
public class SecurityConfig {



    private final CustomUserDetailsService userDetailsService;
    private final UserRepository userRepository;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    public SecurityConfig(UserRepository userRepository, JwtAuthenticationFilter jwtAuthenticationFilter) {  // Modify constructor
        this.userRepository = userRepository;
        this.userDetailsService = new CustomUserDetailsService(userRepository);  // Create instance\
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;

    }

    @Bean
    public UserDetailsService userDetailsService() {  // Add this Bean method
        return userDetailsService;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12); // strength factor of 12 iterations to hash the password
    }

    @Bean
    public SecurityFilterChain fillerChain(HttpSecurity http) throws Exception {


        http
                .csrf(csrf -> csrf.disable()) // it is disabled because we are gonna use JWB instead for security
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class) // Add breakpoint here
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**", "/api/users/add").permitAll()
                        .requestMatchers("/api/bins/**").hasAnyRole("DRIVER", "OPERATOR", "ADMIN")
                        .requestMatchers("/api/routes/**").hasAnyRole("DRIVER", "OPERATOR", "ADMIN")
                        .requestMatchers("/api/analytics/**").hasAnyRole("OPERATOR", "ADMIN")
                        .requestMatchers("/api/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated())
                        .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}
